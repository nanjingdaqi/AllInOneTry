package android.support.design.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import me.ele.commons.AppLogger;

public class NewB extends AppBarLayout.Behavior {

  public NewB(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child,
      View directTargetChild, View target, int nestedScrollAxes) {
    return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target,
        nestedScrollAxes);
  }

  @Override
  public boolean onLayoutChild(CoordinatorLayout parent, AppBarLayout child, int layoutDirection) {
    AppLogger.e("onLayoutChild");
    return super.onLayoutChild(parent, child, layoutDirection);
  }

  @Override
  public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target,
      int dx, int dy, int[] consumed) {
    super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
  }
}