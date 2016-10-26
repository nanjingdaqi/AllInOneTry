package me.ele.shopping.ui.home.toolbar;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import me.ele.base.utils.AppUtil;
import me.ele.base.utils.ViewUtils;

import static me.ele.base.utils.DimenUtil.getStatusBarHeight;
import static me.ele.base.utils.DimenUtil.getToolBarHeight;

public class RVBehavior extends CoordinatorLayout.Behavior<RecyclerView> {

  static final String TAG = RVBehavior.class.getSimpleName();

  private View dependencyView;

  public RVBehavior(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public void setup(RecyclerView child) {
    child.setPadding(child.getPaddingLeft(), child.getPaddingTop(), child.getPaddingRight(),
        (AppUtil.isAtLeastL() ? getStatusBarHeight() : 0)
            + getToolBarHeight(ViewUtils.getActivity(child)));
  }

  @Override
  public boolean layoutDependsOn(CoordinatorLayout parent, RecyclerView child, View dependency) {
    return dependency.getTag().equals("toolbar");
  }

  @Override public boolean onDependentViewChanged(CoordinatorLayout parent, RecyclerView child,
      View dependency) {
    ToolbarBehavior behavior =
        (ToolbarBehavior) ((CoordinatorLayout.LayoutParams) dependency.getLayoutParams()).getBehavior();
    int minH = behavior.getMinH();
    int bottomMargin = ((ViewGroup.MarginLayoutParams) dependency.getLayoutParams()).bottomMargin;
    int y = (int) (dependency.getY() + dependency.getMeasuredHeight() + bottomMargin);
    y = Math.max(minH, y);
    Log.e(TAG, "onDependentViewChanged, Y: " + y);
    child.setY(y);
    return true;
  }

  private View getDependency(CoordinatorLayout parent, View child) {
    if (dependencyView == null) {
      dependencyView = parent.getDependencies(child).get(0);
    }
    return dependencyView;
  }
}
