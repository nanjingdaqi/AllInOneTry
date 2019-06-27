package daqi.app_sr.recorder

import android.media.MediaCodec
import android.media.MediaFormat
import java.nio.ByteBuffer

class CodecContext {
    lateinit var coder: MediaCodec
    lateinit var bufferInfo: MediaCodec.BufferInfo
    lateinit var inputBuffers: Array<ByteBuffer>
    lateinit var outputBuffers: Array<ByteBuffer>

    companion object {
        public fun createDecoder(mime: String, format: MediaFormat): CodecContext {
            return CodecContext().apply {
                coder = MediaCodec.createDecoderByType(mime)
                coder.configure(format, null, null, 0)
                bufferInfo = MediaCodec.BufferInfo()
            }
        }

        public fun createEncoder(mime: String, format: MediaFormat): CodecContext {
            return CodecContext().apply {
                coder = MediaCodec.createEncoderByType(mime)
                coder.configure(format, null, null, MediaCodec.CONFIGURE_FLAG_ENCODE)
                bufferInfo = MediaCodec.BufferInfo()
            }
        }
    }

    data class FeedInfo(val eos: Boolean = false, val size: Int = 0, val presentationTimeUs: Long = 0)

    interface FeedBufferListener {
        fun availBuffer(buffer: ByteBuffer): FeedInfo

        fun bufferTimeout()
    }

    interface DrainBufferListener {
        fun availBuffer(buffer: ByteBuffer, bufferInfo: MediaCodec.BufferInfo)

        fun bufferChanged()

        fun formatChanged(mediaFormat: MediaFormat)

        fun bufferTimeOut()
    }


    fun prepare() {
        coder.start()
        inputBuffers = coder.inputBuffers
        outputBuffers = coder.outputBuffers
    }

    fun finish() {
        coder.run {
            stop()
            release()
        }
    }

    fun feedInputBuffer(timeoutUs: Long, listener: FeedBufferListener) {
        val index = coder.dequeueInputBuffer(timeoutUs)
        if (index >= 0) {
            val feedInfo = listener.availBuffer(inputBuffers[index])
            if (feedInfo.eos) {
                coder.queueInputBuffer(index, 0, 0, 0, MediaCodec.BUFFER_FLAG_END_OF_STREAM)
            } else {
                coder.queueInputBuffer(index, 0, feedInfo.size, feedInfo.presentationTimeUs, 0)
            }
        } else {
            listener.bufferTimeout()
        }
    }

    fun drainOutputBuffer(timeoutUs: Long, eos: Boolean, listener: DrainBufferListener) {
        if (eos) coder.signalEndOfInputStream()

        out_loop@ while (true) {
            val index = coder.dequeueOutputBuffer(bufferInfo, timeoutUs)
            if (index == MediaCodec.INFO_TRY_AGAIN_LATER) {
                if (!eos) {
                    listener.bufferTimeOut()
                    break@out_loop
                } else {
                    // todo logging
                }
            } else if (index == MediaCodec.INFO_OUTPUT_BUFFERS_CHANGED) {
                listener.bufferChanged()
            } else if (index == MediaCodec.INFO_OUTPUT_FORMAT_CHANGED) {
                listener.formatChanged(coder.outputFormat)
            } else if (index < 0) {
                // todo logging ignore
            } else {
                val outData = outputBuffers[index]
                if ((bufferInfo.flags and MediaCodec.BUFFER_FLAG_CODEC_CONFIG) != 0) {
                    bufferInfo.size = 0
                }

                if (bufferInfo.size != 0) {
                    outData.position(bufferInfo.offset)
                    outData.limit(bufferInfo.offset + bufferInfo.size)
                }

                listener.availBuffer(outData, bufferInfo)

                coder.releaseOutputBuffer(index, false)
            }
        }
    }
}