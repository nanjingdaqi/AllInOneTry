package daqi.app_sr

import android.Manifest
import android.animation.ValueAnimator
import android.content.pm.PackageManager
import android.media.MediaCodec
import android.media.MediaCodecInfo
import android.media.MediaFormat
import android.opengl.GLES30
import android.opengl.GLSurfaceView
import android.opengl.Matrix
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import daqi.app_sr.recorder.CodecContext
import daqi.app_sr.recorder.MuxerContext
import kotlinx.android.synthetic.main.activity_main.btn
import kotlinx.android.synthetic.main.activity_main.gl
import java.nio.ByteBuffer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class MainActivity : AppCompatActivity() {

    companion object {
        const val T = "daqi"

        const val MSG_INVOKE_DRAIN = 1
        const val MSG_SIGNAL_EOS = 2
    }

    private var recording = false
    private var audioRecorder: CodecContext? = null
    private var muxer: MuxerContext? = null
    private var recorderFeedWorker = Executors.newSingleThreadExecutor()
    private var recorderDrainWorker = Executors.newSingleThreadExecutor()
    private var audioPlayer: AudioPlayer? = null
    private val mH: MainH = MainH(this)
    private val stopWaiter = CountDownLatch(2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
        playAudio()

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)
            }
        }
    }


    private fun initUI() {
        btn.run {
            setOnClickListener {
                if (!recording) {
                    startRecord()
                    text = "正在录制"
                    setBackgroundColor(resources.getColor(android.R.color.holo_red_light))
                } else {
                    stopRecord()
                    text = "开始录制"
                    setBackgroundColor(resources.getColor(android.R.color.darker_gray))
                }
            }
        }

        gl.run {
            setEGLContextClientVersion(3)
            val renderer = Renderer()
            setRenderer(renderer)
            renderMode = GLSurfaceView.RENDERMODE_WHEN_DIRTY
            requestRender()
            ValueAnimator.ofFloat(0f, 3.14f).apply {
                duration = 10 * 1000
                repeatMode = ValueAnimator.RESTART
                repeatCount = ValueAnimator.INFINITE
                addUpdateListener {
                    renderer.angle = renderer.angle + animatedValue as Float
                    requestRender()
                }
            }.start()
        }
    }

    private fun playAudio() {
        if (audioPlayer == null) {
            audioPlayer = AudioPlayer()
        }
        audioPlayer!!.play(resources.assets.openFd("test.mp3"))
    }

    private fun startRecord() {
        recording = true
        recorderFeedWorker.execute {
            if (audioRecorder == null) {
                val mediaFormat = MediaFormat.createAudioFormat(MediaFormat.MIMETYPE_AUDIO_AAC, 44100, 2)
                mediaFormat.setString(MediaFormat.KEY_MIME, MediaFormat.MIMETYPE_AUDIO_AAC)
                mediaFormat.setInteger(MediaFormat.KEY_AAC_PROFILE, MediaCodecInfo.CodecProfileLevel.AACObjectLC)
                mediaFormat.setInteger(MediaFormat.KEY_BIT_RATE, 96000)
                mediaFormat.setInteger(MediaFormat.KEY_MAX_INPUT_SIZE, 1024 * 1024)
                audioRecorder = CodecContext.createEncoder(MediaFormat.MIMETYPE_AUDIO_AAC, mediaFormat)
            }
            if (muxer == null) {
                muxer = MuxerContext.createMuxer("/sdcard/out2.mp4", 1)
            }
            audioRecorder!!.prepare()
            audioPlayer!!.addListener(audioListener)
        }
    }

    var firstTimeStamp: Long = -1
    val audioListener = object : AudioPlayer.Listener {
        override fun onNewBuffer(buffer: ByteBuffer, inputSampleTime: Long) {
//            recorderFeedWorker.execute {
                if (firstTimeStamp == -1L) {
                    firstTimeStamp = System.currentTimeMillis()
                }
                audioRecorder!!.feedInputBuffer(10000, object : CodecContext.FeedBufferListener {
                    override fun availBuffer(inputBuffer: ByteBuffer): CodecContext.FeedInfo {
                        inputBuffer.clear()
                        val tmpArray = ByteArray(buffer.limit() - buffer.position())
                        for (i in 0 until buffer.limit() - buffer.position()) {
//                            inputBuffer.put(buffer.get())
                            tmpArray[i] = buffer.get()
                        }
                        inputBuffer.put(tmpArray)
                        buffer.flip()
                        mH.sendEmptyMessage(MSG_INVOKE_DRAIN)

                        val tmOffset = System.currentTimeMillis() - firstTimeStamp
                        return CodecContext.FeedInfo(false, buffer.limit() - buffer.position(), tmOffset * 1000)
                    }

                    override fun bufferTimeout() {
                        Log.v(T, "audio recorder timeout")
                    }
                })
            }
//        }
    }

    fun invokeDecodeJob(eos: Boolean = false) {
        recorderDrainWorker.execute {
            if (!recording) {
                stopWaiter.countDown()
            }
            if (!eos && recording) {
                audioRecorder!!.drainOutputBuffer(10000, eos, object : CodecContext.DrainBufferListener {
                    override fun availBuffer(buffer: ByteBuffer, bufferInfo: MediaCodec.BufferInfo) {
                        Log.d(T, "audio encoder buffer available")
                        muxer!!.writeSampleData(audioRecorder!!, buffer, bufferInfo)
                    }

                    override fun bufferChanged() {
                        Log.d(T, "audio recorder buffer changed")
                    }

                    override fun formatChanged(mediaFormat: MediaFormat) {
                        Log.d(T, "audio encoder format change")
                        muxer!!.addTrack(mediaFormat, audioRecorder!!)
                        muxer!!.mayStart()
                    }

                    override fun bufferTimeOut() {
                        Log.v(T, "audio recorder buffer timeout")
                    }
                })
            }
        }
    }

    private fun stopRecord() {
        recording = false
        audioPlayer!!.removeListener(audioListener)
        recorderFeedWorker.execute {
            stopWaiter.countDown()
        }
        recorderDrainWorker.execute {
            stopWaiter.countDown()
        }
        stopWaiter.await()
        audioRecorder!!.finish()
        muxer!!.finish()
        Log.d(T, "Stop is done.")
    }

    class MainH(private val controller: MainActivity) : Handler() {
        override fun handleMessage(msg: Message?) {
            when (msg!!.what) {
                MSG_INVOKE_DRAIN -> {
                    controller.invokeDecodeJob()
                }
                MSG_SIGNAL_EOS -> {
                    controller.invokeDecodeJob(true)
                }
            }
        }
    }
}

