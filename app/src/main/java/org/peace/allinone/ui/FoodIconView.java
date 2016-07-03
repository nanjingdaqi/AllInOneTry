package org.peace.allinone.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import me.ele.commons.CollectionsUtils;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.TRANSPARENT;
import static org.peace.allinone.ui.DimenUtil.dip2px;
import static org.peace.allinone.ui.DimenUtil.sp2px;

public class FoodIconView extends View {

  private List<IconBuilder> iconBuilders = new ArrayList<>();
  private Paint textPaint;
  private Paint bgPaint;
  private final float margin = dip2px(getContext(), 2);

  public static IconBuilder iconBuilder(String text) {
    return new IconBuilder(text);
  }

  public FoodIconView(Context context) {
    this(context, null);
  }

  public FoodIconView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public FoodIconView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    textPaint.setStyle(Paint.Style.FILL);
    bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    setMeasuredDimension(0, 0);
    if (CollectionsUtils.isEmpty(iconBuilders)) {
      return;
    }
    float width = 0;
    float height = 0;
    for (IconBuilder iconBuilder : iconBuilders) {
      textPaint.setTextSize(iconBuilder.textSize);
      textPaint.setTypeface(iconBuilder.bold ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
      Rect textRect = new Rect();
      textPaint.getTextBounds(iconBuilder.text, 0, iconBuilder.text.length(), textRect);
      width += iconBuilder.paddingL
          + iconBuilder.paddingR
          + textRect.width()
          + iconBuilder.strokeWidth * 2
          + margin;
      float iconH = iconBuilder.paddingT
          + iconBuilder.paddingB
          + textRect.height()
          + iconBuilder.strokeWidth * 2;
      height = Math.max(height, iconH);
    }
    width -= margin;
    setMeasuredDimension((int) width, (int) height);
  }

  @Override protected void onDraw(Canvas canvas) {
    if (CollectionsUtils.isEmpty(iconBuilders)) {
      return;
    }
    float mh = getMeasuredHeight();
    float l = 0;
    Paint.FontMetricsInt fontMetrics = textPaint.getFontMetricsInt();
    float baselineY = (mh - fontMetrics.bottom - fontMetrics.top) / 2;
    for (IconBuilder iconBuilder : iconBuilders) {
      int strokeWidth = iconBuilder.strokeWidth;
      textPaint.setTextSize(iconBuilder.textSize);
      textPaint.setColor(iconBuilder.textColor);
      textPaint.setTypeface(iconBuilder.bold ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);

      // bg
      int radius = iconBuilder.maxRadius ? (int) (mh / 2) : iconBuilder.radius;
      Rect textRect = new Rect();
      textPaint.getTextBounds(iconBuilder.text, 0, iconBuilder.text.length(), textRect);
      float textLength = textRect.width();

      float bgW = textLength + iconBuilder.paddingL + iconBuilder.paddingR + strokeWidth * 2;
      float bgH = mh;
      if (iconBuilder.bgFillColor != TRANSPARENT) {
        RectF rect = new RectF(l, 0, l + bgW, bgH);
        bgPaint.setStyle(Paint.Style.FILL);
        bgPaint.setColor(iconBuilder.bgFillColor);
        canvas.drawRoundRect(rect, radius, radius, bgPaint);
      }
      if (iconBuilder.bgStrokeColor != TRANSPARENT) {
        RectF rect = new RectF(l + strokeWidth / 2, strokeWidth / 2, l + bgW - strokeWidth / 2,
            bgH - strokeWidth / 2);
        bgPaint.setStyle(Paint.Style.STROKE);
        bgPaint.setColor(iconBuilder.bgStrokeColor);
        bgPaint.setStrokeWidth(strokeWidth);
        canvas.drawRoundRect(rect, radius, radius, bgPaint);
      }

      // text
      canvas.drawText(iconBuilder.text, l + iconBuilder.paddingL + strokeWidth, baselineY,
          textPaint);

      l += iconBuilder.paddingL
          + iconBuilder.paddingR
          + textLength
          + margin
          + strokeWidth * 2;
    }
  }

  public void iconBuilders(List<IconBuilder> iconBuilders) {
    this.iconBuilders = iconBuilders;
    invalidate();
    requestLayout();
  }

  public void iconBuilder(IconBuilder iconBuilder) {
    iconBuilders.clear();
    iconBuilders.add(iconBuilder);
    invalidate();
    requestLayout();
  }

  public static class IconBuilder {
    private Context ctx;

    @NonNull private String text;
    private int textSize = sp2px(ctx, 10);
    private int textColor = BLACK;
    private int bgFillColor = TRANSPARENT;
    private int strokeWidth = 0;
    private int bgStrokeColor = TRANSPARENT;
    private boolean bold = false;
    private int radius = dip2px(ctx, 2);
    private boolean maxRadius = false;
    private int paddingL = dip2px(ctx, 4);
    private int paddingT = dip2px(ctx, 1);
    private int paddingR = dip2px(ctx, 4);
    private int paddingB = dip2px(ctx, 1);

    private IconBuilder(String text) {
      this.text = text;
    }

    public IconBuilder textSize(int textSize) {
      this.textSize = textSize;
      return this;
    }

    public IconBuilder textColor(int textColor) {
      this.textColor = textColor;
      return this;
    }

    public IconBuilder radius(int radius) {
      this.radius = radius;
      return this;
    }

    public IconBuilder paddingL(int paddingL) {
      this.paddingL = paddingL;
      return this;
    }

    public IconBuilder paddingT(int paddingT) {
      this.paddingT = paddingT;
      return this;
    }

    public IconBuilder paddingR(int paddingR) {
      this.paddingR = paddingR;
      return this;
    }

    public IconBuilder paddingB(int paddingB) {
      this.paddingB = paddingB;
      return this;
    }

    public IconBuilder padding(int padding) {
      this.paddingL = padding;
      this.paddingT = padding;
      this.paddingR = padding;
      this.paddingB = padding;
      return this;
    }

    public IconBuilder bold(boolean bold) {
      this.bold = bold;
      return this;
    }

    public IconBuilder bgFillColor(int bgFillColor) {
      this.bgFillColor = bgFillColor;
      return this;
    }

    public IconBuilder bgStrokeColor(int bgStrokeColor) {
      this.bgStrokeColor = bgStrokeColor;
      return this;
    }

    public IconBuilder maxRadius(boolean maxRadius) {
      this.maxRadius = maxRadius;
      return this;
    }

    public IconBuilder strokeWidth(int strokeWidth) {
      this.strokeWidth = strokeWidth;
      return this;
    }
  }
}
