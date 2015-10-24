package org.peace.allinone.ui;

import android.animation.TimeAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
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
  GestureDetector gestureDetector =
      new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
        @Override public boolean onDown(MotionEvent e) {
          if (flingAnim != null) {
            flingAnim.cancel();
          }
          return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
          scrollBy(0, (int) distanceY);
          return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
          AppLogger.w("vy: " + velocityY / 1000);
          startFlingMove(
              velocityY > 0 ? Math.max(velocityY / 1000, 3) : -Math.max(-velocityY / 1000, 3));
          return true;
        }
      });

  @Override public boolean onTouchEvent(MotionEvent event) {
    return gestureDetector.onTouchEvent(event);
  }

  private void startFlingMove(float yv) {
    final float duration = Math.abs(yv) / dFlingSpd;
    final int csy = getScrollY();
    flingAnim = TimeAnimator.ofFloat(0, 100).setDuration((long) (duration));
    flingAnim.addUpdateListener(anim -> {
      float frac = (float) anim.getAnimatedValue() / 100;
      float t = duration * frac;
      float dy = Math.abs(yv) * t - dFlingSpd * t * t / 2;
      float ty = yv < 0 ? csy + dy : csy - dy;
      AppLogger.d("t: " + t + ", dy: " + dy);
      scrollTo(0, (int) ty);
    });
    flingAnim.start();
  }
}
