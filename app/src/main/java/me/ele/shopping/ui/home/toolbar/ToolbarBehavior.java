package me.ele.shopping.ui.home.toolbar;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ScrollerCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import java.util.HashSet;
import java.util.Set;
import me.ele.base.utils.DimenUtil;

import static me.ele.base.utils.DimenUtil.getStatusBarHeight;

public class ToolbarBehavior extends CoordinatorLayout.Behavior<View> {

  private static final String TAG = ToolbarBehavior.class.getSimpleName();
  private static final int FLING_SCALE = 2;

  private NestedScrollingChildHelper scrollingChildHelper;
  private ScrollerCompat mScroller;
  private FlingRunnable mFlingRunnable;
  private int minH;

  private CoordinatorLayout coordinatorLayout;
  private View child;
  private ValueAnimator scrollToTopAnim;

  private Set<HeightChangeListener> listeners = new HashSet<>();

  public ToolbarBehavior() {
  }

  public ToolbarBehavior(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public void reset() {
    setBottomMargin(coordinatorLayout, child, 0);
  }

  public void setup(CoordinatorLayout coordinatorLayout, View child) {
    Log.e(TAG, "setup");
    this.coordinatorLayout = coordinatorLayout;
    this.child = child;
    scrollingChildHelper = new NestedScrollingChildHelper(coordinatorLayout);
    scrollingChildHelper.setNestedScrollingEnabled(true);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      minH = getStatusBarHeight() + DimenUtil.getToolBarHeight((Activity) child.getContext());
    } else {
      minH = DimenUtil.getToolBarHeight((Activity) child.getContext());
    }
  }

  private int getChildBottomMargin() {
    MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
    return lp.bottomMargin;
  }

