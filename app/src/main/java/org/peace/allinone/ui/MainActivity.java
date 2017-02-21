package org.peace.allinone.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.List;
import org.peace.allinone.IFoo;
import org.peace.allinone.R;
import org.peace.allinone.service.FooService;

public class MainActivity extends AppCompatActivity {

  IFoo iFoo;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

    bindService(new Intent(this, FooService.class), new ServiceConnection() {
      @Override public void onServiceConnected(ComponentName name, IBinder service) {
        iFoo = IFoo.Stub.asInterface(service);
        try {
          arr = iFoo.foo();
        } catch (RemoteException e) {
          e.printStackTrace();
        }
      }

      @Override public void onServiceDisconnected(ComponentName name) {

      }
    }, BIND_AUTO_CREATE);
  }

  @OnClick(R.id.start_btn) public void onClick(View v)  {
    try {
      iFoo.add();
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  List<String> arr = null;

  @OnClick(R.id.show) public void show(View v)  {
    //try {
    //  arr = iFoo.foo();
    //} catch (RemoteException e) {
    //  e.printStackTrace();
    //}
    Toast.makeText(this, build(arr), Toast.LENGTH_SHORT).show();
  }

  static String build(List<String> arrs) {
    String ret = "";
    for (String arr : arrs) {
      ret += arr;
    }
    return ret;
  }

}
