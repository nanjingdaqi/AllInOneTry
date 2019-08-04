package com.bytedance.ttgame.module.screenrecord

import android.util.Log
import com.ss.android.vesdk.VEUtils
import java.io.File
import kotlin.math.max
import kotlin.math.min
import java.util.ArrayList

object Util {

    public var logLevel = 1

    // debug log
    fun logd(tag: String, msg: String) {
        if (logLevel <= 1) Log.d(tag, msg)
    }

    // key log
    fun logk(tag: String, msg: String, e: Throwable? = null) {
        if (logLevel <= 3) if (e == null) Log.w(tag, msg) else Log.w(tag, msg, e)
    }

    fun loge(tag: String, msg: String, e: Throwable? = null) {
        if (e == null) Log.e(tag, msg) else Log.e(tag, msg, e)
    }

    // 向下对齐
    fun alignDown(i: Int, factor: Int): Int {
        return (i / factor) * factor
    }

    // todo
    fun removeDir(dir: String) {
        // 定义文件路径
        val file = File(dir)
        // 判断是文件还是目录
        if (file.exists() && file.isDirectory) {
            val subs = file.listFiles()
            val length = subs.size
            for (i in 0 until length) {
                if (subs[i].isDirectory) {
                    removeDir(subs[i].absolutePath)
                } else {
                    subs[i].delete()
                }
            }
            file.delete()
        }
    }

    fun buildCropInfos(firstTimeStampUs: Long, durationBefore: Int,
                       durationAfter: Int, cropDir: File,
                       moments: List<KeyMoment>, muxedVideoInfo: MuxedVideoInfo): ArrayList<CropInfo> {
        return ArrayList<CropInfo>().apply {
            val org = moments.map {
                val pos = it.timeStampUs - firstTimeStampUs
                val videoDur = muxedVideoInfo.durationTimeMill
                CropInfo(st = max(pos - durationBefore * 1000000, 0) / 1000000,
                        ed= min(pos + durationAfter * 1000000, videoDur * 1000) / 1000000,
                        outPath = File(cropDir, "${it.timeStampUs}.mp4").absolutePath,
                        priority = it.priority)
            }
            var i = 0
            var cur: CropInfo? = null
            while (i < org.size) {
                if (cur == null) {
                    cur = org[i]
                }
                if (i + 1 >= org.size) {
                    add(cur)
                } else {
                    val next = org[i + 1]
                    if (cur.ed >= next.st) {
                        cur = mergeTwoCropInfo(cur, next)
                    } else {
                        add(cur)
                        cur = null
                    }
                }
                i++
            }
        }
    }

    private fun mergeTwoCropInfo(left: CropInfo, right: CropInfo): CropInfo {
        return CropInfo(st = left.st,
                ed = right.ed,
                outPath = if (left.priority < right.priority) right.outPath else left.outPath,
                priority = max(left.priority, right.priority))
    }

    fun resolveMuxedVideoInfo(firstTimeStampUs: Long, filePath: String): MuxedVideoInfo {
        IntArray(11).apply {
            VEUtils.getVideoFileInfo(filePath, this)
        }.run {
            // todo: check duration time unit
            if (VideoManager.DEBUG) {
                Log.w(VideoManager.TAG, "video info: ${map { it.toString() }}")
            }
            return MuxedVideoInfo(firstTimeStampUs, this[3].toLong())
        }
    }
}