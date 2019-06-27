package daqi.app_sr

import android.animation.ValueAnimator
import android.opengl.GLES30
import android.opengl.GLSurfaceView
import android.opengl.Matrix
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class MainActivity : AppCompatActivity() {

    companion object {
        val T = "daqi"
    }

    private var recording = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
        playAudio()
    }

    private fun initUI() {
        btn.run {
            setOnClickListener {
                if (!recording) {
                    startRecord()
                    text = "正在录制"
                    setBackgroundColor(resources.getColor(android.R.color.holo_red_light))
                    recording = true
                } else {
                    stopRecord()
                    text = "开始录制"
                    setBackgroundColor(resources.getColor(android.R.color.darker_gray))
                    recording = false
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
        AudioPlayer().play(resources.assets.openFd("test.mp3"))
    }

    private fun startRecord() {
    }

    private fun stopRecord() {
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
