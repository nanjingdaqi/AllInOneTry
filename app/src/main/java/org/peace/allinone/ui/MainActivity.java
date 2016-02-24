package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @InjectView(R.id.start_btn) Button mStartBtn;

  @InjectView(R.id.list) RecyclerView list;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);
    list.setAdapter(new MyAdapter());
  }

  @OnClick({ R.id.start_btn }) public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.start_btn) {

    }
  }

  static class VH extends RecyclerView.ViewHolder {

    @InjectView(R.id.tv) TextView tv;

    public VH(View itemView) {
      super(itemView);
      ButterKnife.inject(this, itemView);
    }
  }

  static class MyAdapter extends RecyclerView.Adapter<VH> {

    @Override public VH onCreateViewHolder(ViewGroup parent, int viewType) {
      LayoutInflater inflater = LayoutInflater.from(parent.getContext());
      return new VH(inflater.inflate(R.layout.item_simple, parent, false));
    }

    @Override public void onBindViewHolder(VH holder, int position) {
      holder.tv.setText("Item " + position);
    }

    @Override public int getItemCount() {
      return 40;
    }
  }
}
