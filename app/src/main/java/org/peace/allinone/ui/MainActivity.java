package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @InjectView(R.id.toolbar) Toolbar toolbar;
  @InjectView(R.id.rv) RecyclerView rv;
  @InjectView(R.id.toolbar_layout) CollapsingToolbarLayout toolbarLayout;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);

    init();
  }

  private void init() {
    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle("CoOrdinateLayout Try");
    toolbarLayout.setTitle("CoOrdinateLayout Try");

    rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    rv.setAdapter(new RVAdapter(this));
  }


  static class RVVH extends RecyclerView.ViewHolder {

    public RVVH(View itemView) {
      super(itemView);
    }
  }

  static class RVAdapter extends RecyclerView.Adapter<RVVH> {

    AppCompatActivity activity;

    RVAdapter(AppCompatActivity activity) {
      this.activity = activity;
    }

    @Override public RVVH onCreateViewHolder(ViewGroup parent, int viewType) {
      return new RVVH(LayoutInflater.from(activity).inflate(R.layout.listitem_rv, parent, false));
    }

    @Override public void onBindViewHolder(RVVH holder, int position) {

    }

    @Override public int getItemCount() {
      return 100;
    }
  }


}
