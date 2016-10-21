package me.ele.base.utils;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;

public class ViewCompat {

  private ViewCompat() {

  }

  public static void setImageAlpha(ImageView imageView, int alpha) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
      imageView.setImageAlpha(alpha);
    } else {
      imageView.setAlpha(alpha);
    }
  }

  public static void setBackgroundDrawable(View view, Drawable drawable) {
    int l = view.getPaddingLeft();
    int t = view.getPaddingTop();
    int r = view.getPaddingRight();
    int b = view.getPaddingBottom();
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
      view.setBackground(drawable);
    } else {
      view.setBackgroundDrawable(drawable);
    }
    view.setPadding(l, t, r, b);
  }

  public static void removeOnGlobalLayoutListener(View view, OnGlobalLayoutListener listener) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
      view.getViewTreeObserver().removeOnGlobalLayoutListener(listener);
    } else {
      view.getViewTreeObserver().removeGlobalOnLayoutListener(listener);
    }
  }

  public static GradientDrawable updateGradientDrawable(GradientDrawable drawable, int startColor,
      int endColor, GradientDrawable.Orientation orientation) {
    GradientDrawable gradientBg = drawable;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
      gradientBg.setColors(new int[] {startColor, endColor});
      gradientBg.setOrientation(orientation);
    } else {
      gradientBg = new GradientDrawable(orientation, new int[] {startColor, endColor});
      gradientBg.setBounds(drawable.getBounds());
    }
    return gradientBg;
  }
}
