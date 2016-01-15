package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.ele.base.utils.AppLogger;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.root) LinearLayout root;
  @BindView(R.id.list_view) ListView listView;

  ArrayAdapter<String> adapter2;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

    adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
    for (int i = 0; i < 20; ++i) {
      adapter2.add("Item : " + i);
    }
    listView.setAdapter(adapter2);

    listView.setOnScrollListener(new AbsListView.OnScrollListener() {
      @Override public void onScrollStateChanged(AbsListView view, int scrollState) {
        AppLogger.d("scroll state: " + scrollState);
      }

      @Override public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
          int totalItemCount) {
        //AppLogger.d("first visibile item: " + firstVisibleItem);
        //AppLogger.d("visible item count: " + visibleItemCount);
        //
        //AppLogger.d("top: " + listView.getTop());
        if (listView.getChildCount() > 0) {
          AppLogger.d("first item top: " + listView.getChildAt(0).getTop());
        }
      }
    });
  }
}
