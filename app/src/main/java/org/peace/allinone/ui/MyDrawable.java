package org.peace.allinone.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import me.ele.commons.AppLogger;

public class MyDrawable extends Drawable {

  Bitmap bitmap;
  Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

  public MyDrawable(Bitmap bitmap) {
    this.bitmap = bitmap;
    BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.REPEAT);
    paint.setShader(shader);
  }

  @Override public void draw(Canvas canvas) {
    AppLogger.e("draw");
    Rect bounds = getBounds();
    canvas.drawRect(bounds, paint);
  }

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

  @Override public int getIntrinsicHeight() {
    AppLogger.e("getIntrinsicHeight");
    return super.getIntrinsicHeight();
  }

  @Override public int getMinimumHeight() {
    AppLogger.e("getMinimumHeight");
    return super.getMinimumHeight();
  }
}
