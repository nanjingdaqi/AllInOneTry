package org.peace.allinone;

import android.app.Application;
import android.content.Context;
import com.facebook.stetho.DumperPluginsProvider;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.dumpapp.DumperPlugin;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import java.util.ArrayList;
import me.ele.commons.AppLogger;

/**
 * Created by peacepassion on 15/8/11.
 */
public class MyApp extends Application {

  @Override public void onCreate() {
    super.onCreate();
    AppLogger.debug = true;

    if (BuildConfig.DEBUG) {
      Stetho.initialize(Stetho.newInitializerBuilder(this)
          .enableDumpapp(new MyDumperPluginsProvider(this))
          .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
          .build());
    }
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
      return plugins;
    }
  }
}
