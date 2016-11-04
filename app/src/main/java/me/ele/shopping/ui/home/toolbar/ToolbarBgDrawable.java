package me.ele.shopping.ui.home.toolbar;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import me.ele.base.utils.ViewUtils;

import static me.ele.base.utils.ColorParser.parse;

public class ToolbarBgDrawable extends Drawable implements ToolbarBehavior.HeightChangeListener {

  private static final String TAG = ToolbarBgDrawable.class.getSimpleName();

  private static final float ALPHA_FACTOR = 0.25f;

  private ColorDrawable blueDrawable = new ColorDrawable(parse("#0096FF"));
  @Nullable private Drawable skinDrawable;
  private float blueAlpha;
  private int offset;
  private ImageView scaleImageView; // used for drawable scale type

  private HomeFragmentToolbar homeFragmentToolbar;

  public ToolbarBgDrawable(HomeFragmentToolbar homeFragmentToolbar) {
    this.homeFragmentToolbar = homeFragmentToolbar;
    scaleImageView = new ImageView(ViewUtils.getActivity(homeFragmentToolbar));
    scaleImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
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
        canvas.save();
        canvas.concat(scaleImageView.getImageMatrix());
        skinDrawable.draw(canvas);
        canvas.restore();
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
      scaleImageView.layout(bounds.left, bounds.top, bounds.right, bounds.bottom);
      scaleImageView.setImageDrawable(skinDrawable);
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
