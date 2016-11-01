package me.ele.shopping.ui.home;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.ele.shopping.ui.home.toolbar.HomeFragmentToolbar;
import org.peace.allinone.R;

import static me.ele.base.utils.ViewUtils.getActivity;

public class HomePageFragmentRootView extends FrameLayout {

  private static final String TAG = HomePageFragmentRootView.class.getSimpleName();

  @BindView(R.id.toolbar) protected HomeFragmentToolbar toolbar;
  @BindView(R.id.col) protected CoordinatorLayout col;

  private int touchSlop = ViewConfiguration.get(getActivity(this)).getScaledTouchSlop();
  private float initY = -1.f;
  private boolean hookTouch = false;

  {
    Log.e(TAG, "touchSlop: " + touchSlop);
  }

  public HomePageFragmentRootView(Context context) {
    this(context, null);
  }

  public HomePageFragmentRootView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public HomePageFragmentRootView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    ButterKnife.bind(this, this);
  }

  @Override public boolean onInterceptTouchEvent(MotionEvent ev) {
    boolean res = super.onInterceptTouchEvent(ev);
    int action = ev.getAction();
    if (action == MotionEvent.ACTION_DOWN) {
      hookTouch = isWithinToolbar(ev);
      if (hookTouch) {
        initY = ev.getY();
        Log.e(TAG, "hookTouch yes, initY: " + initY);
      }
      return res;
    } else if (action == MotionEvent.ACTION_MOVE) {
      if (!hookTouch) {
        return res;
      }
      float y = ev.getY();
      Log.e(TAG, "move Y: " + y);
      if (y - initY > touchSlop) {
        Log.e(TAG, "hookTouch done");
        return true;
      } else {
        return res;
      }
    }
    hookTouch = false;
    return res;
  }

  private boolean isWithinToolbar(MotionEvent ev) {
    float x = ev.getX();
    float y = ev.getY();
    return x >= toolbar.getLeft()
        && x <= toolbar.getRight()
        && y >= col.getTop()
        && y <= col.getTop() + toolbar.getBehavior().getCurrentDrawHeight();
  }
}
