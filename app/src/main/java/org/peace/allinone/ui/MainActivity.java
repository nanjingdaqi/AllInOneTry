package org.peace.allinone.ui;

import android.animation.ValueAnimator;
import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
  @InjectView(R.id.my_view) View myView;

  int offset = 0;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);
  }

  @OnClick({ R.id.start_btn }) public void onClick(View v) {
    ValueAnimator anim = ValueAnimator.ofInt(0, 1000);
    anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override public void onAnimationUpdate(ValueAnimator animation) {
        int val = (int) animation.getAnimatedValue();
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) myView.getLayoutParams();
        lp.bottomMargin = -val;
        myView.setLayoutParams(lp);
      }
    });
    anim.setDuration(10000).start();
  }

  static class Task implements Runnable {

    @Override public void run() {
      AppLogger.e("t: " + System.currentTimeMillis());
    }
  }
}
