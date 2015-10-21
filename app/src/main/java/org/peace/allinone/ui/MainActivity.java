package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import me.ele.commons.AppLogger;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  double a = 0.051, b = 0.055, c = 0.059;

  @InjectView(R.id.start_btn) Button mStartBtn;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);

    DecimalFormat df = new DecimalFormat("0.##");
    df.setRoundingMode(RoundingMode.FLOOR);
    AppLogger.d("a: " + df.format(a));
    AppLogger.d("b: " + df.format(b));
    AppLogger.d("c: " + df.format(c));
    df.setRoundingMode(RoundingMode.HALF_UP);
    AppLogger.d("a: " + df.format(a));
    AppLogger.d("b: " + df.format(b));
    AppLogger.d("c: " + df.format(c));

    double diff = c - b;
    AppLogger.d("diff: " + diff);
    BigDecimal bd = new BigDecimal(String.valueOf(diff));
    double diff1 = bd.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
    AppLogger.d("diff1: " + diff1);
    AppLogger.d("diff1: " + (diff1 > 0));
    AppLogger.d("diff1: " + (diff1 == 0));
    double diff2 = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    AppLogger.d("diff2: " + diff2);
    AppLogger.d("diff2: " + (diff2 > 0));
    AppLogger.d("diff2: " + (diff2 == 0));
    double diff3 = bd.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
    AppLogger.d("diff3: " + diff3);
    AppLogger.d("diff3: " + (diff3 > 0));
    AppLogger.d("diff3: " + (diff3 == 0));
  }

  @OnClick({ R.id.start_btn }) public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.start_btn) {

    }
  }
}
