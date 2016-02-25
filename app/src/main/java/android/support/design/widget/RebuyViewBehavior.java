package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import me.ele.commons.AppLogger;

import static android.view.View.GONE;

public class RebuyViewBehavior extends CoordinatorLayout.Behavior<View> {

  private static final long FLING_VELOCITY_BASE = 3000;
  private static final long FLING_ANIM_DISTANCE_BASE = 200;

  private ObjectAnimator flingOutAnim;
  private ObjectAnimator flingInAnim;
  private long maxAnimDuration = 200;
  private NestedScrollingChildHelper scrollingChildHelper;
  private boolean skipNestedPreScroll;

  public RebuyViewBehavior(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public boolean onMeasureChild(CoordinatorLayout parent, View child, int parentWidthMeasureSpec,
      int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
    if (scrollingChildHelper == null) {
      scrollingChildHelper = new NestedScrollingChildHelper(parent);
      scrollingChildHelper.setNestedScrollingEnabled(true);
    }
    if (child.getVisibility() == GONE) {
      return true;
    }
    return super.onMeasureChild(parent, child, parentWidthMeasureSpec, widthUsed,
        parentHeightMeasureSpec, heightUsed);
  }

  @Override
  public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
    if (child.getVisibility() == GONE) {
      return true;
    }
    return super.onLayoutChild(parent, child, layoutDirection);
  }

  @Override public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child,
      View directTargetChild, View target, int nestedScrollAxes) {
    if (flingInAnim != null) {
      flingInAnim.cancel();
    }
    if (flingOutAnim != null) {
      flingOutAnim.cancel();
    }

    if (scrollingChildHelper.startNestedScroll(nestedScrollAxes)) {
      return true;
    }

    if (nestedScrollAxes != ViewCompat.SCROLL_AXIS_VERTICAL) {
      return false;
    }
    return true;
  }

  @Override
  public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target) {
    scrollingChildHelper.stopNestedScroll();
    skipNestedPreScroll = false;
  }

  @Override
  public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target,
      int dx, int dy, int[] consumed) {
    // dispatch to the upper level view at first
    int[] offsetInWindow = new int[2];
    scrollingChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);

    // completely consumed by parent
    if (consumed[1] == dy) {
      return;
    }

    // start handle by myself
    if (skipNestedPreScroll) {
      return;
    }
    dy -= consumed[1];
    if (dy < 0) {
      // scroll down, and let child consume first
      return;
    }

    if (isHidden(child) && dy > 0) {
      // scroll up, but has translate to the most
      return;
    }

    consumed[1] = dy;
    float currentY = child.getY();
    float h = child.getMeasuredHeight();
    float destY = currentY - dy;
    if (destY > 0) {
      destY = 0;
      consumed[1] = -(int) (destY - currentY);
    } else if (destY < -h) {
      destY = -h;
      consumed[1] = -(int) (destY - currentY);
    }
    child.setY(destY);

    if (consumed[1] != 0) {
      coordinatorLayout.dispatchDependentViewsChanged(child);
    }
  }

  @Override public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target,
      int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
    if (dyUnconsumed >= 0) {
      // scroll up, not interested at this moment
      scrollingChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed,
          new int[2]);
      skipNestedPreScroll = false;
      return;
    }

    if (isFullyShow(child)) {
      scrollingChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed,
          new int[2]);
      skipNestedPreScroll = true;
      return;
    }

    skipNestedPreScroll = false;
    float destY = Math.min(0, child.getY() - dyUnconsumed);
    dyUnconsumed += destY - child.getY();
    dyConsumed -= destY - child.getY();
    child.setY(destY);
    coordinatorLayout.dispatchDependentViewsChanged(child);

    if (dyUnconsumed < 0) {
      // still not consumed totally
      scrollingChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed,
          new int[2]);
    }
  }

  @Override
  public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, View child, View target,
      float velocityX, float velocityY) {
    scrollingChildHelper.dispatchNestedPreFling(velocityX, velocityY);
    AppLogger.e("vy: " + velocityY);
    if (velocityY > 0 && !isHidden(child)) {
      // fling up
      animateToHide(coordinatorLayout, child, (int) velocityY);
    }
    // do not prevent child flinging itself
    return false;
  }

  @Override
  public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View child, View target,
      float velocityX, float velocityY, boolean consumed) {
    scrollingChildHelper.dispatchNestedFling(velocityX, velocityY, consumed);

    if (consumed) {
      return false;
    }
    if (velocityY < 0 && !isFullyShow(child)) {
      animateToShow(coordinatorLayout, child, (int) velocityY);
    }
    return false;
  }

  private void animateToShow(final CoordinatorLayout parent, final View child, int v) {
    float currentY = child.getY();
    if (currentY >= 0) {
      return;
    }
    flingInAnim = ObjectAnimator.ofFloat(child, "y", currentY, 0);
    flingInAnim.addUpdateListener(new AnimatorUpdateListener() {
      @Override public void onAnimationUpdate(ValueAnimator animation) {
        parent.dispatchDependentViewsChanged(child);
      }
    });
    flingInAnim.addListener(new AnimatorListenerAdapter() {
      @Override public void onAnimationEnd(Animator animation) {
        parent.dispatchDependentViewsChanged(child);
      }
    });
    long distance = (long) Math.abs(currentY);
    long duration = distance * maxAnimDuration / FLING_ANIM_DISTANCE_BASE;
    duration = duration * FLING_VELOCITY_BASE / Math.abs(v);
    flingInAnim.setDuration(duration).start();
  }

  private void animateToHide(final CoordinatorLayout parent, final View child, int v) {
    float currentY = child.getY();
    if (currentY <= -child.getMeasuredHeight()) {
      return;
    }
    flingOutAnim = ObjectAnimator.ofFloat(child, "y", currentY, -child.getMeasuredHeight());
    flingOutAnim.addUpdateListener(new AnimatorUpdateListener() {
      @Override public void onAnimationUpdate(ValueAnimator animation) {
        parent.dispatchDependentViewsChanged(child);
      }
    });
    flingOutAnim.addListener(new AnimatorListenerAdapter() {
      @Override public void onAnimationEnd(Animator animation) {
        parent.dispatchDependentViewsChanged(child);
      }
    });
    long distance = (long) Math.abs(-child.getMeasuredHeight() - currentY);
    long duration = distance * maxAnimDuration / FLING_ANIM_DISTANCE_BASE;
    duration = duration * FLING_VELOCITY_BASE / Math.abs(v);
    AppLogger.e("duration: " + duration);
    flingOutAnim.setDuration(duration).start();
  }

  private boolean isHidden(View child) {
    float h = child.getMeasuredHeight();
    float currentY = child.getY();
    if (currentY <= -h) {
      return true;
    }
    return false;
  }

  private boolean isFullyShow(View child) {
    float currentY = child.getY();
    if (currentY >= 0) {
      return true;
    }
    return false;
  }
}
