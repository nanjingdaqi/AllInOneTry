package me.ele.base.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Bundle;
import android.os.Build.VERSION;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

public class AppUtil {
  public static final String SP_KEY_APPSTARTTIME = "SP_KEY_APPSTARTTIME";
  public static final String SP_KEY_APPINSTALLTIME = "SP_KEY_APPINSTALLTIME";
  public static final String SP_KEY_APPVERSIONSTARTTIME = "SP_KEY_APPVERSIONSTARTTIME";
  public static final String SP_KEY_APPVERSIONCODE = "SP_KEY_APPVERSIONCODE";

  private AppUtil() {
  }

  public static String getVersionName(Context con) {
    String version = "0.0.0";
    PackageManager packageManager = con.getPackageManager();

    try {
      PackageInfo e = packageManager.getPackageInfo(con.getPackageName(), 0);
      version = e.versionName;
    } catch (NameNotFoundException var4) {
      var4.printStackTrace();
    }

    return version;
  }

  public static Bundle getMetaData(Context context) {
    PackageManager packageManager = context.getPackageManager();

    try {
      ApplicationInfo e = packageManager.getApplicationInfo(context.getPackageName(), 128);
      return e.metaData;
    } catch (NameNotFoundException var3) {
      var3.printStackTrace();
      return null;
    }
  }

  public static int getVersionCode(Context con) {
    int version = 1;
    PackageManager packageManager = con.getPackageManager();

    try {
      PackageInfo e = packageManager.getPackageInfo(con.getPackageName(), 0);
      version = e.versionCode;
    } catch (Exception var4) {
      var4.printStackTrace();
    }

    return version;
  }

  public static String getSystemInfo() {
    return Build.MODEL + " " + VERSION.RELEASE;
  }

  public static synchronized Class<? extends Activity> getCurrentTopActivityClass(Context con) {
    ActivityManager mActivityManager = (ActivityManager) con.getSystemService("activity");
    List runningTask = mActivityManager.getRunningTasks(1);
    RunningTaskInfo ar = (RunningTaskInfo) runningTask.get(0);
    String className = ar.topActivity.getClassName();
    Class clazz = null;

    try {
      clazz = Class.forName(className);
    } catch (Exception var7) {
      var7.printStackTrace();
    }

    return clazz;
  }

  public static synchronized boolean isBackgroundRunning(Context con) {
    ActivityManager activityManager = (ActivityManager) con.getSystemService("activity");
    KeyguardManager keyguardManager = (KeyguardManager) con.getSystemService("keyguard");
    if (activityManager == null) {
      return false;
    } else {
      List processList = activityManager.getRunningAppProcesses();
      Iterator var4 = processList.iterator();

      RunningAppProcessInfo process;
      do {
        if (!var4.hasNext()) {
          return false;
        }

        process = (RunningAppProcessInfo) var4.next();
      } while (!process.processName.equals(con.getPackageName()));

      boolean isBackground = process.importance != 100 && process.importance != 200;
      boolean isLockedState = keyguardManager.inKeyguardRestrictedInputMode();
      return isBackground || isLockedState;
    }
  }

  public static synchronized boolean isForegroundRunning(Context con) {
    return !isBackgroundRunning(con);
  }

  public static boolean isDebug(Context context) {
    Object obj = getBuildConfigValue(context, "DEBUG");
    return obj != null ? ((Boolean) obj).booleanValue() : false;
  }

  public static Object getBuildConfigValue(Context context, String fieldName) {
    try {
      Class e = Class.forName(context.getPackageName() + ".BuildConfig");
      Field field = e.getField(fieldName);
      return field.get((Object) null);
    } catch (ClassNotFoundException var4) {
      var4.printStackTrace();
    } catch (NoSuchFieldException var5) {
      var5.printStackTrace();
    } catch (IllegalAccessException var6) {
      var6.printStackTrace();
    }

    return null;
  }

  public static boolean isAtLeastL() {
    return VERSION.SDK_INT >= 21;
  }

  public static boolean isAtLeastKitkat() {
    return VERSION.SDK_INT >= 19;
  }

  public static boolean isAtLeastM() {
    return VERSION.SDK_INT >= 23;
  }

  public static boolean isAtLeastN() {
    return VERSION.SDK_INT >= 24;
  }
}
