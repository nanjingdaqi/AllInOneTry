package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import java.lang.reflect.Field;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @InjectView(R.id.start_btn) Button mStartBtn;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);
  }

  @OnClick(R.id.start_btn) public void onClick(View v) {
    try {
      PopupMenu menu = new PopupMenu(this, mStartBtn, Gravity.RIGHT);
      MenuItem item = menu.getMenu().add("店内搜索");
      item.setIcon(R.drawable.__leak_canary_icon);
      Field field = menu.getClass().getDeclaredField("mPopup");
      field.setAccessible(true);
      MenuPopupHelper popupHelper = (MenuPopupHelper) field.get(menu);
      popupHelper.setForceShowIcon(true);
      menu.show();
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
  }
}
