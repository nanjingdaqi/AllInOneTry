package org.peace.allinone.ui;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.ele.base.utils.AppLogger;
import org.peace.allinone.MyService;
import org.peace.allinone.R;
import org.peace.allinone.Test;

public class MainActivity extends AppCompatActivity {

  Test test;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

    bindService(new Intent(this, MyService.class), new ServiceConnection() {
      @Override public void onServiceConnected(ComponentName name, IBinder service) {
        AppLogger.e("on service connected");
        test = Test.Stub.asInterface(service);
      }

      @Override public void onServiceDisconnected(ComponentName name) {

      }
    }, BIND_AUTO_CREATE);
  }

  @OnClick(R.id.start_btn) public void onClick(View v) {
    if (test != null) {
      try {
        test.foo("hello");
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    }
  }
}
