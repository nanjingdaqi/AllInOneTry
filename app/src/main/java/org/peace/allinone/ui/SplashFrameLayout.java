package org.peace.allinone.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import android.widget.FrameLayout;

public class SplashFrameLayout extends FrameLayout {
  private Object mLastInsets;

  static {
    final int version = Build.VERSION.SDK_INT;
    if (version >= Build.VERSION_CODES.LOLLIPOP) {
      IMPL = new SplashLayoutCompatImplApi21();
      //IMPL = new SplashLayoutCompatImplBase();
    } else {
      IMPL = new SplashLayoutCompatImplBase();
    }
  }

  static final SplashLayoutCompatImpl IMPL;

  public SplashFrameLayout(Context context) {
    this(context, null);
  }

  public SplashFrameLayout(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public SplashFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    if (ViewCompat.getFitsSystemWindows(this)) {
      IMPL.configureApplyInsets(this);
    }
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    final boolean applyInsets = mLastInsets != null && ViewCompat.getFitsSystemWindows(this);
    final int count = getChildCount();
    for (int i = 0; i < count; i++) {
      final View child = getChildAt(i);
      if (child.getVisibility() == GONE) {
        continue;
      }
      final LayoutParams lp = (LayoutParams) child.getLayoutParams();
      if (applyInsets) {
        if (ViewCompat.getFitsSystemWindows(child)) {
          log("dispatch: " + child);
          IMPL.dispatchChildInsets(child, mLastInsets);
        } else {
          // This will cover the layout margin setting on api 21+, so we need wrap the content in
          // a new layout with inner margin setting.
          if (child instanceof Toolbar) {
            lp.topMargin = 0;
          } else {
            log("apply: " + child);
            IMPL.applyMarginInsets(lp, mLastInsets);
          }
        }
      }
    }
  }

  public void setChildInsets(Object insets) {
    mLastInsets = insets;
    requestLayout();
  }

  interface SplashLayoutCompatImpl {
    void configureApplyInsets(View splashLayout);

    void dispatchChildInsets(View child, Object insets);

    void applyMarginInsets(MarginLayoutParams lp, Object insets);
  }

  static class SplashLayoutCompatImplBase implements SplashLayoutCompatImpl {
    @Override public void configureApplyInsets(View splashLayout) {
    }

    @Override public void dispatchChildInsets(View child, Object insets) {
    }

    @Override public void applyMarginInsets(MarginLayoutParams lp, Object insets) {
    }
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP) static class SplashLayoutCompatImplApi21
      implements SplashLayoutCompatImpl {
    @Override public void configureApplyInsets(View splashLayout) {
      splashLayout.setOnApplyWindowInsetsListener(new InsetsListener());
      splashLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
          | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }

    @Override public void dispatchChildInsets(View child, Object insets) {
      WindowInsets wi = (WindowInsets) insets;
      wi = wi.replaceSystemWindowInsets(wi.getSystemWindowInsetLeft(), wi.getSystemWindowInsetTop(),
          wi.getSystemWindowInsetRight(), wi.getSystemWindowInsetBottom());
      child.dispatchApplyWindowInsets(wi);
    }

    @Override public void applyMarginInsets(MarginLayoutParams lp, Object insets) {
      WindowInsets wi = (WindowInsets) insets;
      wi = wi.replaceSystemWindowInsets(wi.getSystemWindowInsetLeft(), wi.getSystemWindowInsetTop(),
          wi.getSystemWindowInsetRight(), wi.getSystemWindowInsetBottom());
      lp.leftMargin = wi.getSystemWindowInsetLeft();
      lp.topMargin = wi.getSystemWindowInsetTop();
      //lp.topMargin = 0;
      lp.rightMargin = wi.getSystemWindowInsetRight();
      lp.bottomMargin = wi.getSystemWindowInsetBottom();
      log("top: " + lp.topMargin);
    }
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP) static class InsetsListener
      implements OnApplyWindowInsetsListener {
    @Override public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
      log("onApply window inset");
      final SplashFrameLayout splashFrameLayout = (SplashFrameLayout) v;
      splashFrameLayout.setChildInsets(insets);
      return insets.consumeSystemWindowInsets();
    }
  }

  public static void log(String msg) {
    Log.e("Splash", msg);
  }
}
