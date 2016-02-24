package android.support.design.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import java.util.List;
import me.ele.commons.AppLogger;
import org.peace.allinone.ui.FirstView;

public class MyB2 extends CoordinatorLayout.Behavior<View> {

  public MyB2(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public boolean onMeasureChild(CoordinatorLayout parent, View child, int parentWidthMeasureSpec,
      int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
    AppLogger.e("onMeasureChild");
    parent.onMeasureChild(child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec, heightUsed);
    return true;
  }

  @Override
  public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
    parent.onLayoutChild(child, layoutDirection);
    AppLogger.e("onLayoutChild, firstView mh: " + getDependency(parent, child).getMeasuredHeight());
    child.offsetTopAndBottom(getDependency(parent, child).getMeasuredHeight());
    return true;
  }

  @Override
  public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
    AppLogger.e("onDependentViewChanged");
    View depend = getDependency(parent, child);
    child.setY(depend.getY() + depend.getMeasuredHeight());
    return true;
  }

  @Override public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
    return dependency instanceof FirstView;
  }

  private View getDependency(CoordinatorLayout parent, View child) {
    List<View> dependencies = parent.getDependencies(child);
    return dependencies.get(0);
  }
}
