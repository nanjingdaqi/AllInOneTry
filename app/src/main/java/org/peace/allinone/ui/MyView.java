package org.peace.allinone.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import org.peace.allinone.R;

public class MyView extends LinearLayout {

  @InjectView(R.id.sub_tv) TextView subTV;

  public MyView(Context context, AttributeSet attrs) {
    super(context, attrs);
    inflate(context, R.layout.my_view, this);

    ButterKnife.inject(this, this);
  }

  @OnClick(R.id.my_start_btn) public void onClick() {
    subTV.setText("New My_view_sub_tv");
  }
}
