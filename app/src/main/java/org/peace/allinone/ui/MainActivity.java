package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.ele.base.utils.DimenUtil;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.target) View targetView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);
  }

  @OnClick(R.id.start_btn) public void onClick(View v) {
    logTargetInfo();
    targetView.setY(30);
    logTargetInfo();
  }

  void logTargetInfo() {
    String log = "y: " + targetView.getY() + "\n"
        + "ty: " + targetView.getTranslationY() + "\n"
        + "top: " + targetView.getTop();
    Log.e("Peace", log);
  }
}
