package org.peace.allinone.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import me.ele.base.utils.AppLogger;
import org.peace.allinone.R;

public class SubActivity extends AppCompatActivity {

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

      AppLogger.e("on create");

    ButterKnife.bind(this);
  }

  @Override
  protected void onNewIntent(Intent intent) {
    super.onNewIntent(intent);
    AppLogger.e("on new Intent");
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    AppLogger.e("sub destroy");
  }
}
