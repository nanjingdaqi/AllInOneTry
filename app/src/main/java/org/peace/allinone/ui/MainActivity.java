package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import me.ele.commons.AppLogger;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @InjectView(R.id.start_btn) Button mStartBtn;
  @InjectView(R.id.container) ViewGroup container;

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

    container.post(new Runnable() {
      @Override public void run() {
        LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_main, container, true);
        container.findViewById(R.id.start_btn)
            .addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
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
