package org.peace.allinone;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import me.ele.base.utils.AppLogger;
import org.peace.allinone.ui.MainActivity;

/**
 * Created by daqi on 17-4-8.
 */

public class MyService extends Service {

  public static class FooImpl extends IFoo.Stub {

    Parcelable p;

    @Override
    public int doSth() throws RemoteException {
      return 100;
    }

    @Override
    public void setP(Messenger p) throws RemoteException {
        this.p = p;
    }
  }

  public FooImpl foo = new FooImpl();

  @Nullable @Override public IBinder onBind(Intent intent) {
    Messenger messenger = intent.getParcelableExtra("messenger");
    try {
        MyApp.doSth(1);
      messenger.send(Message.obtain());
    } catch (RemoteException e) {
      e.printStackTrace();
    }
    return foo.asBinder();
  }

  @Override
  public boolean onUnbind(Intent intent) {
    AppLogger.e("onUnbind");
    return super.onUnbind(intent);
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
