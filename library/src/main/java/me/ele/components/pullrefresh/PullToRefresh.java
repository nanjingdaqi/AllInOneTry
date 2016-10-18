package me.ele.components.pullrefresh;

import android.view.ViewGroup;

public interface PullToRefresh {
  ViewGroup getLayout();

  void setRefreshing(boolean refreshing);

  boolean isRefreshing();

  void setOnRefreshListener(OnRefreshListener listener);

  void setProgressViewOffsetTop(int offset);

  void setOnTargetOffsetListener(OnTargetOffsetListener listener);

  void postPendingTask(Runnable runnable);

  interface OnRefreshListener {
    void onRefresh();
  }

  interface OnTargetOffsetListener {
    void onTargetOffset(int offset);
  }
}
