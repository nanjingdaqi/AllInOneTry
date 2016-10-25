package org.peace.allinone.ui;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Button;

public class MyBtn extends Button {
  public MyBtn(Context context) {
    super(context);
  }

  public MyBtn(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public MyBtn(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    Resources.Theme theme = context.getTheme();
    //TypedArray ta = context.obtainStyledAttributes(attrs, com.android.internal.R.styleable.View,
    //    com.android.internal.R.attr.buttonStyle, 0);
    //
    //int pt = ta.getDimensionPixelSize(com.android.internal.R.styleable.View_paddingTop, 0);
    //int p = ta.getDimensionPixelSize(com.android.internal.R.styleable.View_padding, 0);
    //
    //AppLogger.d("pt: " + pt);
    //AppLogger.d("p: " + p);

  }
}
