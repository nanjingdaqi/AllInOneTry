package com.bytedance.ttgame.module.screenrecord

import android.hardware.display.DisplayManager
import android.hardware.display.VirtualDisplay
import android.media.MediaCodec
import android.media.MediaFormat
import android.media.projection.MediaProjection
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.os.Message
import android.os.SystemClock
import android.util.Log
import com.bytedance.ttgame.module.screenrecord.Listener.Companion.ERROR_AUDIO_ENCODE_FAIL
import com.bytedance.ttgame.module.screenrecord.Listener.Companion.ERROR_CREATE_AUDIO_ENCODER
import com.bytedance.ttgame.module.screenrecord.Listener.Companion.ERROR_CREATE_VIDEO_ENCODER
import com.bytedance.ttgame.module.screenrecord.Listener.Companion.ERROR_IN_USE
import com.bytedance.ttgame.module.screenrecord.Listener.Companion.ERROR_NO
import com.bytedance.ttgame.module.screenrecord.Listener.Companion.ERROR_VIDEO_ENCODE_FAIL
import com.bytedance.ttgame.module.screenrecord.ScreenRecorder.Companion.MSG_DRAIN
import com.bytedance.ttgame.module.screenrecord.ScreenRecorder.Companion.MSG_FEED
import com.bytedance.ttgame.module.screenrecord.ScreenRecorder.Companion.MSG_FINISH
import com.bytedance.ttgame.module.screenrecord.ScreenRecorder.Companion.T
import java.nio.ByteBuffer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.TimeUnit

/**
 * 1. audio有两个线程，分别feed input buffer和drain out buffer, video拉出一个线程进行drain，减少影响
 *
 * 2. audio会从unity层不断接收pcm byte array，为了避免block，这里额外开辟了一块buffer queue
 */
class ScreenRecorder(val outMp4Path: String) {

    companion object {
        const val T = "daqi-ScreenRecorder"
        const val DEBUG = true

        const val MSG_DRAIN = 1
        const val MSG_FINISH = 2
        const val MSG_FEED = 3

        const val DRAIN_TIMEOUT = 1000L

        const val AUDIO_QUEUE_CAPACITY = 3

        const val THREAD_AUDIO_FEED_NAME = "audio-encoder-feed"
        const val THREAD_AUDIO_DRAIN_NAME = "audio-encoder-drain"
        const val THREAD_VIDEO_NAME = "video-encoder-drain"
    }

    enum class State {
        UNSTARTED, RECORDING, PAUSED, STOPPED
    }

    private var state = State.UNSTARTED
    private var pauseLock = Object()

    private lateinit var audioCoder: CodecContext
    private lateinit var videoCoder: CodecContext

    private lateinit var muxer: MuxerContext
    private lateinit var virtualDisplay: VirtualDisplay
    private var audioQueue: LinkedBlockingQueue<ByteBuffer>? = null

    private lateinit var audioFeedH: AudioFeedH
    private lateinit var audioDrainH: AudioDrainH
    var audioFinishCountDownLatch = CountDownLatch(2)
    private lateinit var videoH: VideoH

    private var audioSource: AudioSource? = null
    private var audioObserver: AudioObserver? = null

    private lateinit var mp: MediaProjection
    // todo sync among threads
    private var firstTimeStampUs: Long = -1

    init {
        Util.logLevel = 1
    }

    /**
     * 完成初始化，如果有问题，返回错误码。
     */
    fun prepareVideo(codecName: String, videoFormat: MediaFormat, mp: MediaProjection): Int {
        if (state != State.UNSTARTED) {
            return ERROR_IN_USE
        }
        try {
            videoCoder = CodecContext.createEncoderByName(codecName, videoFormat)
        } catch (e: Exception) {
            if (DEBUG) throw RuntimeException(e)
            e.printStackTrace()
            return ERROR_CREATE_VIDEO_ENCODER
        }
        this.mp = mp
        HandlerThread(THREAD_VIDEO_NAME).run {
            configThread(this, true)
            start()
            videoH = VideoH(looper, this@ScreenRecorder)
        }
        return ERROR_NO
    }

