package org.peace.allinone.ui;

import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.peace.allinone.R;

/**
 * Created by peacepassion on 15/8/24.
 */
public class TagAheadView extends FrameLayout {

    private String tagContent = "学校";

    private String mainContent = "上海市铺头去嘉定国际赛场车大军阀考多少分煎熬答复打算打发";

    private String mainContentPadding;

    private int tagContentLength;

    private TextView tagView;
    private TextView mainView;

    public TagAheadView(Context context) {
        super(context);
        init();
    }

    public TagAheadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TagAheadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        tagView = (TextView) inflater.inflate(R.layout.tag_view, this, false);
        tagView.setTextSize(12);
        tagView.setText(tagContent);
        tagView.setBackgroundDrawable(getResources().getDrawable(R.drawable.tag_bg));
        addView(tagView);
        mainView = (TextView) inflater.inflate(R.layout.tag_view, this, false);
        mainView.setTextSize(12);
        addView(mainView);
        tagView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int left = tagView.getMeasuredWidth();
                Paint paint = new Paint();
                paint.setTextSize(24);
                StringBuilder spaceBuilder = new StringBuilder("");
                while (paint.measureText(spaceBuilder.toString()) < left) {
                    spaceBuilder.append(" ");
                }
                mainView.setText(spaceBuilder.toString() + mainContent);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    mainView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    mainView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });
    }



    public void setTagContent(String tagContent) {
        this.tagContent = tagContent;
    }

    public void setTagContentLength(int tagContentLength) {
        this.tagContentLength = tagContentLength;
    }

    public void setMainContent(String mainContent) {
        this.mainContent = mainContent;
    }

    public void setMainContentPadding(String mainContentPadding) {
        this.mainContentPadding = mainContentPadding;
    }
}
