package daqi.app_sr.recorder

import android.util.Log

object Util {

    public var level = 1

    // debug log
    fun logd(tag: String, msg: String) {
        if (level >= 3) Log.d(tag, msg)
    }

    // key log
    fun logk(tag: String, msg: String) {
        if (level >= 1) Log.w(tag, msg)
    }
}