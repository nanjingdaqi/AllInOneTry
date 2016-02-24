package android.support.design.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import me.ele.commons.AppLogger;

public class NewB2 extends AppBarLayout.ScrollingViewBehavior {

  public NewB2(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
    AppLogger.e("onLayoutChild");
    return super.onLayoutChild(parent, child, layoutDirection);
  }

  @Override
  public boolean onMeasureChild(CoordinatorLayout parent, View child, int parentWidthMeasureSpec,
      int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
    AppLogger.e("onMeasureChild");
    return super.onMeasureChild(parent, child, parentWidthMeasureSpec, widthUsed,
        parentHeightMeasureSpec, heightUsed);
  }

  @Override public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
    return super.layoutDependsOn(parent, child, dependency);
  }
}
