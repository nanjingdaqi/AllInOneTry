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
import com.bytedance.ttgame.module.screenrecord.Listener.Companion.ERROR_AUDIO_ENCODE_FAIL
import com.bytedance.ttgame.module.screenrecord.Listener.Companion.ERROR_CREATE_AUDIO_ENCODER
import com.bytedance.ttgame.module.screenrecord.Listener.Companion.ERROR_CREATE_VIDEO_ENCODER
import com.bytedance.ttgame.module.screenrecord.Listener.Companion.ERROR_FINISH_FAIL
import com.bytedance.ttgame.module.screenrecord.Listener.Companion.ERROR_IN_USE
import com.bytedance.ttgame.module.screenrecord.Listener.Companion.ERROR_NO
import com.bytedance.ttgame.module.screenrecord.Listener.Companion.ERROR_VIDEO_ENCODE_FAIL
import com.bytedance.ttgame.module.screenrecord.ScreenRecorder.Companion.MSG_START
import com.bytedance.ttgame.module.screenrecord.ScreenRecorder.Companion.MSG_STOP
import com.bytedance.ttgame.module.screenrecord.ScreenRecorder.Companion.T
import java.lang.IllegalStateException
import java.nio.ByteBuffer
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.TimeUnit

class ScreenRecorder(val outMp4Path: String, val videoManager: VideoManager) {

    companion object {
        const val T = "daqi-ScreenRecorder"
        const val DEBUG = true

        const val MSG_START = 1
        const val MSG_STOP = 2

        const val AUDIO_QUEUE_CAPACITY = 3

        const val THREAD_ENCODE_NAME = "screen-encoder"
        const val THREAD_AUDIO_FEED_NAME = "audio-feed"
    }

    enum class State {
        UNSTARTED, RECORDING, PAUSED, STOPPED, FAILED
    }

    private var state = State.UNSTARTED
    private var pauseLock = Object()

    var audioCoder: CodecContext? = null
    lateinit var videoCoder: CodecContext

    lateinit var muxer: MuxerContext
    private lateinit var virtualDisplay: VirtualDisplay
    private lateinit var recorderH: Handler
    private var audioFeedH: Handler? = null

    var audioSource: AudioSource? = null
    private var audioObserver: AudioObserver? = null
    private var audioQueue: LinkedBlockingQueue<ByteBuffer>? = null

    private lateinit var mp: MediaProjection
    var firstTimeStampUs: Long = -1

    private val videoCodecCallback = object : CodecContext.AbstraceCallback() {
        override fun onOutputBufferAvailable(codec: MediaCodec, index: Int, bufferInfo: MediaCodec.BufferInfo) {
            recorderH.post {
                if (state == State.RECORDING) {
                    Util.logd(T, "video avail buffer, index: $index, sz: ${bufferInfo.size}, pren time: ${bufferInfo.presentationTimeUs}")
                    codec.getOutputBuffer(index)?.run {
                        bufferInfo.presentationTimeUs = computeTimeStampUs()
                        muxer.writeSampleData(videoCoder, this, bufferInfo)
                        codec.releaseOutputBuffer(index, false)
                        if ((bufferInfo.flags.and(MediaCodec.BUFFER_FLAG_END_OF_STREAM)) != 0) {
                            Util.logk(T, "video encoder eos")
                        }
                    }
                }
            }
        }

        override fun onOutputFormatChanged(codec: MediaCodec, format: MediaFormat) {
            recorderH.post {
                if (state == State.RECORDING) {
                    Util.logk(T, "video format changed")
                    muxer.run {
                        addTrack(format, videoCoder)
                        mayStart()
                    }
                }
            }
        }

        override fun onError(codec: MediaCodec, e: MediaCodec.CodecException) {
            Util.logk(T, "video encoder on error", e)
        }
    }

