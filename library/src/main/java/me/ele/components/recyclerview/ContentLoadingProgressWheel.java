package me.ele.components.recyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.pnikosis.materialishprogress.ProgressWheel;

public class ContentLoadingProgressWheel extends ProgressWheel {

  private static final int MIN_SHOW_TIME = 500; // ms
  private static final int MIN_DELAY = 500; // ms

  private long startTime = -1;
  private boolean postedHide;
  private boolean postedShow;
  private boolean dismissed;
  private int showCount;

  private final Runnable mDelayedHide = new Runnable() {

    @Override public void run() {
      postedHide = false;
      startTime = -1;
      if (showCount <= 0) {
        changeVisibility(View.GONE);
      }
    }
  };

  private final Runnable mDelayedShow = new Runnable() {

    @Override public void run() {
      postedShow = false;
      if (!dismissed) {
        startTime = System.currentTimeMillis();
        changeVisibility(View.VISIBLE);
      }
    }
  };

  public ContentLoadingProgressWheel(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public ContentLoadingProgressWheel(Context context) {
    super(context);
  }

  @Override public void onAttachedToWindow() {
    super.onAttachedToWindow();
    removeCallbacks();
  }

  @Override public void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    removeCallbacks();
  }

  private void removeCallbacks() {
    removeCallbacks(mDelayedHide);
    removeCallbacks(mDelayedShow);
  }

  @Override public void setVisibility(int visibility) {
    if (View.VISIBLE == visibility) {
      show();
    } else {
      hide();
    }
  }

  public void changeVisibility(int visibility) {
    super.setVisibility(visibility);
  }

  /**
   * Hide the progress view if it is visible. The progress view will not be
   * hidden until it has been shown for at least a minimum show time. If the
   * progress view was not yet visible, cancels showing the progress view.
   */
  public void hide() {
    dismissed = true;
    showCount--;
    removeCallbacks(mDelayedShow);
    postedShow = false;
    long diff = System.currentTimeMillis() - startTime;
    if ((diff >= MIN_SHOW_TIME || startTime == -1) && showCount <= 0) {
      // The progress spinner has been shown long enough
      // OR was not shown yet. If it wasn't shown yet,
      // it will just never be shown.
      changeVisibility(View.GONE);
    } else {
      // The progress spinner is shown, but not long enough,
      // so put a delayed message in to hide it when its been
      // shown long enough.
      if (!postedHide) {
        postDelayed(mDelayedHide, MIN_SHOW_TIME - diff);
        postedHide = true;
      }
    }
  }

  /**
   * Show the progress view after waiting for a minimum delay. If
   * during that time, hide() is called, the view is never made visible.
   */
  public void show() {
    // Reset the start time.
    startTime = -1;
    dismissed = false;
    showCount++;
    removeCallbacks(mDelayedHide);
    postedHide = false;
    if (!postedShow) {
      postDelayed(mDelayedShow, MIN_DELAY);
      postedShow = true;
    }
  }
}
