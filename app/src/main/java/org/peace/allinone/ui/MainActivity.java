package org.peace.allinone.ui;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.ele.base.utils.AppLogger;
import org.peace.allinone.MyReceiver;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

    MyReceiver receiver = new MyReceiver();
    receiver.value = "MainActivity";
    AppLogger.e("receiver: " + receiver.hashCode());
    registerReceiver(receiver, new IntentFilter("peace"));
  }

  @OnClick(R.id.start_btn) public void onClick(View v) {
    Intent intent = new Intent("peace");
    //intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
    sendBroadcast(intent);
  }
}
