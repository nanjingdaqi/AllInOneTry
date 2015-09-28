package org.peace.allinone.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.hardware.display.DisplayManager;
import android.util.AttributeSet;
import android.widget.TextView;
import me.ele.commons.DimenUtil;
import org.peace.allinone.R;

public class TriangleRectPopTextView extends TextView {

  private Paint paint;

  private int triangleHeight;
  private int radius;
  private int backgroundColor;

  private int[] systemAttrs = { android.R.attr.background };

  public TriangleRectPopTextView(Context context) {
    this(context, null);
  }

  public TriangleRectPopTextView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public TriangleRectPopTextView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    TypedArray ta = context.obtainStyledAttributes(attrs, systemAttrs);
    backgroundColor = ta.getColor(0, getResources().getColor(android.R.color.holo_blue_dark));
    ta.recycle();
    ta = context.obtainStyledAttributes(attrs, R.styleable.TriangleRectPopTextView);
    triangleHeight = ta.getDimensionPixelSize(R.styleable.TriangleRectPopTextView_triangle_height,
        DimenUtil.dip2px(context, 8));
    radius = ta.getDimensionPixelSize(R.styleable.TriangleRectPopTextView_rect_radius,
        DimenUtil.dip2px(context, 4));
    ta.recycle();

    paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    paint.setColor(backgroundColor);
    paint.setStyle(Style.FILL);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    int h = getMeasuredHeight();
    setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), h + triangleHeight);
    setPadding(getPaddingLeft(), 0, getPaddingRight(), triangleHeight);
  }

  @Override protected void onDraw(Canvas canvas) {
    if (isInEditMode()) {
      super.onDraw(canvas);
      return;
    }
    RectF rectF = new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight() - triangleHeight);
    canvas.drawRoundRect(rectF, radius, radius, paint);
    Path path = new Path();
    int l = getMeasuredWidth() / 2 - triangleHeight / 2 - DimenUtil.dip2px(getContext(), 4);
    int r = l + triangleHeight + DimenUtil.dip2px(getContext(), 4) * 2;
    int h = getMeasuredHeight() - triangleHeight;
    path.moveTo(l, h);
    path.lineTo(r, h);
    path.lineTo((l + r) / 2, getMeasuredHeight());
    path.lineTo(l, h);
    path.close();
    canvas.drawPath(path, paint);

    setBackgroundColor(getResources().getColor(android.R.color.transparent));
    super.onDraw(canvas);
  }
}