package daqi.app_sr

import android.Manifest
import android.animation.ValueAnimator
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.bytedance.ttgame.module.screenrecord.CropInfo
import com.bytedance.ttgame.module.screenrecord.Quality
import com.bytedance.ttgame.module.screenrecord.Util
import com.bytedance.ttgame.module.screenrecord.VideoEditor
import com.bytedance.ttgame.module.screenrecord.VideoManager
import com.bytedance.ttgame.module.screenrecord.VideoManager.Companion.INIT_RESULT_OK
import com.bytedance.ttgame.module.screenrecord.api.Listener
import com.bytedance.ttgame.module.screenrecord.api.UserConfig
import com.ss.android.vesdk.VEUtils
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.content_main2.cut
import kotlinx.android.synthetic.main.content_main2.img
import kotlinx.android.synthetic.main.content_main2.inject_audio
import kotlinx.android.synthetic.main.content_main2.inject_key_moment
import kotlinx.android.synthetic.main.content_main2.mux
import kotlinx.android.synthetic.main.content_main2.pause
import kotlinx.android.synthetic.main.content_main2.prepare
import kotlinx.android.synthetic.main.content_main2.resume
import kotlinx.android.synthetic.main.content_main2.start
import kotlinx.android.synthetic.main.content_main2.stop
import java.io.File
import java.io.FileOutputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder

class MainActivity2 : AppCompatActivity() {

    private var audioPlayer: AudioPlayer? = null
    private lateinit var videoManager: VideoManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main2)
        videoManager = VideoManager()

        startAnim()
//        playAudio()

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)
            }
        } else {
            init()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0) {
            init()
        } else if (requestCode == 1) {
            videoManager.onActivityResult(resultCode, data!!)
        }
    }

    fun startAnim() {
        ValueAnimator.ofFloat(.0f, 360f).apply {
            repeatMode = ValueAnimator.RESTART
            repeatCount = ValueAnimator.INFINITE
            interpolator = null
            duration = 3 * 1000
            addUpdateListener {
                img.rotation = it.animatedValue as Float
            }
        }.start()
    }

    fun init() {
        val dir = File("/sdcard/a_g_game")
        Util.removeDir(dir.absolutePath)
        dir.mkdirs()
        prepare.setOnClickListener {
            prepareRecord()
        }
        start.setOnClickListener {
            UserConfig(5 * 1000).run {
                videoManager.startScreenRecord(this)
            }
        }
        stop.setOnClickListener {
            videoManager.stopScreenRecord()
        }
        pause.setOnClickListener {
            //            recorder.pause()
        }
        resume.setOnClickListener {
            //            recorder.resume()
        }
        cut.setOnClickListener {
            VideoEditor.run {
                crop("/sdcard/mux-org.mp4", listOf(CropInfo(5, 20, "/sdcard/crop_mux1.mp4", 1, false)))
                        .subscribe(object : Observer<List<CropInfo>> {
                            override fun onComplete() {
                                Log.w("daqi", "onComplete")
                            }

                            override fun onNext(t: List<CropInfo>) {
                                Log.w("daqi", "onNext")
                            }

                            override fun onSubscribe(d: Disposable) {

                            }

                            override fun onError(e: Throwable) {
                                Log.w("daqi", "onError")
                            }
                        })
            }
        }
        inject_key_moment.setOnClickListener {
            videoManager.markKeyMoment(0, true)
        }
        inject_audio.setOnClickListener {
            videoManager.injectAudio("/sdcard/test.mp3")
        }

        mux.setOnClickListener {
//            VEUtils.isCanImport("/sdcard/outputVoice.wav").run {
//                Log.e("daqi", "isCanImport: $this")
//            }
            VideoEditor.mux("/sdcard/org.mp4", "/sdcard/outputVoice.wav", "/sdcard/mux2.mp4")
                    .subscribeOn(Schedulers.from(VideoManager.worker))
                    .subscribe(object: Observer<File> {
                        override fun onComplete() {
                            Log.w("daqi", "onComplete")
                        }

                        override fun onSubscribe(d: Disposable) {
                        }

                        override fun onNext(t: File) {
                            Log.w("daqi", "onNext")
                        }

                        override fun onError(e: Throwable) {
                            Log.w("daqi", "onError")
                        }

                    })
        }
    }

    fun copy(asset: String, dst: File) {
        dst.run {
            val os = FileOutputStream(this)
            resources.assets.open(asset).run {
                val buffer = ByteArray(4096)
                while (true) {
                    val sz = read(buffer)
                    if (sz <= 0)
                        break
                    os.write(buffer, 0, sz)
                }
                os.flush()
                os.close()
            }
        }
    }

    fun prepareRecord() {
        videoManager.run {
            if (init(this@MainActivity2) != INIT_RESULT_OK) {
                Toast.makeText(applicationContext, "该设备不支持录屏，请检查log.", Toast.LENGTH_LONG).show()
                return
            }
            selectedQuality = Quality.HIGH!!
            listener = object : Listener {
                override fun onSucc(videoFiles: List<File>) {
                    Log.w("daqi", "recording onSucc: $videoFiles")
                }

                override fun onFail(error: Int, exception: Throwable?) {
                    Log.w("daqi", "recording onFail, error: $error", exception)
                    throw RuntimeException(exception)
                }
            }
            prepare(this@MainActivity2, 1, 0)
        }
    }

    private fun playAudio() {
        if (audioPlayer == null) {
            audioPlayer = AudioPlayer()
        }
        audioPlayer!!.run {
            play(resources.assets.openFd("test.mp3"))
            addListener(object : AudioPlayer.Listener {
                override fun onNewBuffer(buffer: ByteBuffer, sampleTime: Long) {
                    buffer.run {
                        mark()
                        order(ByteOrder.nativeOrder())
                        if (Quality.ONLY_PCM_16) {
                            asShortBuffer().run {
                                ShortArray(limit() - position()).apply {
                                    get(this)
                                    val fa = FloatArray(size)
                                    for (i in 0 until size) {
                                        fa[i] = this[i] * 1.0f / 32767
                                    }
                                    videoManager.onAudioBuffer(fa)
                                }
                            }
                        } else {
                            asFloatBuffer().run {
                                FloatArray(limit() - position()).run {
                                    get(this)
                                    videoManager.onAudioBuffer(this)
                                }
                            }
                        }
                        reset()
                    }
                }
            })
        }
    }
}
