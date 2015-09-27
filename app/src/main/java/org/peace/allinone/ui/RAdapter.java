package org.peace.allinone.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.peace.allinone.R;

/**
 * Created by peacepassion on 15/9/27.
 */
public class RAdapter extends RecyclerView.Adapter<RViewHolder> {

  String text = "Android";

  @Override public RViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);
    return new RViewHolder(v);
  }

  @Override public void onBindViewHolder(RViewHolder holder, int position) {
    holder.tv.setText(text + position);
  }

  @Override public int getItemCount() {
    return 200;
  }

}
