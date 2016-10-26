package me.ele.shopping.ui.home.toolbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import org.peace.allinone.R;

public class SearchView extends LinearLayout {

  @BindView(R.id.arrow) protected View arrowView;
  @BindView(R.id.icon) protected View iconView;
  @BindView(R.id.hint) protected TextView hintView;

  public SearchView(Context context) {
    this(context, null);
  }

  public SearchView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public SearchView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    inflate(context, R.layout.sp_home_search_view, this);
  }

  public void animateToExpand() {

  }
}
