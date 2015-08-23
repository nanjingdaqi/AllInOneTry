package com.example.peacepassion.library;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import me.ele.commons.AppLogger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peacepassion on 15/8/23.
 */
public class WeixinTabLayout extends ViewGroup implements OnPageChangeListener {

    private List<UnitViewHolder> viewHolders = new ArrayList<>();
    private int selectedPage = 0;

    public WeixinTabLayout(Context context) {
        super(context);
    }

    public WeixinTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WeixinTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setUpViewPager(ViewPager viewPager, PagerAdapter adapter, List<DataHolder> dataHolders) {
        viewPager.addOnPageChangeListener(this);
        if (adapter.getCount() == 0) {
            throw new IllegalStateException("adapter should has at least one item");
        }
        if (adapter.getCount() != dataHolders.size()) {
            throw new IllegalStateException("adapter should has the same length as unit view holders");
        }
        for (DataHolder dataHolder : dataHolders) {
            RelativeLayout v = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.unit, this, false);
            TextView tv = (TextView) v.findViewById(R.id.text);
            tv.setText(dataHolder.title);
            ImageView iv = (ImageView) v.findViewById(R.id.image_bg);
            iv.setImageDrawable(dataHolder.back);
            ImageView iv2 = (ImageView) v.findViewById(R.id.image_front);
            iv2.setImageDrawable(dataHolder.front);
            iv2.setAlpha(0);
            viewHolders.add(new UnitViewHolder(tv, iv, iv2));
            addView(v);
        }
        selectedPage = 0;
        viewHolders.get(selectedPage).front.setAlpha(255);
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

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        AppLogger.d("position: " + position + ", offset: " + positionOffset);
        viewHolders.get(position).front.setAlpha((int) ((1 - positionOffset) * 255));
        if (position + 1 < viewHolders.size()) {
            viewHolders.get(position + 1).front.setAlpha((int) (positionOffset * 255));
        }
    }

    @Override
    public void onPageSelected(int position) {
        AppLogger.d("position: " + position);
        selectedPage = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private static class UnitViewHolder {
        private TextView title;
        private ImageView bg;
        private ImageView front;

        public UnitViewHolder(TextView title, ImageView bg, ImageView front) {
            this.title = title;
            this.bg = bg;
            this.front = front;
        }
    }

}
