package org.peace.allinone.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import me.ele.commons.AppLogger;

public class MyView extends TextView {
  public MyView(Context context) {
    super(context);
  }

  public MyView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    AppLogger.e("onAttachedToWindow");
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    //AppLogger.e(new Exception("onMeasure"));

    AppLogger.e("onMeasure");
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    AppLogger.e("onDraw");
  }

  @Override protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    super.onLayout(changed, left, top, right, bottom);
    AppLogger.e("onLayout");
  }

  @Override public boolean dispatchKeyEvent(KeyEvent event) {
    AppLogger.e("view event, code: " + event.getKeyCode() + ", action: " + event.getAction());
    return super.dispatchKeyEvent(event);
  }
}
