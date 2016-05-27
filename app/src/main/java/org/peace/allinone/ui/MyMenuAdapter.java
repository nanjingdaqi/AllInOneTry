package org.peace.allinone.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import java.util.ArrayList;
import java.util.List;
import org.peace.allinone.R;

public class MyMenuAdapter extends BaseAdapter {

  public List<String> strs = new ArrayList<>(3);

  public MyMenuAdapter() {
    for (int i = 0; i < 3; i++) {
      strs.add("item: " + i);
    }
  }

  @Override public int getCount() {
    return strs.size();
  }

  @Override public Object getItem(int position) {
    return strs.get(position);
  }

  @Override public long getItemId(int position) {
    return position;
  }

  @Override public View getView(int position, View convertView, ViewGroup parent) {
    MyVH vh;
    if (convertView == null) {
      convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
      vh = new MyVH(convertView);
      convertView.setTag(vh);
    } else {
      vh = (MyVH) convertView.getTag();
    }
    vh.tv.setText(strs.get(position));
    return convertView;
  }

  public static class MyVH {
    @InjectView(R.id.tv) public TextView tv;

    public MyVH(View item) {
      ButterKnife.inject(this, item);
    }
  }
}
