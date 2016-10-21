package org.peace.allinone.ui;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.Nullable;
import me.ele.base.utils.DimenUtil;
import org.peace.allinone.ui.ToolbarBehavior.HeightChangeListener;

import static me.ele.base.utils.DimenUtil.getToolBarHeight;

public class AnimationManager implements HeightChangeListener {

  private static final String TAG = AnimationManager.class.getSimpleName();
  private static final float ALPHA_FACTOR = 0.7f;

  private Activity activity;
  private ToolbarBehavior toolbarBehavior;
  private HelloView helloView;
  @Nullable private EmotionView emotionView;
  private AddressView addressView;
  private SearchView searchView;
  @Nullable private SearchKeyWordsView searchKeyWordsView;

  private int statusBarHeight;

  public static Builder builder(Activity activity) {
    return new Builder(new AnimationManager(activity));
  }

  private AnimationManager(Activity activity) {
    this.activity = activity;
  }

  public HeightChangeListener getToolbarHeightChangeListener() {
    return this;
  }

  private int getStatusBarHeight() {
    if (statusBarHeight > 0) {
      return statusBarHeight;
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      statusBarHeight = DimenUtil.getStatusBarHeight();
    }
    return statusBarHeight;
  }

  @Override public void onHeightChange(int drawHeight, int measureHeight) {
    updateSearchView(drawHeight, measureHeight);
    updateHelloView(drawHeight, measureHeight);
    updateEmotionView(drawHeight, measureHeight);
    updateAddressView(drawHeight, measureHeight);
    updateSearchKeyWordsView(drawHeight, measureHeight);
  }

  private void updateSearchView(int drawH, int measureH) {
    int minToolbarH = toolbarBehavior.getMinH();
    int maxToolbarH = measureH;

    int searchViewH = searchView.getMeasuredHeight();
    int searchViewMargin = (getToolBarHeight(activity) - searchViewH) / 2;
    int minSearchViewH = searchViewMargin + getStatusBarHeight();
    int maxSearchViewH = searchView.getTop();

    // 使用直线插补，横轴x为toolbar底部所在位置，纵轴y为searchView顶部位置
    // 斜率
    float k = ((maxSearchViewH - minSearchViewH) * 1.0f) / ((maxToolbarH - minToolbarH) * 1.0f);
    int x = drawH - minToolbarH;
    int y = (int) (k * x + minSearchViewH);
    searchView.setY(y);
  }

  private void updateHelloView(int drawH, int measureH) {
    int maxToolbarH = measureH;
    int dy = maxToolbarH - drawH;
    int maxHelloViewH = helloView.getTop();
    int y = maxHelloViewH - dy;
    helloView.setY(y);
    helloView.setAlpha(calculateAlpha(drawH, measureH, dy));
  }

  private void updateAddressView(int drawH, int measureH) {
    int maxToolbarH = measureH;
    int dy = maxToolbarH - drawH;
    int maxAddressViewH = addressView.getTop();
    int y = maxAddressViewH - dy;
    addressView.setY(y);
    addressView.setAlpha(calculateAlpha(drawH, measureH, dy));
  }

  private void updateEmotionView(int drawH, int measureH) {
    if (emotionView == null) {
      return;
    }
    int maxToolbarH = measureH;
    int dy = maxToolbarH - drawH;
    int maxEmotionViewH = emotionView.getTop();
    int y = maxEmotionViewH - dy;
    emotionView.setY(y);
    emotionView.setAlpha(calculateAlpha(drawH, measureH, dy));
  }

  private void updateSearchKeyWordsView(int drawH, int measureH) {
    if (searchKeyWordsView == null) {
      return;
    }
    int maxToolbarH = measureH;
    int dy = maxToolbarH - drawH;
    int maxSearchKeyWordsViewH = searchKeyWordsView.getTop();
    int y = maxSearchKeyWordsViewH - dy;
    searchKeyWordsView.setY(y);
    searchKeyWordsView.setAlpha(calculateAlpha(drawH, measureH, dy));
  }

  private float calculateAlpha(int drawH, int measureH, int dy) {
    int maxToolbarH = measureH;
    int minToolbarH = toolbarBehavior.getMinH();
    int deadH = (int) (maxToolbarH - (maxToolbarH - minToolbarH) * ALPHA_FACTOR);
    float k = 1.0f / (maxToolbarH - deadH);
    int currentH = minToolbarH + (maxToolbarH - minToolbarH) - dy;
    float alpha;
    if (currentH <= deadH) {
      alpha = 0;
    } else {
      alpha = k * (currentH - deadH);
    }
    return alpha;
  }

  public static class Builder {
    private AnimationManager instance;

    private Builder(AnimationManager instance) {
      this.instance = instance;
    }

    public Builder setToolbarBehavior(ToolbarBehavior toolbarBehavior) {
      instance.toolbarBehavior = toolbarBehavior;
      return this;
    }

    public Builder setHelloView(HelloView helloView) {
      instance.helloView = helloView;
      return this;
    }

    public Builder setEmotionView(EmotionView emotionView) {
      instance.emotionView = emotionView;
      return this;
    }

    public Builder setAddressView(AddressView addressView) {
      instance.addressView = addressView;
      return this;
    }

    public Builder setSearchView(SearchView searchView) {
      instance.searchView = searchView;
      return this;
    }

    public Builder setSearchKeyWordsView(
        SearchKeyWordsView searchKeyWordsView) {
      instance.searchKeyWordsView = searchKeyWordsView;
      return this;
    }

    public AnimationManager build() {
      if (instance.toolbarBehavior == null) {
        throw new IllegalStateException("has to set toolbarBehavior for AnimationManager");
      }
      if (instance.searchView == null) {
        throw new IllegalStateException("has to set searchView for AnimationManager");
      }
      if (instance.helloView == null) {
        throw new IllegalStateException("has to set helloView for AnimationManager");
      }
      if (instance.addressView == null) {
        throw new IllegalStateException("has to set addressView for AnimationManager");
      }
      return instance;
    }
  }
}
