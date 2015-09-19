package org.peace.allinone.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @InjectView(R.id.start_btn) Button mStartBtn;

  int SIZE = 10000;
  List<String> unit = new ArrayList<>();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);

    for (int i = 0; i < 100; ++i) {
      unit.add("Hello" + i);
    }
  }

  @OnClick({ R.id.start_btn }) public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.start_btn) {
      Intent intent = new Intent(this, SubActivity.class);
      List<String> data = new LinkedList<>();
      for (int i = 0; i < SIZE; ++i) {
        data.addAll(unit);
      }
      intent.putExtra("TAG", (Serializable) data);
      startActivity(intent);
    }
  }
}
