package org.peace.allinone;

import android.app.Activity;
import me.ele.base.utils.AppLogger;

/**
 * Created by daqi on 17-4-8.
 */

public class A {

  public  Activity activity;

  public static A a = new A();

  public int i;

  String str;

  String str2 = "";

  byte[] bytes = new byte[4];

  @Override
  protected void finalize() throws Throwable {
    super.finalize();
    AppLogger.e("finalize");
//    MyApp.doSth(30);
//      activity = null;
    AppLogger.e("finalize done");
  }
}
