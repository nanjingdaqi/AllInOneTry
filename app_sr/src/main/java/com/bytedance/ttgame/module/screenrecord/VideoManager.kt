package com.bytedance.ttgame.module.screenrecord

import android.app.Activity
import android.app.Application
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.media.MediaCodecInfo
import android.media.MediaFormat
import android.media.projection.MediaProjection
import android.media.projection.MediaProjectionManager
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.bytedance.ttgame.module.screenrecord.Listener.Companion.ERROR_ACTIVITY_PAUSED
import com.bytedance.ttgame.module.screenrecord.Listener.Companion.ERROR_NO_AUDIO_FILE
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import com.ss.android.vesdk.VESDK
import io.reactivex.Observable
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import java.io.File
import java.io.FileReader
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import kotlin.math.abs

class VideoManager {

    companion object {
        public const val DEBUG = true

        const val TAG = "VideoManager"

        const val ROOT_DIR = "g_screen_records"
        const val VESDK_DIR = "vesdk"
        const val CROP_DIR = "crop"
        const val DOWNLOAD_DIR = "download"
        const val ORG_MP4_PREFIX = "org_screen_record_"
        const val MUXED_MP4_PREFIX = "muxed_screen_record_"
        const val CROP_MP4_PREFIX = "cropped_screen_record_"
        const val DOWNLOADED_MP4_PREFIX = "downloaded_screen_record_"

        const val INIT_RESULT_OK = 0
        const val INIT_RESULT_OS_UNSUPPORT = 1
        const val INIT_RESULT_NO_VIDEO_ENCODER = 2

        const val START_RESULT_OK = 0
        const val START_RESULT_NO_MP = 1

        val worker = Executors.newSingleThreadExecutor {
            Thread(null, it, "video-worker")
        }
        val downloadWorker = Executors.newCachedThreadPool {
            Thread(null, it, "video-downloader")
        }

        var rootDir: File? = null
        var cropDir: File? = null
        var downloadDir: File? = null
        var vesdkDir: File? = null

        private fun initOnce(app: Application) {
            if (rootDir == null) {
                rootDir = File(if (!DEBUG) app.filesDir else File("/sdcard"), ROOT_DIR).apply {
                    Util.removeDir(absolutePath)
                    mkdirs()
                    cropDir = File(this, CROP_DIR).apply {
                        mkdirs()
                    }
                    downloadDir = File(this, DOWNLOAD_DIR).apply {
                        mkdirs()
                    }
                    vesdkDir = File(this, VESDK_DIR).apply {
                        mkdirs()
                        VESDK.init(app, absolutePath)
                    }
                }
            }
        }

        private fun clearDir() {
            listOf(cropDir, vesdkDir).forEach { dir ->
                dir?.listFiles()?.forEach {
                    if (it.isFile) {
                        it.delete()
                    } else {
                        Util.removeDir(it.absolutePath)
                    }
                }
            }
        }
    }

    private var initResult = Int.MIN_VALUE

    lateinit var mainActivityComponentName: ComponentName
    lateinit var app: Application
    lateinit var projectionManager: MediaProjectionManager
    lateinit var recorder: ScreenRecorder
    lateinit var orgMp4Path: String
    lateinit var muxedMp4Path: String
    lateinit var audioAdapter: AudioAdapter
    var keyMoments = mutableListOf<KeyMoment>() // 与ScreenRecorder的timeStampUs计算方式一致
    var audioFile: File? = null

    var mp: MediaProjection? = null
    var started: Boolean = false
    lateinit var userConfig: RecordUserConfig
    var failed: Boolean = false

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

    val DEBUG_CONFIG_FILE = "/sdcard/sr.config"
    public var config: Config? = null

    data class Config(val quality: Int, val duration: Int)

