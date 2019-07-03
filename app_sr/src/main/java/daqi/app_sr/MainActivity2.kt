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
import daqi.app_sr.recorder.AudioConfig
import daqi.app_sr.recorder.ScreenRecorder
import daqi.app_sr.recorder.VideoConfig
import kotlinx.android.synthetic.main.content_main2.start
import kotlinx.android.synthetic.main.content_main2.stop

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main2)

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
            prepare()
            start()
        }
    }
}
