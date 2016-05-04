package org.peace.allinone.ui;

import android.animation.ValueAnimator;
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
  @InjectView(R.id.my_view) View myView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);
  }

  @OnClick({ R.id.start_btn }) public void onClick(View v) {
    ValueAnimator anim = ValueAnimator.ofFloat(0, 100);
    anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override public void onAnimationUpdate(ValueAnimator animation) {
        float val = (float) animation.getAnimatedValue();
        myView.setTranslationY(val);
        myView.requestLayout();
      }
    });
    anim.setDuration(2000).start();
  }

  static class Task implements Runnable {

    @Override public void run() {
      AppLogger.e("t: " + System.currentTimeMillis());
    }
  }
}
