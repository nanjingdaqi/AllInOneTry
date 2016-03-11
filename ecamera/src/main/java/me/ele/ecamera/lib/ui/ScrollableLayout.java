package me.ele.ecamera.lib.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import android.widget.Scroller;

public class ScrollableLayout extends LinearLayout {

  private static final Interpolator SINTERPOLATOR = new Interpolator() {
    public float getInterpolation(float t) {
      t -= 1.0f;
      return t * t * t * t * t + 1.0f;
    }
  };

  public interface OnScrollFinished {
    void onScrollFinished();
  }

  private OnScrollFinished onScrollFinished;
  private Scroller mScroller;
  private boolean smoothScrolled;

  public ScrollableLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public ScrollableLayout(Context context) {
    super(context);
    init();
  }

  private void init() {
    setWillNotDraw(true);
    setDescendantFocusability(FOCUS_AFTER_DESCENDANTS);
    setFocusable(true);
    final Context context = getContext();
    mScroller = new Scroller(context, SINTERPOLATOR);
  }

  public void setInterPolator(Interpolator interpolator) {
    mScroller = new Scroller(getContext(), interpolator);
  }

  @Override
  public void computeScroll() {
    if (isScrolling() && mScroller.computeScrollOffset()) {
      int oldX = getScrollX();
      int oldY = getScrollY();
      int x = mScroller.getCurrX();
      int y = mScroller.getCurrY();

      if (oldX != x || oldY != y) {
        scrollTo(x, y);
        smoothScrolled = true;
      }

      invalidate();
      return;
    }
    if (smoothScrolled && onScrollFinished != null) {
      onScrollFinished.onScrollFinished();
      smoothScrolled = false;
    }
  }

  public void smoothScrollTo(int x, int y, int duration) {
    if (isScrolling()) {
      mScroller.forceFinished(true);
    }
    int sx = getScrollX();
    int sy = getScrollY();
    int dx = x - sx;
    int dy = y - sy;
    if (dx == 0 && dy == 0) {
      return;
    }
    mScroller.startScroll(sx, sy, dx, dy, duration);
    invalidate();
  }

  public void smoothScrollBy(int dx, int dy, int duration) {
    if (isScrolling()) {
      mScroller.forceFinished(true);
    }
    if (dx == 0 && dy == 0) {
      return;
    }
    int sx = getScrollX();
    int sy = getScrollY();
    mScroller.startScroll(sx, sy, dx, dy, duration);
    invalidate();
  }

  public synchronized boolean isScrolling() {
    return !mScroller.isFinished();
  }

  public OnScrollFinished getOnScrollFinished() {
    return onScrollFinished;
  }

  public void setOnScrollFinished(OnScrollFinished onScrollFinished) {
    this.onScrollFinished = onScrollFinished;
  }
}
