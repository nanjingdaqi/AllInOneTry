package org.peace.allinone.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import me.ele.commons.AppLogger;

/**
 * Created by peacepassion on 15/10/24.
 */
public class TouchEventViewGroup2 extends ViewGroup {
  public TouchEventViewGroup2(Context context) {
    super(context);
  }

  public TouchEventViewGroup2(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public TouchEventViewGroup2(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {

  }

  @Override public boolean dispatchTouchEvent(MotionEvent ev) {
    AppLogger.w("dispatch event: " + ev);
    int action = ev.getAction();
    float x = ev.getRawX(), y = ev.getRawY();
    switch (action) {
      case MotionEvent.ACTION_DOWN:
        downX = x;
        downY = y;
        mine = false;
        getParent().requestDisallowInterceptTouchEvent(true);
        break;
      case MotionEvent.ACTION_MOVE:
        if (mine) {
          break;
        }
        if (Math.hypot(x - downX, y - downY) < 3) {
          return false;
        }
        if (Math.abs(x - downX) > Math.abs(y - downY)) {
          getParent().requestDisallowInterceptTouchEvent(false);
          return false;
        }
        mine = true;
        break;
      default:
        break;
    }
    return super.dispatchTouchEvent(ev);
  }

  float downX, downY;
  float lx, ly;
  boolean mine;

  @Override public boolean onTouchEvent(MotionEvent event) {
    AppLogger.w("onTouch event: " + event);
    int action = event.getAction();
    float y = event.getRawY();
    switch (action) {
      case MotionEvent.ACTION_MOVE:
        float dy = y - ly;
        setY(getY() + dy);
        break;
      default:
        break;
    }
    ly = y;
    return true;
  }
}
