package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import me.ele.commons.AppLogger;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @InjectView(R.id.start_btn) Button mStartBtn;

  @InjectView(R.id.sv) ScrollView sv;

  @InjectView(R.id.tv) TextView tv;

  @InjectView(R.id.lv) LinearLayout lv;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);
  }

  @OnClick({ R.id.start_btn }) public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.start_btn) {
      AppLogger.d("sv mh: " + sv.getMeasuredHeight());
      AppLogger.d("sv h: " + sv.getHeight());
      AppLogger.d("tv height: " + tv.getMeasuredHeight());
      AppLogger.d("sv sy: " + sv.getScrollY());

      int bottom = lv.getBottom() + sv.getPaddingBottom();
      int sy = sv.getScrollY();
      int sh = sv.getHeight();
      int delta = bottom - (sy + sh);

      sv.scrollBy(0, delta);
    }
  }
}
