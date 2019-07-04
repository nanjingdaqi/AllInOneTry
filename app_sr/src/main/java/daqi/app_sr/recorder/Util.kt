package daqi.app_sr.recorder

import android.util.Log

object Util {

    public var logLevel = 1

    // debug log
    fun logd(tag: String, msg: String) {
        if (logLevel >= 3) Log.d(tag, msg)
    }

    // key log
    fun logk(tag: String, msg: String) {
        if (logLevel >= 1) Log.w(tag, msg)
    }
}