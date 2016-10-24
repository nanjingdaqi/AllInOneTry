package me.ele.shopping.ui.home.toolbar;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;

import static android.graphics.drawable.GradientDrawable.Orientation.TR_BL;
import static me.ele.base.utils.ColorParser.parse;

public class ToolbarBgDrawable extends Drawable implements ToolbarBehavior.HeightChangeListener {

  private static final String TAG = ToolbarBgDrawable.class.getSimpleName();

  private static final float ALPHA_FACTOR = 0.25f;

  private int blueStartColor = parse("#0088ff");
  private int blueEndColor = parse("#0096ff");
  private GradientDrawable blueDrawable =
      new GradientDrawable(TR_BL, new int[] { blueStartColor, blueEndColor });
  @Nullable private Drawable skinDrawable;
  private float blueAlpha;
  private int offset;

  private HomeFragmentToolbar homeFragmentToolbar;

  public ToolbarBgDrawable(HomeFragmentToolbar homeFragmentToolbar) {
    this.homeFragmentToolbar = homeFragmentToolbar;
  }

  public void setSkinDrawable(Drawable skinDrawable) {
    this.skinDrawable = skinDrawable;
    invalidateSelf();
  }

  @Override public void onHeightChange(int drawHeight, int measureHeight) {
    if (skinDrawable == null) {
      return;
    }
    int maxToolbarH = measureHeight;
    int minToolbarH = homeFragmentToolbar.getBehavior().getMinH();
    int deadH = (int) (minToolbarH + (maxToolbarH - minToolbarH) * ALPHA_FACTOR);
    float k = -1.0f / (deadH - minToolbarH);
    int currentH = drawHeight;
    if (currentH <= deadH) {
      blueAlpha = k * (currentH - minToolbarH) + 1.0f;
    } else {
      blueAlpha = 0;
    }
    offset = measureHeight - drawHeight;
    invalidateSelf();
  }

  @Override public void draw(Canvas canvas) {
    canvas.save();
    canvas.translate(0, -offset);
    if (skinDrawable == null) {
      blueDrawable.setAlpha(255);
      blueDrawable.draw(canvas);
    } else {
      int alpha = (int) (blueAlpha * 255);
      if (alpha != 255) {
        skinDrawable.draw(canvas);
      }
      blueDrawable.setAlpha(alpha);
      blueDrawable.draw(canvas);
    }
    canvas.restore();
  }

  @Override protected void onBoundsChange(Rect bounds) {
    super.onBoundsChange(bounds);
    if (skinDrawable != null) {
      skinDrawable.setBounds(bounds);
    }
    blueDrawable.setBounds(bounds);
  }

  @Override public void setAlpha(int alpha) {

  }

  @Override public void setColorFilter(ColorFilter colorFilter) {

  }

  @Override public int getOpacity() {
    return PixelFormat.UNKNOWN;
  }
}