class Renderer : GLSurfaceView.Renderer {
    private lateinit var triangle: Triangle
    private var viewMatrix = FloatArray(16)
    private var mvpMatrix = FloatArray(16)
    private var projectionMatrix = FloatArray(16)
    private var rotationMatrix = FloatArray(16)
    public var angle = 0f

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        GLES30.glClearColor(0f, 0f, 0f, 1.0f)
        triangle = Triangle()
    }

    override fun onDrawFrame(gl: GL10?) {
        val scratch = FloatArray(16)

        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT or GLES30.GL_DEPTH_BUFFER_BIT)
        Matrix.setLookAtM(viewMatrix, 0, 0f, 0f, -3f, 0f, 0f, 0f, 0f, 1.0f, 0.0f)
        Matrix.multiplyMM(mvpMatrix, 0, projectionMatrix, 0, viewMatrix, 0)
        Matrix.setRotateM(rotationMatrix, 0, angle, 0f, 0f, 1.0f)
        Matrix.multiplyMM(scratch, 0, mvpMatrix, 0, rotationMatrix, 0)
        triangle.draw(scratch)

        GLES30.glFinish()
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        GLES30.glViewport(0, 0, width, height)
        val ratio: Float = width.toFloat() / height
        Matrix.frustumM(projectionMatrix, 0, -ratio, ratio, -1f, 1f, 3f, 7f)
    }
}
