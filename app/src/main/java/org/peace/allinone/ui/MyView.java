package org.peace.allinone.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import me.ele.commons.AppLogger;
import me.ele.commons.DimenUtil;

public class MyView extends View {
  public MyView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    int pl = DimenUtil.dip2px(getContext(), 3);
    int pt = DimenUtil.dip2px(getContext(), 3);
    AppLogger.e("pl: " + pl);

    String text = "仅剩100份";
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    int ts = DimenUtil.sp2px(getContext(), 9);
    AppLogger.e("text size: " + ts);
    paint.setTextSize(ts);

    canvas.translate(100, 100);

    Rect rect = new Rect();
    paint.getTextBounds(text, 0, text.length(), rect);
    paint.setStrokeWidth(10);
    paint.setColor(Color.BLACK);
    rect.top -= pt;
    rect.bottom += pt;
    rect.left -= pl;
    rect.right += pl;
    rect.offset(100, 100);
    canvas.drawRect(rect, paint);

    paint.setColor(Color.WHITE);
    canvas.drawText(text, 100, 100, paint);
  }
}
