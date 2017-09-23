package org.peace.allinone.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.OnClick;
import org.peace.allinone.MyService;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity implements ServiceConnection {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);
  }

  @OnClick(R.id.start_btn) public void onClick(View v) {
    Intent intent = new Intent(this, MyService.class);
//    startService(intent);

    bindService(intent, this, BIND_AUTO_CREATE);
  }

  @OnClick(R.id.stop_btn) public void onStop(View v) {
//    stopService(new Intent(this, MyService.class));
      unbindService(this);
  }

  @Override
  public void onServiceConnected(ComponentName name, IBinder service) {
    Toast.makeText(this, "Connected", Toast.LENGTH_LONG).show();
  }

  @Override
  public void onServiceDisconnected(ComponentName name) {
    Toast.makeText(this, "DisConnected", Toast.LENGTH_LONG).show();
  }
}
