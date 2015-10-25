package org.peace.allinone.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Scroller;
import me.ele.commons.AppLogger;

/**
 * Created by peacepassion on 15/10/25.
 */
public class MyPTR extends FrameLayout {

  View head;
  View body;

  PTRListener listener;

  float lastY;
  float lastInterceptY;
  int REFRESH_OFFSET;
  Scroller scroller;
  int state;
  boolean ptrEnabled = true;

  final int HEAD_HIDE = 0;
  final int HEAD_PULLING = 1;
  final int HEAD_SHOW = 2;

  public MyPTR(Context context) {
    super(context);
    init();
  }

  public MyPTR(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public MyPTR(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    post(() -> {
      head = getChildAt(0);
      body = getChildAt(1);
      state = HEAD_HIDE;
      setScrollY(head.getMeasuredHeight());
    });
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    if (head != null && body != null) {
      setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),
          head.getMeasuredHeight() + body.getMeasuredHeight());
    }
  }

  @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {
    if (head != null && body != null) {
      head.layout(0, 0, head.getMeasuredWidth(), head.getMeasuredHeight());
      body.layout(0, head.getMeasuredHeight(), body.getMeasuredWidth(),
          head.getMeasuredHeight() + body.getMeasuredHeight());
      REFRESH_OFFSET = head.getMeasuredHeight() * 1 / 2;
    }
  }

  public void setListener(PTRListener listener) {
    if (!ptrEnabled) {
      throw new IllegalStateException("please enable ptr feature at first");
    }
    this.listener = listener;
  }

  public void setPtrEnabled(boolean ptrEnabled) {
    this.ptrEnabled = ptrEnabled;
  }

  public void startRefresh() {
    if (!ptrEnabled) {
      throw new IllegalStateException("please enable ptr feature at first");
    }
    if (state != HEAD_SHOW) {
      state = HEAD_SHOW;
      smoothToShow();
      if (listener != null) {
        listener.onRefreshStart();
      }
    }
  }

  public void stopRefresh() {
    if (!ptrEnabled) {
      throw new IllegalStateException("please enable ptr feature at first");
    }
    if (state != HEAD_HIDE) {
      state = HEAD_HIDE;
      smoothToHide();
      if (listener != null) {
        listener.onRefreshEnd();
      }
    }
  }

  private void setPulling(int offset) {
    state = HEAD_PULLING;
    setScrollY(head.getMeasuredHeight() - offset);
  }

  @Override public boolean dispatchTouchEvent(MotionEvent ev) {
    AppLogger.d("dispatch: " + ev);
    return super.dispatchTouchEvent(ev);
  }

  @Override public boolean onInterceptTouchEvent(MotionEvent ev) {
    AppLogger.d("on Intercept: " + ev);
    if (!ptrEnabled) {
      return false;
    }
    if (state == HEAD_SHOW) {
      return false;
    }
    int action = ev.getAction();
    float y = ev.getRawY();
    boolean intercept = false;
    switch (action) {
      case MotionEvent.ACTION_DOWN:
        lastY = y;
        intercept = false;
        break;
      case MotionEvent.ACTION_MOVE:
        float dy = y - lastInterceptY;
        AppLogger.d("intercept dy: " + dy);
        if (dy < 0) {
          intercept = false;
          break;
        }
        int bodyScrollY = body.getScrollY();
        AppLogger.d("body scroll y: " + bodyScrollY);
        if (bodyScrollY > 0) {
          intercept = false;
          break;
        } else {
          intercept = true;
          break;
        }
    }
    lastInterceptY = y;
    return intercept;
  }

  @Override public boolean onTouchEvent(MotionEvent event) {
    AppLogger.d("on touch: " + event);
    int action = event.getAction();
    float y = event.getRawY();
    switch (action) {
      case MotionEvent.ACTION_DOWN:
        if (withinHead(event)) {
          return false;
        }
        break;
      case MotionEvent.ACTION_MOVE:
        float dy = y - lastY;
        AppLogger.w("dy: " + dy);
        int target = (int) (getPullingOffset() + dy);
        AppLogger.w("target pulling offset: " + target);
        if (target >= head.getMeasuredHeight()) {
          startRefresh();
          break;
        }
        if (target <= 0) {
          stopRefresh();
          break;
        }
        setPulling(target);
        break;
      case MotionEvent.ACTION_UP:
      case MotionEvent.ACTION_CANCEL:
        if (getPullingOffset() < REFRESH_OFFSET) {
          smoothToHide();
          break;
        }
        startRefresh();
        break;
      default:
        break;
    }
    lastY = y;
    return true;
  }

  private boolean withinHead(MotionEvent ev) {
    float x = ev.getX(), y = ev.getY();
    AppLogger.w("h y: " + head.getY());
    AppLogger.w("h y + h: " + (head.getY() + head.getMeasuredHeight()));
    if (x < head.getX() || x > head.getX() + head.getMeasuredWidth()) {
      return false;
    }
    if (y < head.getY() - getScrollY()
        || y > head.getY() + head.getMeasuredHeight() - getScrollY()) {
      return false;
    }
    return true;
  }

  private int getPullingOffset() {
    int ret = Math.min(head.getMeasuredHeight() - Math.abs(getScrollY()), head.getMeasuredHeight());
    AppLogger.w("pulling offset: " + ret);
    return ret;
  }

  private void smoothToHide() {
    scroller = new Scroller(getContext());
    scroller.startScroll(0, getScrollY(), 0, head.getMeasuredHeight() - getScrollY());
    invalidate();
  }

  private void smoothToShow() {
    scroller = new Scroller(getContext());
    scroller.startScroll(0, getScrollY(), 0, -getScrollY());
    invalidate();
  }

  @Override public void computeScroll() {
    if (scroller == null) {
      return;
    }
    if (scroller.computeScrollOffset()) {
      scrollTo(getScrollX(), scroller.getCurrY());
      postInvalidate();
    }
  }

  public interface PTRListener {
    void onRefreshStart();

    void onRefreshEnd();
  }
}
