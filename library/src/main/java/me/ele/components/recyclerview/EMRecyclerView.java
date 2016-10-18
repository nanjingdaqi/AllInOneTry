package me.ele.components.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.AdapterDataObserver;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import java.util.List;
import me.ele.components.pullrefresh.BoxesPullToRefreshLayout;
import me.ele.components.pullrefresh.PullToRefresh;
import me.ele.recyclerview.R;

public class EMRecyclerView extends FrameLayout {

  private static final int NONE = -1;
  private static final int LOAD_MORE_THRESHOLD = 10;
  private static final int ATTR_MAIN_LAYOUT = R.styleable.recycler_main_layout;
  private static final int ATTR_EMPTY_LAYOUT = R.styleable.recycler_empty_layout;
  private static final int ATTR_LOAD_MORE_LAYOUT = R.styleable.recycler_load_more_layout;
  private static final int ATTR_PROGRESS_LAYOUT = R.styleable.recycler_progress_layout;
  private static final int DEFAULT_MAIN_LAYOUT = R.layout.default_main_layout_without_scrollbar;
  private static final int DEFAULT_EMPTY_LAYOUT = NONE;
  private static final int DEFAULT_LOAD_MORE_LAYOUT = R.layout.default_load_more_layout;
  private static final int DEFAULT_PROGRESS_LAYOUT = R.layout.default_progress_layout;
  private int itemsLeftToLoadMore = 1;
  private PullToRefresh pullToRefresh;
  private RecyclerView recyclerView;
  private EmptyViewController emptyView;
  private View progressView;
  private FrameLayout loadMoreContainer;
  private View loadMoreView;

  private int mainLayout;
  private int emptyId;
  private int loadMoreId;
  private int progressId;

  private OnMoreListener onMoreListener;
  private BoxesPullToRefreshLayout.OnRefreshListener onRefreshListener;
  private boolean enableLoadMore = true;
  private boolean isEmptyViewShow;

  private HeaderViewRecyclerAdapter adapter;
  private AdapterDataObserver mInternalDataObserver = new AdapterDataObserver() {

    @Override public void onItemRangeChanged(int positionStart, int itemCount) {
      super.onItemRangeChanged(positionStart, itemCount);
      update();
    }

    @Override public void onItemRangeInserted(int positionStart, int itemCount) {
      super.onItemRangeInserted(positionStart, itemCount);
      update();
    }

    @Override public void onItemRangeRemoved(int positionStart, int itemCount) {
      super.onItemRangeRemoved(positionStart, itemCount);
      update();
    }

    @Override public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
      super.onItemRangeMoved(fromPosition, toPosition, itemCount);
      update();
    }

    @Override public void onChanged() {
      super.onChanged();
      update();
    }

    private void update() {
      toggleEmptyView();
    }

    private boolean isAdapterEmpty() {
      return adapter == null || adapter.isEmpty();
    }

