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
    List<View> dependencies = parent.getDependencies(child);
    View firstView = dependencies.get(0);
    AppLogger.e("onLayoutChild, firstView mh: " + firstView.getMeasuredHeight());
    child.offsetTopAndBottom(firstView.getMeasuredHeight());
    return true;
  }

  @Override
  public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
    AppLogger.e("onDependentViewChanged");
    child.offsetTopAndBottom(-2);
    parent.invalidate();
    return true;
  }

  @Override public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
    return dependency instanceof FirstView;
  }
}
