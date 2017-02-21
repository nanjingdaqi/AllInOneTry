package org.peace.allinone.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import org.peace.allinone.IFoo;

/**
 * Created by daqi on 17-2-21.
 */

public class FooService extends Service {

  @Nullable @Override public IBinder onBind(Intent intent) {
    return new Foo();
  }

  public static class Foo extends IFoo.Stub {

    List<String> arr = new ArrayList<>();

    public Foo() {
      arr.add("hello");
      arr.add("world");
    }

    @Override public List<String> foo() throws RemoteException {
      return arr;
    }

    @Override public void add() throws RemoteException {
      //arr.add("daqi");
      arr.clear();
    }
  }
}
