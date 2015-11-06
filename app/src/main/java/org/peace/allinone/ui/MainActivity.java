package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
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

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

    list1.setAdapter(adapter1);
    list1.setVisibility(View.GONE);
  }

  @OnClick({ R.id.start_btn }) public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.start_btn) {
      adapter1.addContents();
      list1.setVisibility(View.VISIBLE);
      adapter1.notifyDataSetChanged();
      list1.post(new Runnable() {
        @Override public void run() {
          list1.setVisibility(View.GONE);
          adapter1.contents.remove(0);
          list1.post(new Runnable() {
            @Override public void run() {
              list1.setVisibility(View.VISIBLE);
            }
          });
        }
      });
    }
  }

  class MyAdapter extends BaseAdapter {

    List<String> contents;

    MyAdapter() {
      contents = new LinkedList<>();
    }

    public void clearContents() {
      contents.clear();
    }

    public void addContents() {
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
