package org.peace.allinone.ui;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dalvik.system.BaseDexClassLoader;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import me.ele.commons.AppLogger;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);

    String javaLibraryPath = System.getProperty("java.library.path");
    AppLogger.e("java lib path: " + javaLibraryPath);

    BaseDexClassLoader classLoader = (BaseDexClassLoader) getClassLoader();
    try {
      Method method = BaseDexClassLoader.class.getMethod("getLdLibraryPath");
      String path = (String) method.invoke(classLoader);
      AppLogger.e("lib path: " + path);

      ApplicationInfo applicationInfo = getApplicationInfo();
      AppLogger.e("app info lib path: " + applicationInfo.nativeLibraryDir);
      AppLogger.e("app info app dir: " + applicationInfo.sourceDir);
      //Field field = ApplicationInfo.class.getDeclaredField("primaryCpuAbi");
      //field.setAccessible(true);
      //String primaryCpuAbi = (String) field.get(applicationInfo);
      //AppLogger.e("app info primary abi: " + primaryCpuAbi);
      //
      //field = Application.class.getDeclaredField("mLoadedApk");
      //field.setAccessible(true);
      //Object loadedApk = field.get(getApplication());

      File dir = getDir("lib2", MODE_PRIVATE);
      AppLogger.e("lib dir: " + dir.getAbsolutePath());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @OnClick(R.id.start_btn) public void onClick(View v) {

  }
}