    fun prepareAudio(audioSource: AudioSource, codecName: String, audioFormat: MediaFormat): Int {
        if (state != State.UNSTARTED) {
            return ERROR_IN_USE
        }
        this.audioSource = audioSource
        try {
            audioCoder = CodecContext.createEncoderByName(codecName, audioFormat)
        } catch (e: Exception) {
            if (DEBUG) throw RuntimeException(e)
            e.printStackTrace()
            return ERROR_CREATE_AUDIO_ENCODER
        }
        audioQueue = LinkedBlockingQueue(AUDIO_QUEUE_CAPACITY)
        audioObserver = object : AudioObserver {
            override fun onAudioAvail(audioData: ByteBuffer) {
                handleIncomingAudio(audioData)
            }
        }.apply {
            audioSource.subscribe(this)
        }
        HandlerThread(THREAD_AUDIO_FEED_NAME).run {
            configThread(this, false)
            start()
            audioFeedH = AudioFeedH(looper, this@ScreenRecorder)
        }
        HandlerThread(THREAD_AUDIO_DRAIN_NAME).run {
            configThread(this, false)
            start()
            audioDrainH = AudioDrainH(looper, this@ScreenRecorder)
        }
        return ERROR_NO
    }

    private fun configThread(td: Thread, videoT: Boolean) {
        td.setUncaughtExceptionHandler { t, e ->
            Util.logk(T, "Thread ${t.name} exception", e)
            VideoManager.listener?.onFail(if (videoT) ERROR_VIDEO_ENCODE_FAIL else ERROR_AUDIO_ENCODE_FAIL, e)
        }
    }

    fun start(displayWidth: Int, displayHeight: Int): Int {
        state = State.RECORDING
        virtualDisplay = mp.createVirtualDisplay("screen-recorder", displayWidth,
                displayHeight, 1,
                DisplayManager.VIRTUAL_DISPLAY_FLAG_PUBLIC,
                videoCoder.createInputSurface(),
                null, null)
        videoCoder.prepare()
        videoH.sendEmptyMessage(MSG_DRAIN)
        audioSource?.run {
            audioCoder.prepare()
            audioFeedH.sendEmptyMessage(MSG_FEED)
        }
        muxer = MuxerContext.createMuxer(outMp4Path, if (audioSource == null) 1 else 2)
        return ERROR_NO
    }

    fun pause() {
        state = State.PAUSED
    }

    fun resume() {
        state = State.RECORDING
        synchronized(pauseLock) {
            pauseLock.notifyAll()
        }
    }

    fun stop() {
        state = State.STOPPED
        synchronized(pauseLock) {
            pauseLock.notifyAll()
        }
        videoH.sendEmptyMessage(MSG_FINISH)
        audioSource?.run {
            audioFeedH.removeMessages(MSG_FEED)
            audioFeedH.sendEmptyMessage(MSG_FINISH)
            audioDrainH.removeMessages(MSG_DRAIN)
            audioDrainH.sendEmptyMessage(MSG_FINISH)
            audioFinishCountDownLatch.await()
            finishAudioCoder()
        }
    }

    fun handleIncomingAudio(audioData: ByteBuffer) {
        audioQueue!!.offer(audioData)
        if (state == State.RECORDING && !audioFeedH.hasMessages(MSG_FEED)) {
            audioFeedH.sendEmptyMessage(MSG_FEED)
        }
    }

    fun feedAudioCoderUntilStop() {
        while (state == State.RECORDING && audioQueue!!.isNotEmpty()) {
            val curData = audioQueue!!.poll(10, TimeUnit.MILLISECONDS)
            Util.logk(T, "audio poll one data: $curData")
            if (curData != null) {
                audioCoder.feedInputBuffer(DRAIN_TIMEOUT, object : CodecContext.FeedBufferListener {
                    override fun availBuffer(inputBuffer: ByteBuffer): CodecContext.FeedInfo {
                        inputBuffer.clear()
                        Log.w("daqi", "audio input buffer: $inputBuffer")
                        inputBuffer.put(curData)
                        inputBuffer.flip()
                        if (!audioDrainH.hasMessages(MSG_DRAIN)) {
                            audioDrainH.sendEmptyMessage(MSG_DRAIN)
                        }
                        // 这里的preTs置为零，关键是后面写入muxer的bufferInfo#presentationTimeUs
                        return CodecContext.FeedInfo(false, inputBuffer.limit() - inputBuffer.position(), 0)
                    }

                    override fun bufferTimeout() {
                        Util.logd(T, "audio feed buffer timeout")
                    }
                })
            }
        }
        if (state == State.STOPPED) {
            Util.logk(T, "audio feed eos")
            audioCoder.feedInputBuffer(DRAIN_TIMEOUT, object : CodecContext.FeedBufferListener {
                override fun availBuffer(buffer: ByteBuffer): CodecContext.FeedInfo {
                    Util.logk(T, "audio last feed avail")
                    return CodecContext.FeedInfo(true, 0, 0)
                }

                override fun bufferTimeout() {
                    Util.logk(T, "audio last feed buffer timeout")
                }

            })
        }
    }

    fun drainAudioCoderUntilStop() {
        while (state != State.STOPPED) {
            if (state == State.PAUSED) {
                synchronized(pauseLock) {
                    pauseLock.wait()
                }
            } else {
                drainAudioCoderOnce()
            }
        }
        Util.logk(T, "audio drain over ")
    }

