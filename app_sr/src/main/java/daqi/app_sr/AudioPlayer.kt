package daqi.app_sr

import android.content.res.AssetFileDescriptor
import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack
import android.media.MediaCodec
import android.media.MediaExtractor
import android.media.MediaFormat
import android.support.v7.view.menu.ListMenuItemView
import android.util.Log
import com.bytedance.ttgame.module.screenrecord.CodecContext
import com.bytedance.ttgame.module.screenrecord.Quality
import java.nio.ByteBuffer
import java.util.concurrent.Executors

class AudioPlayer {

    companion object {
        const val T = "daqi"
    }

    interface Listener {
        fun onNewBuffer(buffer: ByteBuffer, sampleTime: Long)
    }

    private val worker = Executors.newSingleThreadExecutor()
    private val listeners = mutableListOf<Listener>()
    private var sampleTime: Long = -1
    public var sampleRate: Int = -1

    public fun play(fd: AssetFileDescriptor) {
        worker.execute {
            try {
                var track: AudioTrack
                var inputBufferSize: Int
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
//                    val maxBufferSize = inputFormat.getInteger(MediaFormat.KEY_MAX_INPUT_SIZE)
//                    inputBufferSize = if (minBufferSize > 0) minBufferSize * 4 else maxBufferSize
//                    val frameSizeInBytes = channels * 2
//                    inputBufferSize = (inputBufferSize / frameSizeInBytes) * frameSizeInBytes

                    val outSampleRate = 44100
                    val attr = AudioAttributes.Builder()
                            .setLegacyStreamType(AudioManager.STREAM_MUSIC)
                            .build()
                    val outFormat = AudioFormat.Builder().apply {
                        setChannelMask(AudioFormat.CHANNEL_OUT_STEREO)
                        setEncoding(if (Quality.ONLY_PCM_16) AudioFormat.ENCODING_PCM_16BIT else AudioFormat.ENCODING_PCM_FLOAT)
                        setSampleRate(outSampleRate)
                    }.build()
                    track = AudioTrack(attr, outFormat, minBufferSize, AudioTrack.MODE_STREAM, AudioManager.AUDIO_SESSION_ID_GENERATE)
                    track.play()

                    if (!Quality.ONLY_PCM_16) {
                        inputFormat.setInteger(MediaFormat.KEY_PCM_ENCODING, AudioFormat.ENCODING_PCM_FLOAT)
                    }
                    val coderCtx = CodecContext.createDecoder(mime, inputFormat)
                    coderCtx.prepare()
                    var eos = false
                    while (!eos) {
                        coderCtx.feedInputBuffer(10000, object : CodecContext.FeedBufferListener {
                            override fun availBuffer(buffer: ByteBuffer): CodecContext.FeedInfo {
                                val sz = extractor.readSampleData(buffer, 0)
                                return if (sz < 0) {
                                    eos = true
                                    CodecContext.FeedInfo(true)
                                } else {
                                    sampleTime = extractor.sampleTime
                                    Log.d(T, "audio player sample time: $sampleTime, sz: $sz")
                                    extractor.advance()
                                    CodecContext.FeedInfo(size = sz, presentationTimeUs = sampleTime)
                                }
                            }

                            override fun bufferTimeout() {
                                Log.d(T, "Audio buffer timeout")
                            }
                        })

                        var tmpBuffer: FloatArray? = null
                        coderCtx.drainOutputBuffer(10000, false, object : CodecContext.DrainBufferListener {
                            override fun availBuffer(buffer: ByteBuffer, bufferInfo: MediaCodec.BufferInfo) {
//                                val st = System.currentTimeMillis()
//                                Log.w(T, "buffer info buffer: $buffer, buffer_info sz: ${bufferInfo.size}, offset: ${bufferInfo.offset}")
                                listeners.forEach {
                                    it.onNewBuffer(buffer, sampleTime)
                                }
//                                Log.i(T, "feed one encoded buffer consume: " + (System.currentTimeMillis() - st) + " ms")
                                if (!Quality.ONLY_PCM_16) {
                                    buffer.asFloatBuffer().run {
                                        val sz = limit() - position()
                                        if (tmpBuffer == null || tmpBuffer!!.size < sz) {
                                            tmpBuffer = FloatArray(limit() - position())
                                        }
//                                    Util.logk("daqi", "float buffer info: $this")
                                        get(tmpBuffer, 0, limit() - position())
                                        clear()
                                        track.write(tmpBuffer, 0, sz, AudioTrack.WRITE_BLOCKING)
                                    }
                                } else {
                                    buffer.run {
                                        val sz = limit() - position()
                                        val ba = ByteArray(sz)
                                        get(ba, 0, sz)
                                        clear()
                                        track.write(ba, 0, sz)
                                    }
                                }
                            }

                            override fun bufferChanged() {
                                Log.d(T, "audio output buffer changed")
                            }

                            override fun formatChanged(mediaFormat: MediaFormat) {
                                Log.d(T, "audio output format changed")
                            }

                            override fun bufferTimeOut() {
                                Log.v(T, "audio output timeout")
                            }
                        })
                    }
                    Log.d(T, "Audio EOS")
                    track.run {
                        stop()
                        release()
                        coderCtx.finish()
                    }
                }
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }
    }

    public fun addListener(listener: Listener) {
        listeners.add(listener)
    }

    public fun removeListener(listener: Listener) {
        listeners.remove(listener)
    }
}

