package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.ele.base.utils.AppLogger;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);
    AppLogger.e("me: " + this);
    AppLogger.e("app: " + this.getApplication());
    AppLogger.e("me app context: " + getApplicationContext());
    AppLogger.e("app app context: " + getApplication().getApplicationContext());

    AppLogger.e("me wifi: " + getSystemService(WIFI_SERVICE));
    AppLogger.e("app wifi: " + getApplication().getSystemService(WIFI_SERVICE));
  }

  @OnClick(R.id.start_btn) public void onClick(View v) {
  }
}
