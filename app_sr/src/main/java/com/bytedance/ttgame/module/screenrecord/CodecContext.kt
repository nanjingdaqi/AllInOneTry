package com.bytedance.ttgame.module.screenrecord

import android.media.MediaCodec
import android.media.MediaFormat
import android.view.Surface

class CodecContext {
    lateinit var coder: MediaCodec

    companion object {
        const val T = "CodecContext"

        public fun createDecoder(mime: String, format: MediaFormat, cb: MediaCodec.Callback): CodecContext {
            return CodecContext().apply {
                coder = MediaCodec.createDecoderByType(mime).apply {
                    setCallback(cb)
                    configure(format, null, null, 0)
                }
            }
        }

        public fun createEncoderByType(mime: String, format: MediaFormat, cb: MediaCodec.Callback): CodecContext {
            return CodecContext().apply {
                coder = MediaCodec.createEncoderByType(mime).apply {
                    setCallback(cb)
                    configure(format, null, null, MediaCodec.CONFIGURE_FLAG_ENCODE)
                }
            }
        }

        public fun createEncoderByName(name: String, format: MediaFormat, cb: MediaCodec.Callback): CodecContext {
            return CodecContext().apply {
                coder = MediaCodec.createByCodecName(name).apply {
                    setCallback(cb)
                    configure(format, null, null, MediaCodec.CONFIGURE_FLAG_ENCODE)
                }
            }
        }
    }

    abstract class AbstraceCallback : MediaCodec.Callback() {
        override fun onOutputBufferAvailable(codec: MediaCodec, index: Int, info: MediaCodec.BufferInfo) { }

        override fun onInputBufferAvailable(codec: MediaCodec, index: Int) { }

        override fun onOutputFormatChanged(codec: MediaCodec, format: MediaFormat) { }

        override fun onError(codec: MediaCodec, e: MediaCodec.CodecException) { }
    }


    fun createInputSurface(): Surface = coder.createInputSurface()

    fun start() {
        coder.start()
    }

    fun stop() {
        coder.run {
            stop()
            release()
        }
    }
}