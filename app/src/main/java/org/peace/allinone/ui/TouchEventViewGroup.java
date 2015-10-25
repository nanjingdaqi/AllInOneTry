package org.peace.allinone.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import me.ele.commons.AppLogger;

/**
 * Created by peacepassion on 15/10/24.
 */
public class TouchEventViewGroup extends FrameLayout {

  public TouchEventViewGroup(Context context) {
    super(context);
  }

  public TouchEventViewGroup(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public TouchEventViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  float lx, ly;
  float downX, downY;
  boolean notMine;

  @Override public boolean dispatchTouchEvent(MotionEvent ev) {
    AppLogger.d("dispatch event: " + ev);
    return super.dispatchTouchEvent(ev);
  }

  @Override public boolean onInterceptTouchEvent(MotionEvent ev) {
    AppLogger.d("on intercept event: " + ev);
    float x = ev.getRawX(), y = ev.getRawY();
    lx = x;
    if (ev.getAction() == MotionEvent.ACTION_DOWN) {
      downX = x;
      downY = y;
      notMine = false;
      return false;
    }

    if (notMine) {
      return false;
    }

    if (Math.hypot(x - downX, y - downY) < 3) {
      return false;
    }

    if (ev.getAction() == MotionEvent.ACTION_MOVE) {
      if (Math.abs(x - downX) >= Math.abs(y - downY)) {
        return true;
      }
      notMine = true;
    }
    return false;
  }

  @Override public boolean onTouchEvent(MotionEvent event) {
    AppLogger.d("onTouch event: " + event);
    boolean res = false;
    int action = event.getAction();
    float x = event.getRawX();
    AppLogger.d("x: " + x);
    if (MotionEvent.ACTION_DOWN == action) {
      res = true;
    } else if (MotionEvent.ACTION_MOVE == action) {
      float dx = x - lx;
      setTranslationX(getTranslationX() + dx);
      res = true;
    }
    lx = x;
    return res;
  }
}
