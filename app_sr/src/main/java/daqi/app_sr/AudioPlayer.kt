package daqi.app_sr

import android.content.res.AssetFileDescriptor
import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack
import android.media.MediaCodec
import android.media.MediaExtractor
import android.media.MediaFormat
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.os.Message
import android.util.Log
import com.bytedance.ttgame.module.screenrecord.CodecContext
import com.bytedance.ttgame.module.screenrecord.Quality
import java.nio.ByteBuffer

class AudioPlayer {

    companion object {
        const val T = "daqi"

        const val MSG_START = 0
        const val MSG_FINISH = 1
    }

    interface Listener {
        fun onNewBuffer(buffer: ByteBuffer, sampleTime: Long)
    }

    private val listeners = mutableListOf<Listener>()
    private var sampleTime: Long = -1
    public var sampleRate: Int = -1
    private lateinit var coder: CodecContext
    private lateinit var audioTrack: AudioTrack

    private val ht: HandlerThread
    private val handler: Handler

    init {
        ht = HandlerThread("Audio-Player").apply {
            start()
            handler = object : Handler(looper) {
                override fun handleMessage(msg: Message?) {
                    when (msg!!.what) {
                        MSG_START -> {
                            play(msg.obj as AssetFileDescriptor)
                        }
                        MSG_FINISH -> {
                            audioTrack.run {
                                release()
                                stop()
                                coder.stop()
                            }
                        }
                    }
                }
            }
        }
    }

    public fun start(fd: AssetFileDescriptor) {
        handler.obtainMessage(MSG_START, fd).sendToTarget()
    }

    public fun stop() {
        handler.sendEmptyMessage(MSG_FINISH)
    }

    public fun play(fd: AssetFileDescriptor) {
        try {
            val extractor = MediaExtractor()
            extractor.setDataSource(fd.fileDescriptor, fd.startOffset, fd.declaredLength)
            for (i in 0 until extractor.trackCount) {
                val inputFormat = extractor.getTrackFormat(i)
                val mime = inputFormat.getString(MediaFormat.KEY_MIME)
                if (!mime.startsWith("audio/")) continue

                extractor.selectTrack(i)
                val channels = inputFormat.getInteger(MediaFormat.KEY_CHANNEL_COUNT)
                sampleRate = inputFormat.getInteger(MediaFormat.KEY_SAMPLE_RATE)
                val minBufferSize = AudioTrack.getMinBufferSize(sampleRate,
                        if (channels == 1) AudioFormat.CHANNEL_OUT_MONO else AudioFormat.CHANNEL_OUT_STEREO,
                        AudioFormat.ENCODING_PCM_FLOAT)

                val outSampleRate = 44100
                val attr = AudioAttributes.Builder()
                        .setLegacyStreamType(AudioManager.STREAM_MUSIC)
                        .build()
                val outFormat = AudioFormat.Builder().apply {
                    setChannelMask(AudioFormat.CHANNEL_OUT_STEREO)
                    setEncoding(if (Quality.ONLY_PCM_16) AudioFormat.ENCODING_PCM_16BIT else AudioFormat.ENCODING_PCM_FLOAT)
                    setSampleRate(outSampleRate)
                }.build()
                audioTrack = AudioTrack(attr, outFormat, minBufferSize, AudioTrack.MODE_STREAM, AudioManager.AUDIO_SESSION_ID_GENERATE).apply {
                    play()
                }

                if (!Quality.ONLY_PCM_16) {
                    inputFormat.setInteger(MediaFormat.KEY_PCM_ENCODING, AudioFormat.ENCODING_PCM_FLOAT)
                }

                var tmpBuffer: FloatArray? = null
                coder = CodecContext.createDecoder(mime, inputFormat, object : CodecContext.AbstraceCallback() {
                    override fun onInputBufferAvailable(codec: MediaCodec, index: Int) {
                        handler.post {
                            val buffer = codec.getInputBuffer(index)
                            val sz = extractor.readSampleData(buffer, 0)
                            if (sz < 0) {
                                codec.queueInputBuffer(index, 0, 0, 0, MediaCodec.BUFFER_FLAG_END_OF_STREAM)
                            } else {
                                sampleTime = extractor.sampleTime
                                extractor.advance()
                                codec.queueInputBuffer(index, 0, sz, sampleTime, 0)
                            }
                        }
                    }

                    override fun onOutputBufferAvailable(codec: MediaCodec, index: Int, info: MediaCodec.BufferInfo) {
                        handler.post {
                            val buffer = codec.getOutputBuffer(index)
                            listeners.forEach {
                                it.onNewBuffer(buffer, sampleTime)
                            }
                            if (!Quality.ONLY_PCM_16) {
                                buffer.asFloatBuffer().run {
                                    val sz = limit() - position()
                                    if (tmpBuffer == null || tmpBuffer!!.size < sz) {
                                        tmpBuffer = FloatArray(limit() - position())
                                    }
                                    get(tmpBuffer, 0, limit() - position())
                                    clear()
                                    audioTrack.write(tmpBuffer, 0, sz, AudioTrack.WRITE_BLOCKING)
                                }
                            } else {
                                buffer.run {
                                    val sz = limit() - position()
                                    val ba = ByteArray(sz)
                                    get(ba, 0, sz)
                                    clear()
                                    audioTrack.write(ba, 0, sz)
                                }
                            }
                            codec.releaseOutputBuffer(index, false)
                        }
                    }

                    override fun onError(codec: MediaCodec, e: MediaCodec.CodecException) {
                        Log.e(T, "audio player decoder onError", e)
                    }
                })
                coder.start()
                Log.w(T, "audio player start successful")
            }
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    public fun addListener(listener: Listener) {
        listeners.add(listener)
    }

    public fun removeListener(listener: Listener) {
        listeners.remove(listener)
    }
}

