package com.bytedance.ttgame.module.screenrecord

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.media.MediaCodecInfo
import android.media.MediaFormat
import android.media.projection.MediaProjection
import android.media.projection.MediaProjectionManager
import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import java.io.File
import java.nio.ByteBuffer
import java.nio.ByteOrder
import kotlin.math.abs

object VideoManager {

    public const val DEBUG = true

    const val TAG = "VideoManager"

    const val DIR = "g_screen_records"
    const val ORG_MP4_PREFIX = "org_screen_record_"
    const val CROP_MP4_PREFIX = "cropped_scree_record_"

    const val INIT_RESULT_OK = 0
    const val INIT_RESULT_OS_UNSURPPORT = 1
    const val INIT_RESULT_NO_VIDEO_ENCODER = 2

    private var initResult = Int.MIN_VALUE

    lateinit var app: Application
    lateinit var projectionManager: MediaProjectionManager
    lateinit var recorder: ScreenRecorder
    lateinit var orgMp4Path: String
    lateinit var audioAdapter: AudioAdapter

    var recorderStarted = false
    var mp: MediaProjection? = null

    var selectedQuality: Quality? = null
        set(value) {
            checkState()
            field = value
        }
    var listener: Listener? = null
        set(value) {
            checkState()
            field = value
        }

    var dm: DisplayMetrics? = null

    fun init(app: Application): Int {
        if (initResult != Int.MIN_VALUE) {
            return initResult
        }
        if (android.os.Build.VERSION.SDK_INT < 21) {
            initResult = INIT_RESULT_OS_UNSURPPORT
            return initResult
        }
        Quality.apply {
            this.init(app)
            if (HIGH == null) {
                initResult = INIT_RESULT_NO_VIDEO_ENCODER
                return initResult
            }
        }
        this.app = app
        initResult = INIT_RESULT_OK
        selectedQuality = Quality.HIGH
        dm = DisplayMetrics().apply {
            (app.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getRealMetrics(this)
        }
        initOutFile()
        return initResult
    }

    private fun initOutFile() {
        File(if (!DEBUG) app.filesDir else File("/sdcard"), DIR).apply {
            Util.removeDir(absolutePath)
            mkdirs()
            orgMp4Path = File(this, "${ORG_MP4_PREFIX}_${System.currentTimeMillis()}.mp4").absolutePath
        }
    }

    private fun checkState() {
        if (initResult == Int.MIN_VALUE) {
            throw IllegalStateException("Please invoke init method at first.")
        }
    }

    fun prepareVideo(activity: Activity, requestCode: Int) {
        checkState()
        projectionManager = app.getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
        activity.startActivityForResult(projectionManager.createScreenCaptureIntent(), requestCode)
    }

    fun onActivityResult(resultCode: Int, data: Intent) {
        if (resultCode == Activity.RESULT_OK) {
            createMediaProjection(resultCode, data)
        } else {
            Log.v(TAG, "User did not choose OK for screen recording.")
        }
    }

    fun createMediaProjection(resultCode: Int, data: Intent) {
        Log.w("daqi", "start screen record")
        mp = projectionManager.getMediaProjection(resultCode, data)
    }

    // 面向Unity环境，使用这个接口
    fun onAudioBuffer(buffer: FloatArray, length: Int, sampleRate: Int) {
        if (mp == null) {
            return
        }
        if (!recorderStarted) {
            recorderStarted = true
            startRecorder(sampleRate)
        } else {
            for (observer in audioAdapter.observers) {
                try {
                    if (Quality.ONLY_PCM_16) {
                        // 转为pcm_16
                        val sa = ShortArray(buffer.size).apply {
                            for (i in 0 until buffer.size) {
                                if (abs(x = buffer[i]) > 1.0) {
                                    Log.e("daqi", "one float is too big: ${buffer[i]}")
                                }
                                set(i, (buffer[i] * 32767).toShort())
                            }
                        }
                        ByteBuffer.allocate(sa.size * 2).run {
                            order(ByteOrder.nativeOrder())
                            val sb = asShortBuffer().apply {
                                put(sa)
                                flip()
                            }
                            flip()
                            limit((sb.limit() - sb.position()) * 2)
                            observer.onAudioAvail(this)
                        }
                    } else {
                        ByteBuffer.allocate(length * 4).run {
                            order(ByteOrder.nativeOrder())
                            val fb = asFloatBuffer().apply {
                                put(buffer)
                                flip()
                            }
                            flip()
                            limit((fb.limit() - fb.position()) * 4)
                            observer.onAudioAvail(this)
                        }
                    }
                } catch (e: Exception) {
                    Log.w(TAG, "exception: ", e)
                }
            }
        }
    }

    fun startRecorder(audioSampleRate: Int) {
        checkState()
        if (DEBUG) {
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(app, "输出视频地址：$orgMp4Path", Toast.LENGTH_LONG).show()
            }
        }
        recorder = ScreenRecorder(orgMp4Path).apply {
            selectedQuality!!.videoFormat.run {
                setInteger(MediaFormat.KEY_COLOR_FORMAT, MediaCodecInfo.CodecCapabilities.COLOR_FormatSurface)
                prepareVideo(Quality.videoCodec!!, this, mp!!).apply {
                    if (this != Listener.ERROR_NO) {
                        listener?.onFail(this)
                    }
                }
            }
            // audio codec可能为空，此时降级为只录取视频
            selectedQuality!!.getAudioFormat(audioSampleRate)?.run {
                audioAdapter = AudioAdapter()
                prepareAudio(audioAdapter, Quality.audioCodec!!, this).apply {
                    if (this != Listener.ERROR_NO) {
                        listener?.onFail(this)
                    }
                }
            }
            start(dm!!.widthPixels, dm!!.heightPixels)
        }
    }

    fun stopScreenRecord() {
        Log.w(TAG, "stop screen record.")
        if (DEBUG) {
            Toast.makeText(app, "录屏结束, 文件地址：$orgMp4Path", Toast.LENGTH_LONG).show()
        }
        recorder.stop()
        recorderStarted = false
        mp = null
    }

    class AudioAdapter : AudioSource {
        var observers = mutableListOf<AudioObserver>()

        override fun subscribe(observer: AudioObserver) {
            observers.add(observer)
        }

        override fun unsubscribe(observer: AudioObserver) {
            observers.remove(observer)
        }
    }
}