package org.peace.allinone;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import com.alipay.euler.andfix.patch.PatchManager;
import com.github.mmin18.layoutcast.LayoutCast;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import me.ele.commons.AppLogger;

import java.io.IOException;

/**
 * Created by peacepassion on 15/8/11.
 */
public class MyApp extends Application {

  private RefWatcher refWatcher;

  @Override public void onCreate() {
    super.onCreate();
    AppLogger.debug = true;
    refWatcher = LeakCanary.install(this);

    PatchManager patchManager = new PatchManager(this);
    patchManager.init(BuildConfig.VERSION_NAME);
    patchManager.loadPatch();

    String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/out.patch";
    try {
      patchManager.addPatch(path);
    } catch (IOException e) {
      AppLogger.e("no patch file");
    }

    if (BuildConfig.DEBUG) {
      LayoutCast.init(this);
    }
  }

  public static RefWatcher refWatcher(Context context) {
    MyApp app = (MyApp) context.getApplicationContext();
    return app.refWatcher;
  }
}
