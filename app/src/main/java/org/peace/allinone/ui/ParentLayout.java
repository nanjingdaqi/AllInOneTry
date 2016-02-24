package org.peace.allinone.ui;

import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import me.ele.commons.AppLogger;

public class ParentLayout extends LinearLayout implements NestedScrollingParent {

  private NestedScrollingParentHelper nestedScrollingParentHelper;

  public ParentLayout(Context context) {
    super(context);
  }

  public ParentLayout(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public ParentLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    nestedScrollingParentHelper = new NestedScrollingParentHelper(this);
  }

  @Override public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
    AppLogger.e("onStartNestedScroll");
    return true;
  }

  @Override public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {
    nestedScrollingParentHelper.onNestedScrollAccepted(child, target, nestedScrollAxes);
  }

  @Override public void onStopNestedScroll(View target) {
    nestedScrollingParentHelper.onStopNestedScroll(target);
  }

  @Override
  public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed,
      int dyUnconsumed) {

  }

  @Override public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
    AppLogger.e("get dy: " + dy);
    consumed[1] = 1;
  }

  @Override
  public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
    return false;
  }

  @Override public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
    return false;
  }

  @Override public int getNestedScrollAxes() {
    return nestedScrollingParentHelper.getNestedScrollAxes();
  }
}
