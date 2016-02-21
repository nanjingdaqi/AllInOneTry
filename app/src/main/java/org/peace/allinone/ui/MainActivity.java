package org.peace.allinone.ui;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import me.ele.commons.AppLogger;
import org.peace.allinone.R;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity {

  @InjectView(R.id.start_btn) Button mStartBtn;
  @InjectView(R.id.list_view_container) SlideBottomPanel myLayout;
  @InjectView(R.id.item_container) LinearLayout itemContainer;
  @InjectView(R.id.drag_view) LinearLayout dragView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);

    for (int i = 0; i < 60; i++) {
      TextView tv = new TextView(this);
      tv.setBackgroundColor(getResources().getColor(android.R.color.white));
      tv.setText("TV: " + i);
      LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 100);
      itemContainer.addView(tv, lp);
    }

    myLayout.setVisibility(GONE);
  }

  @OnClick(R.id.toggle) public void onToggle() {
    myLayout.displayPanel();
  }

  @OnClick(R.id.start_btn) public void onStartBtn() {

  }
}
