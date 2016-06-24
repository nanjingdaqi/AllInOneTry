package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import me.ele.commons.AppLogger;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @InjectView(R.id.start_btn) Button mStartBtn;

  @InjectView(R.id.my_layout) View myLayout;

  int offset = 0;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);
  }

  @OnClick({ R.id.start_btn }) public void onClick(View v) {
    AppLogger.e("start btn clicked");
    myLayout.invalidate();
    myLayout.invalidate();
    myLayout.invalidate();
    myLayout.invalidate();
    myLayout.requestLayout();
  }

  @OnClick(R.id.btn) public void onClickBtn(View v) {
    AppLogger.e("btn clicked");
    myLayout.requestLayout();
    myLayout.requestLayout();
    myLayout.requestLayout();
    myLayout.invalidate();
  }
}
