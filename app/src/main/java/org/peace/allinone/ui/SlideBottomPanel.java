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
import android.widget.LinearLayout;
import android.widget.ScrollView;
import me.ele.commons.AppLogger;

public class SlideBottomPanel extends LinearLayout {

  private static final int MOVE_DISTANCE_TO_TRIGGER = 10;
  private static final int ANIMATION_DURATION = 400;
  private static final int TRIGGER_VELOCITY = 1000;

  private LinearLayout dragView;

  private boolean isAnimating = false;
  private boolean isDragViewShowing = false;

  private float xVelocity;
  private float yVelocity;
  private float mTouchSlop;
  private int mMaxVelocity;
  private VelocityTracker mVelocityTracker;

  private int layoutH;
  private float firstDownX;
  private float firstDownY;
  private float downY;
  private float deltaY;
  private boolean isDragging = false;

  private float dragViewH;
  private boolean isDragViewOnTouch = false;

  private Interpolator mOpenAnimationInterpolator = new AccelerateInterpolator();
  private Interpolator mCloseAnimationInterpolator = new AccelerateInterpolator();

  ArgbEvaluator bgEvaluator = new ArgbEvaluator();
  int trans = getContext().getResources().getColor(android.R.color.transparent);
  int black = getContext().getResources().getColor(android.R.color.black);

  private Context mContext;

  public SlideBottomPanel(Context context) {
    this(context, null);
  }

  public SlideBottomPanel(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public SlideBottomPanel(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    mContext = context;
    ViewConfiguration vc = ViewConfiguration.get(mContext);
    mMaxVelocity = vc.getScaledMaximumFlingVelocity();
    mTouchSlop = vc.getScaledTouchSlop();
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    dragView = (LinearLayout) getChildAt(1);
  }

  @Override
  protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    super.onLayout(changed, left, top, right, bottom);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    layoutH = getMeasuredHeight();
    dragViewH = dragView.getMeasuredHeight();
  }

  private boolean superDispatchTouchEvent(MotionEvent ev) {
    requestDisallowInterceptTouchEvent(false);
    return super.dispatchTouchEvent(ev);
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
        handleActionUp(ev);
        releaseVelocityTracker();
        break;
      case MotionEvent.ACTION_CANCEL:
        handleActionUp(ev);
        releaseVelocityTracker();
        break;
    }
    boolean ret = isConsume || superDispatchTouchEvent(ev);
    return ret;
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
    firstDownX = event.getX();
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
    if (isDragViewShowing && supportScrollInView((int) (firstDownY - event.getY()))) {
      return;
    }
    computeVelocity();
    if (Math.abs(xVelocity) > Math.abs(yVelocity)) {
      return;
    }
    if (!isDragging && Math.abs(event.getY() - firstDownY) > mTouchSlop
        && Math.abs(event.getX() - firstDownX) < mTouchSlop) {
      isDragging = true;
      downY = event.getY();
    }

    if (isDragging) {
      deltaY = event.getY() - downY;
      downY = event.getY();

      float currentY = dragView.getY();
      if (currentY + deltaY <= layoutH - dragViewH) {
        dragView.setY(layoutH - dragViewH);
      } else if (currentY + deltaY >= layoutH) {
        dragView.setY(layoutH);
      } else {
        dragView.setY(currentY + deltaY);
      }

      updateBg();
    }
  }

  private void handleActionUp(MotionEvent event) {
    if (!isDragViewOnTouch) {
      return;
    }
    computeVelocity();

    AppLogger.e("yV: " + yVelocity);

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
        AppLogger.e("dy: " + dy);
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
    ValueAnimator animator = ValueAnimator.ofFloat(dragView.getY(), layoutH);
    animator.setInterpolator(mCloseAnimationInterpolator);
    animator.setTarget(dragView);
    animator.setDuration(ANIMATION_DURATION);
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
        isDragViewShowing = false;
        setVisibility(GONE);
      }

      @Override
      public void onAnimationCancel(Animator animation) {
        isAnimating = false;
        isDragViewShowing = false;
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
    AppLogger.e("mMH: " + layoutH + ", mPH: " + dragViewH);
    float currentY = dragView.getY();
    AppLogger.e("cy: " + currentY);
    ValueAnimator animator =
        ValueAnimator.ofFloat(currentY, layoutH - dragViewH).setDuration(ANIMATION_DURATION);
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
    //units是单位表示， 1代表px/毫秒, 1000代表px/秒
    mVelocityTracker.computeCurrentVelocity(1000, mMaxVelocity);
    xVelocity = mVelocityTracker.getXVelocity();
    yVelocity = mVelocityTracker.getYVelocity();
  }

  private boolean supportScrollInView(int direction) {
    ScrollView scrollView = (ScrollView) dragView.getChildAt(1);
    return scrollView.canScrollVertically(direction);
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
    }
  }
}
