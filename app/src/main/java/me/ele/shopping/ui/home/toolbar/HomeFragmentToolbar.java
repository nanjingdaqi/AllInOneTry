package me.ele.shopping.ui.home.toolbar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import org.peace.allinone.R;

public class HomeFragmentToolbar extends LinearLayout {

  private ToolbarBgDrawable toolbarBgDrawable;

  public HomeFragmentToolbar(Context context) {
    this(context, null);
  }

  public HomeFragmentToolbar(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public HomeFragmentToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    inflate(context, R.layout.sp_home_fragment_toolbar, this);
    toolbarBgDrawable = new ToolbarBgDrawable(this);
    setBackground(toolbarBgDrawable);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
  }

  public ToolbarBehavior getBehavior() {
    return (ToolbarBehavior) ((CoordinatorLayout.LayoutParams) getLayoutParams()).getBehavior();
  }

  @Override protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    getBehavior().addHeightChangeListener(toolbarBgDrawable);
  }

  @Override protected void onDetachedFromWindow() {
    getBehavior().removeHeightChangeListener(toolbarBgDrawable);
    super.onDetachedFromWindow();
  }

  public void setSkinDrawable(Drawable skinDrawable) {
    toolbarBgDrawable.setSkinDrawable(skinDrawable);
  }
}
