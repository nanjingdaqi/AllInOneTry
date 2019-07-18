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
import com.bytedance.ttgame.module.screenrecord.Listener.Companion.ERROR_CREATE_VIDEO_ENCODER
import com.bytedance.ttgame.module.screenrecord.Listener.Companion.ERROR_IN_USE
import com.bytedance.ttgame.module.screenrecord.Listener.Companion.ERROR_NO
import com.bytedance.ttgame.module.screenrecord.Listener.Companion.ERROR_VIDEO_ENCODE_FAIL
import com.bytedance.ttgame.module.screenrecord.ScreenRecorder.Companion.MSG_START
import com.bytedance.ttgame.module.screenrecord.ScreenRecorder.Companion.MSG_STOP
import com.bytedance.ttgame.module.screenrecord.ScreenRecorder.Companion.T
import java.nio.ByteBuffer

// 暂时先不支持audio
class ScreenRecorder(val outMp4Path: String) {

    companion object {
        const val T = "daqi-ScreenRecorder"
        const val DEBUG = true

        const val MSG_START = 1
        const val MSG_STOP = 2

        const val THREAD_VIDEO_NAME = "video-encoder"
    }

    enum class State {
        UNSTARTED, RECORDING, PAUSED, STOPPED
    }

    private var state = State.UNSTARTED
    private var pauseLock = Object()

    lateinit var videoCoder: CodecContext

    lateinit var muxer: MuxerContext
    private lateinit var virtualDisplay: VirtualDisplay
    private lateinit var videoH: Handler

    private lateinit var mp: MediaProjection
    private var firstTimeStampUs: Long = -1

    private val videoCodecCallback = object : CodecContext.AbstraceCallback() {
        override fun onOutputBufferAvailable(codec: MediaCodec, index: Int, bufferInfo: MediaCodec.BufferInfo) {
            if (state == State.RECORDING) {
                Util.logd(T, "video avail buffer, index: $index, sz: ${bufferInfo.size}, pren time: ${bufferInfo.presentationTimeUs}")
                val buffer = codec.getOutputBuffer(index)
                bufferInfo.presentationTimeUs = computeTimeStampUs()
                muxer.writeSampleData(videoCoder, buffer, bufferInfo)
                codec.releaseOutputBuffer(index, false)
                if ((bufferInfo.flags.and(MediaCodec.BUFFER_FLAG_END_OF_STREAM)) != 0) {
                    Util.logk(T, "video encoder eos")
                    finishVideoCoder(false)
                }
            }
        }

        override fun onOutputFormatChanged(codec: MediaCodec, format: MediaFormat) {
            Util.logk(T, "video format changed")
            muxer.run {
                addTrack(format, videoCoder)
                mayStart()
            }
        }

        override fun onError(codec: MediaCodec, e: MediaCodec.CodecException) {
            Util.logk(T, "video encoder on error", e)
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
        HandlerThread(THREAD_VIDEO_NAME).run {
            configThread(this, true)
            start()
            videoH = VideoH(looper, this@ScreenRecorder)
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
        videoH.sendEmptyMessage(MSG_START)
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
        videoH.sendEmptyMessage(MSG_STOP)
    }

    private fun computeTimeStampUs(): Long {
        if (firstTimeStampUs == -1L) {
            firstTimeStampUs = SystemClock.elapsedRealtime() * 1000
            return 0
        }
        val ts = SystemClock.elapsedRealtime() * 1000 - firstTimeStampUs
        return ts
    }

    fun finishVideoCoder(eos: Boolean) {
        Util.logk(T, "finish video coding")
        virtualDisplay.release()
        mp.stop()
        videoCoder.stop()
        if (eos) {
            MediaCodec.BufferInfo().apply {
                set(0, 0, 0, MediaCodec.BUFFER_FLAG_END_OF_STREAM)
                muxer.writeSampleData(videoCoder, ByteBuffer.allocate(0), this)
            }
        }
        muxer.mayFinish()
    }
}

class VideoH(looper: Looper, val recorder: ScreenRecorder) : Handler(looper) {
    override fun handleMessage(msg: Message) {
        when (msg.what) {
            MSG_START -> {
                recorder.run {
                    videoCoder.start()
                    muxer = MuxerContext.createMuxer(outMp4Path, 1)
                }
            }
            MSG_STOP -> {
                Util.logk(T, "Handling video msg_stop")
                recorder.finishVideoCoder(true)
                looper.quitSafely()
            }
        }
    }
}
