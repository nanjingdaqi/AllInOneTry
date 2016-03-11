package me.ele.ecamera.lib.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class SquareLayout extends FrameLayout {
  public SquareLayout(Context context) {
    super(context);
  }

  public SquareLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public SquareLayout(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    // For simple implementation, or internal size is always 0.
    // We depend on the container to specify the layout size of
    // our view. We can't really know what it is since we will be
    // adding and removing different arbitrary views and do not
    // want the layout to change as this happens.
    setMeasuredDimension(resolveSize(0, widthMeasureSpec), resolveSize(0, heightMeasureSpec));
    // Children are just made to fill our space.
    int childWidthSize = getMeasuredWidth();
    heightMeasureSpec = widthMeasureSpec = MeasureSpec.makeMeasureSpec(
        childWidthSize, MeasureSpec.EXACTLY);
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }
}
