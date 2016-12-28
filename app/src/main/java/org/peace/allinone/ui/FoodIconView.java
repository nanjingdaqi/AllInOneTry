package org.peace.allinone.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.AttributeSet;
import android.view.View;
import java.util.List;
import me.ele.base.utils.AppLogger;
import me.ele.base.utils.CollectionsUtils;

import static me.ele.base.utils.DimenUtil.dip2px;
import static me.ele.base.utils.DimenUtil.sp2px;

public class FoodIconView extends View {

  private List<Icon> icons;
  private Paint textPaint;
  private final float padding = dip2px(4);
  private final float strokeWidth = dip2px(1);
  private final float margin = dip2px(2);
  private final float height = dip2px(14);

  public FoodIconView(Context context) {
    this(context, null);
  }

  public FoodIconView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public FoodIconView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    textPaint.setTextSize(sp2px(10));
    textPaint.setTypeface(Typeface.DEFAULT_BOLD);
    textPaint.setStyle(Paint.Style.FILL);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    setMeasuredDimension(0, 0);
    if (CollectionsUtils.isEmpty(icons)) {
      return;
    }
    float width = 0;
    for (Icon icon : icons) {
      float textLength = textPaint.measureText(icon.text());
      width += padding * 2 + strokeWidth * 2 + textLength + margin;
    }
    width -= margin;
    setMeasuredDimension((int) width, (int) height);
    AppLogger.e("width: " + width);
  }

  @Override protected void onDraw(Canvas canvas) {
    if (CollectionsUtils.isEmpty(icons)) {
      return;
    }
    float l = 0;
    Paint.FontMetricsInt fontMetrics = textPaint.getFontMetricsInt();
    float baselineY = (height - fontMetrics.bottom - fontMetrics.top) / 2;
    for (Icon icon : icons) {
      textPaint.setColor(icon.color());
      canvas.drawText(icon.text(), l + padding + strokeWidth, baselineY, textPaint);
      float textLength = textPaint.measureText(icon.text());
      Drawable bg = createRectangleShapeDrawable(getContext(), icon.color());
      float r = l + padding * 2 + strokeWidth * 2 + textLength;
      AppLogger.e("text len: " + textLength + ", l: " + l + ", r: " + r);
      bg.setBounds((int) (l + strokeWidth), (int) strokeWidth, (int) (r - strokeWidth),
          (int) (height - strokeWidth));
      bg.draw(canvas);
      l += padding * 2 + strokeWidth * 2 + textLength + margin;
    }
  }

  public void setIcons(List<Icon> icons) {
    this.icons = icons;
    invalidate();
    requestLayout();
  }

  public interface Icon {
    String text();

    int color();
  }

  private Drawable createRectangleShapeDrawable(Context context, int color) {
    RectShape shape = new RectShape();
    PaintDrawable paintDrawable = new PaintDrawable();
    paintDrawable.setShape(shape);
    paintDrawable.setCornerRadius(dip2px(7));
    Paint paint = paintDrawable.getPaint();
    paint.setAntiAlias(true);
    paint.setStyle(Paint.Style.STROKE);
    paint.setStrokeWidth(dip2px(1));
    paint.setColor(color);
    return paintDrawable;
  }
}
