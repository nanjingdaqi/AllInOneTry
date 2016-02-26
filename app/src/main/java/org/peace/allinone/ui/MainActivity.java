package org.peace.allinone.ui;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import me.ele.commons.AppLogger;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);
  }

  @OnClick({ R.id.start_btn }) public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.start_btn) {
      new Thread(new Runnable() {
        @Override public void run() {
          AppLogger.e("post t: " + System.currentTimeMillis());
          for (int i = 0; i < 10; i++) {
            ViewCompat.postOnAnimation(mStartBtn, new Task());
            try {
              Thread.sleep(18);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        }
      }).start();
    }
  }

  static class Task implements Runnable {

    @Override public void run() {
      AppLogger.e("t: " + System.currentTimeMillis());
    }
  }
}
