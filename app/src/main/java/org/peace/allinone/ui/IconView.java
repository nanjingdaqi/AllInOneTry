package org.peace.allinone.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import me.ele.commons.AppLogger;
import me.ele.commons.CollectionsUtils;
import org.peace.allinone.R;

public class IconView extends View {

  private Paint paint;

  private int textColor;
  private float radius;
  private float padding;
  private int itemMargin;
  private float textSize;
  private int itemHeight;
  private boolean isSolid;
  private Drawable leftDrawable;
  private float drawablePadding;

  private List<CharacterIcon> icons = new ArrayList<>();

  private static final int DEFAULT_RADIUS = 1;
  private static final int DEFAULT_TEXT_SP = 13;
  private static final int DEFAULT_PADDING = 2;

  private int availableWidth;
  private List<List<Integer>> lineChildIndex = new ArrayList<>();
  private List<LayoutParam> childLayoutParams = new ArrayList<>();
  private int currentLineWidth = 0;
  private int maxWidth = 0;
  private List<Integer> currentLineChildIndex;

  public IconView(Context context) {
    this(context, null);
  }

  public IconView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public IconView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    if (isInEditMode()) return;

    //Retrieve styles attributes
    TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.IconView, defStyleAttr, 0);

    textColor = array.getColor(R.styleable.IconView_textColor, Color.WHITE);
    radius = array.getDimension(R.styleable.IconView_itemRadius,
        DimenUtil.dip2px(context, DEFAULT_RADIUS));
    padding = array.getDimension(R.styleable.IconView_padding,
        DimenUtil.dip2px(context, DEFAULT_PADDING));
    itemMargin = (int) array.getDimension(R.styleable.IconView_itemMargin,
        DimenUtil.dip2px(context, DEFAULT_PADDING));
    textSize = array.getDimension(R.styleable.IconView_textSize,
        DimenUtil.dip2px(context, DEFAULT_TEXT_SP));
    isSolid = array.getBoolean(R.styleable.IconView_isSolid, true);
    leftDrawable = array.getDrawable(R.styleable.IconView_drawableLeft);
    drawablePadding = array.getDimension(R.styleable.IconView_drawablePadding, 0);
    array.recycle();

    paint = new Paint();
    paint.setAntiAlias(true);

    itemHeight = (int) (textSize + 2 * padding);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    if (CollectionsUtils.isEmpty(icons)) {
      setMeasuredDimension(0, 0);
      return;
    }

    lineChildIndex.clear();
    childLayoutParams.clear();

    maxWidth = 0;

    int widthSize = getDefaultSize(Integer.MAX_VALUE, widthMeasureSpec);
    availableWidth = widthSize - getPaddingLeft() - getPaddingRight();
    newLine();

    float lineHeight = itemHeight + padding;
    for (int i = 0; i < icons.size(); i++) {
      CharacterIcon icon = icons.get(i);
      int childWidthSize = (int) (getIconTextWidth(icon) + 2 * padding);
      LayoutParam lp = new LayoutParam(childWidthSize, itemHeight);
      if (currentLineWidth + childWidthSize > availableWidth && icons.size() > 1) {
        // end last line
        endLine();
        newLine();
      }
      currentLineChildIndex.add(i);
      lp.left = currentLineWidth;
      lp.top = (lineHeight + itemMargin) * (lineChildIndex.size()) + padding / 2;
      childLayoutParams.add(lp);
      currentLineWidth += childWidthSize + itemMargin;

      if (i == 0 && leftDrawable != null) {
        //仅在第一个icon上画drawable
        currentLineWidth += getLeftDrawableWidth();
        currentLineWidth += drawablePadding;
      }
    }
    endLine();

    int measuredWidth = maxWidth;
    int measuredHeight = (int) ((lineHeight + itemMargin) * lineChildIndex.size() - itemMargin);
    setMeasuredDimension(measuredWidth, measuredHeight);

    AppLogger.e("mw: " + measuredWidth);
  }

  private void endLine() {
    currentLineWidth -= itemMargin;
    lineChildIndex.add(currentLineChildIndex);
    maxWidth = Math.max(currentLineWidth, maxWidth);
  }

  private void newLine() {
    currentLineChildIndex = new ArrayList<Integer>();
    currentLineWidth = 0;
  }

  private float getIconTextWidth(CharacterIcon icon) {
    float length = getIconTextMeasureWidth(icon);
    if (length < textSize) {
      return textSize;
    }
    return length;
  }

  private float getIconTextMeasureWidth(CharacterIcon icon) {
    paint.setStyle(Paint.Style.FILL);
    paint.setTextSize(textSize);
    return paint.measureText(icon.getCharacter(), 0, icon.getCharacter().length());
  }

  private int getLeftDrawableWidth() {
    if (leftDrawable != null) {
      return leftDrawable.getIntrinsicWidth();
    }
    return 0;
  }

  private int getLeftDrawableHeight() {
    if (leftDrawable != null) {
      return leftDrawable.getIntrinsicHeight();
    }
    return 0;
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    if (!CollectionsUtils.isEmpty(lineChildIndex)) {
      for (int line = 0; line < lineChildIndex.size(); line++) {
        List<Integer> lineIndex = lineChildIndex.get(line);
        for (int i = 0; i < lineIndex.size(); i++) {
          CharacterIcon icon = icons.get(lineIndex.get(i));
          LayoutParam lp = childLayoutParams.get(lineIndex.get(i));

          float top = lp.top;
          float right = lp.left + getIconTextWidth(icon) + 2 * padding;
          if (leftDrawable != null) {
            right = right + getLeftDrawableWidth() + drawablePadding;
          }
          float bottom = top + lp.height;
          paint.setStyle(isSolid ? Paint.Style.FILL : Paint.Style.STROKE);
          paint.setColor(icon.getBackgroundColor());

          AppLogger.e("icon right: " + right);

          RectF rectF = new RectF(lp.left, top, Math.min(right, getMeasuredWidth()), bottom);
          canvas.drawRoundRect(rectF, radius, radius, paint);
          if (leftDrawable != null) {
            int l = (int) (lp.left + padding);
            int t = (int) (top + padding) + 1;
            int r = l + getLeftDrawableWidth();
            int b = t + getLeftDrawableHeight();
            leftDrawable.setBounds(l, t, r, b);
            leftDrawable.draw(canvas);
          }
          paint.setColor(isSolid ? textColor : icon.getBackgroundColor());
          paint.setStyle(Paint.Style.FILL);
          paint.setTextSize(textSize);

          RectF bounds = new RectF(rectF);
          bounds.bottom = paint.getFontMetrics().descent - paint.getFontMetrics().ascent;
          bounds.right = getIconTextMeasureWidth(icon);
          bounds.top += (rectF.height() - bounds.bottom) / 2.0f;
          if (leftDrawable != null) {
            bounds.left += getLeftDrawableWidth() + drawablePadding + padding;
          } else {
            bounds.left += (rectF.width() - bounds.right) / 2.0f;
          }

          canvas.drawText(icon.getCharacter(), bounds.left,
              bounds.top - paint.getFontMetrics().ascent + 1, paint);
        }
      }
    }
  }

  public void setIcons(List<? extends CharacterIcon> icons) {
    this.icons.clear();
    if (CollectionsUtils.isEmpty(icons)) {
      setVisibility(View.GONE);
      return;
    }

    for (int i = 0, size = icons.size(); i < size; i++) {
      if (!TextUtils.isEmpty(icons.get(i).getCharacter())) {
        this.icons.add(icons.get(i));
      }
    }

    if (this.icons.isEmpty()) {
      setVisibility(View.GONE);
      return;
    }

    setVisibility(View.VISIBLE);
    requestLayout();
    postInvalidate();
  }

  public void setIcon(CharacterIcon icon) {
    this.icons.clear();
    if (icon == null) {
      setVisibility(View.GONE);
      return;
    }

    if (TextUtils.isEmpty(icon.getCharacter())) {
      setVisibility(View.GONE);
      return;
    }

    setVisibility(View.VISIBLE);
    this.icons.add(icon);

    requestLayout();
    postInvalidate();
  }

  public int getTextColor() {
    return textColor;
  }

  public void setTextColor(int textColor) {
    this.textColor = textColor;
  }

  public float getRadius() {
    return radius;
  }

  public void setRadius(float radius) {
    this.radius = radius;
  }

  public float getPadding() {
    return padding;
  }

  public void setPadding(float padding) {
    this.padding = padding;
  }

  public float getTextSize() {
    return textSize;
  }

  public void setTextSize(float textSize) {
    this.textSize = textSize;
  }

  public boolean isSolid() {
    return isSolid;
  }

  public void setIsSolid(boolean isSolid) {
    this.isSolid = isSolid;
  }

  public float getItemMargin() {
    return itemMargin;
  }

  public void setItemMargin(float itemMargin) {
    this.itemMargin = (int) itemMargin;
  }

  public Drawable getLeftDrawable() {
    return leftDrawable;
  }

  public void setLeftDrawable(Drawable leftDrawable) {
    this.leftDrawable = leftDrawable;
    requestLayout();
    postInvalidate();
  }

  public static class LayoutParam {
    private float left = -1;
    private float top = -1;
    private float width = -1;
    private float height = -1;

    LayoutParam(float w, float h) {
      this.width = w;
      this.height = h;
    }
  }
}
