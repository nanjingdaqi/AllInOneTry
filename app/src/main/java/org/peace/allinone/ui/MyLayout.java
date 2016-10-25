package org.peace.allinone.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import me.ele.base.utils.AppLogger;
import me.ele.base.utils.DimenUtil;
import org.peace.allinone.R;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class MyLayout extends FrameLayout {

  private TextView buyHistoryTV;
  private TextView buyFoodsTV;
  private Button rebuyBtn;

  public MyLayout(Context context) {
    super(context);
    init();
  }

  public MyLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public MyLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    Context ctx = getContext();
    buyHistoryTV = new TextView(ctx);
    buyHistoryTV.setText("Buy History");
    LayoutParams lp = new LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
    addView(buyHistoryTV, lp);

    buyFoodsTV = new TextView(ctx);
    buyFoodsTV.setText(
        "Buy FoodsBuyBuyBuyBuyBuyBuyBuyBuyBuyBuyBuyBuyBuyBuyBuyBuyBuyBuyBuyBuyBuyBuyBuyBuyBuy");
    buyFoodsTV.setSingleLine(true);
    buyFoodsTV.setEllipsize(TextUtils.TruncateAt.END);
    addView(buyFoodsTV, lp);

    rebuyBtn = new Button(ctx);
    rebuyBtn.setText("再来一单");
    Drawable bg = getResources().getDrawable(R.drawable.shape);
    rebuyBtn.setBackgroundDrawable(bg);
    rebuyBtn.setMinimumHeight(DimenUtil.dip2px(0));
    rebuyBtn.setMinHeight(DimenUtil.dip2px(0));
    rebuyBtn.setMinimumWidth(0);
    rebuyBtn.setMinWidth(0);
    rebuyBtn.setIncludeFontPadding(false);
    addView(rebuyBtn, lp);

    setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    int w = MeasureSpec.getSize(widthMeasureSpec);
    int h = DimenUtil.dip2px(60);
    setMeasuredDimension(w, h);
  }

  @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {
    int parentHeight = getMeasuredHeight();
    int parentWidth = getMeasuredWidth();
    AppLogger.d("pw, ph: " + parentWidth + ", " + parentHeight);

    int buyHistoryH = buyHistoryTV.getMeasuredHeight();
    int margin = DimenUtil.dip2px(8);
    int buyFoodH = buyFoodsTV.getMeasuredHeight();

    AppLogger.d("bhh, m, bfh: " + buyHistoryH + ", " + margin + ", " + buyFoodH);

    int paddingLR = DimenUtil.dip2px(22);
    int buyHistoryT = (parentHeight - buyHistoryH - margin - buyFoodH) / 2;
    AppLogger.d("ft: " + buyHistoryT);
    buyHistoryTV.layout(paddingLR, buyHistoryT, paddingLR + buyHistoryTV.getMeasuredWidth(),
        buyHistoryT + buyHistoryTV.getMeasuredHeight());

    int buyFoodT = buyHistoryT + buyHistoryH + margin;
    buyFoodsTV.layout(paddingLR, buyFoodT, paddingLR + buyFoodsTV.getMeasuredWidth(),
        buyFoodT + buyFoodsTV.getMeasuredHeight());

    int rebuyW = rebuyBtn.getMeasuredWidth();
    int rebuyH = rebuyBtn.getMeasuredHeight();

    AppLogger.d("rebuy padding" + ", " + rebuyBtn.getPaddingTop());
    AppLogger.d("rebuy h: " + rebuyH);
    AppLogger.d("rebuy minim h: " + rebuyBtn.getMinHeight());
    AppLogger.d("rebuy cpdt: " + rebuyBtn.getCompoundPaddingTop());
    AppLogger.d("bh pdt: " + buyHistoryTV.getPaddingTop());

    int maxW = parentWidth - paddingLR * 2 - rebuyW - DimenUtil.dip2px(8);
    if (maxW < buyFoodsTV.getMeasuredWidth()) {
      buyFoodsTV.setMaxWidth(maxW);
    }

    int rebuyL = parentWidth - paddingLR - rebuyW;
    int rebuyT = (parentHeight - rebuyH) / 2;
    rebuyBtn.layout(rebuyL, rebuyT, rebuyL + rebuyW, rebuyT + rebuyH);
  }
}
