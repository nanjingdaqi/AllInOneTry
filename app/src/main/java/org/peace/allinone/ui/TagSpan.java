package org.peace.allinone.ui;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.style.ReplacementSpan;

public class TagSpan extends ReplacementSpan {

    private RectF mRect;
    private float textSize;
    private int mBackgroundColor;
    private int mForegroundColor;
    private Padding padding;
    private int radius;
    private String fakeContent = "公司";

    public TagSpan(float textSize, int backgroundColor, int foregroundColor, Padding padding, int radius) {
        this.mRect = new RectF();
        this.textSize = textSize;
        this.mBackgroundColor = backgroundColor;
        this.mForegroundColor = foregroundColor;
        this.padding = padding;
        this.radius = radius;
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        // Background
        paint.setColor(mBackgroundColor);
        paint.setTextSize(textSize);
        float l = x;
        float deltaSpace = (bottom - top - textSize - padding.getPaddingTop() - padding.getPaddingBottom()) / 2;
        deltaSpace = deltaSpace < 0 ? 0 : deltaSpace;
        float t = top + deltaSpace;
        float r = x + paint.measureText(text, start, end) + padding.getPaddingLeft() + padding.getPaddingRight();
        float b = bottom - deltaSpace;
        mRect.set(l, t, r, b);
        canvas.drawRoundRect(mRect, radius, radius, paint);

        // Text
        paint.setColor(mForegroundColor);
        float xPos = Math.round(x + padding.getPaddingLeft());
        Paint.FontMetrics fm = paint.getFontMetrics();
        float yPos = t + padding.getPaddingTop() + textSize / 2 - (fm.bottom - fm.top) / 2 - fm.top;
        paint.setTextSize(textSize);
        canvas.drawText(text, start, end, xPos, yPos, paint);
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        paint.setColor(mBackgroundColor);
        paint.setTextSize(textSize);
        return Math.round(paint.measureText(text, start, end) + padding.getPaddingLeft() + padding.getPaddingRight());
    }

    public static class Padding {
        private static final int NO_VALUE = -1;
        private int padding;
        private int paddingLeft = NO_VALUE;
        private int paddingRight = NO_VALUE;
        private int paddingTop = NO_VALUE;
        private int paddingBottom = NO_VALUE;

        public Padding() {
        }

        public Padding(int paddingLeft, int paddingTop, int paddingRight, int paddingBottom) {
            this.paddingBottom = paddingBottom;
            this.paddingLeft = paddingLeft;
            this.paddingRight = paddingRight;
            this.paddingTop = paddingTop;
        }

        public int getPadding() {
            return padding;
        }

        public void setPadding(int padding) {
            this.padding = padding;
        }

        public int getPaddingBottom() {
            return paddingBottom != NO_VALUE ? paddingBottom : padding;
        }

        public void setPaddingBottom(int paddingBottom) {
            this.paddingBottom = paddingBottom;
        }

        public int getPaddingLeft() {
            return paddingLeft != NO_VALUE ? paddingLeft : padding;
        }

        public void setPaddingLeft(int paddingLeft) {
            this.paddingLeft = paddingLeft;
        }

        public int getPaddingRight() {
            return paddingRight != NO_VALUE ? paddingRight : padding;
        }

        public void setPaddingRight(int paddingRight) {
            this.paddingRight = paddingRight;
        }

        public int getPaddingTop() {
            return paddingTop != NO_VALUE ? paddingTop : padding;
        }

        public void setPaddingTop(int paddingTop) {
            this.paddingTop = paddingTop;
        }
    }


}