    private void toggleEmptyView() {
      if (isAdapterEmpty()) {
        showEmptyView();
      } else {
        hideEmptyView();
      }
    }
  };
  private OnScrollListener mInternalOnScrollListener = new OnScrollListener() {

    @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
      super.onScrolled(recyclerView, dx, dy);
      checkIfLoadMore(recyclerView, dy);
    }

    @Override public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
      super.onScrollStateChanged(recyclerView, newState);
    }

    private void checkIfLoadMore(RecyclerView recyclerView, int dy) {
      RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
      int totalItemCount = layoutManager.getItemCount();
      int lastVisiblePosition = getLastVisibleItemPosition(layoutManager);
      if (onMoreListener != null && canLoadMore(dy, totalItemCount, lastVisiblePosition)) {
        showMoreProgress();
        onMoreListener.onMoreAsked(getAdapter().getPagingItemCount());
      }
    }

    private boolean canLoadMore(int dy, int totalItemCount, int lastVisiblePosition) {
      //load more view as a footer view, so add 1
      boolean hasReachLoadMorePos =
          (totalItemCount - lastVisiblePosition) <= itemsLeftToLoadMore + 1;
      return enableLoadMore
          && hasReachLoadMorePos
          && !isLoadingMore()
          && !isEmptyViewShow()
          && !isRefreshing()
          && dy > LOAD_MORE_THRESHOLD;
    }

    private int getLastVisibleItemPosition(RecyclerView.LayoutManager layoutManager) {
      return ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
    }
  };

  public EMRecyclerView(Context context) {
    this(context, null);
  }

  public EMRecyclerView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public EMRecyclerView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    initAttrs(attrs);
    initView();
  }

  protected void initAttrs(AttributeSet attrs) {
    TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.recycler);
    mainLayout = a.getResourceId(ATTR_MAIN_LAYOUT, DEFAULT_MAIN_LAYOUT);
    emptyId = a.getResourceId(ATTR_EMPTY_LAYOUT, DEFAULT_EMPTY_LAYOUT);
    loadMoreId = a.getResourceId(ATTR_LOAD_MORE_LAYOUT, DEFAULT_LOAD_MORE_LAYOUT);
    progressId = a.getResourceId(ATTR_PROGRESS_LAYOUT, DEFAULT_PROGRESS_LAYOUT);
    a.recycle();
  }

  private void initView() {
    if (isInEditMode()) {
      return;
    }

    LayoutInflater inflater = LayoutInflater.from(getContext());
    View v = inflater.inflate(mainLayout, this);
    pullToRefresh = (PullToRefresh) v.findViewById(R.id.id_ptr_layout);
    disablePullRefresh();

    recyclerView = (RecyclerView) v.findViewById(R.id.id_recyclerview);
    recyclerView.addOnScrollListener(mInternalOnScrollListener);

    ViewStub progressStub = (ViewStub) v.findViewById(R.id.id_progress);
    progressStub.setLayoutResource(progressId);
    progressView = progressStub.inflate();
    hideProgress();

    loadMoreContainer = new FrameLayout(getContext());
    loadMoreView = inflater.inflate(loadMoreId, null);
    loadMoreContainer.addView(loadMoreView);
    addFooterView(loadMoreContainer);
    hideMoreProgress();

    ViewGroup emptyViewContainer = (ViewGroup) v.findViewById(R.id.id_empty);
    emptyView = new EmptyViewControllerFactory().getEmptyView(emptyViewContainer, emptyId);
    if (emptyView != null) {
      emptyView.attach(this);
    }
  }

  public PullToRefresh getPullToRefresh() {
    return pullToRefresh;
  }

  public RecyclerView getRecyclerView() {
    return recyclerView;
  }

  public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
    if (!(layoutManager instanceof LinearLayoutManager)) {
      throw new RuntimeException(
          "Unsupported LayoutManager used. Valid ones are LinearLayoutManager");
    }
    recyclerView.setLayoutManager(layoutManager);
  }

  public boolean hasSetAdapter() {
    return adapter != null && recyclerView.getAdapter() == adapter;
  }

  public boolean addHeaderView(View newHeader, boolean resetAdapter) {
    return addHeaderView(getHeaderViewsCount(), newHeader, resetAdapter);
  }

  public int indexOfHeader(View header) {
    return adapter != null ? adapter.indexOfHeader(header) : -1;
  }

  public int indexOfFooter(View footer) {
    return adapter != null ? adapter.indexOfFooter(footer) : -1;
  }

  public boolean addHeaderView(View v) {
    return addHeaderView(getHeaderViewsCount(), v, true);
  }

  public boolean addHeaderView(int index, View v) {
    return addHeaderViewToAdapter(index, v, true);
  }

  public boolean addHeaderView(int index, View v, boolean resetAdapter) {
    return addHeaderViewToAdapter(index, v, resetAdapter);
  }

  private class AdapterStub extends RecyclerView.Adapter {

    @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      return null;
    }

    @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override public int getItemCount() {
      return 0;
    }
  }

  private boolean addHeaderViewToAdapter(int index, View v, boolean resetAdapter) {
    ensureAdapter();

    boolean result = adapter.addHeaderView(index, v);
    if (!result) {
      return false;
    }

    if (resetAdapter) {
      resetAdapter();
    }

    return true;
  }

  public int getHeaderViewsCount() {
    return adapter != null ? adapter.getHeaderCount() : 0;
  }

  public boolean removeHeaderView(View v) {
    return removeHeaderViewFromAdapter(v, true);
  }

  public boolean removeHeaderView(int pos, boolean reset) {
    return removeHeaderViewFromAdapter(pos, reset);
  }

  public boolean removeHeaderView(View headerToRemove, boolean resetAdapter) {
    return removeHeaderViewFromAdapter(headerToRemove, resetAdapter);
  }

  private boolean removeHeaderViewFromAdapter(int pos, boolean reset) {
    boolean result = adapter != null && adapter.removeHeaderView(pos);
    if (result) {
      if (reset) resetAdapter();
    }
    return result;
  }

  private boolean removeHeaderViewFromAdapter(View v, boolean reset) {
    if (this.adapter == null) {
      return false;
    }

    int index = adapter.getHeaderViews().indexOf(v);
    return removeHeaderViewFromAdapter(index, reset);
  }

  public boolean addFooterView(View v) {
    return addFooterView(getFooterViewsCount(), v);
  }

  public boolean addFooterView(int index, View v) {
    return addFooterViewToAdapter(index, v);
  }

  private boolean addFooterViewToAdapter(int index, View v) {
    ensureAdapter();

    //make sure load-more view always be the last item
    if (v != loadMoreContainer && index == getFooterViewsCount() && getFooterViewsCount() > 0) {
      --index;
    }
    boolean result = adapter.addFooterView(index, v);
    if (result) {
      resetAdapter();
    }
    return result;
  }

  private void ensureAdapter() {
    if (this.adapter == null) {
      this.adapter = new HeaderViewRecyclerAdapter(new AdapterStub());
    }
  }

  public int getFooterViewsCount() {
    return adapter != null ? adapter.getFooterCount() : 0;
  }

  public boolean removeFooterView(View v) {
    return adapter != null && removeFooterViewFromAdapter(v);
  }

  private boolean removeFooterViewFromAdapter(View v) {
    boolean result = adapter != null && adapter.removeFooterView(v);
    if (result) {
      resetAdapter();
    }
    return result;
  }

  protected List<View> getHeaderViews() {
    return adapter != null ? adapter.getHeaderViews() : null;
  }

  protected List<View> getFooterViews() {
    return adapter != null ? adapter.getFooterViews() : null;
  }

  //just to make sure the new header would be visible immediately added
  private void resetAdapter() {
    if (hasSetAdapter()) {
      recyclerView.setAdapter(null);
      recyclerView.setAdapter(adapter);
    }
  }

  public void clear() {
    if (hasSetAdapter()) adapter.unregisterAdapterDataObserver(mInternalDataObserver);
    adapter = null;
    recyclerView.setAdapter(null);
  }

  public void startRefresh() {
    hideProgress();
    hideMoreProgress();
    pullToRefresh.setRefreshing(true);
  }

  public void finishRefresh() {
    hideProgress();
    pullToRefresh.setRefreshing(false);
    recyclerView.setVisibility(VISIBLE);
  }

  public void startLoadMore() {
    showMoreProgress();
    disablePullRefresh();
  }

  public void finishLoadMore() {
    hideMoreProgress();
    enablePullRefresh();
  }

  public void startProgress() {
    showProgress();
    hideEmptyView();
  }

  public void finishProgress() {
    finishRefresh();
  }

  private void showProgress() {
    progressView.setVisibility(View.VISIBLE);
    pullToRefresh.getLayout().setVisibility(View.GONE);
  }

  private void hideProgress() {
    progressView.setVisibility(View.GONE);
    pullToRefresh.getLayout().setVisibility(View.VISIBLE);
  }

  public boolean isInProgress() {
    return progressView.getVisibility() == VISIBLE;
  }

  private void showMoreProgress() {
    loadMoreView.setVisibility(View.VISIBLE);
    loadMoreContainer.postInvalidate();
  }

  private void hideMoreProgress() {
    loadMoreView.setVisibility(View.GONE);
    loadMoreContainer.postInvalidate();
  }

  public void enablePullRefresh() {
    if (onRefreshListener != null) {
      pullToRefresh.getLayout().setEnabled(true);
    }
  }

  public void disablePullRefresh() {
    pullToRefresh.getLayout().setEnabled(false);
  }

  public PullToRefresh.OnRefreshListener getRefreshListener() {
    return onRefreshListener;
  }

  public void setRefreshListener(PullToRefresh.OnRefreshListener listener) {
    this.onRefreshListener = listener;
    pullToRefresh.setOnRefreshListener(listener);
    enablePullRefresh();
  }

  public HeaderViewRecyclerAdapter getAdapter() {
    return hasSetAdapter() ? adapter : null;
  }

  public void setAdapter(RecyclerView.Adapter adapter) {
    if (adapter == null) {
      throw new RuntimeException("adapter cannot be null");
    }

    if (hasSetAdapter()) {
      this.adapter.unregisterAdapterDataObserver(mInternalDataObserver);
    }

    if (adapter instanceof HeaderViewRecyclerAdapter) {
      this.adapter = (HeaderViewRecyclerAdapter) adapter;
    } else {
      HeaderViewRecyclerAdapter old = this.adapter;
      this.adapter = new HeaderViewRecyclerAdapter(adapter);
      if (old != null) {
        this.adapter.getHeaderViews().addAll(old.getHeaderViews());
        this.adapter.getFooterViews().addAll(old.getFooterViews());
      }
    }
    addFooterView(loadMoreContainer);
    this.adapter.registerAdapterDataObserver(mInternalDataObserver);
    recyclerView.setAdapter(this.adapter);
  }

  public RecyclerView.Adapter getWrappedAdapter() {
    return hasSetAdapter() ? adapter.getWrappedAdapter() : null;
  }

  public void setupMoreListener(OnMoreListener onMoreListener, int max) {
    this.onMoreListener = onMoreListener;
    itemsLeftToLoadMore = max;
  }

  public void setOnMoreListener(OnMoreListener onMoreListener) {
    this.onMoreListener = onMoreListener;
  }

  public void removeMoreListener() {
    onMoreListener = null;
  }

  public void setItemsLeftToLoadMore(int itemsLeftToLoadMore) {
    this.itemsLeftToLoadMore = itemsLeftToLoadMore;
  }

  public boolean isLoadingMore() {
    return loadMoreView.getVisibility() == VISIBLE;
  }

  public boolean isRefreshing() {
    return pullToRefresh.isRefreshing();
  }

  public void enableLoadMore() {
    enableLoadMore = true;
  }

  public void disableLoadMore() {
    enableLoadMore = false;
  }

  public View getEmptyView() {
    if (emptyView != null) {
      return emptyView.view();
    }
    return null;
  }

  public void setEmptyView(View emptyView) {
    if (this.emptyView != null) {
      this.emptyView.detach(this);
    }
    if (emptyView != null) {
      this.emptyView = new SimpleEmptyView(emptyView);
    }
  }

  public boolean isEmptyViewShow() {
    return isEmptyViewShow;
  }

  private void showEmptyView() {
    if (isLoadingMore() || isRefreshing() || isInProgress()) {
      return;
    }
    if (emptyView != null) {
      emptyView.show();
      isEmptyViewShow = true;
    }
  }

  private void hideEmptyView() {
    if (emptyView != null) {
      emptyView.hide();
      isEmptyViewShow = false;
    }
  }
}
