package daqi.app_sr

import android.Manifest
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
import kotlinx.android.synthetic.main.content_main2.pause
import kotlinx.android.synthetic.main.content_main2.resume
import kotlinx.android.synthetic.main.content_main2.start
import kotlinx.android.synthetic.main.content_main2.stop
import java.nio.ByteBuffer

class MainActivity2 : AppCompatActivity() {

    private var audioPlayer: AudioPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main2)
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

    fun init() {
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
    }

    lateinit var recorder: ScreenRecorder
    lateinit var projectionManager: MediaProjectionManager


    fun startRecord(resultCode: Int, data: Intent?) {
        recorder = ScreenRecorder(AudioConfig(MediaFormat.MIMETYPE_AUDIO_AAC, 44100, 2,
                96000, MediaCodecInfo.CodecProfileLevel.AACObjectLC, 1024 * 1024),
                VideoConfig("video/avc", 1080, 2340, 30, 6000000,
                        MediaCodecInfo.EncoderCapabilities.BITRATE_MODE_VBR, 10,
                        MediaCodecInfo.CodecProfileLevel.AVCProfileMain),
                "/sdcard/test_${System.currentTimeMillis()}.mp4",
                projectionManager.getMediaProjection(resultCode, data!!)).apply {
            prepareVideo()
            prepareAudio(audioSource)
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
