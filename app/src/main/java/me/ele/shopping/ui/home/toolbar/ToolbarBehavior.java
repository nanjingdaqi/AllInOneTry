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
import java.util.HashSet;
import java.util.Set;
import me.ele.base.utils.DimenUtil;

import static android.view.View.MeasureSpec.makeMeasureSpec;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static me.ele.base.utils.DimenUtil.getScreenHeight;
import static me.ele.base.utils.DimenUtil.getScreenWidth;
import static me.ele.base.utils.DimenUtil.getStatusBarHeight;

public class ToolbarBehavior extends CoordinatorLayout.Behavior<HomeFragmentToolbar> {

  private static final String TAG = ToolbarBehavior.class.getSimpleName();
  private static final int FLING_SCALE = 2;

  private NestedScrollingChildHelper scrollingChildHelper;
  private ScrollerCompat mScroller;
  private FlingRunnable mFlingRunnable;
  private int minH;
  private int currentDrawHeight = -1; // minH <= currentDrawHeight <= child.measureHeight
  private int currentMeasureHeight = -1;

  private CoordinatorLayout coordinatorLayout;
  private HomeFragmentToolbar child;
  private ValueAnimator scrollToTopAnim;

  private Set<HeightChangeListener> listeners = new HashSet<>();

  public ToolbarBehavior() {
  }

  public ToolbarBehavior(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public void reset() {
    setCurrentOffset(coordinatorLayout, child, child.getMeasuredHeight());
  }

  void setup(CoordinatorLayout coordinatorLayout, HomeFragmentToolbar child) {
    this.coordinatorLayout = coordinatorLayout;
    this.child = child;
    scrollingChildHelper = new NestedScrollingChildHelper(coordinatorLayout);
    scrollingChildHelper.setNestedScrollingEnabled(true);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      minH = getStatusBarHeight() + DimenUtil.getToolBarHeight((Activity) child.getContext());
    } else {
      minH = DimenUtil.getToolBarHeight((Activity) child.getContext());
    }
    if (child.getMeasuredHeight() == 0) {
      child.measure(makeMeasureSpec(getScreenWidth(), MATCH_PARENT),
          makeMeasureSpec(getScreenHeight(), WRAP_CONTENT));
    }
  }

  @Override
  public boolean onLayoutChild(CoordinatorLayout parent, final HomeFragmentToolbar child,
      int layoutDirection) {
    Log.e(TAG, "onLayoutChild");
    boolean ret = super.onLayoutChild(parent, child, layoutDirection);
    if (currentDrawHeight == -1 || currentMeasureHeight == -1) {
      Log.e(TAG, "notify due to first layout");
      child.post(new Runnable() {
        @Override public void run() {
          setCurrentOffset(coordinatorLayout, child, child.getMeasuredHeight());
          currentMeasureHeight = child.getMeasuredHeight();
        }
      });
    } else if (currentMeasureHeight != child.getMeasuredHeight()) {
      Log.e(TAG, "notify due to height change");
      child.post(new Runnable() {
        @Override public void run() {
          setCurrentOffset(coordinatorLayout, child,
              currentDrawHeight + (child.getMeasuredHeight() - currentMeasureHeight));
          currentMeasureHeight = child.getMeasuredHeight();
        }
      });
    }
    return ret;
  }

