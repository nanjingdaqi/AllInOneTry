package org.peace.allinone;

import android.util.Log;
import me.ele.base.utils.AppLogger;

/**
 * Created by daqi on 17-7-9.
 */

public class Daqi {

    static {
        AppLogger.e("Daqi CLInit");
    }

    public String msg = "Daqi";

    public Daqi() {
        Log.e("peace", "daqi init");
    }
}
