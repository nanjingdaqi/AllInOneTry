package org.peace.allinone.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import me.ele.commons.AppLogger;

public class MyView2 extends View {
  public MyView2(Context context) {
    super(context);
  }

  public MyView2(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public MyView2(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    AppLogger.e("height mode: " + getMode(heightMeasureSpec));
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    AppLogger.e("mh: " + getMeasuredHeight());
  }

  static String getMode(int spec) {
    int mode = MeasureSpec.getMode(spec);
    if (mode == MeasureSpec.EXACTLY) {
      return "EXACTLY";
    }
    if (mode == MeasureSpec.AT_MOST) {
      return "AT_MOST";
    }
    if (mode == MeasureSpec.UNSPECIFIED) {
      return "UNSPECIFIED";
    }
    return "null";
  }
}
