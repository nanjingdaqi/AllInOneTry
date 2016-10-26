package org.peace.allinone.ui;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.*;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.List;
import me.ele.base.utils.DimenUtil;
import me.ele.base.utils.ResourceUtil;
import me.ele.components.pullrefresh.PullToRefresh;
import me.ele.components.recyclerview.EMRecyclerView;
import me.ele.shopping.ui.home.toolbar.*;
import org.peace.allinone.R;

import static org.peace.allinone.ui.Util.createHead;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.rv) EMRecyclerView rv;
  @BindView(R.id.toolbar) protected HomeFragmentToolbar toolbar;
  @BindView(R.id.hello) protected HelloView helloView;
  @BindView(R.id.emotion) protected EmotionView emotionView;
  @BindView(R.id.address) protected AddressView addressView;
  @BindView(R.id.search) protected me.ele.shopping.ui.home.toolbar.SearchView searchView;
  @BindView(R.id.search_key_words) protected SearchKeyWordsView searchKeyWordsView;

  TextView head1;
  ToolbarBehavior toolbarBehavior;

  int H = DimenUtil.dip2px(60);

  @Override protected void onCreate(Bundle savedInstanceState) {
    if (Build.VERSION.SDK_INT >= 21) {
      getWindow().getDecorView()
          .setSystemUiVisibility(
              View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

    setupRV();
    setupToolbar();
    setupAnimationManager();
  }

  private void setupToolbar() {
    toolbar.setSkinDrawable(ResourceUtil.getDrawable(R.drawable.sp_home_banner_default));
    CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) toolbar.getLayoutParams();
    toolbarBehavior = new ToolbarBehavior();
    toolbarBehavior.addHeightChangeListener(new ToolbarBehavior.HeightChangeListener() {
      @Override public void onHeightChange(int drawHeight, int measureHeight) {
        head1.setText("dH: " + drawHeight + ", mH: " + measureHeight);
      }
    });
    lp.setBehavior(toolbarBehavior);

    emotionView.showPromotion();
    addressView.setAddress("近铁城市广场");
    addressView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {

      }
    });

    List<String> keyWords = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      keyWords.add("麦当劳");
      keyWords.add("粥");
      keyWords.add("KFC");
    }
    searchKeyWordsView.setKeyWords(keyWords);
  }

  private void setupRV() {
    rv.setLayoutManager(new LinearLayoutManager(this));
    Util.insertItem(this, rv);
    rv.addHeaderView(head1 = createHead(this, H, Color.TRANSPARENT, "Head 1"));
    rv.addHeaderView(createHead(this, H, Color.TRANSPARENT, "Head 2"));
    rv.setRefreshListener(new PullToRefresh.OnRefreshListener() {
      @Override public void onRefresh() {
        rv.postDelayed(new Runnable() {
          @Override public void run() {
            if (searchKeyWordsView.getVisibility() == View.VISIBLE) {
              searchKeyWordsView.setVisibility(View.GONE);
            } else {
              searchKeyWordsView.setVisibility(View.VISIBLE);
            }
            toolbarBehavior.reset();
            rv.finishRefresh();
          }
        }, 5000);
      }
    });
  }

  private void setupAnimationManager() {
    ScrollManager scrollManager = ScrollManager.builder(this)
        .setToolbarBehavior(toolbarBehavior)
        .setSearchView(searchView)
        .setHelloView(helloView)
        .setAddressView(addressView)
        .setEmotionView(emotionView)
        .setSearchKeyWordsView(searchKeyWordsView)
        .build();
    toolbarBehavior.addHeightChangeListener(scrollManager.getToolbarHeightChangeListener());
  }
}
