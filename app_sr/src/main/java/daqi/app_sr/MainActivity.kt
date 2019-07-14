package daqi.app_sr

import android.Manifest
import android.animation.ValueAnimator
import android.content.pm.PackageManager
import android.media.MediaCodec
import android.media.MediaCodecInfo
import android.media.MediaFormat
import android.opengl.GLES20
import android.opengl.Matrix
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Choreographer
import android.view.Surface
import android.view.SurfaceHolder
import daqi.app_sr.MainActivity.Companion.MSG_INVOKE_DRAIN
import daqi.app_sr.MainActivity.Companion.MSG_SIGNAL_EOS
import daqi.app_sr.MainActivity.Companion.T
import com.bytedance.ttgame.module.screenrecord.CodecContext
import com.bytedance.ttgame.module.screenrecord.MuxerContext
import gles.Drawable2d
import gles.EglCore
import gles.FlatShadedProgram
import gles.GlUtil
import gles.Sprite2d
import gles.WindowSurface
import kotlinx.android.synthetic.main.activity_main.btn
import kotlinx.android.synthetic.main.activity_main.gl
import java.lang.ref.WeakReference
import java.nio.ByteBuffer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity(), SurfaceHolder.Callback, Choreographer.FrameCallback {
    companion object {
        const val T = "daqi - Activity"

        public const val MSG_INVOKE_DRAIN = 1
        public const val MSG_SIGNAL_EOS = 2
    }

    private var recording = false
    private var audioRecorder: CodecContext? = null
    private var videoRecorder: CodecContext? = null
    private var inputSurface: Surface? = null
    private var muxer: MuxerContext? = null
    private var recorderFeedWorker = Executors.newSingleThreadExecutor()
    private var recorderDrainWorker = Executors.newSingleThreadExecutor()
    private var audioPlayer: AudioPlayer? = null
    public val mH: MainH = MainH(this)
    private val stopWaiter = CountDownLatch(2)
    private var render: RenderThread? = null

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

    override fun surfaceCreated(holder: SurfaceHolder?) {
        render = RenderThread(gl.holder.surface).apply {
            name = "RecordFBO GL render"
            start()
            waitUntilReady()
        }
        render!!.mHandler!!.sendSurfaceCreated()
        // start the draw events
        Choreographer.getInstance().postFrameCallback(this)
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
        render!!.mHandler!!.sendSurfaceChanged(format, width, height)
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        // todo
    }

    override fun doFrame(frameTimeNanos: Long) {
        Log.i(T, "doFrame")
        Choreographer.getInstance().postFrameCallback(this)
        render!!.mHandler!!.sendDoFrame(frameTimeNanos)
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

        val ac = this
        gl.run {
            holder.addCallback(ac)
            ValueAnimator.ofFloat(0f, 3.14f).apply {
                duration = 10 * 1000
                repeatMode = ValueAnimator.RESTART
                repeatCount = ValueAnimator.INFINITE
                addUpdateListener {
                    //                    render!!.angle = render!!.angle + animatedValue as Float
                    // todo
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

    public fun startRecord() {
        recording = true
        recorderFeedWorker.execute {
            if (audioRecorder == null) {
                val format = MediaFormat.createAudioFormat(MediaFormat.MIMETYPE_AUDIO_AAC, 44100, 2).apply {
                    setString(MediaFormat.KEY_MIME, MediaFormat.MIMETYPE_AUDIO_AAC)
                    setInteger(MediaFormat.KEY_AAC_PROFILE, MediaCodecInfo.CodecProfileLevel.AACObjectLC)
                    setInteger(MediaFormat.KEY_BIT_RATE, 96000)
                    setInteger(MediaFormat.KEY_MAX_INPUT_SIZE, 1024 * 1024)
                }
                audioRecorder = CodecContext.createEncoderByType(MediaFormat.MIMETYPE_AUDIO_AAC, format)
            }
            if (videoRecorder == null) {
                // Create and configure the MediaFormat.
                val format = MediaFormat.createVideoFormat("video/avc", 1080, 2340).apply {
                    setInteger(MediaFormat.KEY_COLOR_FORMAT, MediaCodecInfo.CodecCapabilities.COLOR_FormatSurface)
                    setInteger(MediaFormat.KEY_BIT_RATE, 6000000)
                    setInteger(MediaFormat.KEY_FRAME_RATE, 30)
                    setInteger(MediaFormat.KEY_I_FRAME_INTERVAL, 10)
                }
                videoRecorder = CodecContext.createEncoderByType("video/avc", format)
                inputSurface = videoRecorder!!.createInputSurface()
            }
            if (muxer == null) {
                muxer = MuxerContext.createMuxer("/sdcard/out2.mp4", 2)
            }
            audioRecorder!!.prepare()
            videoRecorder!!.prepare()
            audioPlayer!!.addListener(audioListener)
            render!!.mHandler!!.setRecordingEnabled(true, inputSurface!!)
            render!!.controller = this
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
            if (recording) {
                audioRecorder!!.drainOutputBuffer(10000, false, object : CodecContext.DrainBufferListener {
                    override fun availBuffer(buffer: ByteBuffer, bufferInfo: MediaCodec.BufferInfo) {
                        Log.i(T, "audio encoder buffer available")
                        muxer!!.writeSampleData(audioRecorder!!, buffer, bufferInfo)
                    }

                    override fun bufferChanged() {
                        Log.d(T, "audio recorder buffer changed")
                    }

                    override fun formatChanged(mediaFormat: MediaFormat) {
                        Log.w(T, "audio encoder format change")
                        muxer!!.addTrack(mediaFormat, audioRecorder!!)
                        muxer!!.mayStart()
                    }

                    override fun bufferTimeOut() {
                        Log.v(T, "audio recorder buffer timeout")
                    }
                })

                videoRecorder!!.drainOutputBuffer(10000, eos, object : CodecContext.DrainBufferListener {
                    override fun availBuffer(buffer: ByteBuffer, bufferInfo: MediaCodec.BufferInfo) {
                        Log.i(T, "video encoder buffer available")
                        muxer!!.writeSampleData(videoRecorder!!, buffer, bufferInfo)
                    }

                    override fun bufferChanged() {
                        Log.d(T, "video recorder buffer changed")
                    }

                    override fun formatChanged(mediaFormat: MediaFormat) {
                        Log.w(T, "video encoder format change")
                        muxer!!.addTrack(mediaFormat, videoRecorder!!)
                        muxer!!.mayStart()
                    }

                    override fun bufferTimeOut() {
                        Log.v(T, "video recorder buffer timeout")
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
        render!!.mHandler!!.setRecordingEnabled(false, inputSurface!!)
        muxer!!.mayFinish()
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

class RenderThread(val surface: Surface) : Thread() {
    private var mTri: Sprite2d
    private val mTriDrawable = Drawable2d(Drawable2d.Prefab.TRIANGLE)
    // Orthographic projection matrix.
    private val mDisplayProjectionMatrix = FloatArray(16)

    public var angle = 0f
    public var controller: MainActivity? = null

    private var mEglCore: EglCore? = null
    private var mWindowSurface: WindowSurface? = null
    private var mProgram: FlatShadedProgram? = null

    private var recording = false

    // Object must be created on render thread to get correct Looper, but is used from
    // UI thread, so we need to declare it volatile to ensure the UI thread sees a fully
    // constructed object.
    @Volatile
    public var mHandler: RenderHandler? = null
    // Used to wait for the thread to start.
    private val mStartLock = Object()
    private var mReady = false

    init {
        mTri = Sprite2d(mTriDrawable)
    }

    override fun run() {
        Log.d(T, "render thread starts")
        Looper.prepare()
        mHandler = RenderHandler(this)
        mEglCore = EglCore(null, EglCore.FLAG_RECORDABLE or EglCore.FLAG_TRY_GLES3)
        synchronized(mStartLock) {
            mReady = true
            mStartLock.notify()    // signal waitUntilReady()
        }

        Looper.loop()


        Log.d(T, "looper quit")
        releaseGl()
        mEglCore!!.release()

        synchronized(mStartLock) {
            mReady = false
        }
    }

    fun surfaceCreated() {
        prepareGl(surface)
    }

    var W: Int = 0
    var H: Int = 0

    fun surfaceChanged(width: Int, height: Int) {
        prepareFramebuffer(width, height)

        GLES20.glViewport(0, 0, width, height)

        // Simple orthographic projection, with (0,0) in lower-left corner.
        Matrix.orthoM(mDisplayProjectionMatrix, 0, 0f, width.toFloat(), 0f, height.toFloat(), -1f, 1f)

        val smallDim = Math.min(width, height)
        // Set initial shape size / position / velocity based on window size.  Movement
        // has the same "feel" on all devices, but the actual path will vary depending
        // on the screen proportions.  We do it here, rather than defining fixed values
        // and tweaking the projection matrix, so that our squares are square.
        mTri.setColor(0.1f, 0.9f, 0.1f)
        mTri.setScale(smallDim / 4.0f, smallDim / 4.0f)
        mTri.setPosition(width / 2.0f, height / 2.0f)

        W = width
        H = height
    }

    var stTimeNanos = -1L

    fun doFrame(timeStampNanos: Long) {
        if (stTimeNanos == -1L) {
            stTimeNanos = timeStampNanos
        }
        mTri.rotation = ((timeStampNanos - stTimeNanos) / 360).toFloat()
        draw()
        mWindowSurface!!.swapBuffers()

        if (recording) {
            controller!!.mH.sendEmptyMessage(MSG_INVOKE_DRAIN)
            // Draw for recording, swap.
            mInputWindowSurface!!.makeCurrent()
            // If we don't set the scissor rect, the glClear() we use to draw the
            // light-grey background will draw outside the viewport and muck up our
            // letterboxing.  Might be better if we disabled the test immediately after
            // the glClear().  Of course, if we were clearing the frame background to
            // black it wouldn't matter.
            //
            // We do still need to clear the pixels outside the scissor rect, of course,
            // or we'll get garbage at the edges of the recording.  We can either clear
            // the whole thing and accept that there will be a lot of overdraw, or we
            // can issue multiple scissor/clear calls.  Some GPUs may have a special
            // optimization for zeroing out the color buffer.
            //
            // For now, be lazy and zero the whole thing.  At some point we need to
            // examine the performance here.
            GLES20.glClearColor(0f, 0f, 0f, 1f)
            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)

            GLES20.glViewport(0, 0, W, H)
            draw()
            mInputWindowSurface!!.setPresentationTime(timeStampNanos)
            mInputWindowSurface!!.swapBuffers()

            // Restore.
            GLES20.glViewport(0, 0, mWindowSurface!!.getWidth(), mWindowSurface!!.getHeight())
            mWindowSurface!!.makeCurrent()
        }
    }

    /**
     * Draws the scene.
     */
    private fun draw() {
        GlUtil.checkGlError("draw start")

        // Clear to a non-black color to make the content easily differentiable from
        // the pillar-/letter-boxing.
        GLES20.glClearColor(0.2f, 0.2f, 0.2f, 1.0f)
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)

        mTri.draw(mProgram, mDisplayProjectionMatrix)

        GlUtil.checkGlError("draw done")
    }

    /**
     * Prepares window surface and GL state.
     */
    private fun prepareGl(surface: Surface) {
        Log.d(T, "prepareGl")

        mWindowSurface = WindowSurface(mEglCore, surface, false)
        mWindowSurface!!.makeCurrent()

        // Program used for drawing onto the screen.
        mProgram = FlatShadedProgram()

        // Set the background color.
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f)

        // Disable depth testing -- we're 2D only.
        GLES20.glDisable(GLES20.GL_DEPTH_TEST)

        // Don't need backface culling.  (If you're feeling pedantic, you can turn it on to
        // make sure we're defining our shapes correctly.)
        GLES20.glDisable(GLES20.GL_CULL_FACE)
    }

    private var mOffscreenTexture: Int = 0
    private var mFramebuffer: Int = 0
    private var mDepthBuffer: Int = 0
    private var mInputWindowSurface: WindowSurface? = null

    /**
     * Prepares the off-screen framebuffer.
     */
    private fun prepareFramebuffer(width: Int, height: Int) {
        GlUtil.checkGlError("prepareFramebuffer start")

        val values = IntArray(1)

        // Create a texture object and bind it.  This will be the color buffer.
        GLES20.glGenTextures(1, values, 0)
        GlUtil.checkGlError("glGenTextures")
        mOffscreenTexture = values[0]   // expected > 0
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mOffscreenTexture)
        GlUtil.checkGlError("glBindTexture $mOffscreenTexture")

        // Create texture storage.
        GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGBA, width, height, 0,
                GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, null)

        // Set parameters.  We're probably using non-power-of-two dimensions, so
        // some values may not be available for use.
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER,
                GLES20.GL_NEAREST.toFloat())
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER,
                GLES20.GL_LINEAR.toFloat())
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S,
                GLES20.GL_CLAMP_TO_EDGE)
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T,
                GLES20.GL_CLAMP_TO_EDGE)
        GlUtil.checkGlError("glTexParameter")

        // Create framebuffer object and bind it.
        GLES20.glGenFramebuffers(1, values, 0)
        GlUtil.checkGlError("glGenFramebuffers")
        mFramebuffer = values[0]    // expected > 0
        GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, mFramebuffer)
        GlUtil.checkGlError("glBindFramebuffer $mFramebuffer")

        // Create a depth buffer and bind it.
        GLES20.glGenRenderbuffers(1, values, 0)
        GlUtil.checkGlError("glGenRenderbuffers")
        mDepthBuffer = values[0]    // expected > 0
        GLES20.glBindRenderbuffer(GLES20.GL_RENDERBUFFER, mDepthBuffer)
        // Allocate storage for the depth buffer.
        GlUtil.checkGlError("glBindRenderbuffer $mDepthBuffer")

        GLES20.glRenderbufferStorage(GLES20.GL_RENDERBUFFER, GLES20.GL_DEPTH_COMPONENT16,
                width, height)
        GlUtil.checkGlError("glRenderbufferStorage")

        // Attach the depth buffer and the texture (color buffer) to the framebuffer object.
        GLES20.glFramebufferRenderbuffer(GLES20.GL_FRAMEBUFFER, GLES20.GL_DEPTH_ATTACHMENT,
                GLES20.GL_RENDERBUFFER, mDepthBuffer)
        GlUtil.checkGlError("glFramebufferRenderbuffer")
        GLES20.glFramebufferTexture2D(GLES20.GL_FRAMEBUFFER, GLES20.GL_COLOR_ATTACHMENT0,
                GLES20.GL_TEXTURE_2D, mOffscreenTexture, 0)
        GlUtil.checkGlError("glFramebufferTexture2D")

        // See if GLES is happy with all this.
        val status = GLES20.glCheckFramebufferStatus(GLES20.GL_FRAMEBUFFER)
        if (status != GLES20.GL_FRAMEBUFFER_COMPLETE) {
            throw RuntimeException("Framebuffer not complete, status=$status")
        }

        // Switch back to the default framebuffer.
        GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, 0)

        GlUtil.checkGlError("prepareFramebuffer done")
    }

    /**
     * Creates the video encoder object and starts the encoder thread.  Creates an EGL
     * surface for encoder input.
     */
    public fun startEncoder(surface: Surface) {
        mInputWindowSurface = WindowSurface(mEglCore, surface, true)
        recording = true
    }

    /**
     * Stops the video encoder if it's running.
     */
    public fun stopEncoder() {
        if (mInputWindowSurface != null) {
            recording = false
            controller!!.mH.sendEmptyMessage(MSG_SIGNAL_EOS)
            mInputWindowSurface!!.release()
            mInputWindowSurface = null
        }
    }

    /**
     * Waits until the render thread is ready to receive messages.
     *
     *
     * Call from the UI thread.
     */
    fun waitUntilReady() {
        synchronized(mStartLock) {
            while (!mReady) {
                try {
                    mStartLock.wait()
                } catch (ie: InterruptedException) { /* not expected */
                }

            }
        }
    }


    /**
     * Releases most of the GL resources we currently hold.
     *
     *
     * Does not release EglCore.
     */
    private fun releaseGl() {
        GlUtil.checkGlError("releaseGl start")

        val values = IntArray(1)

        if (mWindowSurface != null) {
            mWindowSurface!!.release()
            mWindowSurface = null
        }
        if (mProgram != null) {
            mProgram!!.release()
            mProgram = null
        }
        if (mOffscreenTexture > 0) {
            values[0] = mOffscreenTexture
            GLES20.glDeleteTextures(1, values, 0)
            mOffscreenTexture = -1
        }
        if (mFramebuffer > 0) {
            values[0] = mFramebuffer
            GLES20.glDeleteFramebuffers(1, values, 0)
            mFramebuffer = -1
        }
        if (mDepthBuffer > 0) {
            values[0] = mDepthBuffer
            GLES20.glDeleteRenderbuffers(1, values, 0)
            mDepthBuffer = -1
        }

        GlUtil.checkGlError("releaseGl done")

        mEglCore!!.makeNothingCurrent()
    }
}

