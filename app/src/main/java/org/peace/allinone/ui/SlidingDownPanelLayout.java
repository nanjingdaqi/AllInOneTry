package org.peace.allinone.ui;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import org.peace.allinone.R;

public class SlidingDownPanelLayout extends LinearLayout {

  private static final int MOVE_DISTANCE_TO_TRIGGER = 10;
  private static final int MAX_ANIMATION_DURATION = 400;
  private static final int TRIGGER_VELOCITY = 1;

  private View dragView;
  private ScrollView scrollView;
  private int dragViewId;
  private int scrollViewId;

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
  int bgColor = trans;

  public SlidingDownPanelLayout(Context context) {
    this(context, null);
  }

  public SlidingDownPanelLayout(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public SlidingDownPanelLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    TypedArray ta = null;
    try {
      ta = context.obtainStyledAttributes(attrs, R.styleable.SlidingDownPanelLayout, defStyleAttr, 0);
      dragViewId = ta.getResourceId(R.styleable.SlidingDownPanelLayout_drag_view_id, -1);
      scrollViewId = ta.getResourceId(R.styleable.SlidingDownPanelLayout_scroll_view_id, -1);
    } finally {
      ta.recycle();
    }

    ViewConfiguration vc = ViewConfiguration.get(context);
    mMaxVelocity = vc.getScaledMaximumFlingVelocity();
    mTouchSlop = Math.min(vc.getScaledTouchSlop(), 8);

    Drawable bg = getBackground();
    if (bg instanceof ColorDrawable) {
      bgColor = ((ColorDrawable) bg).getColor();
    }
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    dragView = findViewById(dragViewId);
    if (dragView == null) {
      throw new IllegalStateException("must set a valid drag_view_id");
    }
    View sv = findViewById(scrollViewId);
    if (sv != null && !(sv instanceof ScrollView)) {
      throw new IllegalStateException("scroll_view_id must be ScrollView");
    }
    scrollView = (ScrollView) sv;
  }

  @Override public void setVisibility(int visibility) {
    super.setVisibility(visibility);
    if (visibility == VISIBLE) {
      dragView.setY(layoutH - dragViewH);
      isDragViewShowing = true;
      updateBg();
    } else if (visibility == GONE) {
      dragView.setY(layoutH);
      isDragViewShowing = false;
      updateBg();
    }
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
      hide(true);
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
    if (scrollView == null) {
      return false;
    }

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
          hide(true);
        } else {
          animateToShow();
        }
      } else {
        float dy = currentY - (layoutH - dragViewH);
        if (dy < dragViewH / 2) {
          animateToShow();
        } else {
          hide(true);
        }
      }
    }

    isDragViewOnTouch = false;
    isDragging = false;
    deltaY = 0;
  }

  public void hide(boolean hasAnimation) {
    if (!hasAnimation) {
      isDragViewShowing = false;
      dragView.setY(layoutH);
      updateBg();
      isAnimating = false;
      return;
    }

    if (isAnimating) {
      return;
    }
    final float y0 = dragView.getY();
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
        setVisibility(GONE);
      }

      @Override
      public void onAnimationCancel(Animator animation) {
        isAnimating = false;
        setVisibility(GONE);
      }

      @Override
      public void onAnimationRepeat(Animator animation) {
      }
    });
    animator.start();
  }

  public void show(boolean hasAnimation) {
    if (!hasAnimation) {
      setVisibility(VISIBLE);
      return;
    }

    if (getVisibility() == GONE) {
      getViewTreeObserver().addOnGlobalLayoutListener(
          new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override public void onGlobalLayout() {
              dragView.setY(layoutH);
              animateToShow();
              getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
          });
      super.setVisibility(VISIBLE);
    } else {
      super.setVisibility(VISIBLE);
      animateToShow();
    }
  }

  private void animateToShow() {
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
    int bg = (int) bgEvaluator.evaluate(fraction, bgColor, trans);
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
