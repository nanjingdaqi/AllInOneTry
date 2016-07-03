package org.peace.allinone.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowInsets;
import me.ele.commons.AppLogger;

public class MyView extends View {

  public MyView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override public WindowInsets onApplyWindowInsets(WindowInsets insets) {
    AppLogger.e("myView onApply");
    AppLogger.e("insets top: " + insets.getSystemWindowInsetTop());
    return super.onApplyWindowInsets(insets);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    AppLogger.e("myView onMeasure");
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }
}
