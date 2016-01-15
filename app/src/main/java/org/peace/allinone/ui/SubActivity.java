package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import me.ele.commons.AppLogger;
import org.peace.allinone.R;

public class SubActivity extends AppCompatActivity {

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    AppLogger.e("sub destroy");
  }
}
