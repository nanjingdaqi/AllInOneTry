package org.peace.allinone;

import android.app.Application;
import me.ele.base.utils.AppLogger;
import me.ele.base.utils.DimenUtil;
import me.ele.base.utils.ResourceUtil;

/**
 * Created by peacepassion on 15/8/11.
 */
public class MyApp extends Application {

  @Override public void onCreate() {
    super.onCreate();
    AppLogger.debug = true;
    DimenUtil.init(this);
    ResourceUtil.init(this);

//    doSth(5);
  }

  public static void doSth(long d) {
    long tm = System.currentTimeMillis();
    while (System.currentTimeMillis() - tm < d * 1000) {

    }
  }
}
