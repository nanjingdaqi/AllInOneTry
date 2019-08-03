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
import com.bytedance.ttgame.module.screenrecord.CropInfo
import com.bytedance.ttgame.module.screenrecord.Util
import com.bytedance.ttgame.module.screenrecord.VideoEditor
import com.bytedance.ttgame.module.screenrecord.VideoManager
import com.bytedance.ttgame.module.screenrecord.api.AudioEngineType
import com.bytedance.ttgame.module.screenrecord.api.Config
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.content_main2.cut
import kotlinx.android.synthetic.main.content_main2.img
import kotlinx.android.synthetic.main.content_main2.inject_key_moment
import kotlinx.android.synthetic.main.content_main2.mux
import kotlinx.android.synthetic.main.content_main2.pause
import kotlinx.android.synthetic.main.content_main2.prepare
import kotlinx.android.synthetic.main.content_main2.resume
import kotlinx.android.synthetic.main.content_main2.start
import kotlinx.android.synthetic.main.content_main2.stop
import java.io.File
import java.io.FileOutputStream

class MainActivity2 : AppCompatActivity() {

    private var audioPlayer: AudioPlayer? = null
    private lateinit var videoManager: VideoManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main2)
        videoManager = VideoManager()

        startAnim()

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
            videoManager.prepare(this, 0)
        }
        start.setOnClickListener {
            Config(com.bytedance.ttgame.module.screenrecord.api.Quality.HIGH, 5, 5, 0, "", AudioEngineType.DEFAULT, "", "").run {
                videoManager.startScreenRecord(this)
            }
        }
        stop.setOnClickListener {
            videoManager.stopScreenRecord(3, 3)
        }
        pause.setOnClickListener {
            //            recorder.pause()
        }
        resume.setOnClickListener {
            //            recorder.resume()
        }
        cut.setOnClickListener {
            VideoEditor.run {
                crop("/sdcard/mux-org.mp4", listOf(CropInfo(5, 20, "/sdcard/crop_mux1.mp4", 1)))
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
            videoManager.markKeyMoment(0)
        }

        mux.setOnClickListener {
            VideoEditor.mux("/sdcard/org.mp4", "/sdcard/outputVoice.wav", "/sdcard/mux2.mp4")
                    .subscribeOn(Schedulers.from(VideoManager.worker))
                    .subscribe(object : Observer<File> {
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
}
