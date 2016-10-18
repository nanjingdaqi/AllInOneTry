package org.peace.allinone.ui;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class RVBehavior extends HeaderBehavior<RecyclerView> {

  static final String TAG = RVBehavior.class.getSimpleName();

  private View dependencyView;

  public RVBehavior(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public boolean layoutDependsOn(CoordinatorLayout parent, RecyclerView child, View dependency) {
    return dependency.getTag().equals("toolbar");
  }

  @Override public boolean onDependentViewChanged(CoordinatorLayout parent, RecyclerView child,
      View dependency) {
    Log.e(TAG, "onDependentViewChanged");
    child.setY(dependency.getY() + dependency.getHeight());
    return true;
  }

  @Override
  public boolean onLayoutChild(CoordinatorLayout parent, RecyclerView child, int layoutDirection) {
    Log.e(TAG, "onLayoutChild");
    child.layout(0, getDependency(parent, child).getBottom(), parent.getMeasuredWidth(),
        getDependency(parent, child).getBottom() + child.getMeasuredHeight());
    return true;
  }

  private View getDependency(CoordinatorLayout parent, View child) {
    if (dependencyView == null) {
      dependencyView = parent.getDependencies(child).get(0);
    }
    return dependencyView;
  }

  @Override public boolean onInterceptTouchEvent(CoordinatorLayout parent, RecyclerView child,
      MotionEvent ev) {
    Log.e(TAG, "onInterceptTouchEvent");
    return super.onInterceptTouchEvent(parent, child, ev);
  }

  @Override
  public boolean onTouchEvent(CoordinatorLayout parent, RecyclerView child, MotionEvent ev) {
    Log.e(TAG, "onTouchEvent");
    return super.onTouchEvent(parent, child, ev);
  }
}
