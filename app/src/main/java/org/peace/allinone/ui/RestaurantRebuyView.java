package org.peace.allinone.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class RestaurantRebuyView extends TextView {
  public RestaurantRebuyView(Context context) {
    super(context);
  }

  public RestaurantRebuyView(Context context, AttributeSet attrs) {
    super(context, attrs);
    setNestedScrollingEnabled(true);
  }

  public RestaurantRebuyView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }
}
