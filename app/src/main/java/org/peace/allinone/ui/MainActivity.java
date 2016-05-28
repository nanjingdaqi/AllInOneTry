package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListPopupWindow;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupWindow;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @InjectView(R.id.anchor) protected View anchor;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);
  }

  @OnClick({ R.id.start_btn }) public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.start_btn) {
      showListPopup();
    }
  }

  private void showListPopup() {
    ListPopupWindow popupWindow = new ListPopupWindow(this);
    popupWindow.setAdapter(new MyMenuAdapter());
    popupWindow.setAnchorView(anchor);
    popupWindow.setContentWidth(500);
    popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NOT_NEEDED);
    popupWindow.setDropDownGravity(Gravity.END);
    popupWindow.setModal(true);
    popupWindow.setHorizontalOffset(100);
    popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg));
    popupWindow.show();
    ListView listView = popupWindow.getListView();
    listView.setDivider(getResources().getDrawable(R.drawable.divider));
    listView.setOverScrollMode(ListView.OVER_SCROLL_NEVER);
    listView.setVerticalScrollBarEnabled(false);
  }
}
