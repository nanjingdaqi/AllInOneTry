package org.peace.allinone.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import me.ele.commons.AppLogger;
import org.peace.allinone.R;

public class MyView2 extends MyView {
  public MyView2(Context context) {
    super(context);
  }

  public MyView2(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public MyView2(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyView2);

    int N = ta.getIndexCount();
    AppLogger.d("N2: " + N);

    int p = ta.getDimensionPixelSize(R.styleable.MyView2_p, 0);
    AppLogger.d("p2: " + p);

    ta.recycle();
  }
}