    fun init(activity: Activity): Int {
        if (initResult != Int.MIN_VALUE) {
            return initResult
        }
        if (android.os.Build.VERSION.SDK_INT < 21) {
            initResult = INIT_RESULT_OS_UNSUPPORT
            return initResult
        }
        app = activity.application
        mainActivityComponentName = activity.componentName

        initOnce(app)
        // 每次开始录屏时，都清除之前的数据
        clearDir()

        orgMp4Path = File(rootDir, "${ORG_MP4_PREFIX}_${System.currentTimeMillis()}.mp4").absolutePath
        muxedMp4Path = File(rootDir, "${MUXED_MP4_PREFIX}_${System.currentTimeMillis()}.mp4").absolutePath

        File(DEBUG_CONFIG_FILE).run {
            if (!exists()) {
                Toast.makeText(app, "没有发现配置文件 $DEBUG_CONFIG_FILE, 走默认配置", Toast.LENGTH_LONG).show()
            } else {
                config = Gson().fromJson(JsonReader(FileReader(DEBUG_CONFIG_FILE)), Config::class.java)
            }
        }
        Quality.apply {
            this.init(app)
            if (HIGH == null) {
                initResult = INIT_RESULT_NO_VIDEO_ENCODER
                return initResult
            }
        }
        initResult = INIT_RESULT_OK
        selectedQuality = Quality.HIGH
        config?.run {
            selectedQuality = when (quality) {
                1 -> Quality.HIGH
                2 -> Quality.BASE
                else -> Quality.LOW
            }
        }
        Log.w(TAG, "Selected quality is: ${selectedQuality!!.name}")
        dm = DisplayMetrics().apply {
            (app.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getRealMetrics(this)
        }
        observeActivityInvisible(app)
        return initResult
    }

    private fun observeActivityInvisible(app: Application) {
        app.registerActivityLifecycleCallbacks(object : EmptyActivityLifecycleCallbacks() {
            override fun onActivityPaused(activity: Activity?) {
                if (mainActivityComponentName == activity?.componentName) {
                    Util.logk(TAG, "Check current activity paused")
                    // 延迟处理，处理转屏情况
                    if (started) {
                        onFail(ERROR_ACTIVITY_PAUSED)
                    }
                }
            }
        })
    }

    private fun checkState() {
        if (initResult == Int.MIN_VALUE) {
            throw IllegalStateException("Please invoke init method at first.")
        }
        if (failed) {
            throw IllegalStateException("当前VideoManager对象已经录制失败，无法再次使用，请重新创建.")
        }
    }

    fun prepareVideo(activity: Activity, requestCode: Int) {
        checkState()
        projectionManager = app.getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
        activity.startActivityForResult(projectionManager.createScreenCaptureIntent(), requestCode)
    }

    fun onActivityResult(resultCode: Int, data: Intent) {
        if (resultCode == Activity.RESULT_OK) {
            Log.w(TAG, "User confirmed screen recording.")
            createMediaProjection(resultCode, data)
        } else {
            Log.v(TAG, "User did not choose OK for screen recording.")
        }
    }

    private fun createMediaProjection(resultCode: Int, data: Intent) {
        mp = projectionManager.getMediaProjection(resultCode, data)
    }

    fun startScreenRecord(userConfig: RecordUserConfig): Int {
        checkState()
        if (mp == null) {
            return START_RESULT_NO_MP
        }
        if (!started) {
            if (DEBUG) {
                Handler(Looper.getMainLooper()).post {
                    Toast.makeText(app, "输出视频地址：$orgMp4Path", Toast.LENGTH_LONG).show()
                }
            }
            this.userConfig = userConfig
            recorder = ScreenRecorder(orgMp4Path, this).apply {
                selectedQuality!!.videoFormat.run {
                    setInteger(MediaFormat.KEY_COLOR_FORMAT, MediaCodecInfo.CodecCapabilities.COLOR_FormatSurface)
                    prepareVideo(Quality.videoCodec!!, this, mp!!).apply {
                        if (this != Listener.ERROR_NO) {
                            onFail(this)
                        }
                    }
                }
                if (userConfig.withAudio) {
                    // audio codec可能为空，此时降级为只录取视频
                    selectedQuality!!.getAudioFormat(userConfig.audioSampleRate)?.run {
                        audioAdapter = AudioAdapter()
                        prepareAudio(audioAdapter, Quality.audioCodec!!, this).apply {
                            if (this != Listener.ERROR_NO) {
                                onFail(this)
                            }
                        }
                    }
                }
                start(dm!!.widthPixels, dm!!.heightPixels)
            }
            started = true
        }
        return START_RESULT_OK
    }

    // 面向Unity环境，使用这个接口
    fun onAudioBuffer(buffer: FloatArray) {
        if (!started || !userConfig.withAudio) {
            return
        }
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
                    ByteBuffer.allocate(buffer.size * 4).run {
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

    fun markKeyMoment(priority: Int, addToConcatenatedVideo: Boolean) {
        keyMoments.add(KeyMoment(SystemClock.elapsedRealtime() * 1000, priority, addToConcatenatedVideo))
    }

    fun stopScreenRecord() {
        if (started) {
            Log.w(TAG, "stop screen record.")
            if (DEBUG) {
                Toast.makeText(app, "录屏结束, 文件地址：$orgMp4Path", Toast.LENGTH_LONG).show()
            }
            recorder.stop()
            started = false
            mp = null
        }
    }

    /**
     * CP 注入音频文件，开始后续的处理
     */
    fun injectAudioFile(filePath: String) {
        audioFile = File(filePath)
        if (!audioFile!!.exists()) {
            Log.e(TAG, "Injected audio file not exist. $filePath")
            onFail(ERROR_NO_AUDIO_FILE)
        } else {
            performPostRecordingJob()
        }
    }

    /**
     * 1. mux
     * 2. build crop info
     * 3. crop
     * 4. upload to 视频云
     * 5. 上传信息到中台
     * 6. 下载中台处理后的视频
     * 7. 返回给CP
     */
    private fun performPostRecordingJob() {
        val muxedMp4FileObservable = audioFile?.run {
            VideoEditor.mux(orgMp4Path, audioFile!!.absolutePath, muxedMp4Path)
        } ?: Observable.fromArray(File(orgMp4Path))
        var cropInfos: List<CropInfo>? = null
        val cropInfoObservable = muxedMp4FileObservable
                .subscribeOn(Schedulers.from(worker))
                .observeOn(Schedulers.from(worker))
                .map {
                    Pair(it, Util.resolveMuxedVideoInfo(recorder.firstTimeStampUs, it.absolutePath))
                }
                .map {
                    cropInfos = Util.buildCropInfos(recorder.firstTimeStampUs, userConfig.keyMomentDurationMill, cropDir!!, keyMoments, it.second)
                    Pair(it.first, cropInfos)
                }
                .flatMap {
                    VideoEditor.crop(it.first.absolutePath, it.second!!)
                }
        VideoNet.fetchAuth()
                .subscribeOn(Schedulers.from(worker))
                .observeOn(Schedulers.from(worker))
                .zipWith(cropInfoObservable, BiFunction<String, List<CropInfo>, Pair<String, List<CropInfo>>> { t, u -> Pair(t, u) })
                .flatMap {
                    VideoNet.uploadVideos(it.first, it.second.map { path -> File(path.outPath) })
                }
                .flatMap {
                    VideoNet.uploadVids(it, cropInfos!!.map { info -> info.priority })
                }
                .flatMap {
                    VideoNet.queryProcessedVideos(it)
                }
                .flatMapIterable {
                    mutableListOf<DownloadedVideo>().apply {
                        val sz = it.vids.size
                        for (i in 0 until sz) {
                            add(DownloadedVideo(it.vids[i], it.urls[i], File(downloadDir, "${it.vids[i]}.mp4").absolutePath))
                        }
                        add(DownloadedVideo(it.finalVid, it.finalUrl, File(downloadDir, "final_${it.finalVid}.mp4").absolutePath))
                    }
                }
                .observeOn(Schedulers.from(downloadWorker))
                .flatMap {
                    VideoNet.download(it)
                }
                .toList()
                .observeOn(Schedulers.from(worker))
                .subscribe(object : SingleObserver<List<DownloadedVideo>> {
                    override fun onSubscribe(d: Disposable) {}

                    override fun onSuccess(t: List<DownloadedVideo>) {

                    }

                    override fun onError(e: Throwable) {

                    }
                })
    }

    public fun shareVideo(localPath: String) {

    }

    fun onFail(error: Int, exception: Throwable? = null) {
        if (!failed) {
            // 保证只会向外通知一次
            failed = true
            stopScreenRecord()
            listener?.onFail(error, exception)
        }
    }
}