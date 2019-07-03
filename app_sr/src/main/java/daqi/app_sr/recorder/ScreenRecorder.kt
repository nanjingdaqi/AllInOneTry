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
import daqi.app_sr.recorder.ScreenRecorder.Companion.MSG_FINISH
import daqi.app_sr.recorder.ScreenRecorder.Companion.T
import java.nio.ByteBuffer
import java.util.concurrent.LinkedBlockingQueue

/**
 * 1. audio和video分为两个线程进行drain，减少影响
 *
 * 2. audio会从unity层不断接收pcm byte array，为了避免block，这里额外开辟了一块buffer queue
 */
class ScreenRecorder(val audioConfig: AudioConfig, val videoConfig: VideoConfig, val outMp4Path: String, val mp: MediaProjection) {

    companion object {
        const val T = "daqi-ScreenRecorder"

        const val MSG_DRAIN = 1
        const val MSG_FINISH = 2
        const val MSG_PREPARE = 2
        const val MSG_START = 3
        const val MSG_PAUSE = 4
        const val MSG_RESUME = 5
        const val MSG_STOP = 6

        const val DRAIN_TIMEOUT = 1000L

        const val AUDIO_QUEUE_CAPACITY = 3

        const val THREAD_AUDIO_NAME = "audio-encoder-drain"
        const val THREAD_VIDEO_NAME = "video-encoder-drain"

        const val ERROR_NO = 0
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
    private var audioQueue = LinkedBlockingQueue<ByteBuffer>(AUDIO_QUEUE_CAPACITY)

    private lateinit var audioH: AudioH
    private lateinit var videoH: VideoH

    init {
        Util.level = 1
    }

    /**
     * todo: 目前这个方法不够明确
     */
    public fun prepare(): Int {
//        audioConfig.run {
//            MediaFormat.createAudioFormat(mime, sampleRate, channelCount).run {
//                setString(MediaFormat.KEY_MIME, mime)
//                setInteger(MediaFormat.KEY_AAC_PROFILE, profileLevel)
//                setInteger(MediaFormat.KEY_BIT_RATE, bitrate)
//                setInteger(MediaFormat.KEY_MAX_INPUT_SIZE, inputSize)
//
//                audioCoder = CodecContext.createEncoder(mime, this)
//            }
//        }
        videoConfig.run {
            MediaFormat.createVideoFormat(mime, width, height).run {
                setInteger(MediaFormat.KEY_COLOR_FORMAT, MediaCodecInfo.CodecCapabilities.COLOR_FormatSurface)
                setInteger(MediaFormat.KEY_BIT_RATE, bitrate)
//                setInteger(MediaFormat.KEY_BITRATE_MODE, bitrateMode)
                setInteger(MediaFormat.KEY_FRAME_RATE, frameRate)
                setInteger(MediaFormat.KEY_I_FRAME_INTERVAL, iframeInterval)
                videoCoder = CodecContext.createEncoder(mime, this).apply {
                }
            }
        }
        muxer = MuxerContext.createMuxer(outMp4Path, 1)
//        HandlerThread(THREAD_AUDIO_NAME).run {
//            start()
//            audioH = AudioH(looper, this@ScreenRecorder)
//        }
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
//        audioH.sendEmptyMessage(MSG_DRAIN)
        videoH.sendEmptyMessage(MSG_DRAIN)
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
//        audioH.sendEmptyMessage(MSG_FINISH)
//        audioH.looper.quitSafely()
        videoH.sendEmptyMessage(MSG_FINISH)
    }

    public fun drainVideoCoderUntilStop() {
        while (state != State.STOPPED) {
            if (state == State.PAUSED) {
                synchronized(pauseLock) {
                    pauseLock.wait()
                }
            } else {
                drainVideoCoderOnce(false)
            }
        }
        Util.logk(T, "last drain")
        drainVideoCoderOnce(true)
        Util.logk(T, "last drain is over")
    }

    private fun drainVideoCoderOnce(eos: Boolean) {
        videoCoder.drainOutputBuffer(DRAIN_TIMEOUT, eos, object : CodecContext.DrainBufferListener {
            override fun availBuffer(buffer: ByteBuffer, bufferInfo: MediaCodec.BufferInfo) {
                Util.logd(T, "video avail buffer, sz: ${bufferInfo.size}")
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

class AudioH(looper: Looper, val recorder: ScreenRecorder) : Handler(looper) {
    override fun handleMessage(msg: Message?) {
        when (msg!!.what) {
            MSG_DRAIN -> {

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