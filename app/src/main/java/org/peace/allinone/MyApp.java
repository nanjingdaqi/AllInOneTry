package org.peace.allinone;

import android.app.Application;
import android.os.Process;
import android.util.Log;
import me.ele.commons.AppLogger;

/**
 * Created by peacepassion on 15/8/11.
 */
public class MyApp extends Application {

  @Override public void onCreate() {
    super.onCreate();

    Log.e("MyApp", "1");

    System.exit(0);
    Process.killProcess(Process.myPid());
  }
}
