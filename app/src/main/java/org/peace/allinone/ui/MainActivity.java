package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  byte[] bytes;
  byte[] bytes2;
  byte[] byte3;
  byte[] bytes4;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);
  }

  @OnClick(R.id.start_btn) public void onClick(View v) {
    if (bytes == null) {
      bytes = new byte[10 * 1024 * 1024];
      return;
    }
    if (bytes2 == null) {
      bytes2 = new byte[10 * 1024 * 1024];
      return;
    }
    if (byte3 == null) {
      byte3 = new byte[200 * 1024 * 1024];
      return;
    }
    if (bytes4 == null) {
      bytes4 = new byte[600 * 1024 * 1024];
      return;
    }
  }
}
