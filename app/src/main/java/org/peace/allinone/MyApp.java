package org.peace.allinone;

import android.app.Activity;
import android.app.Application;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.util.LongSparseArray;
import com.squareup.leakcanary.HeapDump;
import com.squareup.leakcanary.LeakCanary;
import java.lang.reflect.Field;
import me.ele.base.utils.AppLogger;
import me.ele.base.utils.DimenUtil;
import me.ele.base.utils.ResourceUtil;

import static com.google.gson.reflect.TypeToken.get;

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

    getSystemService(CONNECTIVITY_SERVICE);

    try {
      Field field = Resources.class.getDeclaredField("sPreloadedDrawables");
      field.setAccessible(true);
      LongSparseArray<Object>[] sPreloadedDrawables = (LongSparseArray<Object>[]) field.get(Resources.class);
      AppLogger.e("sPreloaded size: " + sPreloadedDrawables.length);
      AppLogger.e("sPreloaded 0: " + sPreloadedDrawables[0].size());
      AppLogger.e("sPreloaded 1: " + sPreloadedDrawables[1].size());
      sPreloadedDrawables[0].clear();
      sPreloadedDrawables[1].clear();
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  private void installLeakCanary() {
    if (LeakCanary.isInAnalyzerProcess(this)) {
      return;
    }

    LeakCanary.refWatcher(this)
        .listenerServiceClass(SettingsLeakService.class)
        .maxStoredHeapDumps(1000)
        .excludedRefs(null)
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
