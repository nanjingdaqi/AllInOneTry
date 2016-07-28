package org.peace.allinone.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.ButterKnife;
import butterknife.InjectView;
import me.ele.commons.AppLogger;
import org.peace.allinone.R;

import static android.view.View.MeasureSpec.EXACTLY;
import static android.view.View.MeasureSpec.makeMeasureSpec;

public class ActivityEntranceLayout extends LinearLayout {

  @InjectView(R.id.left) protected View leftView;
  @InjectView(R.id.right) protected View rightView;
  @InjectView(R.id.right_top) protected View rightTopView;
  @InjectView(R.id.right_left) protected View rightLeftView;
  @InjectView(R.id.right_right) protected View rightRightView;
  @InjectView(R.id.right_bottom) protected View rightBottomView;

  public ActivityEntranceLayout(Context context) {
    this(context, null);
  }

  public ActivityEntranceLayout(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public ActivityEntranceLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    ButterKnife.inject(this, this);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    int w = 0;
    int h = 0;

    w = leftView.getMeasuredWidth();
    h = leftView.getMeasuredHeight();
    AppLogger.e("left w: " + w + ", h: " + h);

    h = w * 510 / 450;
    leftView.measure(makeMeasureSpec(w, EXACTLY), makeMeasureSpec(h, EXACTLY));
    w = leftView.getMeasuredWidth();
    h = leftView.getMeasuredHeight();
    AppLogger.e("left w: " + w + ", h: " + h);


    w = rightView.getMeasuredWidth();
    h = rightView.getMeasuredHeight();
    AppLogger.e("right w: " + w + ", h: " + h);


    h = w * 510 / 677;
    h = leftView.getMeasuredHeight();
    rightView.measure(makeMeasureSpec(w, EXACTLY), makeMeasureSpec(h, EXACTLY));
    w = rightView.getMeasuredWidth();
    h = rightView.getMeasuredHeight();
    AppLogger.e("right w: " + w + ", h: " + h);



    w = rightTopView.getMeasuredWidth();
    h = rightTopView.getMeasuredHeight();
    AppLogger.e("right top w: " + w + ", h: " + h);

    h = w * 204 / 677;
    rightTopView.measure(makeMeasureSpec(w, EXACTLY), makeMeasureSpec(h, EXACTLY));
    w = rightTopView.getMeasuredWidth();
    h = rightTopView.getMeasuredHeight();
    AppLogger.e("right top w: " + w + ", h: " + h);


    w = rightBottomView.getMeasuredWidth();
    h = rightBottomView.getMeasuredHeight();
    AppLogger.e("right bottom w: " + w + ", h: " + h);

    h = w * 305 / 677;
    h = rightView.getMeasuredHeight() - 1 - rightTopView.getMeasuredHeight();
    rightBottomView.measure(makeMeasureSpec(w, EXACTLY), makeMeasureSpec(h, EXACTLY));
    w = rightBottomView.getMeasuredWidth();
    h = rightBottomView.getMeasuredHeight();
    AppLogger.e("right bottom w: " + w + ", h: " + h);


    w = rightLeftView.getMeasuredWidth();
    h = rightLeftView.getMeasuredHeight();
    AppLogger.e("right left w: " + w + ", h: " + h);

    h = w * 305 / 338;
    h = rightView.getMeasuredHeight() - 1 - rightTopView.getMeasuredHeight();
    AppLogger.e("h: " + h);
    rightLeftView.measure(makeMeasureSpec(w, EXACTLY), makeMeasureSpec(h, EXACTLY));
    w = rightLeftView.getMeasuredWidth();
    h = rightLeftView.getMeasuredHeight();
    AppLogger.e("right left w: " + w + ", h: " + h);

  }
}
