package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @InjectView(R.id.start_btn) Button mStartBtn;
  @InjectView(R.id.tv) TriangleRectPopTextView tv;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);

    tv.postDelayed(new Runnable() {
      @Override public void run() {
        tv.setVisibility(View.GONE);
        tv.setVisibility(View.GONE);
      }
    }, 1000);
  }

  @OnClick({ R.id.start_btn }) public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.start_btn) {
      tv.postDelayed(new Runnable() {
        @Override public void run() {
          tv.setVisibility(View.GONE);
          tv.setVisibility(View.GONE);
        }
      }, 1000);

      tv.postDelayed(new Runnable() {
        @Override public void run() {
          tv.setVisibility(View.GONE);
          tv.setVisibility(View.GONE);
        }
      }, 1000);

      tv.postDelayed(new Runnable() {
        @Override public void run() {
          tv.setVisibility(View.GONE);
          tv.setVisibility(View.VISIBLE);
        }
      }, 1000);
    }
  }
}
