package daqi.app_sr.recorder

import android.media.MediaCodec
import android.media.MediaFormat
import android.media.MediaMuxer
import java.nio.ByteBuffer

class MuxerContext {
    lateinit var muxer: MediaMuxer
    private val trackIdMap = mutableMapOf<CodecContext, Int>()
    private var trackCount: Int = 0
    private var started = false

    companion object {
        public fun createMuxer(outputFilePath: String, inputTrackCount: Int): MuxerContext {
            return MuxerContext().apply {
                muxer = MediaMuxer(outputFilePath, MediaMuxer.OutputFormat.MUXER_OUTPUT_MPEG_4)
                trackCount = inputTrackCount
            }
        }
    }

    fun addTrack(track: MediaFormat, coder: CodecContext) {
        trackIdMap[coder] = muxer.addTrack(track)
    }

    fun mayStart() {
        if (started) {
            throw RuntimeException("Mux has already been started.")
        }
        if (trackIdMap.size == trackCount) {
            muxer.start()
            started = true
        }
    }

    fun writeSampleData(coder: CodecContext, buffer: ByteBuffer, bufferInfo: MediaCodec.BufferInfo) {
        muxer.writeSampleData(trackIdMap[coder]!!, buffer, bufferInfo)
    }

    fun finish() {
        muxer.run {
            stop()
            release()
        }
    }
}