package daqi.app_sr.recorder

import android.media.MediaFormat
import android.media.MediaMuxer

class MuxerContext {
    lateinit var muxer: MediaMuxer

    companion object {
        public fun createMuxer(outputFilePath: String): MuxerContext {
            return MuxerContext().apply {
                muxer = MediaMuxer(outputFilePath, MediaMuxer.OutputFormat.MUXER_OUTPUT_MPEG_4)
            }
        }
    }

    fun prepare(vararg tracks: MediaFormat) {
        tracks.forEach {
            muxer.addTrack(it)
        }
        muxer.start()
    }

    fun finish() {
        muxer.run {
            stop()
            release()
        }
    }
}