package org.peace.allinone.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import me.ele.base.utils.AppLogger;

/**
 * Created by peacepassion on 15/9/9.
 */
public class CartPopupContentLayout extends LinearLayout {

  private View headView;
  private ViewGroup listView;

  private int maxHeight;
  private int headViewHeight;
  private int listViewItemHeight;

  public CartPopupContentLayout(Context context) {
    this(context, null);
  }

  public CartPopupContentLayout(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public CartPopupContentLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    maxHeight = DimenUtil.dip2px(context, 390);
    headViewHeight = DimenUtil.dip2px(context, 46);
    listViewItemHeight = DimenUtil.dip2px(context, 64);
  }

  void setChildrenView(View headView, ViewGroup listView) {
    this.headView = headView;
    this.listView = listView;
    invalidate();
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int w = MeasureSpec.getSize(widthMeasureSpec), h = MeasureSpec.getSize(heightMeasureSpec);
    AppLogger.e("measured height: " + getMeasuredHeight());

    if (headView != null && listView != null) {
      headView.measure(MeasureSpec.makeMeasureSpec(w, MeasureSpec.EXACTLY),
          MeasureSpec.makeMeasureSpec(headViewHeight, MeasureSpec.EXACTLY));

      LayoutParams lp = new LayoutParams(w, headViewHeight);
      headView.setLayoutParams(lp);

      int num = 0;
      if (listView instanceof ListView) {
        ListView lv = (ListView) listView;
        num = lv.getAdapter().getCount();
      } else if (listView instanceof ScrollView) {
        num = ((ViewGroup) listView.getChildAt(0)).getChildCount();
      }
      if (num * listViewItemHeight + headView.getMeasuredHeight() > maxHeight) {
        //listView.measure(MeasureSpec.makeMeasureSpec(w, MeasureSpec.EXACTLY),
        //    MeasureSpec.makeMeasureSpec(maxHeight - headViewHeight, MeasureSpec.EXACTLY));
        lp.height = maxHeight - headViewHeight;
      } else {
        lp.height = num * listViewItemHeight;
        //listView.measure(MeasureSpec.makeMeasureSpec(w, MeasureSpec.EXACTLY),
        //    MeasureSpec.makeMeasureSpec(num * listViewItemHeight, MeasureSpec.EXACTLY));
      }

      listView.setLayoutParams(lp);
    }

    super.onMeasure(MeasureSpec.makeMeasureSpec(w, MeasureSpec.EXACTLY),
        MeasureSpec.makeMeasureSpec(h, MeasureSpec.EXACTLY));
  }
}