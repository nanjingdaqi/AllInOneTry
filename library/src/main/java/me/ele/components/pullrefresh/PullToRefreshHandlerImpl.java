package me.ele.components.pullrefresh;

import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import me.ele.recyclerview.R;

class PullToRefreshHandlerImpl extends LinearLayout implements PullToRefreshHandler {
  private static final int PINNED_MARGIN_TOP = 15;
  private static final int ANIMATE_TO_FINAL_BOXES_DURATION = 30;
  private static final int ANIMATE_TO_CLOSE_BOXES_DURATION = 150;

  private ImageView mBoxesImage;

  private ImageView mPinnedImage;

  private BoxesDrawable mBoxesDrawable;

  private BoxesPullToRefreshLayout ptr;

  private int mOriginalOffsetTop;
  private int mTotalDragDistance;

  private float mFromRotation;

  public PullToRefreshHandlerImpl(Context context, BoxesPullToRefreshLayout ptr) {
    super(context);
    this.ptr = ptr;
    final float density = getContext().getResources().getDisplayMetrics().density;
    setOrientation(VERTICAL);
    mBoxesDrawable = new BoxesDrawable(this);

    final int wc = ViewGroup.LayoutParams.WRAP_CONTENT;
    LayoutParams boxesImageLp = new LayoutParams(wc, wc);
    boxesImageLp.gravity = Gravity.CENTER_HORIZONTAL;
    mBoxesImage = new ImageView(context);
    mBoxesImage.setLayoutParams(boxesImageLp);
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
      mBoxesImage.setBackgroundDrawable(mBoxesDrawable);
    } else {
      mBoxesImage.setBackground(mBoxesDrawable);
    }
    addView(mBoxesImage);

    LayoutParams pinnedImageLp = new LayoutParams(wc, wc);
    pinnedImageLp.gravity = Gravity.CENTER_HORIZONTAL;
    pinnedImageLp.topMargin = (int) (PINNED_MARGIN_TOP * density);
    mPinnedImage = new ImageView(context);
    mPinnedImage.setLayoutParams(pinnedImageLp);
    mPinnedImage.setBackgroundResource(R.drawable.label);
    addView(mPinnedImage);
  }

  @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    mOriginalOffsetTop = (int) (-mBoxesImage.getMeasuredHeight() / 2f);
    mTotalDragDistance = -mOriginalOffsetTop;
    mBoxesImage.offsetTopAndBottom(mOriginalOffsetTop);
  }

  @Override public View getStage() {
    return this;
  }

  @Override public void onStart() {
    mBoxesDrawable.start();
  }

  @Override public void onPulling(float percent) {
    mBoxesImage.setVisibility(View.VISIBLE);
    mPinnedImage.setVisibility(View.VISIBLE);
    if (ptr.getMode() == BoxesPullToRefreshLayout.Mode.Z_AXIS) {
      final int targetTop = (int) (getTop() + getPaddingTop()
          + mOriginalOffsetTop + mTotalDragDistance * percent);
      final int offset = targetTop - mBoxesImage.getTop();
      mBoxesImage.offsetTopAndBottom(offset);
    }
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
      invalidate();
    }
    mBoxesDrawable.setLidRotation(percent);
    final float endRotation = BoxesDrawable.LID_MAXIMUM_ROTATION;
    final float targetRotation = endRotation * percent;
    mBoxesDrawable.setLidRotation(targetRotation);
    mFromRotation = targetRotation;
  }

  @Override public void onPreFinish() {
    mAnimateToCloseBoxes.setDuration(ANIMATE_TO_CLOSE_BOXES_DURATION);
    mAnimateToCloseBoxes.setAnimationListener(mCloseBoxesListener);
    mBoxesImage.startAnimation(mAnimateToCloseBoxes);
    mBoxesDrawable.postStop();
  }

  @Override public void onFinish() {
    mBoxesDrawable.stop();
  }

  @Override public void onReset() {
    mBoxesImage.setVisibility(View.VISIBLE);
    mPinnedImage.setVisibility(View.VISIBLE);
    mAnimateToFinalBoxes.setDuration(ANIMATE_TO_FINAL_BOXES_DURATION);
    mBoxesImage.startAnimation(mAnimateToFinalBoxes);
  }

  @Override public void setProgressViewOffsetTop(int offset) {
    setPadding(0, offset, 0, 0);
    requestLayout();
  }

  private Animation mAnimateToCloseBoxes = new Animation() {
    @Override protected void applyTransformation(float interpolatedTime, Transformation t) {
      final float targetRotation = mFromRotation - mFromRotation * interpolatedTime;
      mBoxesDrawable.setLidRotation(targetRotation);
    }
  };

  private Animation mAnimateToFinalBoxes = new Animation() {
    @Override protected void applyTransformation(float interpolatedTime, Transformation t) {
      final float finalRotation = BoxesDrawable.LID_FINAL_ROTATION;
      final float targetRotation = mFromRotation
          - (mFromRotation - finalRotation) * interpolatedTime;
      mBoxesDrawable.setLidRotation(targetRotation);
    }
  };

  private Animation.AnimationListener mCloseBoxesListener = new Animation.AnimationListener() {
    @Override public void onAnimationStart(Animation animation) {
    }

    @Override public void onAnimationRepeat(Animation animation) {
    }

    @Override public void onAnimationEnd(Animation animation) {
      mBoxesImage.setVisibility(View.GONE);
      mPinnedImage.setVisibility(View.GONE);
    }
  };
}
