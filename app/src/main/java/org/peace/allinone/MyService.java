package org.peace.allinone;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;
import me.ele.base.utils.AppLogger;
import org.peace.allinone.ui.MainActivity;

/**
 * Created by daqi on 17-4-8.
 */

public class MyService extends Service {

  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    Log.d("daqi", "onBind");
    return new Binder();
  }

  @Override
  public boolean onUnbind(Intent intent) {
    Log.d("daqi", "onUnbind");
    return super.onUnbind(intent);
  }

  @Override
  public void onCreate() {
    super.onCreate();
    Log.d("daqi", "onCreate");
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    Log.d("daqi", "onStartCmd intent: " + intent);
    Handler handler = new Handler(Looper.getMainLooper());
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
//          Log.d("daqi", "stop self");
//          stopSelf();
      }
    }, 60 * 1000);
    return START_STICKY;
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    Log.d("daqi", "onDestroy");
  }

}
