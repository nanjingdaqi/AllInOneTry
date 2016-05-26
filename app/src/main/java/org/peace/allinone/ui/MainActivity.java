package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @InjectView(R.id.start_btn) Button mStartBtn;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);
  }

  @OnClick(R.id.start_btn) public void onClick(View v) {
    PopupMenu menu = new PopupMenu(this, mStartBtn, Gravity.RIGHT);
    menu.getMenu().add("店内搜索");
    menu.getMenu().add("微信拼单");
    menu.getMenu().add("分享");
    menu.getMenu().add("浏览历史");
    menu.show();
  }
}
