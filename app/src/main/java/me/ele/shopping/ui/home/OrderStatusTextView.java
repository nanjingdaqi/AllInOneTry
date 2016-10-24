package me.ele.shopping.ui.home;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextSwitcher;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import static android.view.Gravity.CENTER_VERTICAL;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static me.ele.shopping.ui.home.TextSwitcherHelper.createInAnim;
import static me.ele.shopping.ui.home.TextSwitcherHelper.createOutAnim;

public class OrderStatusTextView extends TextSwitcher {

  private final long ANIM_DURATION = 2000;
  private final long ANIM_DELAY = 3000;

  private List<String> texts = new ArrayList<>();

  private int index;

  private Handler handler = new Handler();

  private Runnable task = new Runnable() {
    @Override public void run() {
      setText(texts.get(index++ % texts.size()));
      handler.postDelayed(task, ANIM_DELAY);
    }
  };

  public OrderStatusTextView(Context context) {
    this(context, null);
  }

  public OrderStatusTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
    addView(createTextView());
    addView(createTextView());
    setInAnimation(createInAnim(ANIM_DURATION));
    setOutAnimation(createOutAnim(ANIM_DURATION));
  }

  private TextView createTextView() {
    TextView tv = new TextView(getContext());
    tv.setMaxLines(2);
    tv.setTextColor(Color.WHITE);
    tv.setTextSize(10);
    tv.setGravity(CENTER_VERTICAL);
    LayoutParams lp = new LayoutParams(WRAP_CONTENT, MATCH_PARENT);
    lp.gravity = CENTER_VERTICAL;
    tv.setLayoutParams(lp);
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
