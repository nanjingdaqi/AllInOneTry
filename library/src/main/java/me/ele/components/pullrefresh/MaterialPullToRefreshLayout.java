package me.ele.components.pullrefresh;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import me.ele.recyclerview.R;

public class MaterialPullToRefreshLayout extends SwipeRefreshLayout implements PullToRefresh {
  private PullToRefresh.OnRefreshListener mListener;

  public MaterialPullToRefreshLayout(Context context) {
    this(context, null);
  }

  public MaterialPullToRefreshLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
    setColorSchemeColors(Color.parseColor("#3190e8"));
  }

  @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {
    super.onLayout(changed, l, t, r, b);
    for (int i = 0; i < getChildCount(); i++) {
      final View child = getChildAt(i);
      if (child.getId() == R.id.id_sticky_container) {
        int measuredWidth = child.getMeasuredWidth();
        int measureHeight = child.getMeasuredHeight();
        child.layout(l, t, l + measuredWidth, t + measureHeight);
        break;
      }
    }
  }

  @Override public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    for (int i = 0; i < getChildCount(); i++) {
      View child = getChildAt(i);
      if (child.getId() == R.id.id_sticky_container) {
        child.measure(MeasureSpec.makeMeasureSpec(getMeasuredWidth(), MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(getMeasuredHeight(), MeasureSpec.EXACTLY));
        break;
      }
    }
  }

  @Override public void setRefreshing(boolean refreshing) {
    if (refreshing) {
      super.setRefreshing(true);
      if (mListener != null) {
        mListener.onRefresh();
      }
    } else {
      super.setRefreshing(false);
    }
  }

  @Override public ViewGroup getLayout() {
    return this;
  }

  @Override public void setOnRefreshListener(PullToRefresh.OnRefreshListener listener) {
    this.mListener = listener;
    setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override public void onRefresh() {
        if (mListener != null) {
          mListener.onRefresh();
        }
      }
    });
  }

  @Override public void setProgressViewOffsetTop(int offset) {
    throw new UnsupportedOperationException();
  }

  @Override public void postPendingTask(Runnable runnable) {
    throw new UnsupportedOperationException();
  }

  @Override public void setOnTargetOffsetListener(OnTargetOffsetListener listener) {
    throw new UnsupportedOperationException();
  }
}