package org.peace.allinone.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.ele.base.utils.DimenUtil;
import org.peace.allinone.R;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static me.ele.base.utils.DimenUtil.dip2px;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.ll) LinearLayout llView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

    init();
  }

  @OnClick(R.id.start_btn) public void onClick(View v) {

  }

  private void init() {
    llView.addView(createCell(Color.RED));
    llView.addView(createCell(Color.GREEN));
    llView.addView(createCell(Color.BLUE));
    llView.addView(createCell(Color.BLACK));
  }

  View createCell(int bg) {
    View view = new View(this);
    view.setLayoutParams(new ViewGroup.MarginLayoutParams(dip2px(200), MATCH_PARENT));
    view.setBackgroundColor(bg);
    return view;
  }
}
