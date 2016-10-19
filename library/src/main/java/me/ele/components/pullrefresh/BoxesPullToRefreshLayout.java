package me.ele.components.pullrefresh;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.AbsListView;
import me.ele.recyclerview.R;

public class BoxesPullToRefreshLayout extends ViewGroup implements NestedScrollingParent,
    NestedScrollingChild, PullToRefresh {
  private static final String LOG_TAG = BoxesPullToRefreshLayout.class.getSimpleName();

  private static final int INVALID_POINTER = -1;
  private static final float DRAG_RATE = .5f;

  private static final int ANIMATE_TO_TRIGGER_DURATION = 200;
  private static final int ANIMATE_TO_START_DURATION = 200;

  // Default offset in dips from the top of the layout to where the target should stop.
  private static final int DEFAULT_TARGET_OFFSET = 76;

  private final NestedScrollingParentHelper mNestedScrollingParentHelper;
  private final NestedScrollingChildHelper mNestedScrollingChildHelper;

  private final int[] mParentScrollConsumed = new int[2];
  private final int[] mParentOffsetInWindow = new int[2];

  private View mTarget;

  private PullToRefresh.OnRefreshListener mListener;

  private PullToRefresh.OnTargetOffsetListener mOnTargetOffsetListener;

  private boolean mRefreshing = false;

  private int mTouchSlop;

  private float mTotalDragDistance = -1;

  // If nested scrolling is enabled, the total amount that needed to be consumed by this as the
  // nested scrolling parent is used in place of the overscroll determined by MOVE events in the
  // onTouch handler.
  private float mTotalUnconsumed;
  private int mTotalConsumed;
  private boolean mNestedScrollInProgress;
  private int mCurrentTargetOffsetTop;
  private int mTempNestedTargetOffsetTop;

  private int mCurrentStageOffsetTop;

  // Whether or not the starting offset has been determined.
  private boolean mOriginOffsetCalculated = false;

  private float mInitialMotionY;
  private float mInitialDownY;

  private boolean mIsBeingDragged;
  private boolean mReadyToPull;

  private int mActivePointerId = INVALID_POINTER;

  // Target is returning to its start offset because it was cancelled.
  private boolean mReturningToStart;

  private PullToRefreshHandler mPullToRefreshHandler;
  private Mode mode = Mode.Z_AXIS;

  private Runnable mRefreshRunnable;

  private View mStage;
  private int mStageViewIndex = -1;

  private int mFrom;

  private int mOriginalOffsetTop;
  private float mSpinnerFinalOffset;

  private boolean mOffsetConsumed = false;

  private boolean mNotify;

  /**
   * This listener is a workaround for fix inactive pull to refresh bug.
   * Reproducible steps: pull double times quickly.
   */
  private Animation.AnimationListener mFixInactivePTRListener;

  private Animation.AnimationListener mRefreshListener = new Animation.AnimationListener() {
    @Override public void onAnimationStart(Animation animation) {
    }

    @Override public void onAnimationRepeat(Animation animation) {
    }

    @Override public void onAnimationEnd(Animation animation) {
      mFixInactivePTRListener = null;
      mPullToRefreshHandler.onStart();
      if (mNotify) {
        mNotify = false;
        if (mListener != null) {
          mListener.onRefresh();
        }
      }
    }
  };

  private Animation.AnimationListener mFinishListener = new Animation.AnimationListener() {
    @Override public void onAnimationStart(Animation animation) {
    }

    @Override public void onAnimationRepeat(Animation animation) {
    }

    @Override public void onAnimationEnd(Animation animation) {
      mPullToRefreshHandler.onFinish();
      resetStageOffsetTop();

      // Lazy refresh for better performance.
      if (mRefreshRunnable != null) {
        mRefreshRunnable.run();
        mRefreshRunnable = null;
      }
    }
  };

  private Animation mAnimateToStartPosition = new Animation() {
    @Override protected void applyTransformation(float interpolatedTime, Transformation t) {
      int targetTop = (mFrom + (int) ((mOriginalOffsetTop - mFrom) * interpolatedTime));
      int offset = targetTop - mTarget.getTop();
      setTargetOffsetTopAndBottom(offset, false);
      if (mode == Mode.Y_AXIS) {
        setStageOffsetTopAndBottom(offset);
      }
    }
  };

  private Animation mAnimateToCorrectPosition = new Animation() {
    @Override protected void applyTransformation(float interpolatedTime, Transformation t) {
      int endTarget = (int) (mSpinnerFinalOffset - Math.abs(mOriginalOffsetTop));
      int targetTop = (mFrom + (int) ((endTarget - mFrom) * interpolatedTime));
      int offset = targetTop - mTarget.getTop();
      if (mode == Mode.Y_AXIS) {
        setStageOffsetTopAndBottom(offset);
      }
      setTargetOffsetTopAndBottom(offset, false);
    }
  };

  public BoxesPullToRefreshLayout(Context context) {
    this(context, null);
  }

  public BoxesPullToRefreshLayout(Context context, AttributeSet attrs) {
    super(context, attrs);

    mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

    setWillNotDraw(false);

    final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ptr);
    setEnabled(a.getBoolean(R.styleable.ptr_android_enabled, true));
    mode = Mode.parse(a.getInteger(R.styleable.ptr_ptr_mode, 0));
    a.recycle();

    final DisplayMetrics metrics = getResources().getDisplayMetrics();

    createStageView();
    ViewCompat.setChildrenDrawingOrderEnabled(this, true);
    // The absolute offset has to take into account that the circle starts at an offset.
    mSpinnerFinalOffset = DEFAULT_TARGET_OFFSET * metrics.density;
    mTotalDragDistance = mSpinnerFinalOffset;
    mTempNestedTargetOffsetTop = (int) mSpinnerFinalOffset;
    mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);

    mNestedScrollingChildHelper = new NestedScrollingChildHelper(this);
    setNestedScrollingEnabled(true);
  }

  @Override protected int getChildDrawingOrder(int childCount, int i) {
    if (mStageViewIndex < 0) {
      return i;
    } else if (i == 0) {
      return mStageViewIndex;
    } else if (i <= mStageViewIndex) {
      return i - 1;
    } else {
      return i;
    }
  }

  private void createStageView() {
    mPullToRefreshHandler = new PullToRefreshHandlerImpl(getContext(), this);
    mStage = mPullToRefreshHandler.getStage();
    addView(mStage);
  }

  @Override public boolean isRefreshing() {
    return mRefreshing;
  }

  @Override public void setOnRefreshListener(PullToRefresh.OnRefreshListener listener) {
    mListener = listener;
  }

  @Override public void setOnTargetOffsetListener(OnTargetOffsetListener listener) {
    mOnTargetOffsetListener = listener;
  }

  @Override public void setProgressViewOffsetTop(int offset) {
    mPullToRefreshHandler.setProgressViewOffsetTop(offset);
  }

  @Override public void postPendingTask(Runnable runnable) {
    mRefreshRunnable = runnable;
  }

  @Override public ViewGroup getLayout() {
    return this;
  }

  @Override public void setRefreshing(boolean refreshing) {
    if (!isEnabled()) {
      return;
    }

    ensureTarget();
    if (refreshing) {
      if (mRefreshing) {
        animateOffsetToCorrectPosition(mCurrentTargetOffsetTop, true);
      } else {
        animateOffsetToCorrectPosition(mCurrentTargetOffsetTop, false);
        mPullToRefreshHandler.onReset();
      }
      mRefreshing = true;
    } else {
      mRefreshing = false;
      mPullToRefreshHandler.onPreFinish();
      animateOffsetToStartPosition(mCurrentTargetOffsetTop);
    }
    mNotify = true;
  }

  private void ensureTarget() {
    if (mTarget == null) {
      for (int i = 0; i < getChildCount(); i++) {
        View child = getChildAt(i);
        if (!child.equals(mStage)) {
          mTarget = child;
          break;
        }
      }
    }
  }

  @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {
    final int w = getMeasuredWidth();
    final int h = getMeasuredHeight();
    if (getChildCount() == 0) {
      return;
    }
    if (mTarget == null) {
      ensureTarget();
    }
    if (mTarget == null) {
      return;
    }

    final int childLeft = getPaddingLeft();
    final int childTop = getPaddingTop();
    final int childRight = w - getPaddingRight();
    final int childBottom = h - getPaddingBottom();
    if (mode == Mode.Y_AXIS) {
      mStage.layout(childLeft, mCurrentStageOffsetTop, childRight,
          mCurrentStageOffsetTop + mStage.getMeasuredHeight());
    } else {
      mStage.layout(childLeft, childTop, childRight, childBottom);
    }
    mTarget.layout(childLeft, mCurrentTargetOffsetTop, childRight, mCurrentTargetOffsetTop + h);

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

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    ensureTarget();
    if (mTarget == null) {
      return;
    }
    int w = MeasureSpec.makeMeasureSpec(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(),
        MeasureSpec.EXACTLY);
    int h = MeasureSpec.makeMeasureSpec(getMeasuredHeight() - getPaddingTop() - getPaddingBottom(),
        MeasureSpec.EXACTLY);
    mTarget.measure(w, h);
    mStage.measure(w,
        MeasureSpec.makeMeasureSpec(getMeasuredHeight() - getPaddingTop() - getPaddingBottom(),
            MeasureSpec.AT_MOST));
    if (!mOriginOffsetCalculated) {
      mCurrentTargetOffsetTop = mOriginalOffsetTop = 0;
      resetStageOffsetTop();
      mOriginOffsetCalculated = true;
    }
    mStageViewIndex = -1;

    for (int i = 0; i < getChildCount(); i++) {
      if (getChildAt(i) == mStage) {
        mStageViewIndex = i;
        break;
      }
    }

    for (int i = 0; i < getChildCount(); i++) {
      View child = getChildAt(i);
      if (child.getId() == R.id.id_sticky_container) {
        child.measure(MeasureSpec.makeMeasureSpec(getMeasuredWidth(), MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(getMeasuredHeight(), MeasureSpec.EXACTLY));
        break;
      }
    }
  }

  // Animations

  public boolean canChildScrollUp() {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
      if (mTarget instanceof AbsListView) {
        final AbsListView absListView = (AbsListView) mTarget;
        return absListView.getChildCount() > 0 && (absListView.getFirstVisiblePosition() > 0
            || absListView.getChildAt(0).getTop() < absListView.getPaddingTop());
      } else {
        return ViewCompat.canScrollVertically(mTarget, -1) || mTarget.getScrollY() > 0;
      }
    } else {
      return ViewCompat.canScrollVertically(mTarget, -1);
    }
  }

  @Override public boolean onInterceptTouchEvent(MotionEvent ev) {
    ensureTarget();

    final int action = MotionEventCompat.getActionMasked(ev);

    if (mReturningToStart && action == MotionEvent.ACTION_DOWN) {
      mReturningToStart = false;
    }

    if (!isEnabled() || mReturningToStart || canChildScrollUp() || mNestedScrollInProgress) {
      // Fail fast if we're not in a state where a swipe is possible.
      return false;
    }

    switch (action) {
      case MotionEvent.ACTION_DOWN:
        //setTargetOffsetTopAndBottom(mOriginalOffsetTop - mTarget.getTop(), true);
        mActivePointerId = MotionEventCompat.getPointerId(ev, 0);
        mIsBeingDragged = false;
        mReadyToPull = true;
        final float initialDownY = getMotionEventY(ev, mActivePointerId);
        if (initialDownY == -1) {
          return false;
        }
        mInitialDownY = initialDownY;
        break;

      case MotionEvent.ACTION_MOVE:
        if (mActivePointerId == INVALID_POINTER) {
          Log.e(LOG_TAG, "Got ACTION_MOVE event but don't have an active pointer id.");
          return false;
        }

        final float y = getMotionEventY(ev, mActivePointerId);
        if (y == -1) {
          return false;
        }
        final float yDiff = y - mInitialDownY;
        if (yDiff > mTouchSlop && !mIsBeingDragged) {
          mInitialMotionY = mInitialDownY + mTouchSlop;
          mIsBeingDragged = true;
        }
        break;

      case MotionEventCompat.ACTION_POINTER_UP:
        onSecondaryPointerUp(ev);
        break;

      case MotionEvent.ACTION_UP:
      case MotionEvent.ACTION_CANCEL:
        mIsBeingDragged = false;
        mActivePointerId = INVALID_POINTER;
        break;
    }

    return mIsBeingDragged;
  }

  @Override public boolean onTouchEvent(MotionEvent ev) {
    final int action = MotionEventCompat.getActionMasked(ev);
    int pointerIndex;

    if (mReturningToStart && action == MotionEvent.ACTION_DOWN) {
      mReturningToStart = false;
    }

    if (!isEnabled() || mReturningToStart || canChildScrollUp() || mNestedScrollInProgress) {
      // Fail fast if we're not in a state where a swipe is possible
      return false;
    }

    switch (action) {
      case MotionEvent.ACTION_DOWN:
        mActivePointerId = MotionEventCompat.getPointerId(ev, 0);
        mIsBeingDragged = false;
        break;

      case MotionEvent.ACTION_MOVE: {
        pointerIndex = MotionEventCompat.findPointerIndex(ev, mActivePointerId);
        if (pointerIndex < 0) {
          Log.d(LOG_TAG, "Got ACTION_MOVE event but have an invalid active pointer id.");
          return false;
        }

        final float y = MotionEventCompat.getY(ev, pointerIndex);
        final float overscrollTop = (y - mInitialMotionY) * DRAG_RATE;
        if (mIsBeingDragged) {
          if (overscrollTop > 0) {
            moveTarget(overscrollTop, false);
          } else {
            return false;
          }
        }
        break;
      }
      case MotionEventCompat.ACTION_POINTER_DOWN:
        pointerIndex = MotionEventCompat.getActionIndex(ev);
        if (pointerIndex < 0) {
          Log.e(LOG_TAG, "Got ACTION_POINTER_DOWN event but have an invalid action index.");
          return false;
        }
        mActivePointerId = MotionEventCompat.getPointerId(ev, pointerIndex);
        break;

      case MotionEventCompat.ACTION_POINTER_UP:
        onSecondaryPointerUp(ev);
        break;

      case MotionEvent.ACTION_UP: {
        pointerIndex = MotionEventCompat.findPointerIndex(ev, mActivePointerId);
        if (pointerIndex < 0) {
          Log.d(LOG_TAG, "Got ACTION_UP event but don't have an active pointer id.");
          return false;
        }
        mIsBeingDragged = false;
        finishTarget();
        mActivePointerId = INVALID_POINTER;
        return false;
      }
      case MotionEvent.ACTION_CANCEL:
        return false;
    }

    return true;
  }

  private void animateOffsetToCorrectPosition(int from, boolean alreadyStarted) {
    mFrom = from;
    mAnimateToCorrectPosition.reset();
    mAnimateToCorrectPosition.setDuration(ANIMATE_TO_TRIGGER_DURATION);
    if (alreadyStarted) {
      mAnimateToCorrectPosition.setAnimationListener(null);
      if (mFixInactivePTRListener != null) {
        mFixInactivePTRListener.onAnimationEnd(null);
        mFixInactivePTRListener = null;
      }
    } else {
      mAnimateToCorrectPosition.setAnimationListener(mRefreshListener);
      mFixInactivePTRListener = mRefreshListener;
    }
    mTarget.clearAnimation();
    mTarget.startAnimation(mAnimateToCorrectPosition);
  }

  private void animateOffsetToStartPosition(int from) {
    mFrom = from;
    mAnimateToStartPosition.reset();
    mAnimateToStartPosition.setDuration(ANIMATE_TO_START_DURATION);
    mAnimateToStartPosition.setAnimationListener(mFinishListener);
    mTarget.clearAnimation();
    mTarget.startAnimation(mAnimateToStartPosition);
    mReadyToPull = false;
  }

  private float getMotionEventY(MotionEvent ev, int activePointerId) {
    final int index = MotionEventCompat.findPointerIndex(ev, activePointerId);
    if (index < 0) {
      return -1;
    }
    return MotionEventCompat.getY(ev, index);
  }

  @Override public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    // If this is a List < L or another view that doesn't support nested scrolling,
    // ignore this request so that the vertical scroll event isn't stolen.
    if ((Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP && mTarget instanceof AbsListView)
        || (mTarget != null && !ViewCompat.isNestedScrollingEnabled(mTarget))) {
      // Nope.
    } else {
      super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }
  }

  private boolean isAnimationRunning(Animation animation) {
    return animation != null && animation.hasStarted() && !animation.hasEnded();
  }

  private void moveTarget(float overscrollTop, boolean reverse) {
    if (!mReadyToPull) {
      return;
    }
    float originalDragPercent = overscrollTop / mTotalDragDistance;
    float dragPercent = Math.min(1f, Math.abs(originalDragPercent));
    float extraOS = Math.abs(overscrollTop) - mTotalDragDistance;
    float slingshotDist = mSpinnerFinalOffset;
    float tensionSlingshotPercent =
        Math.max(0, Math.min(extraOS, slingshotDist * 2) / slingshotDist);
    float tensionPercent = (float) ((tensionSlingshotPercent / 4)
        - Math.pow((tensionSlingshotPercent / 4), 2)) * 2f;
    float extraMove = slingshotDist * tensionPercent * 2;
    int targetY;
    if (reverse) {
      targetY = mTempNestedTargetOffsetTop - (int) ((slingshotDist * dragPercent) + extraMove);
      Log.e("toolbar", "mTempNestedTargetOffsetTop: " + mTempNestedTargetOffsetTop);
      Log.e("toolbar", "targetY: " + targetY);
    } else {
      targetY = mOriginalOffsetTop + (int) ((slingshotDist * dragPercent) + extraMove);
    }
    targetY = Math.max(0, targetY);
    int offset = targetY - mCurrentTargetOffsetTop;
    setTargetOffsetTopAndBottom(offset, true);
    if (!mRefreshing) {
      setStageOffsetTopAndBottom(offset);
      mPullToRefreshHandler.onPulling(dragPercent);
    }
  }

  private void finishTarget() {
    if (mode == Mode.Y_AXIS && mCurrentStageOffsetTop >= getPaddingTop()) {
      setRefreshing(true);
    } else if (mode != Mode.Y_AXIS && mCurrentTargetOffsetTop > mTotalDragDistance) {
      setRefreshing(true);
    } else {
      setRefreshing(false);
    }
  }

  private void setStageOffsetTopAndBottom(int offset) {
    if (mode == Mode.Y_AXIS) {
      int maxY = (int) (getPaddingTop() + mStage.getPaddingTop() + mSpinnerFinalOffset);
      if (mCurrentTargetOffsetTop >= maxY || offset + mStage.getTop() >= getPaddingTop()) {
        offset = getPaddingTop() - mStage.getTop();
      } else if (offset + mStage.getTop() <= getStageStartOffsetTop()) {
        offset = getStageStartOffsetTop() - mStage.getTop();
      }
      mStage.offsetTopAndBottom(offset);
      invalidate();
      mCurrentStageOffsetTop = mStage.getTop();
    }
  }

  private void resetStageOffsetTop() {
    setStageOffsetTopAndBottom(getStageStartOffsetTop() - mCurrentStageOffsetTop);
  }

  private int getStageStartOffsetTop() {
    return (int) -(mStage.getPaddingTop() + mSpinnerFinalOffset);
  }

  private void setTargetOffsetTopAndBottom(int offset, boolean requiresUpdate) {
    mTarget.offsetTopAndBottom(offset);
    mCurrentTargetOffsetTop = mTarget.getTop();
    if (requiresUpdate && Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
      invalidate();
    }
    if (mOnTargetOffsetListener != null) {
      mOnTargetOffsetListener.onTargetOffset(offset);
    }
  }

  private void onSecondaryPointerUp(MotionEvent ev) {
    final int pointerIndex = MotionEventCompat.getActionIndex(ev);
    final int pointerId = MotionEventCompat.getPointerId(ev, pointerIndex);
    if (pointerId == mActivePointerId) {
      // This was our active pointer going up. Choose a new active pointer and adjust accordingly.
      final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
      mActivePointerId = MotionEventCompat.getPointerId(ev, newPointerIndex);
    }
  }

  public Mode getMode() {
    return mode;
  }

  // NestedScrollingParent

  @Override public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
    return isEnabled() && !mReturningToStart
        && (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
  }

  @Override public void onNestedScrollAccepted(View child, View target, int axes) {
    mNestedScrollingParentHelper.onNestedScrollAccepted(child, target, axes);
    startNestedScroll(axes & ViewCompat.SCROLL_AXIS_VERTICAL);
    // Reset the counter of how much leftover scroll needs to be consumed.
    mTotalUnconsumed = 0;
    mNestedScrollInProgress = true;
  }

  @Override public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
    // If we are in the middle of consuming a scroll, then we want to move the target back up
    // before allowing the list to scroll.
    if (dy > 0) {
      Log.e("toolbar", "mTotalUnconsumed: " + mTotalUnconsumed);
      if (mTotalUnconsumed > 0) { // top - bottom - top
        if (dy > mTotalUnconsumed) {
          consumed[1] = dy - (int) mTotalUnconsumed; // not bug
          mTotalUnconsumed = 0;
        } else {
          consumed[1] = dy;
          mTotalUnconsumed -= dy;
        }
        moveTarget(mTotalUnconsumed, false);
      } else if (mCurrentTargetOffsetTop > 0 && mTotalUnconsumed == 0) { // bottom - top
        if (dy < mCurrentTargetOffsetTop) {
          consumed[1] = dy;
          mTotalConsumed += dy;
          Log.e("toolbar", "1");
        } else {
          consumed[1] = dy;
          mTotalConsumed += mCurrentTargetOffsetTop;
          Log.e("toolbar", "2");
          Log.e("toolbar", "mCurrentTargetOffsetTop: " + mCurrentTargetOffsetTop);
        }
        Log.e("toolbar", "mTotalConsumed: " + mTotalConsumed);
        moveTarget(mTotalConsumed, true);
      }
    }

    // Now let our nested parent consume the leftovers.
    final int[] parentConsumed = mParentScrollConsumed;
    if (dispatchNestedPreScroll(dx - consumed[0], dy - consumed[1], parentConsumed, null)) {
      consumed[0] += parentConsumed[0];
      consumed[1] += parentConsumed[1];
    }
  }

  @Override public int getNestedScrollAxes() {
    return mNestedScrollingParentHelper.getNestedScrollAxes();
  }

  @Override public void onStopNestedScroll(View target) {
    mNestedScrollingParentHelper.onStopNestedScroll(target);
    mNestedScrollInProgress = false;
    mOffsetConsumed = false;
    // Finish the target for nested scrolling if we ever consumed any unconsumed nested scroll.
    if (mTotalUnconsumed > 0) {
      if (mCurrentTargetOffsetTop > mTotalDragDistance) {
        mTempNestedTargetOffsetTop = (int) mSpinnerFinalOffset;
      }
      mTotalUnconsumed = 0;
    }
    if (mTotalConsumed > 0) {
      mTempNestedTargetOffsetTop = mCurrentTargetOffsetTop;
      mTotalConsumed = 0;
    }
    if (mRefreshing) {
      if (mCurrentTargetOffsetTop > mTotalDragDistance) {
        animateOffsetToCorrectPosition(mCurrentTargetOffsetTop, true);
      }
    } else {
      finishTarget();
    }
    // Dispatch up our nested parent.
    stopNestedScroll();
  }

  @Override
  public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed,
      int dyUnconsumed) {
    // Dispatch to the nested parent first.
    dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, mParentOffsetInWindow);

    final int dy = dyUnconsumed + mParentOffsetInWindow[1];
    if (dy < 0) { // top-bottom scroll
      //if ((mTotalConsumed == 0 || mTotalConsumed == mSpinnerFinalOffset)) {
      if (mRefreshing && !mOffsetConsumed) {
        mTotalUnconsumed += mCurrentTargetOffsetTop;
        mOffsetConsumed = true;
      }
      mTotalUnconsumed += Math.abs(dy);
      moveTarget(mTotalUnconsumed, false);

      //} else { // bottom - top - bottom
      //
      //}
    }
  }

  @Override public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
    if (dispatchNestedPreFling(velocityX, velocityY)) {
      return true;
    }
    if (mRefreshing && velocityY > 0) {
      return true;
    }
    return false;
  }

  @Override
  public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
    return dispatchNestedFling(velocityX, velocityY, consumed);
  }

  @Override public boolean isNestedScrollingEnabled() {
    return mNestedScrollingChildHelper.isNestedScrollingEnabled();
  }

  // NestedScrollingChild

  @Override public void setNestedScrollingEnabled(boolean enabled) {
    mNestedScrollingChildHelper.setNestedScrollingEnabled(enabled);
  }

  @Override public boolean startNestedScroll(int axes) {
    return mNestedScrollingChildHelper.startNestedScroll(axes);
  }

  @Override public void stopNestedScroll() {
    mNestedScrollingChildHelper.stopNestedScroll();
  }

  @Override public boolean hasNestedScrollingParent() {
    return mNestedScrollingChildHelper.hasNestedScrollingParent();
  }

  @Override public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed,
      int dyUnconsumed, int[] offsetInWindow) {
    return mNestedScrollingChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed,
        dyUnconsumed, offsetInWindow);
  }

  @Override
  public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
    return mNestedScrollingChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
  }

  @Override public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
    return mNestedScrollingChildHelper.dispatchNestedFling(velocityX, velocityY, consumed);
  }

  @Override public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
    return mNestedScrollingChildHelper.dispatchNestedPreFling(velocityX, velocityY);
  }

  public enum Mode {
    Z_AXIS, Y_AXIS;

    public static Mode parse(int value) {
      if (value == 0) {
        return Z_AXIS;
      }
      if (value == 1) {
        return Y_AXIS;
      }
      return null;
    }
  }
}