package org.peace.allinone;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import me.ele.base.utils.AppLogger;
import me.ele.base.utils.DimenUtil;
import me.ele.base.utils.ResourceUtil;

import java.lang.reflect.Method;

/**
 * Created by peacepassion on 15/8/11.
 */
public class MyApp extends Application {

  @Override
  protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);
    Log.d("daqi", "attach" + showAppGlobals());
  }

  @Override public void onCreate() {
    Log.d("daqi", "onCreate" + showAppGlobals());
    super.onCreate();
    AppLogger.debug = true;
    DimenUtil.init(this);
    ResourceUtil.init(this);
  }

  Object showAppGlobals() {
    try {
        Class clz = Class.forName("android.app.AppGlobals");
        Method mtd = clz.getDeclaredMethod("getInitialApplication");
        Object obj = mtd.invoke(clz);
        return obj;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
