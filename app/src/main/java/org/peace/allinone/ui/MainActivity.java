package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @InjectView(R.id.target) View target;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);
  }

  @OnClick({ R.id.start_btn }) public void onStartBtn(View v) {
    target.setVisibility(View.VISIBLE);
    target.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
      @Override public void onGlobalLayout() {
        target.animate().translationXBy(300).setDuration(4000).start();

        target.getViewTreeObserver().removeGlobalOnLayoutListener(this);
      }
    });
  }

  @OnClick(R.id.second_btn) public void onSecondBtn(View v) {
    target.animate().translationXBy(-300).setDuration(4000).start();
  }
}
