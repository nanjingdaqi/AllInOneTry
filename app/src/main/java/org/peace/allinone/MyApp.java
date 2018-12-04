package org.peace.allinone;

import android.app.Application;

/**
 * Created by peacepassion on 15/8/11.
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static void doSth(long tm) {
        long t = System.currentTimeMillis();
        while (System.currentTimeMillis() - t < tm * 1000) {

        }
    }
}
