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
    if (ev.getAction() == MotionEvent.ACTION_DOWN) {
      lx = ev.getRawX();
      return false;
    }
    return true;
  }

  @Override public boolean onTouchEvent(MotionEvent event) {
    AppLogger.d("onTouch event: " + event);
    int action = event.getAction();
    float x = event.getRawX();
    switch (action) {
      case MotionEvent.ACTION_MOVE:
        float dx = x - lx;
        setX(getX() + dx);
        break;
      default:
        break;
    }
    lx = x;
    return true;
  }
}
