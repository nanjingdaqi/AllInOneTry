package org.peace.allinone.ui;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class SlidingDownPanelLayout extends LinearLayout {

  private static final int MOVE_DISTANCE_TO_TRIGGER = 10;
  private static final int MAX_ANIMATION_DURATION = 400;
  private static final int TRIGGER_VELOCITY = 1;

  private LinearLayout dragView;
  private ScrollView scrollView;

  private boolean isAnimating = false;
  private boolean isDragViewShowing = false;

  private float yVelocity;
  private float mTouchSlop;
  private int mMaxVelocity;
  private VelocityTracker mVelocityTracker;

  private int layoutH;
  private float firstDownY;
  private float downY;
  private float deltaY;
  private boolean isDragging = false;

  private float dragViewH;
  private boolean isDragViewOnTouch = false;

  private Interpolator mOpenAnimationInterpolator = new AccelerateInterpolator();
  private Interpolator mCloseAnimationInterpolator = new LinearInterpolator();

  ArgbEvaluator bgEvaluator = new ArgbEvaluator();
  int trans = getContext().getResources().getColor(android.R.color.transparent);
  int black = getContext().getResources().getColor(android.R.color.black);

  public SlidingDownPanelLayout(Context context) {
    this(context, null);
  }

  public SlidingDownPanelLayout(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public SlidingDownPanelLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    ViewConfiguration vc = ViewConfiguration.get(context);
    mMaxVelocity = vc.getScaledMaximumFlingVelocity();
    mTouchSlop = Math.min(vc.getScaledTouchSlop(), 8);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    dragView = (LinearLayout) getChildAt(1);
    scrollView = (ScrollView) dragView.getChildAt(1);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    layoutH = getMeasuredHeight();
    dragViewH = dragView.getMeasuredHeight();
  }

  @Override
  public boolean dispatchTouchEvent(MotionEvent ev) {
    initVelocityTracker(ev);
    boolean isConsume = false;
    switch (ev.getAction()) {
      case MotionEvent.ACTION_DOWN:
        isConsume = handleActionDown(ev);
        break;
      case MotionEvent.ACTION_MOVE:
        handleActionMove(ev);
        break;
      case MotionEvent.ACTION_UP:
      case MotionEvent.ACTION_CANCEL:
        handleActionUp(ev);
        releaseVelocityTracker();
        break;
    }
    return isConsume || superDispatchTouchEvent(ev);
  }

  private boolean superDispatchTouchEvent(MotionEvent ev) {
    requestDisallowInterceptTouchEvent(false);
    return super.dispatchTouchEvent(ev);
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent ev) {
    return isDragging;
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    return true;
  }

  private boolean handleActionDown(MotionEvent event) {
    boolean isConsume = false;
    firstDownY = downY = event.getY();
    float currentY = dragView.getY();
    if (!isDragViewShowing && downY > layoutH) {
      isDragViewOnTouch = true;
      isConsume = true;
    } else if (!isDragViewShowing && downY <= layoutH) {
      isDragViewOnTouch = false;
    } else if (isDragViewShowing && downY > currentY) {
      isDragViewOnTouch = true;
    } else if (isDragViewShowing && downY < currentY) {
      hidePanel();
      isDragViewOnTouch = false;
    }
    return isConsume;
  }

  private void handleActionMove(MotionEvent event) {
    if (!isDragViewOnTouch) {
      return;
    }

    if (!isDragging && isDragViewShowing && canConsumedByScrollView(event)) {
      return;
    }

    computeVelocity();

    if (!isDragging && Math.abs(event.getY() - firstDownY) > mTouchSlop) {
      isDragging = true;
      downY = event.getY();
    }

    if (isDragging) {
      deltaY = event.getY() - downY;
      downY = event.getY();

      float currentY = dragView.getY();
      float destY = currentY + deltaY;
      if (destY <= layoutH - dragViewH) {
        dragView.setY(layoutH - dragViewH);
      } else if (destY >= layoutH) {
        dragView.setY(layoutH);
      } else {
        dragView.setY(destY);
      }

      updateBg();
    }
  }

  private boolean canConsumedByScrollView(MotionEvent ev) {
    float l = scrollView.getX() + dragView.getX();
    float t = scrollView.getY() + dragView.getY();
    float r = l + scrollView.getWidth();
    float b = t + scrollView.getHeight();
    float x = ev.getX();
    float y = ev.getY();
    if (x >= l && x <= r && y >= t && y <= b
        && firstDownY >= t && firstDownY <= b
        && scrollView.canScrollVertically((int) (firstDownY - y))) {
      return true;
    }
    return false;
  }

  private void handleActionUp(MotionEvent event) {
    if (!isDragViewOnTouch) {
      return;
    }

    computeVelocity();

    float currentY = dragView.getY();

    if (isDragViewShowing) {
      if (yVelocity > TRIGGER_VELOCITY) {
        if (currentY > layoutH - dragViewH + MOVE_DISTANCE_TO_TRIGGER) {
          hidePanel();
        } else {
          doDisplayPanel();
        }
      } else {
        float dy = currentY - (layoutH - dragViewH);
        if (dy < dragViewH / 2) {
          doDisplayPanel();
        } else {
          hidePanel();
        }
      }
    }

    isDragViewOnTouch = false;
    isDragging = false;
    deltaY = 0;
  }

  private void hidePanel() {
    if (isAnimating) {
      return;
    }
    float y0 = dragView.getY();
    float fdy = layoutH - dragView.getY();
    long duration = (long) (MAX_ANIMATION_DURATION * (layoutH - dragView.getY()) / dragViewH);
    float minSpd = fdy / duration;
    float v0 = minSpd;
    float vy = Math.min(yVelocity, 8);
    if (vy > minSpd) {
      v0 = vy;
      duration = (long) (fdy / v0);
    }
    final float finalV = v0;
    ValueAnimator animator = ValueAnimator.ofFloat(0, duration);
    animator.setInterpolator(mCloseAnimationInterpolator);
    animator.setTarget(dragView);
    animator.setDuration(duration);
    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate(ValueAnimator animation) {
        float t = (float) animation.getAnimatedValue();
        float dy = finalV * t;
        float y = y0 + dy;
        dragView.setY(Math.min(y, layoutH));
        updateBg();
      }
    });
    animator.addListener(new Animator.AnimatorListener() {
      @Override
      public void onAnimationStart(Animator animation) {
        isAnimating = true;
      }

      @Override
      public void onAnimationEnd(Animator animation) {
        isAnimating = false;
        isDragViewShowing = false;
        setVisibility(GONE);
      }

      @Override
      public void onAnimationCancel(Animator animation) {
        isAnimating = false;
        isDragViewShowing = false;
        setVisibility(GONE);
      }

      @Override
      public void onAnimationRepeat(Animator animation) {
      }
    });
    animator.start();
  }

  public void displayPanel() {
    if (layoutH == 0) {
      getViewTreeObserver().addOnGlobalLayoutListener(
          new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override public void onGlobalLayout() {
              dragView.setY(layoutH);
              doDisplayPanel();

              getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
          });
      setVisibility(VISIBLE);
    } else {
      dragView.setY(layoutH);
      setVisibility(VISIBLE);
      doDisplayPanel();
    }
  }

  private void doDisplayPanel() {
    if (isAnimating) {
      return;
    }

    float currentY = dragView.getY();
    long duration =
        (long) ((currentY - (layoutH - dragViewH)) / dragViewH * MAX_ANIMATION_DURATION);
    ValueAnimator animator =
        ValueAnimator.ofFloat(currentY, layoutH - dragViewH).setDuration(duration);
    animator.setTarget(dragView);
    animator.setInterpolator(mOpenAnimationInterpolator);
    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate(ValueAnimator animation) {
        float value = (float) animation.getAnimatedValue();
        dragView.setY(value);
        updateBg();
      }
    });
    animator.addListener(new Animator.AnimatorListener() {
      @Override
      public void onAnimationStart(Animator animation) {
        isAnimating = true;
      }

      @Override
      public void onAnimationEnd(Animator animation) {
        isAnimating = false;
      }

      @Override
      public void onAnimationCancel(Animator animation) {
        isAnimating = false;
      }

      @Override
      public void onAnimationRepeat(Animator animation) {
      }
    });
    animator.start();
    isDragViewShowing = true;
  }

  private void updateBg() {
    float fraction = (dragView.getY() - (layoutH - dragViewH)) / dragViewH;
    int bg = (int) bgEvaluator.evaluate(fraction, black, trans);
    setBackgroundColor(bg);
  }

  private void computeVelocity() {
    mVelocityTracker.computeCurrentVelocity(1, mMaxVelocity);
    yVelocity = mVelocityTracker.getYVelocity();
  }

  private void initVelocityTracker(MotionEvent event) {
    if (mVelocityTracker == null) {
      mVelocityTracker = VelocityTracker.obtain();
    }
    mVelocityTracker.addMovement(event);
  }

  private void releaseVelocityTracker() {
    if (mVelocityTracker != null) {
      mVelocityTracker.clear();
      mVelocityTracker.recycle();
      mVelocityTracker = null;
      yVelocity = 0;
    }
  }
}