  @Override
  public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, HomeFragmentToolbar child,
      View directTargetChild, View target, int nestedScrollAxes) {
    if (scrollingChildHelper.startNestedScroll(nestedScrollAxes)) {
      return true;
    }
    return target instanceof RecyclerView && nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
  }

  @Override
  public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, HomeFragmentToolbar child,
      View target) {
    scrollingChildHelper.stopNestedScroll();
  }

  @Override
  public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, HomeFragmentToolbar child,
      View target, int dx, int dy, int[] consumed) {
    int parentDy = dy;
    if (dy > 0) {
      parentDy = Math.min(coordinatorLayout.getTop(), dy);
    }
    scrollingChildHelper.dispatchNestedPreScroll(dx, parentDy, consumed, new int[2]);

    if (consumed[1] == dy) {
      return;
    }

    dy -= consumed[1];

    // up scroll
    if (dy > 0) {
      int orgDrawHeight = currentDrawHeight;
      setCurrentOffset(coordinatorLayout, child, currentDrawHeight - dy);

      consumed[1] = orgDrawHeight - currentDrawHeight;
    }
  }

  @Override
  public void onNestedScroll(CoordinatorLayout coordinatorLayout, HomeFragmentToolbar child,
      View target,
      int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
    // down scroll
    if (dyUnconsumed < 0) {
      int orgDrawHeight = currentDrawHeight;
      setCurrentOffset(coordinatorLayout, child, currentDrawHeight - dyUnconsumed);
      dyUnconsumed += currentDrawHeight - orgDrawHeight;
      scrollingChildHelper.dispatchNestedScroll(0, 0, 0, dyUnconsumed, new int[2]);
    }
  }

  @Override
  public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, HomeFragmentToolbar child,
      View target,
      float velocityX, float velocityY) {
    if (mScroller == null) {
      mScroller = ScrollerCompat.create(child.getContext());
    }
    if (coordinatorLayout.getTop() > 0 && scrollingChildHelper.dispatchNestedPreFling(velocityX,
        velocityY)) {
      return true;
    }

    // up fling
    if (velocityY > 0 && currentDrawHeight > minH) {
      mScroller.fling(0, currentDrawHeight, 0, (int) (-velocityY / FLING_SCALE), 0, 0, minH,
          child.getMeasuredHeight());
      if (mScroller.computeScrollOffset()) {
        mFlingRunnable = new FlingRunnable(coordinatorLayout, child);
        ViewCompat.postOnAnimation(child, mFlingRunnable);
        return true;
      }
    }

    return false;
  }

  @Override
  public boolean onNestedFling(CoordinatorLayout coordinatorLayout, HomeFragmentToolbar child,
      View target,
      float velocityX, float velocityY, boolean consumed) {
    if (mScroller == null) {
      mScroller = ScrollerCompat.create(child.getContext());
    }

    // down fling
    RecyclerView rv = (RecyclerView) target;
    LinearLayoutManager llm = (LinearLayoutManager) rv.getLayoutManager();
    if (velocityY < 0
        && llm.findFirstCompletelyVisibleItemPosition() == 0
        && currentDrawHeight < child.getMeasuredHeight()) {
      mScroller.fling(0, currentDrawHeight, 0, (int) (-velocityY / FLING_SCALE), 0, 0, minH,
          child.getMeasuredHeight());
      if (mScroller.computeScrollOffset()) {
        mFlingRunnable = new FlingRunnable(coordinatorLayout, child);
        ViewCompat.postOnAnimation(child, mFlingRunnable);
        return true;
      }
    }

    return false;
  }

  private void setCurrentOffset(CoordinatorLayout coordinatorLayout, View child, int offset) {
    int orgOffset = currentDrawHeight;
    currentDrawHeight = Math.max(minH, offset);
    currentDrawHeight = Math.min(currentDrawHeight, child.getMeasuredHeight());
    if (orgOffset != currentDrawHeight) {
      notifyHeightChange(currentDrawHeight, child.getMeasuredHeight());
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

  public int getCurrentDrawHeight() {
    return currentDrawHeight;
  }

  public void animateToTop() {
    ensureScrollToTopAnim();
    final int initOffset = currentDrawHeight;
    final int target = minH;
    scrollToTopAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override public void onAnimationUpdate(ValueAnimator animation) {
        float value = (float) animation.getAnimatedValue();
        int targetOffset = (int) (initOffset + (target - initOffset) * value);
        setCurrentOffset(coordinatorLayout, child, targetOffset);
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

    private CoordinatorLayout coordinatorLayout;
    private View child;

    public FlingRunnable(CoordinatorLayout coordinatorLayout, View child) {
      this.coordinatorLayout = coordinatorLayout;
      this.child = child;
    }

    @Override public void run() {
      if (mScroller.computeScrollOffset()) {
        int value = mScroller.getCurrY();
        setCurrentOffset(coordinatorLayout, child, value);
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
