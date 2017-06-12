package org.peace.allinone.ui;

import android.util.Log;

/**
 * Created by daqi on 17-6-2.
 */

public class A {
    private static int i = 0;

    static {
        Log.e("Peace", "A cinit");
    }

    public void foo() throws MyException {
        synchronized (this) {
            i++;
        }
        if ( hashCode() > 0) {
            throw new MyException();
        }
    }
}
