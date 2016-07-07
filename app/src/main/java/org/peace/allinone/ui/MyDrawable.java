package org.peace.allinone.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import me.ele.commons.AppLogger;

public class MyDrawable extends Drawable {

  @Override public void setAlpha(int alpha) {

  }

  @Override public void setColorFilter(ColorFilter colorFilter) {

  }

  @Override public int getOpacity() {
    return PixelFormat.OPAQUE;
  }

  @Override protected void onBoundsChange(Rect bounds) {
    AppLogger.e("onBoundsChange");
    super.onBoundsChange(bounds);
  }

  @Override public void setBounds(int left, int top, int right, int bottom) {
    AppLogger.e("setBounds");
    super.setBounds(left, top, right, bottom);
  }

  @Override public void draw(Canvas canvas) {
    AppLogger.e("draw");
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    paint.setColor(Color.RED);
    Rect bounds = getBounds();
    canvas.drawRect(bounds, paint);
  }

  @Override public int getIntrinsicHeight() {
    AppLogger.e("getIntrinsicHeight");
    return super.getIntrinsicHeight();
  }

  @Override public int getMinimumHeight() {
    AppLogger.e("getMinimumHeight");
    return 1000;
  }
}
