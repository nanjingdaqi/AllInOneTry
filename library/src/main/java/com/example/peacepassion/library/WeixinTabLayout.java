package com.example.peacepassion.library;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by peacepassion on 15/8/23.
 */
public class WeixinTabLayout extends ViewGroup {
    public WeixinTabLayout(Context context) {
        super(context);
    }

    public WeixinTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WeixinTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void addUnit(Drawable drawable, String title) {
        RelativeLayout v = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.unit, this, false);
        TextView tv = (TextView) v.findViewById(R.id.text);
        tv.setText(title);
        ImageView iv = (ImageView) v.findViewById(R.id.image);
        iv.setImageDrawable(drawable);
        addView(v);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int wSize = MeasureSpec.getSize(widthMeasureSpec);
        int hSize = MeasureSpec.getSize(heightMeasureSpec);
        int count = getChildCount();
        if (count == 0) {
            wSize = 0;
            hSize = getPaddingBottom() + getPaddingTop();
        }
        else {
            int cw = wSize / count;
            measureChildren(MeasureSpec.makeMeasureSpec(cw, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(hSize, MeasureSpec.AT_MOST));
            hSize = getChildAt(0).getMeasuredHeight() + getPaddingBottom() + getPaddingTop();
        }
        setMeasuredDimension(wSize, hSize);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int count = getChildCount();
        if (count != 0) {
            int l = 0, t = getPaddingTop(), r = 0, b = t + getMeasuredHeight(), cw = getMeasuredWidth() / count;
            for (int i = 0; i < count; ++i) {
                View child = getChildAt(i);
                r += cw;
                child.layout(l, t, r, b);
                l = r;
            }
        }
    }
}
