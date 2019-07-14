package com.bytedance.ttgame.module.screenrecord

import android.util.Log

object Util {

    public var logLevel = 1

    // debug log
    fun logd(tag: String, msg: String) {
        if (logLevel <= 1) Log.d(tag, msg)
    }

    // key log
    fun logk(tag: String, msg: String) {
        if (logLevel <= 3) Log.w(tag, msg)
    }

    // 向下对齐
    fun alignDown(i: Int, factor: Int): Int {
        return (i / factor) * factor
    }
}