/**
 * Handler for RenderThread.  Used for messages sent from the UI thread to the render thread.
 *
 *
 * The object is created on the render thread, and the various "send" methods are called
 * from the UI thread.
 */
public class RenderHandler
/**
 * Call from render thread.
 */
(rt: RenderThread) : Handler() {

    // This shouldn't need to be a weak ref, since we'll go away when the Looper quits,
    // but no real harm in it.
    private val mWeakRenderThread: WeakReference<RenderThread>

    init {
        mWeakRenderThread = WeakReference<RenderThread>(rt)
    }

    /**
     * Sends the "surface created" message.
     *
     *
     * Call from UI thread.
     */
    fun sendSurfaceCreated() {
        sendMessage(obtainMessage(RenderHandler.MSG_SURFACE_CREATED))
    }

    /**
     * Sends the "surface changed" message, forwarding what we got from the SurfaceHolder.
     *
     *
     * Call from UI thread.
     */
    fun sendSurfaceChanged(format: Int,
                           width: Int, height: Int) {
        // ignore format
        sendMessage(obtainMessage(RenderHandler.MSG_SURFACE_CHANGED, width, height))
    }

    /**
     * Sends the "do frame" message, forwarding the Choreographer event.
     *
     *
     * Call from UI thread.
     */
    fun sendDoFrame(frameTimeNanos: Long) {
        sendMessage(obtainMessage(RenderHandler.MSG_DO_FRAME,
                (frameTimeNanos shr 32).toInt(), frameTimeNanos.toInt()))
    }

    /**
     * Enable or disable recording.
     *
     *
     * Call from non-UI thread.
     */
    fun setRecordingEnabled(enabled: Boolean, surface: Surface) {
        sendMessage(obtainMessage(MSG_RECORDING_ENABLED, if (enabled) 1 else 0, 0, surface))
    }

    /**
     * Set the method used to render a frame for the encoder.
     *
     *
     * Call from non-UI thread.
     */
    fun setRecordMethod(recordMethod: Int) {
        sendMessage(obtainMessage(MSG_RECORD_METHOD, recordMethod, 0))
    }

    /**
     * Sends the "shutdown" message, which tells the render thread to halt.
     *
     *
     * Call from UI thread.
     */
    fun sendShutdown() {
        sendMessage(obtainMessage(RenderHandler.MSG_SHUTDOWN))
    }

    override// runs on RenderThread
    fun handleMessage(msg: Message) {
        val what = msg.what
        //Log.d(TAG, "RenderHandler [" + this + "]: what=" + what);

        val renderThread = mWeakRenderThread.get()
        if (renderThread == null) {
            Log.w(T, "RenderHandler.handleMessage: weak ref is null")
            return
        }

        when (what) {
            MSG_SURFACE_CREATED -> renderThread!!.surfaceCreated()
            MSG_SURFACE_CHANGED -> renderThread!!.surfaceChanged(msg.arg1, msg.arg2)
            MSG_DO_FRAME -> {
                val timestamp = msg.arg1.toLong() shl 32 or (msg.arg2.toLong() and 0xffffffffL)
                renderThread!!.doFrame(timestamp)
            }
            MSG_RECORDING_ENABLED -> if (msg.arg1 != 0) renderThread.startEncoder(msg.obj as Surface) else renderThread.stopEncoder()
            else -> throw RuntimeException("unknown message $what")
        }
    }

    companion object {
        private val MSG_SURFACE_CREATED = 0
        private val MSG_SURFACE_CHANGED = 1
        private val MSG_DO_FRAME = 2
        private val MSG_RECORDING_ENABLED = 3
        private val MSG_RECORD_METHOD = 4
        private val MSG_SHUTDOWN = 5
        const val T = "daqi - Handler"
    }
}
