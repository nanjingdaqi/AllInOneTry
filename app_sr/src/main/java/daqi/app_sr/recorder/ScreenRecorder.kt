package daqi.app_sr.recorder

import android.hardware.display.DisplayManager
import android.hardware.display.VirtualDisplay
import android.media.MediaCodec
import android.media.MediaCodecInfo
import android.media.MediaFormat
import android.media.projection.MediaProjection
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.os.Message
import daqi.app_sr.recorder.ScreenRecorder.Companion.MSG_DRAIN
import daqi.app_sr.recorder.ScreenRecorder.Companion.MSG_FEED
import daqi.app_sr.recorder.ScreenRecorder.Companion.MSG_FINISH
import daqi.app_sr.recorder.ScreenRecorder.Companion.T
import java.nio.ByteBuffer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.TimeUnit

/**
 * 1. audio有两个线程，分别feed input buffer和drain out buffer, video拉出一个线程进行drain，减少影响
 *
 * 2. audio会从unity层不断接收pcm byte array，为了避免block，这里额外开辟了一块buffer queue
 */
class ScreenRecorder(val audioConfig: AudioConfig, val videoConfig: VideoConfig, val outMp4Path: String, val mp: MediaProjection) {

    companion object {
        const val T = "daqi-ScreenRecorder"

        const val MSG_DRAIN = 1
        const val MSG_FINISH = 2
        const val MSG_FEED = 3

        const val DRAIN_TIMEOUT = 1000L

        const val AUDIO_QUEUE_CAPACITY = 3

        const val THREAD_AUDIO_FEED_NAME = "audio-encoder-feed"
        const val THREAD_AUDIO_DRAIN_NAME = "audio-encoder-drain"
        const val THREAD_VIDEO_NAME = "video-encoder-drain"

        const val ERROR_NO = 0
        const val ERROR_USED = 1
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

    var firstAudioTimeStamp: Long = Long.MIN_VALUE

    private lateinit var audioFeedH: AudioFeedH
    private lateinit var audioDrainH: AudioDrainH
    var audioFinishCountDownLatch = CountDownLatch(2)
    private lateinit var videoH: VideoH

    private var audioSource: AudioSource? = null
    private var audioObserver: AudioObserver? = null

    init {
        Util.logLevel = 3
    }

    /**
     * 完成初始化，如果有问题，返回错误码。
     */
    public fun prepare(audioSource: AudioSource? = null): Int {
        if (state != State.UNSTARTED) {
            return ERROR_USED
        }
        this.audioSource = audioSource
        if (audioSource != null) {
            audioConfig.run {
                MediaFormat.createAudioFormat(mime, sampleRate, channelCount).run {
                    setString(MediaFormat.KEY_MIME, mime)
                    setInteger(MediaFormat.KEY_AAC_PROFILE, profileLevel)
                    setInteger(MediaFormat.KEY_BIT_RATE, bitrate)
                    setInteger(MediaFormat.KEY_MAX_INPUT_SIZE, inputSize)
                    audioCoder = CodecContext.createEncoder(mime, this)
                    audioQueue = LinkedBlockingQueue(3)
                    audioObserver = object : AudioObserver {
                        override fun onAudioAvail(audioData: ByteBuffer) {
                            handleIncomingAudio(audioData)
                        }
                    }.apply {
                        audioSource.subscribe(this)
                    }
                }
            }
        }
        videoConfig.run {
            MediaFormat.createVideoFormat(mime, width, height).run {
                setInteger(MediaFormat.KEY_COLOR_FORMAT, MediaCodecInfo.CodecCapabilities.COLOR_FormatSurface)
                setInteger(MediaFormat.KEY_BIT_RATE, bitrate)
                setInteger(MediaFormat.KEY_BITRATE_MODE, bitrateMode)
                setInteger(MediaFormat.KEY_FRAME_RATE, frameRate)
                setInteger(MediaFormat.KEY_I_FRAME_INTERVAL, iframeInterval)
                videoCoder = CodecContext.createEncoder(mime, this).apply {
                }
            }
        }
        HandlerThread(THREAD_AUDIO_FEED_NAME).run {
            start()
            audioFeedH = AudioFeedH(looper, this@ScreenRecorder)
        }
        HandlerThread(THREAD_AUDIO_DRAIN_NAME).run {
            start()
            audioDrainH = AudioDrainH(looper, this@ScreenRecorder)
        }
        HandlerThread(THREAD_VIDEO_NAME).run {
            start()
            videoH = VideoH(looper, this@ScreenRecorder)
        }
        return ERROR_NO
    }

    public fun start(): Int {
        state = State.RECORDING
        virtualDisplay = mp.createVirtualDisplay("screen-recorder", videoConfig.width,
                videoConfig.height, 1,
                DisplayManager.VIRTUAL_DISPLAY_FLAG_PUBLIC,
                videoCoder.createInputSurface(),
                null, null)
        videoCoder.prepare()
        videoH.sendEmptyMessage(MSG_DRAIN)
        if (audioSource != null) {
            audioCoder.prepare()
            audioFeedH.sendEmptyMessage(MSG_FEED)
        }
        muxer = MuxerContext.createMuxer(outMp4Path, if (audioSource == null) 1 else 2)
        return ERROR_NO
    }

    public fun pause() {
        state = State.PAUSED
    }

    public fun resume() {
        state = State.RECORDING
        synchronized(pauseLock) {
            pauseLock.notifyAll()
        }
    }

    public fun stop() {
        state = State.STOPPED
        synchronized(pauseLock) {
            pauseLock.notifyAll()
        }
        videoH.sendEmptyMessage(MSG_FINISH)
        if (audioSource != null) {
            audioFeedH.removeMessages(MSG_FEED)
            audioFeedH.sendEmptyMessage(MSG_FINISH)
            audioDrainH.removeMessages(MSG_DRAIN)
            audioDrainH.sendEmptyMessage(MSG_FINISH)
        }
        audioFinishCountDownLatch.await()
        finishAudioCoder()
    }

    fun handleIncomingAudio(audioData: ByteBuffer) {
        audioQueue!!.offer(audioData)
        if (state == State.RECORDING && !audioFeedH.hasMessages(MSG_FEED)) {
            audioFeedH.sendEmptyMessage(MSG_FEED)
        }
    }

    fun feedAudioCoderUntilStop() {
        while (state == State.RECORDING && audioQueue!!.isNotEmpty()) {
            if (firstAudioTimeStamp == Long.MIN_VALUE) {
                Util.logk(T, "firstAudioTimeStamp get assigned")
//                firstAudioTimeStamp = SystemClock.uptimeMillis()
                firstAudioTimeStamp = System.currentTimeMillis()
            }
            val curData = audioQueue!!.poll(10, TimeUnit.MILLISECONDS)
            Util.logk(T, "audio poll one data: $curData")
            if (curData != null) {
                audioCoder.feedInputBuffer(DRAIN_TIMEOUT, object : CodecContext.FeedBufferListener {
                    override fun availBuffer(inputBuffer: ByteBuffer): CodecContext.FeedInfo {
                        inputBuffer.clear()
                        inputBuffer.put(curData)
                        inputBuffer.flip()
//                        val tmOffset = SystemClock.uptimeMillis() - firstAudioTimeStamp
                        val tmOffset = System.currentTimeMillis() - firstAudioTimeStamp
                        Util.logd(T, "write tmOffset: $tmOffset")
                        audioDrainH.sendEmptyMessage(MSG_DRAIN)
                        // 用这个prenTm，能够得到与视频相同的时间
                        return CodecContext.FeedInfo(false, inputBuffer.limit() - inputBuffer.position(), tmOffset * 1000)
//                        return CodecContext.FeedInfo(false, inputBuffer.limit() - inputBuffer.position(), System.nanoTime())
//                        return CodecContext.FeedInfo(false, inputBuffer.limit() - inputBuffer.position(), 0)
                    }

                    override fun bufferTimeout() {
                        Util.logd(T, "audio feed buffer timeout")
                    }
                })
            }
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
                Util.logk(T, "audio avail buffer sz: ${bufferInfo.size}, pre tm: ${bufferInfo.presentationTimeUs}")
                bufferInfo.presentationTimeUs = System.currentTimeMillis() * 1000
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
                Util.logk(T, "video avail buffer, sz: ${bufferInfo.size}, pren time: ${bufferInfo.presentationTimeUs}")
                bufferInfo.presentationTimeUs = System.currentTimeMillis() * 1000
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

    public fun finishAudioCoder() {
        Util.logk(T, "finish audio coding")
        audioSource!!.unsubscribe(audioObserver!!)
        audioCoder.finish()
        muxer.mayFinish()
    }

    public fun finishVideoCoder() {
        Util.logk(T, "finish video coding")
        videoCoder.finish()
        muxer.mayFinish()
    }
}

data class AudioConfig(val mime: String, val sampleRate: Int, val channelCount: Int,
                       val bitrate: Int, val profileLevel: Int, val inputSize: Int)

data class VideoConfig(val mime: String, val width: Int, val height: Int,
                       val frameRate: Int, val bitrate: Int, val bitrateMode: Int,
                       val iframeInterval: Int, val profileLevel: Int)

class AudioFeedH(looper: Looper, val recorder: ScreenRecorder) : Handler(looper) {
    override fun handleMessage(msg: Message?) {
        when (msg!!.what) {
            MSG_FEED -> {
                recorder.feedAudioCoderUntilStop()
            }
            MSG_FINISH -> {
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