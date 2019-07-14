package com.bytedance.ttgame.module.screenrecord

import android.app.Application
import android.content.Context
import android.media.AudioFormat
import android.media.MediaCodecInfo
import android.media.MediaCodecList
import android.media.MediaFormat
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowManager
import java.util.*
import kotlin.math.min

// audio format目前由客户端决定
class Quality {
    companion object {
        var HIGH: Quality? = null
        var BASE: Quality? = null
        var LOW: Quality? = null

        const val VIDEO_TYPE = MediaFormat.MIMETYPE_VIDEO_AVC
        const val AUDIO_TYPE = MediaFormat.MIMETYPE_AUDIO_AAC

        val ONLY_PCM_16 = android.os.Build.VERSION.SDK_INT < 24

        const val DEBUG = true
        const val TAG = "daqi-Quality"

        var videoCodec: String? = null
        var videoCap: MediaCodecInfo.VideoCapabilities? = null
        var audioCodec: String? = null
        var audioCap: MediaCodecInfo.AudioCapabilities? = null

        init {
            MediaCodecList(MediaCodecList.REGULAR_CODECS).run {
                val availVideoCodec = mutableListOf<MediaCodecInfo>()
                val availAudioCodec = mutableListOf<MediaCodecInfo>()
                codecInfos.filter {
                    it.isEncoder
                }.run {
                    filter {
                        it.supportedTypes.any { type ->
                            type == VIDEO_TYPE
                        }
                    }.run {
                        availVideoCodec.addAll(this)
                    }
                    filter {
                        it.supportedTypes.any { type ->
                            type == AUDIO_TYPE
                        }
                    }.run {
                        availAudioCodec.addAll(this)
                    }
                }
                if (availAudioCodec.isEmpty() || availVideoCodec.isEmpty()) {
                    return@run
                }
                selectBestVideoCodec(availVideoCodec).run {
                    Log.w(TAG, "select video codec: $name")
                    videoCodec = name
                    videoCap = getCapabilitiesForType(VIDEO_TYPE).videoCapabilities
                }
                selectBestAudioCodec(availAudioCodec).run {
                    Log.w(TAG, "select audio codec: $name")
                    audioCodec = name
                    audioCap = getCapabilitiesForType(AUDIO_TYPE).audioCapabilities
                }
            }
        }

        // audio 可以没有
        public fun init(app: Application) {
            if (videoCodec == null) {
                Log.w(TAG, "No avail video coder.")
                return
            }
            val dm = DisplayMetrics()
            (app.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getRealMetrics(dm)
            var width = min(dm.widthPixels, videoCap!!.supportedWidths.upper)
            if (videoCap!!.widthAlignment > 0) {
                width = Util.alignDown(width, videoCap!!.widthAlignment)
            }
            var height = min(dm.heightPixels, videoCap!!.supportedHeights.upper)
            if (videoCap!!.heightAlignment > 0) {
                height = Util.alignDown(height, videoCap!!.heightAlignment)
            }
            val fpsBest = min(30, videoCap!!.supportedFrameRates.upper)
            val bitrateBest = videoCap!!.bitrateRange.upper
            val iFrameInterval = fpsBest // 按照vesdk的建议，设置为fps大小，得到的视频的I帧就是1s的周期
            if (DEBUG) {
                Log.w(TAG, "width: $width, height: $height, fps: $fpsBest, bit_rate: $bitrateBest, iFrameInterval: $iFrameInterval")
            }
            HIGH = Quality().apply {
                videoFormat = MediaFormat.createVideoFormat(VIDEO_TYPE, width, height).apply {
                    setInteger(MediaFormat.KEY_FRAME_RATE, fpsBest)
                    setInteger(MediaFormat.KEY_BIT_RATE, bitrateBest)
                    setInteger(MediaFormat.KEY_I_FRAME_INTERVAL, iFrameInterval)
                }
            }
            BASE = Quality().apply {
                videoFormat = MediaFormat.createVideoFormat(VIDEO_TYPE, width, height).apply {
                    setInteger(MediaFormat.KEY_FRAME_RATE, fpsBest / 2)
                    setInteger(MediaFormat.KEY_BIT_RATE, bitrateBest / 2)
                    setInteger(MediaFormat.KEY_I_FRAME_INTERVAL, iFrameInterval / 2)
                }
            }
            LOW = Quality().apply {
                videoFormat = MediaFormat.createVideoFormat(VIDEO_TYPE, width, height).apply {
                    setInteger(MediaFormat.KEY_FRAME_RATE, fpsBest / 3)
                    setInteger(MediaFormat.KEY_BIT_RATE, bitrateBest / 3)
                    setInteger(MediaFormat.KEY_I_FRAME_INTERVAL, iFrameInterval / 3)
                }
            }
        }

        // 根据参数选择一个最佳encoder
        private fun selectBestVideoCodec(videoCodecInfos: List<MediaCodecInfo>): MediaCodecInfo {
            var cur: MediaCodecInfo? = null
            var curVideoCap: MediaCodecInfo.VideoCapabilities? = null
            videoCodecInfos.forEach {
                it.getCapabilitiesForType(VIDEO_TYPE).videoCapabilities.run {
                    if (DEBUG) {
                        Log.w(TAG, "dumping video encoder: ${it.name}")
                        dumpCapability(this, null)
                    }
                    if (cur == null || betterVideoCap(curVideoCap!!, this)) {
                        cur = it
                        curVideoCap = this
                    }
                }
            }
            return cur!!
        }

        // 目前采用这种简单的方式
        private fun betterVideoCap(cur: MediaCodecInfo.VideoCapabilities, cand: MediaCodecInfo.VideoCapabilities): Boolean {
            cand.run {
                if (supportedWidths.contains(cur.supportedWidths) && supportedHeights.contains(cur.supportedHeights)) {
                    return true
                }
            }
            return false
        }

        private fun selectBestAudioCodec(audioCodecInfos: List<MediaCodecInfo>): MediaCodecInfo {
            var cur: MediaCodecInfo? = null
            var curAudioCap: MediaCodecInfo.AudioCapabilities? = null
            audioCodecInfos.forEach {
                it.getCapabilitiesForType(AUDIO_TYPE).audioCapabilities.run {
                    if (DEBUG) {
                        Log.w(TAG, "Dumping audio encoder: ${it.name}")
                        dumpCapability(null, this)
                    }
                    if (cur == null || betterAudioCap(curAudioCap!!, this)) {
                        cur = it
                        curAudioCap = this
                    }
                }
            }
            return cur!!
        }

        // 目前采用这种简单的方式
        private fun betterAudioCap(cur: MediaCodecInfo.AudioCapabilities, cand: MediaCodecInfo.AudioCapabilities): Boolean {
            cand.run {
                if (bitrateRange.contains(cur.bitrateRange)) {
                    return true
                }
            }
            return false
        }

        private fun dumpCapability(video: MediaCodecInfo.VideoCapabilities?, audio: MediaCodecInfo.AudioCapabilities?) {
            video?.run {
                Log.w(TAG, "height_range: $supportedHeights")
                Log.w(TAG, "width_range: $supportedWidths")
                Log.w(TAG, "frame_rate_range: $supportedFrameRates")
                Log.w(TAG, "bitrate_range: $bitrateRange")
                Log.w(TAG, "width_alignment: $widthAlignment")
                Log.w(TAG, "height_alignment: $heightAlignment")
            }
            audio?.run {
                Log.w(TAG, "bit_rate_range: $bitrateRange")
                Log.w(TAG, "sample_rates: ${supportedSampleRates.map { it.toString() }}")
                Log.w(TAG, "sample_rate_range: ${Arrays.deepToString(supportedSampleRateRanges)}")
                Log.w(TAG, "channel_count: $maxInputChannelCount")
            }
        }
    }

    lateinit var videoFormat: MediaFormat

    fun getAudioFormat(sampleRate: Int): MediaFormat? {
        if (!audioCap!!.supportedSampleRates.contains(sampleRate)) {
            Log.e(TAG, "Input sample rate($sampleRate) is not supported by current encoder that supported: ${audioCap!!.supportedSampleRates.map { it.toString() }}")
            return null
        }
        return MediaFormat.createAudioFormat(MediaFormat.MIMETYPE_AUDIO_AAC, sampleRate, 2).apply {
            if (ONLY_PCM_16) {
                setInteger(MediaFormat.KEY_BIT_RATE, sampleRate * 16 * 2)
            } else {
                setInteger(MediaFormat.KEY_BIT_RATE, sampleRate * 32 * 2)
                setInteger(MediaFormat.KEY_PCM_ENCODING, AudioFormat.ENCODING_PCM_FLOAT)
            }
            setInteger(MediaFormat.KEY_MAX_INPUT_SIZE, 32768) // 参考HeliumDemo
            setInteger(MediaFormat.KEY_SAMPLE_RATE, sampleRate)
        }
    }
}