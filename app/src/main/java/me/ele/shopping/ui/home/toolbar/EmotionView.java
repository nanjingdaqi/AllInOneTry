package me.ele.shopping.ui.home.toolbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import org.peace.allinone.R;

public class EmotionView extends FrameLayout {

  private final int ANIM_DUR1 = 300;
  private final int ANIM_DUR2 = 1000;

  @BindView(R.id.info_and_icon) protected InfoAndIconView infoAndIconView;
  @BindView(R.id.order_status) protected OrderStatusView orderStatusView;

  public EmotionView(Context context) {
    this(context, null);
  }

  public EmotionView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public EmotionView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    inflate(context, R.layout.sp_home_emotion_view, this);
    ButterKnife.bind(this, this);
  }

  public void showWeather() {
    infoAndIconView.setVisibility(VISIBLE);
    orderStatusView.setVisibility(GONE);
    infoAndIconView.showWeather();
  }

  public void showPromotion() {
    infoAndIconView.setVisibility(VISIBLE);
    orderStatusView.setVisibility(GONE);
    infoAndIconView.showPromotion();
  }

  public void showOrderStatus(boolean anim) {
    if (!anim) {
      infoAndIconView.setVisibility(GONE);
      orderStatusView.setVisibility(VISIBLE);
      orderStatusView.setStatusTexts();
    } else {
      showOrderStatusWithAnim();
    }
  }

  private void showOrderStatusWithAnim() {
    infoAndIconView.animate()
        .setListener(new AnimatorListenerAdapter() {
          @Override public void onAnimationEnd(Animator animation) {
            infoAndIconView.setVisibility(GONE);
          }
        })
        .alpha(0)
        .setDuration(ANIM_DUR1)
        .start();

    orderStatusView.getViewTreeObserver().addOnGlobalLayoutListener(
        new ViewTreeObserver.OnGlobalLayoutListener() {
          @Override public void onGlobalLayout() {
            // todo
            orderStatusView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            orderStatusView.setTranslationX(orderStatusView.getMeasuredWidth());
            orderStatusView.animate()
                .setStartDelay(ANIM_DUR1)
                .translationX(0)
                .setInterpolator(buildInterpolator())
                .setListener(new AnimatorListenerAdapter() {
                  @Override public void onAnimationStart(Animator animation) {
                  }
                })
                .setDuration(ANIM_DUR2)
                .start();
          }
        });
    orderStatusView.setVisibility(VISIBLE);
  }

  private Interpolator buildInterpolator() {
    OvershootInterpolator interpolator = new OvershootInterpolator(1.0f);
    return interpolator;
  }
}
