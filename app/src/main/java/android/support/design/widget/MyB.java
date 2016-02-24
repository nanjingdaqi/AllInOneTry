package android.support.design.widget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import me.ele.commons.AppLogger;

public class MyB extends CoordinatorLayout.Behavior<View> {

  private ObjectAnimator flingOutAnim;
  private ObjectAnimator flingInAnim;
  private long maxAnimDuration = 400;

  public MyB(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child,
      View directTargetChild, View target, int nestedScrollAxes) {
    if (flingInAnim != null) {
      flingInAnim.cancel();
    }
    if (flingOutAnim != null) {
      flingOutAnim.cancel();
    }
    if (nestedScrollAxes != ViewCompat.SCROLL_AXIS_VERTICAL) {
      return false;
    }
    return true;
  }

  @Override
  public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target,
      int dx, int dy, int[] consumed) {
    AppLogger.e("dy: " + dy);
    if (isHidden(child) && dy < 0) {
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
    if (dyUnconsumed < 0) {
      float destY = child.getY() - dyUnconsumed;
      child.setY(Math.min(0, destY));
    }
  }

  @Override
  public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, View child, View target,
      float velocityX, float velocityY) {
    AppLogger.e("vy: " + velocityY);
    if (velocityY > 0 && !isHidden(child)) {
      float currentY = child.getY();
      flingOutAnim = ObjectAnimator.ofFloat(child, "y", currentY, -child.getMeasuredHeight());
      flingOutAnim.addUpdateListener(new AnimatorUpdateListener() {
        @Override public void onAnimationUpdate(ValueAnimator animation) {
          coordinatorLayout.dispatchDependentViewsChanged(child);
        }
      });
      long distance = (long) Math.abs(-child.getMeasuredHeight() - currentY);
      long duration = distance * maxAnimDuration / 1000;
      flingOutAnim.setDuration(duration).start();
      AppLogger.e("cy: " + currentY + ", dis: " + distance);
      return true;
    }
    if (velocityY < 0 && !isTotalShow(child) && !isHidden(child)) {
      float currentY = child.getY();
      flingInAnim = ObjectAnimator.ofFloat(child, "y", currentY, 0);
      flingInAnim.addUpdateListener(new AnimatorUpdateListener() {
        @Override public void onAnimationUpdate(ValueAnimator animation) {
          coordinatorLayout.dispatchDependentViewsChanged(child);
        }
      });
      long distance = (long) Math.abs(currentY);
      long duration = distance * maxAnimDuration / 1000;
      flingInAnim.setDuration(duration).start();
    }

    return false;
  }

  @Override
  public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View child, View target,
      float velocityX, float velocityY, boolean consumed) {
    if (!consumed && velocityY < 0 && !isTotalShow(child)) {
      float currentY = child.getY();
      flingInAnim = ObjectAnimator.ofFloat(child, "y", currentY, 0);
      flingInAnim.addUpdateListener(new AnimatorUpdateListener() {
        @Override public void onAnimationUpdate(ValueAnimator animation) {
          coordinatorLayout.dispatchDependentViewsChanged(child);
        }
      });
      long distance = (long) Math.abs(currentY);
      long duration = distance * maxAnimDuration / 1000;
      flingInAnim.setDuration(duration).start();
      return true;
    }
    return false;
  }

  private boolean isHidden(View child) {
    float h = child.getMeasuredHeight();
    float currentY = child.getY();
    if (currentY <= -h) {
      return true;
    }
    return false;
  }

  private boolean isTotalShow(View child) {
    float currentY = child.getY();
    if (currentY >= 0) {
      return true;
    }
    return false;
  }
}
