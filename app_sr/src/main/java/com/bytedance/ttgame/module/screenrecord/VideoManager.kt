package com.bytedance.ttgame.module.screenrecord

import android.annotation.TargetApi
import android.app.Activity
import android.app.Application
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.media.MediaCodecInfo
import android.media.MediaFormat
import android.media.projection.MediaProjection
import android.media.projection.MediaProjectionManager
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.util.Log
import android.widget.Toast
import com.bytedance.ttgame.module.screenrecord.api.AudioEngineType
import com.bytedance.ttgame.module.screenrecord.api.Config
import com.bytedance.ttgame.module.screenrecord.api.ConvertResult
import com.bytedance.ttgame.module.screenrecord.api.Error
import com.bytedance.ttgame.module.screenrecord.api.IScreenRecordService
import com.bytedance.ttgame.module.screenrecord.api.ScreenRecordCallback
import com.bytedance.ttgame.module.screenrecord.api.Result
import com.bytedance.ttgame.module.screenrecord.api.VideoType
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import com.ss.android.vesdk.VESDK
import io.reactivex.Observable
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.io.File
import java.io.FileReader
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.util.concurrent.Executors
import kotlin.math.abs

class VideoManager : IScreenRecordService {

    companion object {
        public const val DEBUG = true

        const val TAG = "VideoManager"

        const val DEBUG_CONFIG_FILE = "/sdcard/sr.config"

        const val ROOT_DIR = "g_screen_records"
        const val VESDK_DIR = "vesdk"
        const val CROP_DIR = "crop"
        const val DOWNLOAD_DIR = "download"
        const val ORG_MP4_PREFIX = "org_screen_record_"
        const val TRANS_AUDIO_PREFIX = "trans_audio_"
        const val MUXED_MP4_PREFIX = "muxed_screen_record_"

        const val PREPARE_RESULT_OK = 0
        const val PREPARE_RESULT_NO_VIDEO_ENCODER = 1

        enum class State {
            UNPREPARED, PREPARED, USER_CONFIRMED, RECORDING, PROCESSING, FINISHED, FAILED
        }

        val worker = Executors.newSingleThreadExecutor {
            Thread(null, it, "video-worker")
        }
        val downloadWorker = Executors.newSingleThreadExecutor {
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
            listOf(cropDir, downloadDir, vesdkDir).forEach { dir ->
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

    private var state = State.UNPREPARED
    private var prepareResult = Int.MIN_VALUE

    lateinit var mainActivityComponentName: ComponentName
    lateinit var app: Application
    lateinit var recorder: ScreenRecorder
    lateinit var orgMp4Path: String
    lateinit var transAudioPath: String
    lateinit var muxedMp4Path: String
    var audioSampleRate: Int = Int.MIN_VALUE
    lateinit var audioAdapter: AudioAdapter
    var keyMoments = mutableListOf<KeyMoment>() // 与ScreenRecorder的timeStampUs计算方式一致

    var mp: MediaProjection? = null
    lateinit var userConfig: Config
    public var debugConfig: DebugConfig? = null
    val videoNet: VideoNet = VideoNet(this)
    var selectedQuality: Quality? = null
    var userCallback: ScreenRecordCallback? = null

    var lifecycleCallback = object : EmptyActivityLifecycleCallbacks() {
        override fun onActivityPaused(activity: Activity?) {
            if (mainActivityComponentName == activity?.componentName) {
                Util.logk(TAG, "Check current activity paused")
                if (state == State.RECORDING) {
                    onFail(Error.EXIT_OR_OUT_OF_MEMORY)
                }
            }
        }
    }


    override fun isSupported(activity: Activity): Boolean {
        if (Build.VERSION.SDK_INT < 21) {
            return false
        }
        return true
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun prepare(activity: Activity, audioSampleRate: Int): Boolean {
        if (prepareResult != Int.MIN_VALUE) {
            return prepareResult == PREPARE_RESULT_OK
        }
        app = activity.application
        mainActivityComponentName = activity.componentName

        initOnce(app)
        // 每次开始录屏时，都清除之前的数据
        clearDir()

        val suffix = System.currentTimeMillis()
        orgMp4Path = File(rootDir, "${ORG_MP4_PREFIX}_$suffix.mp4").absolutePath
        transAudioPath = File(rootDir, "${TRANS_AUDIO_PREFIX}_$suffix.aac").absolutePath
        muxedMp4Path = File(rootDir, "${MUXED_MP4_PREFIX}_$suffix.mp4").absolutePath

        Quality.apply {
            this.init(app)
            if (HIGH == null) {
                prepareResult = PREPARE_RESULT_NO_VIDEO_ENCODER
                return false
            }
        }
        prepareResult = PREPARE_RESULT_OK
        this.audioSampleRate = audioSampleRate
        activity.startActivity(Intent(app, ScreenRecordHandlerActivity::class.java))
        EventBus.getDefault().register(this)
        state = State.PREPARED
        return prepareResult == PREPARE_RESULT_OK
    }

    @Subscribe
    fun onEvent(event: ScreenRecordHandlerActivity.ActivityResultEvent) {
        onActivityResult(event.resultCode, event.data)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    fun onActivityResult(resultCode: Int, data: Intent?) {
        EventBus.getDefault().unregister(this)
        if (!ensureState(State.PREPARED, action = "onActivityResult")) return
        if (resultCode == Activity.RESULT_OK) {
            Log.d(TAG, "User confirmed screen recording.")
            val pm = app.getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
            mp = pm.getMediaProjection(resultCode, data)
            state = State.USER_CONFIRMED
        } else {
            Log.w(TAG, "User did not choose OK for screen recording.")
        }
    }

    override fun setCallback(listener: ScreenRecordCallback) {
        userCallback = listener
    }

    private fun checkUserConfig(userConfig: Config): Boolean {
        userConfig.run {
            if (auth == null || auth!!.isEmpty()) {
                Log.e(TAG, "Auth is empty.")
                return false
            }
        }
        return true
    }

    override fun startScreenRecord(userConfig: Config): Boolean {
        if (!ensureState(State.USER_CONFIRMED, action = "startScreenRecord")) return false
        if (!checkUserConfig(userConfig)) return false
        this.userConfig = userConfig
        selectedQuality = when (userConfig.quality) {
            com.bytedance.ttgame.module.screenrecord.api.Quality.LOW -> Quality.LOW
            com.bytedance.ttgame.module.screenrecord.api.Quality.MEDIUM -> Quality.BASE
            com.bytedance.ttgame.module.screenrecord.api.Quality.HIGH -> Quality.HIGH
            else -> Quality.BASE
        }
        if (DEBUG) {
            File(DEBUG_CONFIG_FILE).run {
                if (!exists()) {
                    Toast.makeText(app, "没有发现配置文件 $DEBUG_CONFIG_FILE, 走默认配置", Toast.LENGTH_LONG).show()
                } else {
                    debugConfig = Gson().fromJson(JsonReader(FileReader(DEBUG_CONFIG_FILE)), DebugConfig::class.java)
                }
            }
            debugConfig?.run {
                selectedQuality = when (quality) {
                    1 -> Quality.HIGH
                    2 -> Quality.BASE
                    else -> Quality.LOW
                }
            }
        }
        Log.w(TAG, "Selected quality is: ${selectedQuality!!.name}")
        if (DEBUG) {
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(app, "输出视频地址：$orgMp4Path", Toast.LENGTH_LONG).show()
            }
        }
        recorder = ScreenRecorder(orgMp4Path, this).apply {
            selectedQuality!!.videoFormat.run {
                setInteger(MediaFormat.KEY_COLOR_FORMAT, MediaCodecInfo.CodecCapabilities.COLOR_FormatSurface)
                prepareVideo(Quality.videoCodec!!, this, mp!!).apply {
                    if (this != ScreenRecorder.ERROR_NO) {
                        return false
                    }
                }
            }
            if (userConfig.audioEngineType == AudioEngineType.UNITY) {
                if (audioSampleRate <= 0) {
                    Log.w(TAG, "Input audio sample rate is illegal: $audioSampleRate. Won't record audio.")
                } else {
                    // audio codec可能为空，此时降级为只录取视频
                    selectedQuality!!.getAudioFormat(audioSampleRate)?.run {
                        audioAdapter = AudioAdapter()
                        prepareAudio(audioAdapter, Quality.audioCodec!!, this).apply {
                            if (this != ScreenRecorder.ERROR_NO) {
                                return false
                            }
                        }
                    }
                }
            }
        }
        recorder.start(selectedQuality!!.width, selectedQuality!!.height)
        state = State.RECORDING
        app.registerActivityLifecycleCallbacks(lifecycleCallback)
        return true
    }

    // 面向Unity环境，使用这个接口
    override fun onAudioBuffer(buffer: FloatArray) {
        if (!ensureState(State.RECORDING, action = "onAudioBuffer", log = false) || audioSampleRate <= 0) {
            return
        }
        for (observer in audioAdapter.observers) {
            try {
                if (Quality.ONLY_PCM_16) {
                    // 转为pcm_16
                    val sa = ShortArray(buffer.size).apply {
                        for (i in 0 until buffer.size) {
                            if (abs(x = buffer[i]) > 1.0) {
                                Log.e(TAG, "one float is too big: ${buffer[i]}")
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

    override fun markKeyMoment(priority: Int) {
        if (!ensureState(State.RECORDING, action = "markKeyMoment")) return
        Log.w(TAG, "Mark key moment, at moment: ${(SystemClock.elapsedRealtime() * 1000 - recorder.firstTimeStampUs) / 1000 / 1000}s")
        keyMoments.add(KeyMoment(SystemClock.elapsedRealtime() * 1000, priority))
    }

    override fun stopScreenRecord(fragModifyCount: Int, albumFragCount: Int) {
        if (ensureState(State.RECORDING, action = "stopScreenRecord")) {
            Log.w(TAG, "stop screen record.")
            if (DEBUG) {
                Toast.makeText(app, "录屏结束, 文件地址：$orgMp4Path", Toast.LENGTH_LONG).show()
            }
            release()
            videoNet.fragModifyCount = fragModifyCount
            videoNet.albumFragCount = albumFragCount
            performProcessingJob()
        }
        app.unregisterActivityLifecycleCallbacks(lifecycleCallback)
    }

    private fun release() {
        if (mp != null) {
            recorder.stop()
            mp = null
        }
    }

    /**
     * 1. trans audio to aac
     * 2. mux
     * 3. build crop info
     * 4. crop
     * 5. upload to 视频云
     * 6. 上传信息到中台
     * 7. 下载中台处理后的视频
     * 8. 返回给CP
     */
    public fun performProcessingJob() {
        state = State.PROCESSING
        var injectedAudio = userConfig.audioPath
        Log.w(TAG, "User inject audio path: $injectedAudio")
        if (injectedAudio != null && !File(injectedAudio).exists()) {
            injectedAudio = null
            Log.w(TAG, "Audio file does not exist")
        }
        val muxMp4FileObservable = if (injectedAudio != null) {
            VideoEditor.transAudio(injectedAudio, transAudioPath).flatMap {
                VideoEditor.mux(orgMp4Path, it.absolutePath, muxedMp4Path)
            }
        } else {
            Observable.fromArray(File(orgMp4Path))
        }

        var cropInfos: List<CropInfo>? = null
        muxMp4FileObservable
                .subscribeOn(Schedulers.from(worker))
                .observeOn(Schedulers.from(worker))
                .map {
                    Pair(it, Util.resolveMuxedVideoInfo(recorder.firstTimeStampUs, it.absolutePath))
                }
                .map {
                    cropInfos = Util.buildCropInfos(recorder.firstTimeStampUs, userConfig.durationBefore, userConfig.durationAfter, cropDir!!, keyMoments, it.second)
//                    cropInfos = listOf(CropInfo(10, 30, File(rootDir, "crop1.mp4").absolutePath, 1))
                    Pair(it.first, cropInfos)
                }
                .flatMap {
                    VideoEditor.crop(it.first.absolutePath, it.second!!)
                }
                // uploadVideo要切到主线程执行
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap {
                    if (DEBUG) {
                        Log.w(TAG, "uploadVideos")
                    }
                    videoNet.uploadVideos(it.map { path -> File(path.outPath) })
                }
                .observeOn(Schedulers.from(worker))
                .flatMap {
                    if (DEBUG) {
                        Log.w(TAG, "uploadVids")
                    }
                    videoNet.uploadVids(it, cropInfos!!.map { info -> info.priority })
                }
                .flatMap {
                    if (DEBUG) {
                        Log.w(TAG, "queryProcessedVideos")
                    }
                    videoNet.queryProcessedVideos(it)
                }
                .flatMapIterable {
                    it.map {
                        val url = String(android.util.Base64.decode(it.url, android.util.Base64.DEFAULT))
                        if (DEBUG) {
                            Log.w(TAG, "download url: $url")
                        }
                        DownloadedVideo(it.vid, it.aid, url, it.type, File(downloadDir, "${it.vid}.mp4").absolutePath)
                    }
                }
                .observeOn(Schedulers.from(downloadWorker))
                .flatMap {
                    videoNet.download(it)
                }
                .toList()
                .observeOn(Schedulers.from(worker))
                .subscribe(object : SingleObserver<List<DownloadedVideo>> {
                    override fun onSubscribe(d: Disposable) {
                        if (DEBUG) {
                            Log.d(TAG, "ProcessingJob start")
                        }
                    }

                    override fun onSuccess(t: List<DownloadedVideo>) {
                        if (DEBUG) {
                            Log.w(TAG, "Downloaded videos: $t")
                        }
                        userCallback?.onSuccess(Result(0, Error.NO_ERROR, "", t.map {
                            ConvertResult(it.aid, it.vid, it.url, it.localPath, VideoType.values()[it.type])
                        }.toTypedArray()))
                    }

                    override fun onError(e: Throwable) {
                        Log.w(TAG, "视频后续处理失败", e)
                        if (DEBUG) {
                            Toast.makeText(app, "视屏后续处理失败，查看log.", Toast.LENGTH_LONG).show()
                        }
                    }
                })
    }

    override fun shareVideo(localPath: String, aid: String) {
        if (!ensureState(State.FINISHED, action = "shareVideo")) return

    }

    // todo: 改为只在stop调用后，才开始发出onFail的callback
    fun onFail(error: Error, exception: Throwable? = null) {
        if (state != State.FAILED) {
            Log.e(TAG, "Fail!!!", exception)
            // 保证只会向外通知一次
            state = State.FAILED
            release()
            userCallback?.onFail(Result(-1, error, "", null))
        }
    }

    private fun ensureState(vararg expected: State, action: String = "", log: Boolean = true): Boolean {
        expected.forEach {
            if (state == it) return true
        }
        val msg = "Action($action) failed!. Wrong current state $state"
        Log.w(TAG, msg)
        if (DEBUG) throw IllegalStateException(msg) else return false
    }
}