    private val audioCodecCallback = object : CodecContext.AbstraceCallback() {
        override fun onInputBufferAvailable(codec: MediaCodec, index: Int) {
            audioFeedH?.post {
                while (state == State.RECORDING) {
                    audioQueue?.poll(1, TimeUnit.SECONDS)?.run {
                        val data = this
                        try {
                            codec.getInputBuffer(index)?.run {
                                Util.logd(T, "audio feed one buffer: $this")
                                put(data)
                                flip()
                                codec.queueInputBuffer(index, 0, limit(), 0, 0)
                            }
                        } catch (e: IllegalStateException) {
                            // 这里的线程没有与encode线程同步，会存在这种潜在风险
                            Util.logd(T, "audio feed fail because encoder has been closed.")
                            e.printStackTrace()
                        }
                        return@post
                    }
                }
            }
        }

        override fun onOutputBufferAvailable(codec: MediaCodec, index: Int, bufferInfo: MediaCodec.BufferInfo) {
            recorderH.post {
                if (state == State.RECORDING) {
                    Util.logd(T, "audio avail buffer, index: $index, sz: ${bufferInfo.size}, pren time: ${bufferInfo.presentationTimeUs}")
                    codec.getOutputBuffer(index)?.run {
                        bufferInfo.presentationTimeUs = computeTimeStampUs()
                        muxer.writeSampleData(audioCoder!!, this, bufferInfo)
                        codec.releaseOutputBuffer(index, false)
                        if ((bufferInfo.flags.and(MediaCodec.BUFFER_FLAG_END_OF_STREAM)) != 0) {
                            Util.logk(T, "audio encoder eos")
                        }
                    }
                }
            }
        }

        override fun onOutputFormatChanged(codec: MediaCodec, format: MediaFormat) {
            recorderH.post {
                if (state == State.RECORDING) {
                    Util.logk(T, "audio format changed")
                    muxer.run {
                        addTrack(format, audioCoder!!)
                        mayStart()
                    }
                }
            }
        }

        override fun onError(codec: MediaCodec, e: MediaCodec.CodecException) {
            Util.logk(T, "audio encoder on error", e)
        }
    }

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
            videoCoder = CodecContext.createEncoderByName(codecName, videoFormat, videoCodecCallback)
        } catch (e: Exception) {
            if (DEBUG) throw RuntimeException(e)
            e.printStackTrace()
            return ERROR_CREATE_VIDEO_ENCODER
        }
        this.mp = mp
        HandlerThread(THREAD_ENCODE_NAME).run {
            configThread(this, true)
            start()
            recorderH = VideoH(looper, this@ScreenRecorder)
        }
        return ERROR_NO
    }

    fun prepareAudio(audioSource: AudioSource, codecName: String, audioFormat: MediaFormat): Int {
        if (state != State.UNSTARTED) {
            return ERROR_IN_USE
        }
        this.audioSource = audioSource
        try {
            audioCoder = CodecContext.createEncoderByName(codecName, audioFormat, audioCodecCallback)
        } catch (e: Exception) {
            if (DEBUG) throw RuntimeException(e)
            e.printStackTrace()
            return ERROR_CREATE_AUDIO_ENCODER
        }
        audioQueue = LinkedBlockingQueue(AUDIO_QUEUE_CAPACITY)
        audioObserver = object : AudioObserver {
            override fun onAudioAvail(audioData: ByteBuffer) {
                audioQueue?.offer(audioData)
            }
        }.apply {
            audioSource.subscribe(this)
        }
        audioFeedH = HandlerThread(THREAD_AUDIO_FEED_NAME).run {
            start()
            configThread(this, false)
            Handler(looper)
        }

        return ERROR_NO
    }

    private fun configThread(td: Thread, videoT: Boolean) {
        td.setUncaughtExceptionHandler { t, e ->
            state = State.FAILED
            Util.logk(T, "Thread ${t.name} exception", e)
            videoManager.onFail(if (videoT) ERROR_VIDEO_ENCODE_FAIL else ERROR_AUDIO_ENCODE_FAIL, e)
            stop()
        }
    }

    fun start(displayWidth: Int, displayHeight: Int): Int {
        state = State.RECORDING
        virtualDisplay = mp.createVirtualDisplay("screen-recorder", displayWidth,
                displayHeight, 1,
                DisplayManager.VIRTUAL_DISPLAY_FLAG_PUBLIC,
                videoCoder.createInputSurface(),
                null, null)
        recorderH.sendEmptyMessage(MSG_START)
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
        if (state != State.STOPPED) {
            state = State.STOPPED
            synchronized(pauseLock) {
                pauseLock.notifyAll()
            }
            recorderH.sendEmptyMessage(MSG_STOP)
        }
    }

    private fun computeTimeStampUs(): Long {
        if (firstTimeStampUs == -1L) {
            // 这里的计算方式要与VideoManager#makeKeyMoment保持一致
            firstTimeStampUs = SystemClock.elapsedRealtime() * 1000
            return 0
        }
        return SystemClock.elapsedRealtime() * 1000 - firstTimeStampUs
    }

    fun writeEos(coder: CodecContext) {
        MediaCodec.BufferInfo().apply {
            set(0, 0, 0, MediaCodec.BUFFER_FLAG_END_OF_STREAM)
            muxer.writeSampleData(coder, ByteBuffer.allocate(0), this)
        }
    }

    fun finishAll() {
        try {
            Util.logk(T, "finish all resource")
            virtualDisplay.release()
            mp.stop()
            videoCoder.stop()
            muxer.mayFinish()
            audioCoder?.run {
                stop()
                muxer.mayFinish()
            }
        } catch (e: Exception) {
            Util.logk(T, "finish resource failed")
            videoManager.onFail(ERROR_FINISH_FAIL, e)
        }
    }
}

class VideoH(looper: Looper, val recorder: ScreenRecorder) : Handler(looper) {
    override fun handleMessage(msg: Message) {
        when (msg.what) {
            MSG_START -> {
                recorder.run {
                    videoCoder.start()
                    audioCoder?.start()
                    muxer = MuxerContext.createMuxer(outMp4Path, if (audioCoder != null) 2 else 1)
                }
            }
            MSG_STOP -> {
                Util.logk(T, "Handling msg_stop")
                recorder.run {
                    writeEos(videoCoder)
                    audioCoder?.run {
                        writeEos(this)
                    }
                    finishAll()
                    looper.quitSafely()
                }
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

