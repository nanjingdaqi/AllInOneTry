package org.peace.allinone.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.squareup.okhttp.Interceptor;
import me.ele.commons.AppLogger;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @InjectView(R.id.target) View target;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);
  }

  @OnClick({ R.id.start_btn }) public void onStartBtn(View v) {
    target.setVisibility(View.VISIBLE);
    target.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
      @Override public void onGlobalLayout() {
        //target.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        ObjectAnimator anim = ObjectAnimator.ofFloat(target, "translationY", 0f, 300f);
        anim.setDuration(4000);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
          @Override public void onAnimationUpdate(ValueAnimator animation) {
            int layerType = target.getLayerType();
            AppLogger.e("onUpdate layerT: " + getLayerType(layerType));
            AppLogger.e("ishardware open: " + target.isHardwareAccelerated());
          }
        });
        anim.addListener(new AnimatorListenerAdapter() {
          @Override public void onAnimationEnd(Animator animation) {
            target.setLayerType(View.LAYER_TYPE_NONE, null);
          }
        });
        anim.start();

        target.getViewTreeObserver().removeGlobalOnLayoutListener(this);
      }
    });
  }

  @OnClick(R.id.second_btn) public void onSecondBtn(View v) {
    target.animate().translationXBy(-300).setDuration(4000).start();
  }

  String getLayerType(int type) {
    switch (type) {
      case View.LAYER_TYPE_NONE:
        return "none";
      case View.LAYER_TYPE_SOFTWARE:
        return "soft";
      case View.LAYER_TYPE_HARDWARE:
        return "hard";
    }
    return "";
  }
}
