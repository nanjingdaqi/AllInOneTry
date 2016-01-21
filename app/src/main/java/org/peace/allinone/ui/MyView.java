package org.peace.allinone.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import me.ele.commons.AppLogger;
import org.peace.allinone.R;

public class MyView extends View {
  public MyView(Context context) {
    super(context);
  }

  public MyView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyView);

    int N = ta.getIndexCount();
    AppLogger.d("N: " + N);

    int p = ta.getDimensionPixelSize(R.styleable.MyView_p, 0);
    AppLogger.d("p: " + p);

    ta.recycle();
  }
}
