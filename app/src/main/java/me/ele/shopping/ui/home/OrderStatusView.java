package me.ele.shopping.ui.home;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.Arrays;
import org.peace.allinone.R;

public class OrderStatusView extends FrameLayout {

  @BindView(R.id.text) protected OrderStatusTextView statusTextView;

  public OrderStatusView(Context context) {
    this(context, null);
  }

  public OrderStatusView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public OrderStatusView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    inflate(context, R.layout.sp_home_order_status, this);
    ButterKnife.bind(this, this);
  }

  public void setStatusTexts() {
    statusTextView.setTexts(Arrays.asList("one", "two", "three"));
  }
}
