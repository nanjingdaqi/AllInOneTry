package org.peace.allinone.ui;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import me.ele.commons.AppLogger;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @InjectView(R.id.start_btn) Button mStartBtn;
  @InjectView(R.id.tv) TextView tv;

  TextPaint textPaint;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);

    textPaint = new TextPaint();
    textPaint.setTextSize(20);
  }

  @OnClick({ R.id.start_btn }) public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.start_btn) {
      tv.setText("");
      float w = textPaint.measureText(tv.getText().toString());
      AppLogger.e("width: " + w);
    }
  }
}
