package org.peace.allinone.ui;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ScrollerCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import me.ele.components.recyclerview.EMRecyclerView;

import static me.ele.commons.DimenUtil.dip2px;

public class ToolbarBehavior extends CoordinatorLayout.Behavior<View> {

  static final String TAG = ToolbarBehavior.class.getSimpleName();

  EMRecyclerView emRecyclerView;

  public ToolbarBehavior(EMRecyclerView emRecyclerView) {
    this.emRecyclerView = emRecyclerView;
  }

  @Override public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child,
      View directTargetChild, View target, int nestedScrollAxes) {
    return target instanceof RecyclerView && nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
  }

  @Override
  public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target,
      int dx, int dy, int[] consumed) {
    Log.e(TAG, "onNestedPreScroll, dy: " + dy);

    consumed[0] = 0;
    // up scroll
    if (dy > 0) {
      ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
      int h = lp.height;
      setHeight(child, h - dy);

      Log.e(TAG, "onNestedPreScroll, consume dy: " + (h - lp.height));
      consumed[1] = h - lp.height;
    } else {
      consumed[1] = 0;
    }
  }

  @Override public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target,
      int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
    Log.e(TAG, "onNestedScroll, dyUnconsumed: " + dyUnconsumed);

    // down scroll
    if (dyUnconsumed < 0) {
      ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
      setHeight(child, lp.height - dyUnconsumed);
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
    if (velocityY > 0 && child.getLayoutParams().height > dip2px(child.getContext(), 40)) {
      mScroller.fling(0, (int) target.getY(), 0, (int) (-velocityY), 0, 0,
          dip2px(child.getContext(), 40), dip2px(child.getContext(), 300));
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
    if (velocityY < 0
        && llm.findFirstVisibleItemPosition() == 0
        && child.getLayoutParams().height < dip2px(child.getContext(), 300)) {
      mScroller.fling(0, (int) target.getY(), 0, (int) (-velocityY), 0, 0,
          dip2px(child.getContext(), 40), dip2px(child.getContext(), 300));
      if (mScroller.computeScrollOffset()) {
        mFlingRunnable = new FlingRunnable(child, target);
        ViewCompat.postOnAnimation(child, mFlingRunnable);
        return true;
      }
    }

    return false;
  }

  public void setHeight(View child, int height) {
    Context ctx = child.getContext();
    ViewGroup.LayoutParams lp = child.getLayoutParams();
    lp.height = Math.min(dip2px(ctx, 300), Math.max(dip2px(ctx, 40), height));
    child.setLayoutParams(lp);
    if (lp.height == dip2px(ctx, 300)) {
      emRecyclerView.enablePullRefresh();
    } else {
      emRecyclerView.disablePullRefresh();
    }
  }

  private class FlingRunnable implements Runnable {

    View child;
    View target;

    public FlingRunnable(View child, View target) {
      this.child = child;
      this.target = target;
    }

    @Override public void run() {
      if (mScroller.computeScrollOffset()) {
        int value = mScroller.getCurrY();
        setHeight(child, value);
        ViewCompat.postOnAnimation(child, this);
      }
    }
  }
}
