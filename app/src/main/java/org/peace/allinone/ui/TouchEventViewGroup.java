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

  @Override public boolean dispatchTouchEvent(MotionEvent ev) {
    AppLogger.d("dispatch event: " + ev);
    return super.dispatchTouchEvent(ev);
  }

  @Override public boolean onInterceptTouchEvent(MotionEvent ev) {
    AppLogger.d("on intercept event: " + ev);
    if (ev.getAction() == MotionEvent.ACTION_MOVE) {
      return true;
    }
    return false;
  }

  @Override public boolean onTouchEvent(MotionEvent event) {
    AppLogger.d("onTouch event: " + event);
    int action = event.getAction();
    if (MotionEvent.ACTION_DOWN == action) {
      return false;
    }
    return false;
  }
}
