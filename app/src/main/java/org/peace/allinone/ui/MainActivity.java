package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.ele.base.utils.AppLogger;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.my_tv) TextView tv;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

    AppLogger.e("on create");

    if (savedInstanceState != null) {
      //tv.setText("Hello World");
    } else {
      tv.setText("Fuck World");
    }
  }

  @OnClick(R.id.start_btn) public void onClick(View v) {

  }
}
