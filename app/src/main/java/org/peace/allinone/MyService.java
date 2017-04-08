package org.peace.allinone;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import me.ele.base.utils.AppLogger;

/**
 * Created by daqi on 17-4-8.
 */

public class MyService extends Service {
  @Nullable @Override public IBinder onBind(Intent intent) {
    return null;
  }

  @Override public void onCreate() {
    super.onCreate();
    AppLogger.e("onCreate");
  }

  Thread thread = new Thread(new Runnable() {
    @Override public void run() {
      job(20);
      AppLogger.e("job finish");
      stopSelf();
    }
  });

  @Override public int onStartCommand(Intent intent, int flags, int startId) {
    thread.start();
    return super.onStartCommand(intent, flags, startId);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    AppLogger.e("onDestroy");
  }

  public void job(int s) {
    try {
      Thread.sleep(s * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
