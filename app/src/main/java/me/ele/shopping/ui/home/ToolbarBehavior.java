package me.ele.shopping.ui.home;

import android.app.Activity;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ScrollerCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import java.util.HashSet;
import java.util.Set;
import me.ele.base.utils.DimenUtil;

import static me.ele.base.utils.DimenUtil.getStatusBarHeight;

public class ToolbarBehavior extends CoordinatorLayout.Behavior<View> {

  private static final String TAG = ToolbarBehavior.class.getSimpleName();
  private static final int FLING_SCALE = 5;

  private NestedScrollingChildHelper scrollingChildHelper;
  private ScrollerCompat mScroller;
  private FlingRunnable mFlingRunnable;
  private int minH;

  private CoordinatorLayout coordinatorLayout;
  private View child;

  private Set<HeightChangeListener> listeners = new HashSet<>();

  public ToolbarBehavior() {
  }

  public void reset() {
    setBottomMargin(coordinatorLayout, child, 0);
  }

  @Override
  public boolean onMeasureChild(CoordinatorLayout parent, View child, int parentWidthMeasureSpec,
      int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
    if (scrollingChildHelper == null) {
      scrollingChildHelper = new NestedScrollingChildHelper(parent);
      scrollingChildHelper.setNestedScrollingEnabled(true);
      this.coordinatorLayout = parent;
      this.child = child;
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        minH = getStatusBarHeight() + DimenUtil.getToolBarHeight((Activity) child.getContext());
      } else {
        minH = DimenUtil.getToolBarHeight((Activity) child.getContext());
      }
    }
    return super.onMeasureChild(parent, child, parentWidthMeasureSpec, widthUsed,
        parentHeightMeasureSpec, heightUsed);
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
      MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
      int orgMargin = lp.bottomMargin;
      setBottomMargin(coordinatorLayout, child, lp.bottomMargin - dy);

      Log.e(TAG, "onNestedPreScroll, consume dy: " + (-lp.bottomMargin + orgMargin));
      consumed[1] = -lp.bottomMargin + orgMargin;
    }
  }

  @Override public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target,
      int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
    Log.e(TAG, "onNestedScroll, dyUnconsumed: " + dyUnconsumed);

    // down scroll
    if (dyUnconsumed < 0) {
      MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
      int orgMargin = lp.bottomMargin;
      setBottomMargin(coordinatorLayout, child, lp.bottomMargin - dyUnconsumed);
      dyUnconsumed += -orgMargin - (-lp.bottomMargin);
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
    MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
    if (velocityY > 0 && child.getMeasuredHeight() + lp.bottomMargin > minH) {
      mScroller.fling(0, lp.bottomMargin, 0, (int) (-velocityY / FLING_SCALE), 0, 0,
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
    MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
    if (velocityY < 0
        && llm.findFirstCompletelyVisibleItemPosition() == 0
        && lp.bottomMargin < 0) {
      mScroller.fling(0, lp.bottomMargin, 0, (int) (-velocityY / FLING_SCALE), 0, 0,
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
