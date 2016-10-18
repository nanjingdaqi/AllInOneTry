package org.peace.allinone.ui;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ScrollerCompat;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import me.ele.commons.DimenUtil;

public class ToolbarBehavior extends CoordinatorLayout.Behavior<View> {

  static final String TAG = ToolbarBehavior.class.getSimpleName();

  public ToolbarBehavior(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child,
      View directTargetChild, View target, int nestedScrollAxes) {
    return target instanceof RecyclerView && nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
  }

  @Override
  public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target,
      int dx, int dy, int[] consumed) {
    Log.e(TAG, "onNestedPreScroll, dy: " + dy);
    int minH = DimenUtil.dip2px(child.getContext(), 40);

    if (dy < 0) {
      Log.d(TAG, "hang on");
    }

    consumed[0] = 0;
    // up scroll
    if (dy > 0) {
      ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
      int h = lp.height;
      lp.height = Math.max(minH, h - dy);
      child.setLayoutParams(lp);

      Log.e(TAG, "onNestedPreScroll, consume dy: " + (h - lp.height));
      consumed[1] = h - lp.height;
    } else {
      consumed[1] = 0;
    }
  }

  @Override public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target,
      int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
    Log.e(TAG, "onNestedScroll, dyUnconsumed: " + dyUnconsumed);
    int maxH = DimenUtil.dip2px(child.getContext(), 300);

    // down scroll
    if (dyUnconsumed < 0) {
      ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
      lp.height = Math.min(maxH, lp.height - dyUnconsumed);
      child.setLayoutParams(lp);
    }
  }

  private ScrollerCompat mScroller;
  private FlingRunnable mFlingRunnable;

  @Override
  public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, View child, View target,
      float velocityX, float velocityY) {
    Log.e(TAG, "onNestedPreFling, vy: " + velocityY);
    if (mScroller == null) {
      mScroller = ScrollerCompat.create(child.getContext());
    }
    // up fling
    if (velocityY > 0 && child.getLayoutParams().height > DimenUtil.dip2px(child.getContext(), 40)) {
      mScroller.fling(0, (int) target.getY(), 0, (int) (-velocityY), 0, 0, DimenUtil.dip2px(child.getContext(), 40), DimenUtil.dip2px(child.getContext(), 300));
      if (mScroller.computeScrollOffset()) {
        mFlingRunnable = new FlingRunnable(child, target);
        ViewCompat.postOnAnimation(child, mFlingRunnable);
        return true;
      }
    }

    return false;
  }

  @Override
  public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View child, View target,
      float velocityX, float velocityY, boolean consumed) {
    Log.e(TAG, "onNestedFling, vy: " + velocityY);
    if (mScroller == null) {
      mScroller = ScrollerCompat.create(child.getContext());
    }

    // down fling
    RecyclerView rv = (RecyclerView) target;
    LinearLayoutManager llm = (LinearLayoutManager) rv.getLayoutManager();
    if (velocityY < 0 && llm.findFirstVisibleItemPosition() == 0 && child.getLayoutParams().height < DimenUtil.dip2px(child.getContext(), 300)) {
      mScroller.fling(0, (int) target.getY(), 0, (int) (-velocityY), 0, 0, DimenUtil.dip2px(child.getContext(), 40), DimenUtil.dip2px(child.getContext(), 300));
      if (mScroller.computeScrollOffset()) {
        mFlingRunnable = new FlingRunnable(child, target);
        ViewCompat.postOnAnimation(child, mFlingRunnable);
        return true;
      }
    }

    return false;
  }

  private class FlingRunnable implements Runnable {

    View child;
    View target;
    int minH;

    public FlingRunnable(View child, View target) {
      this.child = child;
      this.target = target;
      minH = DimenUtil.dip2px(child.getContext(), 40);
    }

    @Override public void run() {
      if (mScroller.computeScrollOffset()) {
        ViewGroup.LayoutParams lp = child.getLayoutParams();
        int value = mScroller.getCurrY();
        lp.height = Math.max(minH, value);
        child.setLayoutParams(lp);
        ViewCompat.postOnAnimation(child, this);
      }
    }
  }
}
