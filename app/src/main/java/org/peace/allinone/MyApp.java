package org.peace.allinone;

import android.app.Activity;
import android.app.Application;
import me.ele.base.utils.AppLogger;
import me.ele.base.utils.DimenUtil;
import me.ele.base.utils.ResourceUtil;
import android.app.Instrumentation;
import android.content.Context;
import com.facebook.stetho.DumperPluginsProvider;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.dumpapp.DumperPlugin;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import me.ele.commons.AppLogger;
import org.peace.allinone.ui.MyInstrumentation;
import org.shikato.infodumper.InfoDumperPlugin;

/**
 * Created by peacepassion on 15/8/11.
 */
public class MyApp extends Application {

  @Override public void onCreate() {
    super.onCreate();
    AppLogger.debug = true;
    DimenUtil.init(this);
    ResourceUtil.init(this);
    refWatcher = LeakCanary.install(this);

    if (BuildConfig.DEBUG) {
      Stetho.initialize(Stetho.newInitializerBuilder(this)
          .enableDumpapp(new MyDumperPluginsProvider(this))
          .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
          .build());
    }

    //hookInstrumentation();
  }

  private void hookInstrumentation() {
    try {
      final Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
      final Method method = activityThreadClass.getMethod("currentActivityThread");
      Object thread = method.invoke(null, (Object[]) null);
      Field field = activityThreadClass.getDeclaredField("mInstrumentation");
      field.setAccessible(true);
      Instrumentation wrapper = new MyInstrumentation();
      field.set(thread, wrapper);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static RefWatcher refWatcher(Context context) {
    MyApp app = (MyApp) context.getApplicationContext();
    return app.refWatcher;
  }

  private static class MyDumperPluginsProvider implements DumperPluginsProvider {
    private final Context mContext;

    public MyDumperPluginsProvider(Context context) {
      mContext = context;
    }

    @Override public Iterable<DumperPlugin> get() {
      ArrayList<DumperPlugin> plugins = new ArrayList<DumperPlugin>();
      for (DumperPlugin defaultPlugin : Stetho.defaultDumperPluginsProvider(mContext).get()) {
        plugins.add(defaultPlugin);
      }
      // Add InfoDumperPlugin
      plugins.add(new InfoDumperPlugin(mContext));
      return plugins;
    }
  }
}
