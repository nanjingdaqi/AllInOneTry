package org.peace.allinone;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class MyService extends Service {

  Test test = new Test.Stub() {

    @Override public void foo(String arg) throws RemoteException {
      Toast.makeText(MyService.this, "server test get called", Toast.LENGTH_LONG).show();
    }
  };

  @Nullable @Override public IBinder onBind(Intent intent) {
    return test.asBinder();
  }
}
