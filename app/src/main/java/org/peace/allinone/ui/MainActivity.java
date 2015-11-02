package org.peace.allinone.ui;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.LinkedList;
import java.util.List;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.start_btn) Button mStartBtn;

  @BindView(R.id.list1) ListView list1;
  @BindView(R.id.list2) ListView list2;

  MyAdapter adapter1 = new MyAdapter();
  MyAdapter adapter2 = new MyAdapter();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

    list1.setAdapter(adapter1);
    list2.setAdapter(adapter2);
    list1.getViewTreeObserver()
        .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
          @TargetApi(Build.VERSION_CODES.JELLY_BEAN) @Override public void onGlobalLayout() {
            list1.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            int h = adapter1.getCount() * 60;
            LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, h);
            list1.setLayoutParams(params);
            list1.requestLayout();
          }
        });
    list2.getViewTreeObserver()
        .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
          @TargetApi(Build.VERSION_CODES.JELLY_BEAN) @Override public void onGlobalLayout() {
            list2.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            int h = adapter1.getCount() * 60;
            LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, h);
            list2.setLayoutParams(params);
            list2.requestLayout();
          }
        });
  }

  @OnClick(R.id.start_btn) public void onClick(View v) {

  }

  class MyAdapter extends BaseAdapter {

    List<String> contents;

    MyAdapter() {
      contents = new LinkedList<>();
      for (int i = 0; i < 20; ++i) {
        contents.add("Item: " + i);
      }
    }

    @Override public int getCount() {
      return contents.size();
    }

    @Override public String getItem(int position) {
      return contents.get(position);
    }

    @Override public long getItemId(int position) {
      return position;
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
      VH vh;
      if (convertView == null) {
        convertView = getLayoutInflater().inflate(R.layout.item, parent, false);
        vh = new VH();
        vh.tv = (TextView) convertView.findViewById(R.id.tv);
        convertView.setTag(vh);
      } else {
        vh = (VH) convertView.getTag();
      }
      vh.tv.setText(getItem(position));
      return convertView;
    }
  }

  class VH {
    TextView tv;
  }
}
