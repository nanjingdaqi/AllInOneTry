package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import me.ele.commons.AppLogger;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @InjectView(R.id.start_btn) Button mStartBtn;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);

    mStartBtn.post(new Runnable() {
      @Override public void run() {
        mStartBtn.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
          @Override public void onViewAttachedToWindow(View v) {
            AppLogger.d("attach");
          }

          @Override public void onViewDetachedFromWindow(View v) {
            AppLogger.d("detach");
          }
        });
      }
    });
  }

  @OnClick({ R.id.start_btn }) public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.start_btn) {

    }
  }
}
