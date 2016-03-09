package me.ele.ecamera.lib.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import me.ele.ecamera.R;

public class CameraRoundCornerLayout extends FrameLayout {

  private Bitmap maskBitmap;
  private Paint paint, maskPaint;
  private float cornerRadius;

  public CameraRoundCornerLayout(Context context) {
    super(context);
    init(context, null, 0);
  }

  public CameraRoundCornerLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs, 0);
  }

  public CameraRoundCornerLayout(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init(context, attrs, defStyle);
  }

  private void init(Context context, AttributeSet attrs, int defStyle) {
    TypedArray ta =
        context.obtainStyledAttributes(attrs, R.styleable.CameraRoundCornerLayout, defStyle, 0);
    cornerRadius = ta.getDimensionPixelSize(R.styleable.CameraRoundCornerLayout_radius, 4);
    ta.recycle();

    paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    maskPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
    maskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

    setWillNotDraw(false);
  }

  @Override
  public void draw(Canvas canvas) {
    Bitmap offscreenBitmap =
        Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
    Canvas offscreenCanvas = new Canvas(offscreenBitmap);

    super.draw(offscreenCanvas);

    if (maskBitmap == null) {
      maskBitmap = createMask(canvas.getWidth(), canvas.getHeight());
    }

    offscreenCanvas.drawBitmap(maskBitmap, 0f, 0f, maskPaint);
    canvas.drawBitmap(offscreenBitmap, 0f, 0f, paint);
  }

  private Bitmap createMask(int width, int height) {
    Bitmap mask = Bitmap.createBitmap(width, height, Bitmap.Config.ALPHA_8);
    Canvas canvas = new Canvas(mask);

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    paint.setColor(Color.WHITE);

    canvas.drawRect(0, 0, width, height, paint);

    paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    canvas.drawRoundRect(new RectF(0, 0, width, height), cornerRadius, cornerRadius, paint);

    return mask;
  }
}