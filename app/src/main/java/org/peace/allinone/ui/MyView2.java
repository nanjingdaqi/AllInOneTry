package org.peace.allinone.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import org.peace.allinone.R;

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

  @Override protected void dispatchDraw(Canvas canvas) {
    super.dispatchDraw(canvas);
    Drawable drawable = getResources().getDrawable(R.drawable.ic_launcher);
    drawable.setBounds(0, getMeasuredHeight() - drawable.getIntrinsicHeight(), getMeasuredWidth(),
        getMeasuredHeight());
    drawable.draw(canvas);
  }
}
