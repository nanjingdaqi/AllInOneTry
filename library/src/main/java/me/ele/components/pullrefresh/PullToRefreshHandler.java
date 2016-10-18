package me.ele.components.pullrefresh;

import android.view.View;

interface PullToRefreshHandler {
  View getStage();

  void onStart();

  void onPulling(float percent);

  void onPreFinish();

  void onFinish();

  void onReset();

  void setProgressViewOffsetTop(int offset);
}
