package org.peace.allinone.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;
import me.ele.commons.DimenUtil;

/**
 * Created by peacepassion on 15/9/27.
 */
public class MyView extends View {

  Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
  Scroller scroller;
  float scaler = 1.0f;
  float ly;

  public MyView(Context context) {
    super(context);
    init();
  }

  public MyView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    textPaint.setColor(getResources().getColor(android.R.color.holo_blue_dark));
    textPaint.setTextSize(DimenUtil.dip2px(getContext(), 20));
    scroller = new Scroller(getContext());
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    int h = getMeasuredHeight(), y = 0, uh = DimenUtil.dip2px(getContext(), 20), i = 0;
    while (y + uh / 2 < h) {
      canvas.drawText("android " + i++, 0, y + uh / 2, textPaint);
      y += uh;
    }
  }

  @Override public boolean onTouchEvent(MotionEvent event) {
    int action = event.getAction();
    if (action == MotionEvent.ACTION_DOWN) {
      ly = event.getY();
      return true;
    }
    if (action == MotionEvent.ACTION_MOVE) {
      float y = event.getY();
      float dy = (y - ly) * scaler;
      scrollBy(0, (int) -dy);
      ly = y;
      return true;
    }
    if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
      return true;
    }
    return true;
  }

  public void smoothScrollBy(int dy) {
    scroller.startScroll(0, getScrollY(), 0, dy, 3000);
    invalidate();
  }

  @Override public void computeScroll() {
    super.computeScroll();
    //if (scroller.computeScrollOffset()) {
    //  scrollTo(0, scroller.getCurrY());
    //  postInvalidate();
    //}
  }
}
