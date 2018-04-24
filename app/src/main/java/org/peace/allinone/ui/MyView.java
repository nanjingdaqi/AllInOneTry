package org.peace.allinone.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import org.peace.allinone.R;

/**
 * Created by daqi on 18-4-24.
 */

public class MyView extends View {
    BitmapDrawable bitmapDrawable;
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmapDrawable = (BitmapDrawable) getContext().getResources().getDrawable(R.drawable.screen_color_preview);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG | Paint.DITHER_FLAG);
        canvas.drawBitmap(bitmapDrawable.getBitmap(), null, new Rect(0, 0, getWidth() - 20, getHeight() - 20), paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(bitmapDrawable.getIntrinsicWidth(), bitmapDrawable.getIntrinsicHeight());
    }
}
