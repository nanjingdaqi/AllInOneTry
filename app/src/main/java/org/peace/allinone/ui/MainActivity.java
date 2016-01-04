package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @InjectView(R.id.start_btn) Button mStartBtn;

  @InjectView(R.id.ll1) ViewGroup ll1;
  @InjectView(R.id.one) View one;
  @InjectView(R.id.two) View two;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);
  }

  @OnClick({ R.id.start_btn }) public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.start_btn) {
      //doFrame();
      ll1.post(new Runnable() {
        @Override public void run() {
          doFrame();
        }
      });
    }
  }

  void doFrame() {
    post(new Runnable() {
      @Override public void run() {
        ll1.removeAllViews();
      }
    });
    post(new Runnable() {
      @Override public void run() {
        ll1.addView(one);
        ll1.addView(two);
      }
    });
  }

  void sleep(long mill) {
    try {
      Thread.sleep(mill);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  void post(Runnable runnable) {
    ll1.post(runnable);
  }
}
