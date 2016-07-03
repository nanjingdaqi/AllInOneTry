package org.peace.allinone.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import me.ele.commons.AppLogger;
import me.ele.commons.DimenUtil;

public class MyFrameLayout extends FrameLayout {
  public MyFrameLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override public WindowInsets onApplyWindowInsets(WindowInsets insets) {
    AppLogger.e("myFrame onApply");
    AppLogger.e("insets top: " + insets.getSystemWindowInsetTop());
    AppLogger.e("before padding top: " + getPaddingTop());
    WindowInsets insets1 = super.onApplyWindowInsets(insets);
    //setPadding(0, getPaddingTop() + 50, 0, 0);
    AppLogger.e("after padding top: " + getPaddingTop());
    //insets1 = insets.replaceSystemWindowInsets(insets.getSystemWindowInsetLeft(),
    //    insets.getSystemWindowInsetTop() - 50, insets.getSystemWindowInsetRight(),
    //    insets.getSystemWindowInsetBottom());
    AppLogger.e("insets consumed: " + insets1.isConsumed());
    //WindowInsets insets2 = new WindowInsets(insets);
    //AppLogger.e("inset2 consumed: " + insets2.isConsumed());

    //return insets2;
    return insets1;
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    AppLogger.e("myFrame onMeasure");
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }
}
