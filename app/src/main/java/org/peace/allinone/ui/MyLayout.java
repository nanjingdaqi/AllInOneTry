package org.peace.allinone.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import me.ele.commons.AppLogger;
import org.peace.allinone.R;

public class MyLayout extends LinearLayout {

  int[] params = { android.R.attr.background, android.R.attr.padding };

  public MyLayout(Context context) {
    this(context, null);
  }

  public MyLayout(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public MyLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    inflate(context, R.layout.my_layout, this);

    AppLogger.e("background: " + attrs.getAttributeValue("android", "background"));
    AppLogger.e("padding: " + attrs.getAttributeValue("android", "padding"));

    TypedArray ta = context.obtainStyledAttributes(attrs, params);
    AppLogger.e("background: " + ta.hasValue(0));
    AppLogger.e("padding: " + ta.hasValue(1));
    ta.recycle();
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    AppLogger.e("height spec size: " + MeasureSpec.getSize(heightMeasureSpec));
    AppLogger.e("final height: " + getMeasuredHeight());
  }

  @Override protected void dispatchDraw(Canvas canvas) {
    super.dispatchDraw(canvas);
    AppLogger.e("dispatchDraw");
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    AppLogger.e("onDraw");
  }

  @Override protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    super.onLayout(changed, left, top, right, bottom);
    AppLogger.e("onLayout");
  }
}
