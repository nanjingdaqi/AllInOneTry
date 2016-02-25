package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
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

  @InjectView(R.id.list) RecyclerView list;
  BottomSheetBehavior behavior;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);

    list.setAdapter(new Ad());
    list.setLayoutManager(new LinearLayoutManager(this));
    behavior =
        (BottomSheetBehavior) ((CoordinatorLayout.LayoutParams) list.getLayoutParams()).getBehavior();
  }

  @OnClick(R.id.hide) public void onToggle() {
    behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
  }

  @OnClick(R.id.show) public void onStartBtn() {
    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
  }

  static class VH extends RecyclerView.ViewHolder {
    @InjectView(R.id.tv) TextView tv;

    public VH(View itemView) {
      super(itemView);
      ButterKnife.inject(this, itemView);
    }
  }

  static class Ad extends RecyclerView.Adapter<VH> {

    @Override public VH onCreateViewHolder(ViewGroup parent, int viewType) {
      View root =
          LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_item, parent, false);
      return new VH(root);
    }

    @Override public void onBindViewHolder(VH holder, int position) {
      holder.tv.setText("Item " + position);
    }

    @Override public int getItemCount() {
      return 2;
    }
  }
}
