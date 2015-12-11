package org.peace.allinone.ui;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.root) LinearLayout root;
  @BindView(R.id.list_view) ListView listView;

  ArrayAdapter<String> adapter2;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

    adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
    for (int i = 0; i < 5; ++i) {
      adapter2.add("Item : " + i);
    }
    listView.setAdapter(adapter2);

  }
}
