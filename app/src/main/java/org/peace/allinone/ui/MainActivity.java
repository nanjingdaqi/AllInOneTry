package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.Locale;
import me.ele.base.utils.AppLogger;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);
  }

  @OnClick(R.id.start_btn) public void onClick(View v) {
    Locale l1 = new Locale("zh", "cn");
    AppLogger.e("l1: " + l1);
    Locale l2 = new Locale("zh_CN");
    AppLogger.e("l2: " + l2);
    Locale l3 = new Locale(l1.toString());
    AppLogger.e("l3: " + l3);
  }
}
