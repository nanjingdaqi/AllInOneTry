package org.peace.allinone.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.InjectView;
import me.ele.components.pullrefresh.PullToRefresh;
import me.ele.components.recyclerview.EMRecyclerView;
import org.peace.allinone.R;

import static me.ele.commons.DimenUtil.dip2px;
import static org.peace.allinone.ui.Util.createHead;

public class MainActivity extends AppCompatActivity {

  static final String TAG = MainActivity.class.getSimpleName();

  int H;

  @InjectView(R.id.rv) EMRecyclerView rv;
  @InjectView(R.id.toolbar) View toolbar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    H = dip2px(this, 60);

    ButterKnife.inject(this);
    setupRV();
    setupToolbar();
  }

  private void setupToolbar() {
    CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) toolbar.getLayoutParams();
    lp.setBehavior(new ToolbarBehavior(rv));
  }

  public void setupRV() {
    rv.setLayoutManager(new LinearLayoutManager(this));
    Util.insertItem(this, rv);
    rv.addHeaderView(createHead(this, H, Color.TRANSPARENT, "Head 1"));
    rv.addHeaderView(createHead(this, H, Color.TRANSPARENT, "Head 2"));
    rv.setRefreshListener(new PullToRefresh.OnRefreshListener() {
      @Override public void onRefresh() {
        rv.postDelayed(new Runnable() {
          @Override public void run() {
            rv.finishRefresh();
          }
        }, 30000);
      }
    });
  }
}
