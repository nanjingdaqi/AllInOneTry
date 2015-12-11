package org.peace.allinone.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.LinkedList;
import java.util.List;
import me.ele.commons.AppLogger;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.start_btn) Button mStartBtn;

  @BindView(R.id.list1) ListView list1;
  @BindView(R.id.list2) ListView list2;

  MyAdapter adapter1 = new MyAdapter();
  ArrayAdapter<String> adapter2;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

    adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
    for (int i = 0; i < 100; ++i) {
      adapter2.add("Item: " + i);
    }

    list1.setAdapter(adapter2);

    list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        int oh = view.getHeight();
        ValueAnimator va = ValueAnimator.ofInt(oh, 1);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
          @Override public void onAnimationUpdate(ValueAnimator animation) {
            int h = (int) animation.getAnimatedValue();
            lp.height = h;
            view.setLayoutParams(lp);
            view.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
          }
        });
        va.addListener(new AnimatorListenerAdapter() {
          @Override public void onAnimationEnd(Animator animation) {
            // todo 根据position删除元素,在同时操作多个item时,会出现后面删除的position已经无效的bug
            adapter2.remove(adapter2.getItem(position));
            adapter2.notifyDataSetChanged();
            lp.height = oh;
            view.setLayoutParams(lp);
            view.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
          }
        });
        va.setDuration(500).start();
      }
    });
  }

  @OnClick({ R.id.start_btn }) public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.start_btn) {

    }
  }

  class MyAdapter extends BaseAdapter {

    List<String> contents;

    MyAdapter() {
      contents = new LinkedList<>();
      for (int i = 0; i < 100; ++i) {
        contents.add("item: " + i);
      }
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

    public void removeItem(int position) {
      contents.remove(position);
      notifyDataSetChanged();
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
