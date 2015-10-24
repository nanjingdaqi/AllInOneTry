package org.peace.allinone.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import me.ele.commons.AppLogger;

/**
 * Created by peacepassion on 15/10/24.
 */
public class MyView extends View implements GestureDetector.OnGestureListener {

  VelocityTracker velocityTracker;
  GestureDetector gestureDetector;

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
    gestureDetector = new GestureDetector(getContext(), this);
  }

  @Override public boolean dispatchTouchEvent(MotionEvent event) {
    super.dispatchTouchEvent(event);
    return gestureDetector.onTouchEvent(event);
  }

  @Override public boolean onTouchEvent(MotionEvent event) {
    //AppLogger.d("motion event: " + event);
    //int action = event.getAction();
    //if (action == MotionEvent.ACTION_DOWN) {
    //  velocityTracker = VelocityTracker.obtain();
    //} else if (action == MotionEvent.ACTION_MOVE) {
    //  velocityTracker.addMovement(event);
    //  velocityTracker.computeCurrentVelocity(1000);
    //  AppLogger.w("velocity x: " + velocityTracker.getXVelocity());
    //  AppLogger.w("velocity y: " + velocityTracker.getYVelocity());
    //}
    return true;
  }

  @Override public boolean onDown(MotionEvent e) {
    AppLogger.d("onDown");
    return true;
  }

  @Override public void onShowPress(MotionEvent e) {

  }

  @Override public boolean onSingleTapUp(MotionEvent e) {
    AppLogger.d("onSingleTapUp");
    return true;
  }

  @Override
  public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
    AppLogger.d("e1: " + e1);
    AppLogger.d("e2: " + e2);
    return true;
  }

  @Override public void onLongPress(MotionEvent e) {
    AppLogger.d("onLongPress");
  }

  @Override
  public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
    AppLogger.d("onFling, vx: " + velocityX + ", vy: " + velocityY);
    return true;
  }
}
