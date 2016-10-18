package me.ele.components.recyclerview;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Space;
import java.util.ArrayList;
import java.util.List;
import me.ele.recyclerview.R;

public class EMStickyRecyclerView extends EMRecyclerView {

  private LinearLayout floatContainer;
  private List<Pair<View, FrameLayout>> list = new ArrayList<>();
  private int topOffset;

  public EMStickyRecyclerView(Context context) {
    super(context);
    init();
  }

  public EMStickyRecyclerView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public EMStickyRecyclerView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init();
  }

  private void init() {
    floatContainer = (LinearLayout) findViewById(R.id.id_sticky_container);
    getRecyclerView().addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        restoreStickyHeader();
      }
    });
  }

  private void restoreStickyHeader() {
    int threshold = topOffset;
    int preStickyHeaderHeight = 0;
    for (int i = 0, size = list.size(); i < size; i++) {
      Pair<View, FrameLayout> pair = list.get(i);
      int top = getInflateViewTop(pair.second);
      threshold += preStickyHeaderHeight;
      preStickyHeaderHeight = pair.second.getHeight();
      View view = pair.first;
      FrameLayout container = pair.second;
      if (top < threshold
          && floatContainer.indexOfChild(view) == -1
          && container.indexOfChild(view) != -1) {
        container.removeView(view);
        resetLayoutParams(view);
        floatContainer.addView(view);
        continue;
      }

      if (top >= threshold
          && container.indexOfChild(view) == -1
          && floatContainer.indexOfChild(view) != -1) {
        floatContainer.removeView(view);
        resetLayoutParams(view);
        container.addView(view);
      }
    }
  }

  private void resetLayoutParams(View view) {
    ViewGroup.LayoutParams layoutParams =
        new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT);
    view.setLayoutParams(layoutParams);
  }

  private int getInflateViewTop(FrameLayout container) {
    LinearLayoutManager layoutManager = (LinearLayoutManager) getRecyclerView().getLayoutManager();
    //if first visible item position is invalid, just return -1.
    final int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
    if (firstVisibleItemPosition < 0) {
      return -1;
    }

    int top =
        ViewCompat.isAttachedToWindow(container) ? ((View) container.getParent()).getTop() : -1;
    // If header view is ahead of first visible item, just return -1.
    List<View> headerViews = getAdapter().getHeaderViews();
    final int headerViewIndex = headerViews.indexOf(container);
    if (top > 0 && firstVisibleItemPosition > headerViewIndex) {
      return -1;
    }

    final int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
    //if last visible item position is invalid, just return -1.
    if (lastVisibleItemPosition < 0) {
      return -1;
    }

    // If header view is below last visible item, then calculate.
    if (lastVisibleItemPosition < headerViewIndex) {
      View firstVisibleHeaderView = headerViews.get(firstVisibleItemPosition);
      top = firstVisibleHeaderView instanceof HeaderViewRecyclerAdapter.CloneableHeaderView
          ? firstVisibleHeaderView.getBottom()
          : ((View) firstVisibleHeaderView.getParent()).getBottom();
      for (int i = firstVisibleItemPosition + 1; i < headerViewIndex; i++) {
        top += headerViews.get(i).getHeight();
      }
    }
    return top;
  }

  public void addStickyHeaderView(View view) {
    if (hasStickyView(view)) {
      return;
    }
    FrameLayout container = new FrameLayout(getContext());
    Space inflateView = new Space(getContext());
    view.measure(0, 0);
    inflateView.setMinimumHeight(view.getMeasuredHeight());
    container.setMinimumHeight(view.getMeasuredHeight());
    container.addView(inflateView);
    container.addView(view);
    list.add(new Pair<>(view, container));
    addHeaderView(container);
  }

  public void removeStickyHeaderView(View view) {
    Pair<View, FrameLayout> removedPair = null;
    for (Pair<View, FrameLayout> pair : list) {
      if (pair.first == view) {
        removedPair = pair;
        break;
      }
    }
    if (removedPair == null) {
      return;
    }

    list.remove(removedPair);
    floatContainer.removeView(removedPair.first);
    removeHeaderView(removedPair.second);
    removedPair.second.removeView(view);
  }

  public void setTopOffset(int topOffset) {
    this.topOffset = topOffset;
    this.floatContainer.setPadding(0, topOffset, 0, 0);
  }

  private boolean hasStickyView(View view) {
    for (Pair<View, FrameLayout> pair : list) {
      if (pair.first == view) {
        return true;
      }
    }
    return false;
  }

  public boolean isFloating(View view) {
    return floatContainer.indexOfChild(view) != -1;
  }
}
