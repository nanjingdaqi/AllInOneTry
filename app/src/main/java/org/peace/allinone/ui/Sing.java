package org.peace.allinone.ui;

import android.content.Context;

/**
 * Created by daqi on 17-6-21.
 */

public class Sing {

    Context context;

    static Sing sing;

    public static Sing getInstance(Context context) {
        if (sing == null) {
            sing = new Sing(context);
        }
        return sing;
    }

    public Sing(Context context) {
        this.context = context;
    }
}
