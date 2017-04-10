package org.peace.allinone;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Environment;
import com.squareup.leakcanary.HeapDump;
import com.squareup.leakcanary.LeakCanary;
import me.ele.base.utils.AppLogger;
import me.ele.base.utils.DimenUtil;
import me.ele.base.utils.ResourceUtil;

/**
 * Created by peacepassion on 15/8/11.
 */
public class MyApp extends Application {

  @Override public void onCreate() {
    super.onCreate();

    installLeakCanary();

    AppLogger.e("dir: " + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));

    AppLogger.debug = true;
    DimenUtil.init(this);
    ResourceUtil.init(this);
    registerActivityLifecycleCallbacks(new MyCallback());
  }

  private void installLeakCanary() {
    if (LeakCanary.isInAnalyzerProcess(this)) {
      return;
    }

    LeakCanary.refWatcher(this)
        .listenerServiceClass(SettingsLeakService.class)
        .maxStoredHeapDumps(100)
        .buildAndInstall();
  }

  public static class MyCallback implements ActivityLifecycleCallbacks {

    @Override public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
      //AppLogger.e("activity obj: " + Long.toHexString(ObjectAddress.oopAddress(activity)));
    }

    @Override public void onActivityStarted(Activity activity) {

    }

    @Override public void onActivityResumed(Activity activity) {

    }

    @Override public void onActivityPaused(Activity activity) {

    }

    @Override public void onActivityStopped(Activity activity) {

    }

    @Override public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override public void onActivityDestroyed(Activity activity) {

    }
  }
}
