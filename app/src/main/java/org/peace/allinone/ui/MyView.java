package org.peace.allinone.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import me.ele.commons.AppLogger;

/**
 * Created by peacepassion on 15/10/24.
 */
public class MyView extends View {

  VelocityTracker velocityTracker;

  public MyView(Context context) {
    super(context);
  }

  public MyView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    setMeasuredDimension(MeasureSpec.makeMeasureSpec(600, MeasureSpec.EXACTLY),
        MeasureSpec.makeMeasureSpec(600, MeasureSpec.EXACTLY));
  }

  @Override public boolean onTouchEvent(MotionEvent event) {
    AppLogger.d("motion event: " + event);
    int action = event.getAction();
    if (action == MotionEvent.ACTION_DOWN) {
      velocityTracker = VelocityTracker.obtain();
    } else if (action == MotionEvent.ACTION_MOVE) {
      velocityTracker.addMovement(event);
      velocityTracker.computeCurrentVelocity(1000);
      AppLogger.w("velocity x: " + velocityTracker.getXVelocity());
      AppLogger.w("velocity y: " + velocityTracker.getYVelocity());
    }
    return true;
  }
}
