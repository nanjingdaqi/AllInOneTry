package org.peace.allinone.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
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
    return super.dispatchTouchEvent(ev);
  }

  float lx, ly;

  @Override public boolean onTouchEvent(MotionEvent event) {
    AppLogger.w("onTouch event: " + event);
    int action = event.getAction();
    float x = event.getRawY(), y = event.getRawY();
    if (MotionEvent.ACTION_DOWN == action) {
      lx = x;
      ly = y;
      return true;
    }
    if (MotionEvent.ACTION_MOVE == action) {
      float dx = x - lx, dy = y - ly;
      if (Math.hypot(dx, dy) < 3) {
        return false;
      }
      setTranslationY(getTranslationY() + dy);
      ly = y;
    }
    return true;
  }
}
