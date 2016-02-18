package org.peace.allinone.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import me.ele.commons.AppLogger;

public class MyView extends View {
  public MyView(Context context) {
    super(context);
  }

  public MyView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override public boolean onTouchEvent(MotionEvent event) {
    int motion = event.getActionMasked();
    int pointer = event.getActionIndex();
    AppLogger.d("motion: " + trans(motion) + ", pointer: " + pointer);

    return super.onTouchEvent(event);
  }

  public String trans(int event) {
    String s = "unknown";
    if (event == MotionEvent.ACTION_DOWN) {
      s = "action_down";
    } else if (event == MotionEvent.ACTION_MOVE) {
      s = "action_move";
    } else if (event == MotionEvent.ACTION_CANCEL) {
      s = "action_cancel";
    } else if (event == MotionEvent.ACTION_UP) {
      s = "action_up";
    } else if (event == MotionEvent.ACTION_POINTER_DOWN) {
      s = "action_pointer_down";
    } else if (event == MotionEvent.ACTION_POINTER_UP) {
      s = "action_pointer_up";
    }
    return s;
  }
}
