package org.peace.allinone.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;

import static android.view.View.MeasureSpec.AT_MOST;
import static android.view.View.MeasureSpec.EXACTLY;
import static android.view.View.MeasureSpec.makeMeasureSpec;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static me.ele.commons.DimenUtil.dip2px;

public class ShopLogoView extends View {

  private TextView statusView;

  public ShopLogoView(Context context) {
    this(context, null);
  }

  public ShopLogoView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public ShopLogoView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    statusView = new TextView(context);
    statusView.setMaxLines(1);
    statusView.setPadding(dip2px(context,3), 0, dip2px(context, 3), 0);
    statusView.setTextColor(Color.BLACK);
    statusView.setTextSize(8);
    statusView.setGravity(Gravity.CENTER);
    statusView.setLayoutParams(new LayoutParams(MATCH_PARENT, WRAP_CONTENT));
    statusView.setText("fuck");
    statusView.setVisibility(VISIBLE);
    statusView.setBackgroundColor(Color.BLUE);
  }

  @Override protected void dispatchDraw(Canvas canvas) {
    super.dispatchDraw(canvas);
    if (statusView.getVisibility() == VISIBLE) {
      canvas.save();
      canvas.translate(0, getMeasuredHeight() - statusView.getMeasuredHeight());
      statusView.draw(canvas);
      canvas.restore();
    }
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    statusView.measure(makeMeasureSpec(getMeasuredWidth(), EXACTLY),
        makeMeasureSpec(getMeasuredHeight(), AT_MOST));
  }

  @Override protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    super.onLayout(changed, left, top, right, bottom);
    statusView.layout(0, 0, statusView.getMeasuredWidth(), statusView.getMeasuredHeight());
  }

  public TextView getStatusView() {
    return statusView;
  }
}
