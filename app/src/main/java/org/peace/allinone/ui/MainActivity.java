package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import org.peace.allinone.R;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity {

  @InjectView(R.id.show) Button mStartBtn;
  @InjectView(R.id.list_view_container) SL2 myLayout;
  @InjectView(R.id.list) ListView listView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);

    ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);

    for (int i = 0; i < 60; i++) {
      adapter.add("Item " + i);
    }

    listView.setAdapter(adapter);
  }

  @OnClick(R.id.show) public void show() {
    myLayout.show();
  }

  @OnClick(R.id.hide) public void hide() {
    myLayout.hide();
  }
}
