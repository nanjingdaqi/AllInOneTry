package org.peace.allinone.ui;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import me.ele.commons.AppLogger;

public class SecondView extends View implements NestedScrollingChild {

  private NestedScrollingChildHelper nestedScrollingChildHelper;
  private float lastY;

  public SecondView(Context context) {
    super(context);
  }

  public SecondView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public SecondView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    nestedScrollingChildHelper = new NestedScrollingChildHelper(this);
    setNestedScrollingEnabled(true);
  }

  @Override public boolean onTouchEvent(MotionEvent event) {
    int action = MotionEventCompat.getActionMasked(event);
    switch (action) {
      case MotionEvent.ACTION_DOWN:
        startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL);
        break;
      case MotionEvent.ACTION_MOVE:
        float y = event.getY();
        float dy = y - lastY;
        int[] consumed = new int[2];
        int[] offsetWindow = new int[2];
        AppLogger.e("send dy: " + dy);
        if (dispatchNestedPreScroll(0, (int) dy, consumed, offsetWindow)) {
          AppLogger.e("consumedY: " + consumed[1] + ", offY: " + offsetWindow[1]);
        }
        break;
      case MotionEvent.ACTION_UP:
      case MotionEvent.ACTION_CANCEL:
        stopNestedScroll();
        break;
    }
    lastY = event.getY();
    return true;
  }

  @Override public void setNestedScrollingEnabled(boolean enabled) {
    nestedScrollingChildHelper.setNestedScrollingEnabled(enabled);
  }

  @Override public boolean isNestedScrollingEnabled() {
    return nestedScrollingChildHelper.isNestedScrollingEnabled();
  }

  @Override public boolean startNestedScroll(int axes) {
    return nestedScrollingChildHelper.startNestedScroll(axes);
  }

  @Override public void stopNestedScroll() {
    nestedScrollingChildHelper.stopNestedScroll();
  }

  @Override public boolean hasNestedScrollingParent() {
    return nestedScrollingChildHelper.hasNestedScrollingParent();
  }

  @Override public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed,
      int dyUnconsumed, int[] offsetInWindow) {
    return nestedScrollingChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed,
        dyUnconsumed, offsetInWindow);
  }

  @Override
  public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
    return nestedScrollingChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
  }

  @Override public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
    return nestedScrollingChildHelper.dispatchNestedFling(velocityX, velocityY, consumed);
  }

  @Override public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
    return nestedScrollingChildHelper.dispatchNestedPreFling(velocityX, velocityY);
  }
}
