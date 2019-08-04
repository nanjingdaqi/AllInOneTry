package com.bytedance.ttgame.module.screenrecord

import com.ss.android.vesdk.VEUtils

object VideoEditorWorker {

    const val TAG = "daqi-VideoEditorWorker"

    fun transAudio(inAudioPath: String, outAudioPath: String): Int {
        return VEUtils.transCodeAudio(inAudioPath, outAudioPath, 2, 128 * 1000)
    }

    // todo 做个优化，以视频为主，音频适配视频
    fun mux(inVideoPath: String, inAudioPath: String, outPath: String): Int {
        return VEUtils.mux(inVideoPath, inAudioPath, outPath)
    }

    fun crop(inVideoPath: String, cropInfos: List<CropInfo>): Int {
        cropInfos.run {
            val outPaths = ArrayList<String>(size)
            val stTimes = ArrayList<String>(size)
            val durations = ArrayList<String>(size)
            forEach {
                outPaths.add(it.outPath)
                stTimes.add(it.st.run {
                    String.format("%d:%02d:%02d", this / 3600, (this % 3600) / 60, this % 60)
                })
                durations.add((it.ed - it.st).run {
                    String.format("%d:%02d:%02d", this / 3600, (this % 3600) / 60, this % 60)
                })
            }
            // 代码注释写的有误，是时长
            return VEUtils.curVideo(inVideoPath, outPaths, stTimes, durations)
        }
    }
}