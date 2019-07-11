package daqi.app_sr

import android.Manifest
import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaCodecInfo
import android.media.MediaFormat
import android.media.projection.MediaProjectionManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.bytedance.ttgame.module.screenrecord.AudioConfig
import com.bytedance.ttgame.module.screenrecord.AudioObserver
import com.bytedance.ttgame.module.screenrecord.AudioSource
import com.bytedance.ttgame.module.screenrecord.ScreenRecorder
import com.bytedance.ttgame.module.screenrecord.VideoConfig
import com.bytedance.ttgame.module.screenrecord.VideoManager
import com.ss.android.vesdk.VEListener
import kotlinx.android.synthetic.main.content_main2.cut
import kotlinx.android.synthetic.main.content_main2.img
import kotlinx.android.synthetic.main.content_main2.pause
import kotlinx.android.synthetic.main.content_main2.resume
import kotlinx.android.synthetic.main.content_main2.start
import kotlinx.android.synthetic.main.content_main2.stop
import java.io.File
import java.nio.ByteBuffer

class MainActivity2 : AppCompatActivity() {

    private var audioPlayer: AudioPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main2)
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
            startRecord(resultCode, data)
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
        VideoManager.init(application, dir.absolutePath)
        start.setOnClickListener {
            projectionManager = (getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager).apply {
                startActivityForResult(createScreenCaptureIntent(), 1)
            }
        }
        stop.setOnClickListener {
            recorder.stop()
        }
        pause.setOnClickListener {
            recorder.pause()
        }
        resume.setOnClickListener {
            recorder.resume()
        }
        cut.setOnClickListener {
            VideoManager.run {
                val dir = File("/sdcard/a_g_game")
                init(application, dir.absolutePath)
                var index = 0
                mutableListOf<VideoManager.CropInfo>().apply {
                    add(VideoManager.CropInfo(0, 5000, File(dir, "crop_${System.currentTimeMillis()}_${index++}.mp4").absolutePath))
                    add(VideoManager.CropInfo(5000, 10000, File(dir, "crop_${System.currentTimeMillis()}_${index++}.mp4").absolutePath))
                    add(VideoManager.CropInfo(0, 20000, File(dir, "crop_${System.currentTimeMillis()}_${index++}.mp4").absolutePath))
                    add(VideoManager.CropInfo(40000, 50000, File(dir, "crop_${System.currentTimeMillis()}_${index++}.mp4").absolutePath))
                    add(VideoManager.CropInfo(70000, 80000, File(dir, "crop_${System.currentTimeMillis()}_${index++}.mp4").absolutePath))
                }.run {
                    var stMil = System.currentTimeMillis()
                    crop("/sdcard/test_1562833951373.mp4", this, object : VideoManager.CropListener {
                        override fun onFinish() {
                            android.util.Log.w("daqi", "onFinish")
                        }

                        override fun onProgress(finishedCount: Int, totalCount: Int) {
                            android.util.Log.w("daqi", "progress index: $finishedCount of total: $totalCount, time: ${System.currentTimeMillis() - stMil}")
                            stMil = System.currentTimeMillis()
                        }

                        override fun onError(errorCode: Int) {
                            android.util.Log.w("daqi", "error: $errorCode")
                        }

                    })
                }
            }
        }
    }

    lateinit var recorder: ScreenRecorder
    lateinit var projectionManager: MediaProjectionManager


    fun startRecord(resultCode: Int, data: Intent?) {
        val ac = AudioConfig(MediaFormat.MIMETYPE_AUDIO_AAC, 44100, 2,
                MediaCodecInfo.CodecProfileLevel.AACObjectLC, 1024 * 1024)
        val vc = VideoConfig("video/avc", 1080, 2340, 30, 6000000,
                MediaCodecInfo.EncoderCapabilities.BITRATE_MODE_VBR, 10,
                MediaCodecInfo.CodecProfileLevel.AVCProfileMain)
        val mp4 = "/sdcard/test_${System.currentTimeMillis()}.mp4"
        val mp = projectionManager.getMediaProjection(resultCode, data!!)
        recorder = ScreenRecorder(mp4).apply {
            prepareVideo(vc, mp)
            prepareAudio(audioSource, ac)
            start()
            audioPlayer!!.addListener(object : AudioPlayer.Listener {
                override fun onNewBuffer(buffer: ByteBuffer, sampleTime: Long) {
                    audioSource.observers.forEach {
                        buffer.mark()
                        it.onAudioAvail(ByteBuffer.allocate(buffer.limit() - buffer.position()).apply {
                            put(buffer)
                            flip()
                        })
                        buffer.reset()
                    }
                }
            })
        }
    }

    val audioSource = AudioAdapter()

    private fun playAudio() {
        if (audioPlayer == null) {
            audioPlayer = AudioPlayer()
        }
        audioPlayer!!.run {
            play(resources.assets.openFd("test.mp3"))
        }
    }

    class AudioAdapter : AudioSource {
        val observers = mutableListOf<AudioObserver>()

        override fun subscribe(observer: AudioObserver) {
            observers.add(observer)
        }

        override fun unsubscribe(observer: AudioObserver) {
            observers.remove(observer)
        }
    }
}
