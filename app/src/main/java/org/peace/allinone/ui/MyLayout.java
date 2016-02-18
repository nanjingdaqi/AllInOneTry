package org.peace.allinone.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MyLayout extends LinearLayout {
  public MyLayout(Context context) {
    super(context);
  }

  public MyLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public MyLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override public boolean onInterceptTouchEvent(MotionEvent ev) {
    int event = ev.getActionMasked();
    if (event == MotionEvent.ACTION_POINTER_DOWN) {
      return true;
    }

    return false;
  }
}