  @Override public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child,
      View directTargetChild, View target, int nestedScrollAxes) {
    if (scrollingChildHelper.startNestedScroll(nestedScrollAxes)) {
      return true;
    }
    return target instanceof RecyclerView && nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
  }

  @Override
  public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target) {
    scrollingChildHelper.stopNestedScroll();
  }

  @Override
  public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target,
      int dx, int dy, int[] consumed) {
    Log.e(TAG, "onNestedPreScroll, dy: " + dy);

    int parentDy = dy;
    if (dy > 0) {
      parentDy = Math.min(coordinatorLayout.getTop(), dy);
    }
    Log.e(TAG, "parentDy: " + parentDy);
    scrollingChildHelper.dispatchNestedPreScroll(dx, parentDy, consumed, new int[2]);

    Log.e(TAG, "consumed[1]: " + consumed[1]);
    if (consumed[1] == dy) {
      return;
    }

    dy -= consumed[1];

    // up scroll
    if (dy > 0) {
      int orgMargin = getChildBottomMargin();
      setBottomMargin(coordinatorLayout, child, getChildBottomMargin() - dy);

      Log.e(TAG, "onNestedPreScroll, consume dy: " + (-getChildBottomMargin() + orgMargin));
      consumed[1] = -getChildBottomMargin() + orgMargin;
    }
  }

  @Override public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target,
      int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
    Log.e(TAG, "onNestedScroll, dyUnconsumed: " + dyUnconsumed);

    // down scroll
    if (dyUnconsumed < 0) {
      int orgMargin = getChildBottomMargin();
      setBottomMargin(coordinatorLayout, child, getChildBottomMargin() - dyUnconsumed);
      dyUnconsumed += -orgMargin - (-getChildBottomMargin());
      Log.e(TAG, "onNestedScroll to parent, dyUn: " + dyUnconsumed);
      scrollingChildHelper.dispatchNestedScroll(0, 0, 0, dyUnconsumed, new int[2]);
    }
  }

  @Override
  public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, View child, View target,
      float velocityX, float velocityY) {
    Log.e(TAG, "onNestedPreFling, vy: " + velocityY + ", cor top: " + coordinatorLayout.getTop());
    if (mScroller == null) {
      mScroller = ScrollerCompat.create(child.getContext());
    }
    if (coordinatorLayout.getTop() > 0 && scrollingChildHelper.dispatchNestedPreFling(velocityX,
        velocityY)) {
      return true;
    }

    // up fling
    if (velocityY > 0 && child.getMeasuredHeight() + getChildBottomMargin() > minH) {
      mScroller.fling(0, getChildBottomMargin(), 0, (int) (-velocityY / FLING_SCALE), 0, 0,
          -child.getMeasuredHeight() + minH, 0);
      if (mScroller.computeScrollOffset()) {
        mFlingRunnable = new FlingRunnable(coordinatorLayout, child);
        ViewCompat.postOnAnimation(child, mFlingRunnable);
        return true;
      }
    }

    return false;
  }

  @Override
  public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View child, View target,
      float velocityX, float velocityY, boolean consumed) {
    Log.e(TAG, "onNestedFling, vy: " + velocityY);
    if (mScroller == null) {
      mScroller = ScrollerCompat.create(child.getContext());
    }

    // down fling
    RecyclerView rv = (RecyclerView) target;
    LinearLayoutManager llm = (LinearLayoutManager) rv.getLayoutManager();
    if (velocityY < 0
        && llm.findFirstCompletelyVisibleItemPosition() == 0
        && getChildBottomMargin() < 0) {
      mScroller.fling(0, getChildBottomMargin(), 0, (int) (-velocityY / FLING_SCALE), 0, 0,
          -child.getMeasuredHeight() + minH,
          0);
      if (mScroller.computeScrollOffset()) {
        mFlingRunnable = new FlingRunnable(coordinatorLayout, child);
        ViewCompat.postOnAnimation(child, mFlingRunnable);
        return true;
      }
    }

    return false;
  }

  // use child's bottom margin for locating recylerview's y
  private void setBottomMargin(CoordinatorLayout coordinatorLayout, View child, int bottomMargin) {
    MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
    int orgMargin = lp.bottomMargin;
    int h = child.getMeasuredHeight();
    int fakeHeight = Math.max(minH, h + bottomMargin);
    lp.bottomMargin = Math.min(0, -(h - fakeHeight));
    child.setLayoutParams(lp);
    coordinatorLayout.dispatchDependentViewsChanged(child);
    if (orgMargin != lp.bottomMargin) {
      notifyHeightChange(child.getMeasuredHeight() + lp.bottomMargin, child.getMeasuredHeight());
    }
  }

  private void notifyHeightChange(int drawHeight, int measureHeight) {
    for (HeightChangeListener listener : listeners) {
      listener.onHeightChange(drawHeight, measureHeight);
    }
  }

  public void addHeightChangeListener(HeightChangeListener listener) {
    listeners.add(listener);
  }

  public void removeHeightChangeListener(HeightChangeListener listener) {
    listeners.remove(listener);
  }

  public int getMinH() {
    return minH;
  }

  public void animateToTop() {
    ensureScrollToTopAnim();
    final int currentBottomMargin = getChildBottomMargin();
    final int target = -(child.getMeasuredHeight() - getMinH());
    scrollToTopAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override public void onAnimationUpdate(ValueAnimator animation) {
        float value = (float) animation.getAnimatedValue();
        int targetMargin = (int) (currentBottomMargin + (target - currentBottomMargin) * value);
        setBottomMargin(coordinatorLayout, child, targetMargin);
      }
    });
    scrollToTopAnim.start();
  }

  private void ensureScrollToTopAnim() {
    if (scrollToTopAnim == null) {
      scrollToTopAnim = ValueAnimator.ofFloat(0.f, 1.f);
      scrollToTopAnim.setDuration(200);
    }
  }

  private class FlingRunnable implements Runnable {

    CoordinatorLayout coordinatorLayout;
    View child;

    public FlingRunnable(CoordinatorLayout coordinatorLayout, View child) {
      this.coordinatorLayout = coordinatorLayout;
      this.child = child;
    }

    @Override public void run() {
      if (mScroller.computeScrollOffset()) {
        int value = mScroller.getCurrY();
        setBottomMargin(coordinatorLayout, child, value);
        ViewCompat.postOnAnimation(child, this);
      }
    }
  }

  public interface HeightChangeListener {
    /**
     * @param drawHeight the height can be seen
     * @param measureHeight the height was measured
     */
    void onHeightChange(int drawHeight, int measureHeight);
  }
}
