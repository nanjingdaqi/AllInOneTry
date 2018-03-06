package org.peace.allinone;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class MyService extends Service {

    Handler handler = new Handler(Looper.getMainLooper());

  Test test = new Test.Stub() {

    @Override public void foo(String arg) throws RemoteException {
        final long uid = Binder.getCallingUid();
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MyService.this, "server test get called by uid: " + uid, Toast.LENGTH_LONG).show();
            }
        });
    }
  };

  @Nullable @Override public IBinder onBind(Intent intent) {
    return test.asBinder();
  }
}
