package org.peace.allinone.ui;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

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

    // up scroll
    if (dy > 0) {
      float y = child.getY();
      float destY = - Math.min(-y + dy, child.getMeasuredHeight());
      child.setY(destY);
      consumed[1] = (int) (- destY + y);
    }
  }

  @Override public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target,
      int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
    Log.e(TAG, "onNestedScroll, dyUnconsumed: " + dyUnconsumed);

    // down scroll
    if (dyUnconsumed < 0) {
      float y = child.getY();
      float destY = Math.min(y - dyUnconsumed, child.getMeasuredHeight());
      child.setY(destY);
    }
  }

  @Override public void onNestedScrollAccepted(CoordinatorLayout coordinatorLayout, View child,
      View directTargetChild, View target, int nestedScrollAxes) {
    super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target,
        nestedScrollAxes);
  }
}
