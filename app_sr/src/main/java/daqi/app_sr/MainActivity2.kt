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
import com.bytedance.ttgame.module.screenrecord.Listener
import com.bytedance.ttgame.module.screenrecord.Quality
import com.bytedance.ttgame.module.screenrecord.VideoEditor
import com.bytedance.ttgame.module.screenrecord.VideoManager
import com.bytedance.ttgame.module.screenrecord.VideoManager.Companion.INIT_RESULT_OK
import kotlinx.android.synthetic.main.content_main2.cut
import kotlinx.android.synthetic.main.content_main2.img
import kotlinx.android.synthetic.main.content_main2.pause
import kotlinx.android.synthetic.main.content_main2.prepare
import kotlinx.android.synthetic.main.content_main2.resume
import kotlinx.android.synthetic.main.content_main2.start
import kotlinx.android.synthetic.main.content_main2.stop
import java.io.File
import java.lang.RuntimeException
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
        playAudio()

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
        dir.mkdirs()
        VideoEditor.init(application, dir.absolutePath)
        prepare.setOnClickListener {
            prepareRecord()
        }
        start.setOnClickListener {
            videoManager.startScreenRecord(true, audioPlayer!!.sampleRate)
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
                val dir = File("/sdcard/a_g_game")
                init(application, dir.absolutePath)
                var index = 0
                mutableListOf<VideoEditor.CropInfo>().apply {
                    add(VideoEditor.CropInfo(0, 5000, File(dir, "crop_${System.currentTimeMillis()}_${index++}.mp4").absolutePath))
                    add(VideoEditor.CropInfo(5000, 10000, File(dir, "crop_${System.currentTimeMillis()}_${index++}.mp4").absolutePath))
                    add(VideoEditor.CropInfo(0, 20000, File(dir, "crop_${System.currentTimeMillis()}_${index++}.mp4").absolutePath))
                    add(VideoEditor.CropInfo(40000, 50000, File(dir, "crop_${System.currentTimeMillis()}_${index++}.mp4").absolutePath))
                    add(VideoEditor.CropInfo(70000, 80000, File(dir, "crop_${System.currentTimeMillis()}_${index++}.mp4").absolutePath))
                }.run {
                    var stMil = System.currentTimeMillis()
                    crop("/sdcard/test_1562833951373.mp4", this, object : VideoEditor.CropListener {
                        override fun onFinish() {
                            Log.w("daqi", "finish time consumption: ${System.currentTimeMillis() - stMil}")
                        }

                        override fun onError(error: Int) {
                            throw RuntimeException("fail $error")
                        }

                    })
                }
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
            prepareVideo(this@MainActivity2, 1)
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
