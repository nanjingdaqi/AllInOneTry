package com.bytedance.ttgame.module.screenrecord

import android.util.Log
import java.io.File

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
}