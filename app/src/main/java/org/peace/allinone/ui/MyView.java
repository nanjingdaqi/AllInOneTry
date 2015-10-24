package org.peace.allinone.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by peacepassion on 15/10/24.
 */
public class MyView extends View {

  public MyView(Context context) {
    super(context);
  }

  public MyView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    setMeasuredDimension(MeasureSpec.makeMeasureSpec(100, MeasureSpec.EXACTLY),
        MeasureSpec.makeMeasureSpec(100, MeasureSpec.EXACTLY));
  }

}
