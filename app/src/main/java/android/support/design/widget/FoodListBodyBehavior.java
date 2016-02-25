package android.support.design.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import java.util.List;
import org.peace.allinone.ui.RestaurantRebuyView;

public class FoodListBodyBehavior extends CoordinatorLayout.Behavior<View> {

  public FoodListBodyBehavior(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
    parent.onLayoutChild(child, layoutDirection);
    child.offsetTopAndBottom(getDependency(parent, child).getMeasuredHeight());
    return true;
  }

  @Override
  public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
    View depend = getDependency(parent, child);
    child.setY(depend.getY() + depend.getMeasuredHeight());
    return true;
  }

  @Override public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
    return dependency instanceof RestaurantRebuyView;
  }

  private View getDependency(CoordinatorLayout parent, View child) {
    List<View> dependencies = parent.getDependencies(child);
    return dependencies.get(0);
  }
}