    private fun drainAudioCoderOnce() {
        audioCoder.drainOutputBuffer(DRAIN_TIMEOUT, false, object : CodecContext.DrainBufferListener {
            override fun availBuffer(buffer: ByteBuffer, bufferInfo: MediaCodec.BufferInfo) {
                Util.logd(T, "audio avail buffer sz: ${bufferInfo.size}, pre tm: ${bufferInfo.presentationTimeUs}")
                bufferInfo.presentationTimeUs = computeTimeStampUs()
                muxer.writeSampleData(audioCoder, buffer, bufferInfo)
            }

            override fun bufferChanged() {
                Util.logd(T, "audio buffer changed")
            }

            override fun formatChanged(mediaFormat: MediaFormat) {
                Util.logk(T, "audio format changed")
                muxer.addTrack(mediaFormat, audioCoder)
                muxer.mayStart()
            }

            override fun bufferTimeOut() {
                Util.logd(T, "audio buffer timeout")
            }
        })
    }

    private fun computeTimeStampUs(): Long {
        if (firstTimeStampUs == -1L) {
            firstTimeStampUs = SystemClock.elapsedRealtime() * 1000
            return 0
        }
        val ts = SystemClock.elapsedRealtime() * 1000 - firstTimeStampUs
        return ts
    }

    fun drainVideoCoderUntilStop() {
        while (state != State.STOPPED) {
            if (state == State.PAUSED) {
                synchronized(pauseLock) {
                    pauseLock.wait()
                }
            } else {
                drainVideoCoderOnce(false)
            }
        }
        drainVideoCoderOnce(true)
        Util.logk(T, "video last drain is over")
    }

    private fun drainVideoCoderOnce(eos: Boolean) {
        videoCoder.drainOutputBuffer(DRAIN_TIMEOUT, eos, object : CodecContext.DrainBufferListener {
            override fun availBuffer(buffer: ByteBuffer, bufferInfo: MediaCodec.BufferInfo) {
                Util.logd(T, "video avail buffer, sz: ${bufferInfo.size}, pren time: ${bufferInfo.presentationTimeUs}")
                bufferInfo.presentationTimeUs = computeTimeStampUs()
                muxer.writeSampleData(videoCoder, buffer, bufferInfo)
            }

            override fun bufferChanged() {
                Util.logd(T, "video buffer changed")
            }

            override fun formatChanged(mediaFormat: MediaFormat) {
                Util.logk(T, "video format changed")
                muxer.run {
                    addTrack(mediaFormat, videoCoder)
                    mayStart()
                }
            }

            override fun bufferTimeOut() {
                Util.logd(T, "video buffer timeout")
            }
        })
    }

    fun finishAudioCoder() {
        Util.logk(T, "finish audio coding")
        audioSource!!.unsubscribe(audioObserver!!)
        audioCoder.finish()
        muxer.mayFinish()
    }

    fun finishVideoCoder() {
        Util.logk(T, "finish video coding")
        videoCoder.finish()
        muxer.mayFinish()
    }
}

class AudioFeedH(looper: Looper, val recorder: ScreenRecorder) : Handler(looper) {
    override fun handleMessage(msg: Message?) {
        when (msg!!.what) {
            MSG_FEED -> {
                recorder.feedAudioCoderUntilStop()
            }
            MSG_FINISH -> {
                recorder.feedAudioCoderUntilStop()
                recorder.audioFinishCountDownLatch.countDown()
                Looper.myLooper().quit()
            }
        }
    }
}

class AudioDrainH(looper: Looper, val recorder: ScreenRecorder) : Handler(looper) {
    override fun handleMessage(msg: Message?) {
        when (msg!!.what) {
            MSG_DRAIN -> {
                recorder.drainAudioCoderUntilStop()
            }
            MSG_FINISH -> {
                recorder.audioFinishCountDownLatch.countDown()
                Looper.myLooper().quit()
            }
        }
    }
}

class VideoH(looper: Looper, val recorder: ScreenRecorder) : Handler(looper) {
    override fun handleMessage(msg: Message?) {
        when (msg!!.what) {
            MSG_DRAIN -> {
                Util.logk(T, "Handling msg_drain")
                recorder.drainVideoCoderUntilStop()
            }
            MSG_FINISH -> {
                Util.logk(T, "Handling msg_finish")
                recorder.finishVideoCoder()
                Looper.myLooper().quit()
            }
        }
    }
}

interface AudioSource {
    fun subscribe(observer: AudioObserver)
    fun unsubscribe(observer: AudioObserver)
}

interface AudioObserver {
    fun onAudioAvail(audioData: ByteBuffer)
}