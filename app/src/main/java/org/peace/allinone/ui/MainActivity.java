package org.peace.allinone.ui;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

  @InjectView(R.id.ll) LinearLayout ll;
  @InjectView(R.id.root) LinearLayout root;

  MyAdapter adapter1 = new MyAdapter();
  ArrayAdapter<String> adapter2;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

    for (int i = 0; i < 10; ++i) {
      TextView tv = new TextView(this);
      tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 160));
      tv.setText("Item " + i);
      ll.addView(tv);
      tv.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          ValueAnimator va = ValueAnimator.ofInt(tv.getHeight(), 0);
          LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) tv.getLayoutParams();
          float y = tv.getY();
          va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override public void onAnimationUpdate(ValueAnimator animation) {
              int h = (int) animation.getAnimatedValue();
              lp.height = h;
              tv.setLayoutParams(lp);
              //tv.setTranslationY(h);
              //tv.setY(y + h);
            }
          });
          va.setDuration(1000).start();
        }
      });
    }
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
