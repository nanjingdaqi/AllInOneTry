package org.peace.allinone.ui;

import android.util.Log;

/**
 * Created by daqi on 17-6-12.
 */

public class MyException extends Exception {
    static {
        Log.e("Peace", "MyException cinit");
    }
}
