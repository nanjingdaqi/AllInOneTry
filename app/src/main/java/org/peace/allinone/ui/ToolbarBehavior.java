package org.peace.allinone.ui;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
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
    int maxH = DimenUtil.dip2px(child.getContext(), 100);

    // down scroll
    if (dyUnconsumed < 0) {
      ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
      lp.height = Math.min(maxH, lp.height - dyUnconsumed);
      child.setLayoutParams(lp);
    }
  }

  @Override public void onNestedScrollAccepted(CoordinatorLayout coordinatorLayout, View child,
      View directTargetChild, View target, int nestedScrollAxes) {
    super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target,
        nestedScrollAxes);
  }
}
