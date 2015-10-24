package org.peace.allinone.ui;

import android.animation.TimeAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import me.ele.commons.AppLogger;

/**
 * Created by peacepassion on 15/10/24.
 */
public class MyScrollView extends LinearLayout {

  public MyScrollView(Context context) {
    super(context);
    init();
  }

  public MyScrollView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {

  }

  float ly;
  VelocityTracker velocityTracker;
  float dFlingSpd = 0.005f; // px/ms
  ValueAnimator flingAnim;

  @Override public boolean onTouchEvent(MotionEvent event) {
    int action = event.getAction();
    if (action == MotionEvent.ACTION_DOWN) {
      if (flingAnim != null) {
        flingAnim.cancel();
      }
      ly = event.getY();
      velocityTracker = VelocityTracker.obtain();
      velocityTracker.addMovement(event);
      return true;
    }
    velocityTracker.addMovement(event);
    if (action == MotionEvent.ACTION_MOVE) {
      for (int i = 0, hz = event.getHistorySize(); i < hz; ++i) {
        //AppLogger.d("hz: " + hz);
        float cy = event.getHistoricalY(i);
        float dy = cy - ly;
        scrollBy(0, -(int) dy);
        ly = cy;
      }
      return true;
    }
    if (action == MotionEvent.ACTION_UP) {
      velocityTracker.computeCurrentVelocity(1);
      float yv = velocityTracker.getYVelocity();
      AppLogger.w("fling speed: " + yv);
      startFlingMove(yv);
      return true;
    }
    return true;
  }

  private void startFlingMove(float yv) {
    final float duration = Math.abs(yv) / dFlingSpd;
    final int csy = getScrollY();
    flingAnim = TimeAnimator.ofFloat(0, 100).setDuration((long) (duration));
    flingAnim.addUpdateListener(anim -> {
      float frac = (float) anim.getAnimatedValue() / 100;
      float t = duration * frac;
      float dy = Math.abs(yv) * t - dFlingSpd * t * t / 2;
      float ty = yv < 0 ? csy + dy : csy -dy;
      AppLogger.d("t: " + t + ", dy: " + dy);
      scrollTo(0, (int) ty);
    });
    flingAnim.start();
  }
}
