package me.ele.shopping.ui.home.toolbar;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextSwitcher;
import android.widget.TextView;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class PromotionInfoView extends TextSwitcher {

  private final long ANIM_DURATION = 2000;
  private final long ANIM_DELAY = 3000;

  private List<String> texts;
  private int index;

  private Handler handler = new Handler();

  private Runnable task = new Runnable() {
    @Override public void run() {
      setText(texts.get(index++ % texts.size()));
      handler.postDelayed(task, ANIM_DELAY);
    }
  };

  public PromotionInfoView(Context context) {
    this(context, null);
  }

  public PromotionInfoView(Context context, AttributeSet attrs) {
    super(context, attrs);
    addView(createTextView());
    addView(createTextView());
    setInAnimation(TextSwitcherHelper.createInAnim(ANIM_DURATION));
    setOutAnimation(TextSwitcherHelper.createOutAnim(ANIM_DURATION));
  }

  private TextView createTextView() {
    TextView tv = new TextView(getContext());
    tv.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
    tv.setTextColor(Color.WHITE);
    tv.setTextSize(11);
    tv.setLayoutParams(new LayoutParams(MATCH_PARENT, MATCH_PARENT));
    return tv;
  }

  public void setTexts(List<String> texts) {
    this.texts = texts;
    resetAnim();
    setCurrentText(texts.get(0));
    ++index;
    if (texts.size() > 1) {
      handler.postDelayed(task, ANIM_DELAY);
    }
  }

  private void resetAnim() {
    handler.removeCallbacks(task);
    index = 0;
  }
}
