package org.peace.allinone.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import butterknife.InjectView;

public class MyLayout extends LinearLayout {

  public MyLayout(Context context) {
    super(context);
    init();
  }

  public MyLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public MyLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {

  }

}
