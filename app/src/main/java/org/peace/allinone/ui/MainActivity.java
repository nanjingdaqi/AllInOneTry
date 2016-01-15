package org.peace.allinone.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.ele.commons.AppLogger;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);
  }

  @Override protected void onResume() {
    super.onResume();
    AppLogger.e("main onresume");
  }

  @Override protected void onPostResume() {
    super.onPostResume();
    AppLogger.e("main onpostresume");
  }

  @OnClick({ R.id.start_btn }) public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.start_btn) {
      startActivity(new Intent(this, SubActivity.class));
    }
  }
}
