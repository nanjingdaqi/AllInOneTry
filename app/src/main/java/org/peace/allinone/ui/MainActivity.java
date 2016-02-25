package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
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

  @InjectView(R.id.start_btn) Button mStartBtn;
  RecyclerView list;

  BottomSheetDialog bottomSheetDialog;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);

    bottomSheetDialog = new BottomSheetDialog(this);
    bottomSheetDialog.setContentView(R.layout.bottom_sheet_dlg_content);
    list = (RecyclerView) bottomSheetDialog.findViewById(R.id.list);
    list.setAdapter(new Ad());
    list.setLayoutManager(new LinearLayoutManager(this));
  }

  @OnClick(R.id.toggle) public void onToggle() {
    bottomSheetDialog.cancel();
  }

  @OnClick(R.id.start_btn) public void onStartBtn() {
    bottomSheetDialog.show();
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
      View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_item, parent, false);
      return new VH(root);
    }

    @Override public void onBindViewHolder(VH holder, int position) {
      holder.tv.setText("Item " + position);
    }

    @Override public int getItemCount() {
      return 40;
    }
  }
}
