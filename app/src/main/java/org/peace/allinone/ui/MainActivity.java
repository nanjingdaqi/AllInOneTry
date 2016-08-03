package org.peace.allinone.ui;

import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.ele.commons.AppLogger;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);
  }

  @OnClick(R.id.start_btn) public void onClick(View v) {
    Matrix matrix = new Matrix();
    AppLogger.e("m0: " + matrix);
    matrix.preTranslate(10, 10);
    AppLogger.e("m1: " + matrix);
    matrix.preRotate(90);
    AppLogger.e("m2: " + matrix);
  }
}
