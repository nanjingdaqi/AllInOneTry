package org.peace.allinone.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.TextView;
import me.ele.commons.DimenUtil;
import org.peace.allinone.R;

public class CouYiCouButton extends TextView {

    private Paint paint;

    private int triangleHeight;
    private int radius;

    public CouYiCouButton(Context context) {
        this(context, null);
    }

    public CouYiCouButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CouYiCouButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        triangleHeight = DimenUtil.dip2px(getContext(), 8);
        radius = DimenUtil.dip2px(getContext(), 4);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(getResources().getColor(R.color.blue));
        paint.setStyle(Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int h = getMeasuredHeight();
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), h + triangleHeight);
        setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom() + triangleHeight / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isInEditMode()) {
            super.onDraw(canvas);
            return;
        }
        RectF rectF = new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight() - triangleHeight);
        canvas.drawRoundRect(rectF, radius, radius, paint);
        Path path = new Path();
        int l = getMeasuredWidth() / 2 - triangleHeight / 2;
        int r = l + triangleHeight;
        int h = getMeasuredHeight() - triangleHeight;
        path.moveTo(l, h);
        path.lineTo(r, h);
        path.lineTo((l + r) / 2, getMeasuredHeight());
        path.lineTo(l, h);
        path.close();
        canvas.drawPath(path, paint);
        super.onDraw(canvas);
    }

}