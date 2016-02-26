package org.peace.allinone.ui;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import me.ele.commons.AppLogger;
import org.peace.allinone.R;

public class SL2 extends LinearLayout {

  private static final int MOVE_DISTANCE_TO_TRIGGER = 10;
  private static final int TRIGGER_VELOCITY = 1000;

  private ViewDragHelper viewDragHelper;
  private DragHelperCallback dragHelperCallback;

  private View dragView;
  private ScrollView scrollView;
  private ListView listView;
  private int dragViewId;
  private int scrollViewId;
  private int listViewId;

  private int layoutH;
  private int dragViewH;

  private float yVelocity;
  private float mTouchSlop;
  private int mMaxVelocity;
  private VelocityTracker mVelocityTracker;

  private ArgbEvaluator bgEvaluator = new ArgbEvaluator();
  private int trans = getContext().getResources().getColor(android.R.color.transparent);
  private int bgColor = trans;

  public SL2(Context context) {
    this(context, null);
  }

  public SL2(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public SL2(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    TypedArray ta = null;

    try {
      ta = context.obtainStyledAttributes(attrs, R.styleable.SlidingDownPanelLayout, defStyleAttr,
          0);
      dragViewId = ta.getResourceId(R.styleable.SlidingDownPanelLayout_drag_view_id, -1);
      scrollViewId = ta.getResourceId(R.styleable.SlidingDownPanelLayout_scroll_view_id, -1);
      listViewId = ta.getResourceId(R.styleable.SlidingDownPanelLayout_list_view_id, -1);
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

    dragHelperCallback = new DragHelperCallback();
    viewDragHelper = ViewDragHelper.create(this, 1.0f, dragHelperCallback);
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

    View lv = findViewById(listViewId);
    if (lv != null && !(lv instanceof ListView)) {
      throw new IllegalStateException("list_view_id must be ListView");
    }
    listView = (ListView) lv;
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    layoutH = getMeasuredHeight();
    dragViewH = dragView.getMeasuredHeight();
  }

  @Override public boolean onInterceptTouchEvent(MotionEvent ev) {
    return viewDragHelper.shouldInterceptTouchEvent(ev);
  }

  @Override public boolean onTouchEvent(MotionEvent event) {
    viewDragHelper.processTouchEvent(event);
    return true;
  }

  public void show() {
    smoothSlideTo(dragView.getLeft(), layoutH - dragViewH);
  }

  public void hide() {
    smoothSlideTo(dragView.getLeft(), layoutH);
  }

  public void smoothSlideTo(int l, int t) {
    if (viewDragHelper.smoothSlideViewTo(dragView, l, t)) {
      ViewCompat.postOnAnimation(dragView, new SettleRunnable(dragView));
    }
  }

  private class DragHelperCallback extends ViewDragHelper.Callback {

    @Override public boolean tryCaptureView(View child, int pointerId) {
      return child == dragView;
    }

    @Override public int getViewVerticalDragRange(View child) {
      return child.getMeasuredHeight();
    }

    @Override public int clampViewPositionVertical(View child, int top, int dy) {
      if (dy > 0) {
        // scroll down
        return Math.min(top, layoutH);
      } else {
        // scroll up
        return Math.max(top, layoutH - dragViewH);
      }
    }

    @Override public void onViewReleased(View releasedChild, float xvel, float yvel) {
      AppLogger.e("yv: " + yvel);
      float currentY = releasedChild.getY();
      if (yvel > TRIGGER_VELOCITY) {
        // scroll down
        if (currentY > layoutH - dragViewH + MOVE_DISTANCE_TO_TRIGGER) {
          hide();
        } else {
          show();
        }
      } else {
        float dy = currentY - (layoutH - dragViewH);
        if (dy < dragViewH / 2) {
          show();
        } else {
          hide();
        }
      }
    }

  }

  private class SettleRunnable implements Runnable {
    private final View view;

    SettleRunnable(View view) {
      this.view = view;
    }

    @Override
    public void run() {
      if (viewDragHelper != null && viewDragHelper.continueSettling(true)) {
        ViewCompat.postOnAnimation(view, this);
      }
    }
  }
}
