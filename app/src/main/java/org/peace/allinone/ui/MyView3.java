package org.peace.allinone.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import me.ele.base.utils.ResourceUtil;
import org.peace.allinone.R;

public class MyView3 extends View{

  public MyView3(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    Drawable drawable = ResourceUtil.getDrawable(R.drawable.ic_launcher);
    drawable.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
    drawable.draw(canvas);
  }
}
