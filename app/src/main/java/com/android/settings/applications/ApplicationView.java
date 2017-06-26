package com.android.settings.applications;


import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import org.peace.allinone.R;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static android.widget.ImageView.ScaleType.FIT_CENTER;

public class ApplicationView extends FrameLayout {

    private ImageView mIconView;
    private TextView mAppNameView;
    private TextView mSizeView;
    private TextView mStatusView;

    private Resources mRes = getContext().getResources();

    public ApplicationView(@NonNull Context context) {
        super(context);

        // todo: set bg

        mIconView = new ImageView(context);
        int sz = mRes.getDimensionPixelSize(R.dimen.application_icon_size);
        mIconView.setLayoutParams(new LayoutParams(sz, sz));
        mIconView.setScaleType(FIT_CENTER);
        addView(mIconView);

        mAppNameView = new TextView(context);
        mAppNameView.setLayoutParams(new LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        mAppNameView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        // todo: set tv style
        addView(mAppNameView);

        mSizeView = new TextView(context);
        mSizeView.setLayoutParams(new LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        // todo: set tv style
        addView(mSizeView);

        mStatusView = new TextView(context);
        mStatusView.setLayoutParams(new LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        // todo: set tv style
        addView(mStatusView);
    }

//    @Override
//    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        int width = getMeasuredWidth();
//        int height = getMeasuredHeight();
//        if (getLayoutDirection() != LAYOUT_DIRECTION_RTL) {
//            mIconView.layout();
//        } else {
//
//        }
//    }
}
