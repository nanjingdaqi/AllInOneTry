package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewConfiguration;
import butterknife.ButterKnife;
import me.ele.commons.AppLogger;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);

    int slop = ViewConfiguration.get(this).getScaledTouchSlop();
    AppLogger.d("touch slop: " + slop);
  }
}
