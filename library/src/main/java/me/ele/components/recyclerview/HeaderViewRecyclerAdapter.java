package me.ele.components.recyclerview;
/*
 * Copyright (C) 2014 darnmason
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * <p>
 * RecyclerView adapter designed to wrap an existing adapter allowing the addition of
 * header views and footer views.
 * </p>
 * <p>
 * I implemented it to aid with the transition from ListView to RecyclerView where the ListView's
 * addHeaderView and addFooterView methods were used. Using this class you may initialize your
 * header views in the Fragment/Activity and add them to the adapter in the same way you used to
 * add them to a ListView.
 * </p>
 * <p>
 * I also required to be able to swap out multiple adapters with different content, therefore
 * setAdapter may be called multiple times.
 * </p>
 * Created by darnmason on 07/11/2014.
 */
public class HeaderViewRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
    implements Cloneable, PagingAdapter {

  private static final int ITEMS_START = Integer.MIN_VALUE;
  private static final int ADAPTER_MAX_TYPES = 100;
  private static final String TAG = "headerAdapter";
  private static final String HEADER_VIEW_TAG = "recycler_header_adapter";
  private static final int HEADER_VIEW_TAG_KEY = Integer.MIN_VALUE;

  private RecyclerView.Adapter wrappedAdapter;
  private List<View> headerViews;
  private List<View> footerViews;
  private Map<Class, Integer> itemTypesOffset;
  private RecyclerView.AdapterDataObserver mDataObserver = new RecyclerView.AdapterDataObserver() {
    @Override
    public void onChanged() {
      notifyDataSetChanged();
    }

    @Override
    public void onItemRangeChanged(int positionStart, int itemCount) {
      notifyItemRangeChanged(positionStart + getHeaderCount(), itemCount);
    }

    @Override
    public void onItemRangeInserted(int positionStart, int itemCount) {
      notifyItemRangeInserted(positionStart + getHeaderCount(), itemCount);
    }

    @Override
    public void onItemRangeRemoved(int positionStart, int itemCount) {
      notifyItemRangeRemoved(positionStart + getHeaderCount(), itemCount);
    }

    @Override
    public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
      notifyItemMoved(getHeaderCount() + fromPosition, getHeaderCount() + toPosition);
    }
  };

  /**
   * Construct a new header view recycler adapter
   *
   * @param adapter The underlying adapter to wrap
   */
  public HeaderViewRecyclerAdapter(RecyclerView.Adapter adapter) {
    this(adapter, null, null);
  }

  /**
   * Construct a new header view recycler adapter
   *
   * @param adapter The underlying adapter to wrap
   * @param headerViews header view list
   * @param footerViews footer view list
   */
  public HeaderViewRecyclerAdapter(RecyclerView.Adapter adapter, List<View> headerViews,
      List<View> footerViews) {
    if (headerViews != null && headerViews.size() > 0) {
      this.headerViews = headerViews;
    } else {
      this.headerViews = new ArrayList<>();
    }

    if (footerViews != null && footerViews.size() > 0) {
      this.footerViews = footerViews;
    } else {
      this.footerViews = new ArrayList<>();
    }

    itemTypesOffset = new HashMap<>();
    setWrappedAdapter(adapter);
  }

  public static boolean remove(List<?> list, int pos) {
    if (list == null || pos < 0 || pos >= list.size()) {
      return false;
    }

    return list.remove(pos) != null;
  }

  @Override
  public int getItemViewType(int position) {
    // return a unique type associated to the header or footer instance instead of the position
    // to make sure that a removed header or footer is not reused by the RV
    int hCount = getHeaderCount();
    if (position < hCount) {
      return headerViews.get(position).hashCode();
    } else {
      int itemCount = wrappedAdapter.getItemCount();
      if (position < hCount + itemCount) {
        return getAdapterTypeOffset() + wrappedAdapter.getItemViewType(position - hCount);
      } else {
        return footerViews.get(position - hCount - itemCount).hashCode();
      }
    }
  }

  public boolean isEmpty() {
    return getWrappedItemCount() == 0;
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int viewType) {
    for (int i = headerViews.size() - 1; i >= 0; i--) {
      View header = headerViews.get(i);
      if (header.hashCode() == viewType) {
        return new StaticViewHolder(wrapIntoContainer(header));
      }
    }

    for (int i = footerViews.size() - 1; i >= 0; i--) {
      View footer = footerViews.get(i);
      if (footer.hashCode() == viewType) {
        return new StaticViewHolder(wrapIntoContainer(footer));
      }
    }

    return wrappedAdapter.onCreateViewHolder(viewGroup, viewType - getAdapterTypeOffset());
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
    int hCount = getHeaderCount();
    if (position >= hCount && position < hCount + wrappedAdapter.getItemCount()) {
      wrappedAdapter.onBindViewHolder(viewHolder, position - hCount);
      return;
    }

    rebindHeaderFooter(viewHolder, position, hCount);
  }

  // If notifyItemChanged() was called on a header or footer position, the RV would create a copy of
  // that header or footer view for the sake of item animations, but the current
  // HeaderViewRecyclerAdapter implementation actually would not create that copy, and would just
  // create a new StaticViewHolder instance bound to the original header, and if that
  // happens, a runtime exception would be thrown, cause the original header is already attached to
  // the RV.
  // -----------------------------------------------------------------------------------------------
  // A workaround here is that we wrap the original header or footer into a ViewGroup and use this
  // ViewGroup instance as the itemView argument to create a new StaticViewHolder instance later.
  @NonNull
  private View wrapIntoContainer(View headerOrFooter) {
    if (headerOrFooter instanceof CloneableHeaderView) {
      if (ViewCompat.isAttachedToWindow(headerOrFooter)) {
        Log.d(TAG, "wrapHeaderView: cloneHeader");
        return ((CloneableHeaderView) headerOrFooter).cloneHeader();
      } else {
        return headerOrFooter;
      }
    }

    IncludeChildMarginFrameLayout wrapper =
        new IncludeChildMarginFrameLayout(headerOrFooter.getContext());
    wrapper.setTag(HEADER_VIEW_TAG_KEY, HEADER_VIEW_TAG);
    if (headerOrFooter.getParent() != null) {
      ((ViewGroup) headerOrFooter.getParent()).removeView(headerOrFooter);
    }
    ViewGroup.LayoutParams sourceLp = headerOrFooter.getLayoutParams();
    if (sourceLp == null) {
      //setup default layout size
      headerOrFooter.setLayoutParams(new FrameLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT));
    } else if (sourceLp instanceof ViewGroup.MarginLayoutParams) {
      //copy layout size and margins
      headerOrFooter.setLayoutParams(
          new FrameLayout.LayoutParams((ViewGroup.MarginLayoutParams) sourceLp));
    } else {
      //copy layout size
      headerOrFooter.setLayoutParams(new FrameLayout.LayoutParams(sourceLp));
    }
    wrapper.addView(headerOrFooter);
    return wrapper;
  }

  private void rebindHeaderFooter(RecyclerView.ViewHolder viewHolder, int position, int hCount) {

    if (viewHolder.itemView instanceof CloneableHeaderView) {
      return;
    }

    if (!HEADER_VIEW_TAG.equals(viewHolder.itemView.getTag(HEADER_VIEW_TAG_KEY))) {
      Log.e(TAG, "bindHeader: position = " + position + ", unexpected viewHolder = " + viewHolder);
      return;
    }

    View target;
    if (position < hCount) {
      target = headerViews.get(position);
    } else {
      target = footerViews.get(position - hCount - getWrappedItemCount());
    }
    ViewGroup wrapper = (ViewGroup) viewHolder.itemView;
    ViewGroup.MarginLayoutParams headerLp = (ViewGroup.MarginLayoutParams) target.getLayoutParams();
    // apply the child's margins
    wrapper.setLayoutParams(headerLp != null ? new RecyclerView.LayoutParams(headerLp)
        : new RecyclerView.LayoutParams(MATCH_PARENT, WRAP_CONTENT));

    if (target == wrapper.getChildAt(0)) return;
    if (target.getParent() != null) ((ViewGroup) target.getParent()).removeView(target);
    wrapper.removeAllViews();
    wrapper.addView(target);
  }

  /**
   * Add a static view to appear at the start of the RecyclerView. Headers are displayed in the
   * order they were added.
   *
   * @param view The header view to add
   */
  public boolean addHeaderView(View view) {
    return addHeaderView(getHeaderCount(), view);
  }

  public boolean addHeaderView(int index, View view) {
    if (view == null) return false;

    if (!headerViews.contains(view)) {
      headerViews.add(index, view);
      notifyItemInserted(index);
      return true;
    }

    return false;
  }

  /**
   * Removes a previously-added header view.
   *
   * @param v The view to remove
   * @return true if the view was removed, false if the view was not a header
   * view
   */
  public boolean removeHeaderView(View v) {
    int index = headerViews.indexOf(v);
    if (index == -1) return false;

    if (headerViews.remove(index) != null) {
      notifyItemRemoved(index);
      return true;
    }
    return false;
  }

  public boolean removeHeaderView(int pos) {
    if (remove(this.headerViews, pos)) {
      notifyItemRemoved(pos);
      return true;
    }
    return false;
  }

  /**
   * Add a static view to appear at the end of the RecyclerView. Footers are displayed in the
   * order they were added.
   *
   * @param view The footer view to add
   */
  public boolean addFooterView(View view) {
    return addFooterView(getFooterCount(), view);
  }

  public boolean addFooterView(int index, View view) {
    if (view != null && !footerViews.contains(view)) {
      footerViews.add(index, view);
      notifyItemInserted(index + getHeaderCount() + getWrappedItemCount());
      return true;
    }

    return false;
  }

  /**
   * Removes a previously-added footer view.
   *
   * @param v The view to remove
   * @return true if the view was removed, false if the view was not a footer view
   */
  public boolean removeFooterView(View v) {
    int index = footerViews.indexOf(v);
    if (index == -1) return false;

    boolean b = footerViews.remove(v);
    if (b) {
      notifyItemRemoved(index + getHeaderCount() + getWrappedItemCount());
    }
    return b;
  }

  public RecyclerView.Adapter getWrappedAdapter() {
    return wrappedAdapter;
  }

  private void setWrappedAdapter(RecyclerView.Adapter adapter) {
    if (wrappedAdapter != null) wrappedAdapter.unregisterAdapterDataObserver(mDataObserver);
    wrappedAdapter = adapter;
    Class adapterClass = wrappedAdapter.getClass();
    if (!itemTypesOffset.containsKey(adapterClass)) putAdapterTypeOffset(adapterClass);
    wrappedAdapter.registerAdapterDataObserver(mDataObserver);
  }

  @Override
  public int getItemCount() {
    return getHeaderCount() + getFooterCount() + getWrappedItemCount();
  }

  /**
   * @return The item count in the underlying adapter
   */
  public int getWrappedItemCount() {
    return wrappedAdapter.getItemCount();
  }

  /**
   * @return The number of header views added
   */
  public int getHeaderCount() {
    return headerViews.size();
  }

  /**
   * @return The number of footer views added
   */
  public int getFooterCount() {
    return footerViews.size();
  }

  List<View> getHeaderViews() {
    return headerViews;
  }

  List<View> getFooterViews() {
    return footerViews;
  }

  public int indexOfHeader(View header) {
    return this.headerViews.indexOf(header);
  }

  public int indexOfFooter(View footer) {
    return this.footerViews.indexOf(footer);
  }

  private void putAdapterTypeOffset(Class adapterClass) {
    itemTypesOffset.put(adapterClass, ITEMS_START + itemTypesOffset.size() * ADAPTER_MAX_TYPES);
  }

  private int getAdapterTypeOffset() {
    return itemTypesOffset.get(wrappedAdapter.getClass());
  }

  @Override
  public int getPagingItemCount() {
    if (wrappedAdapter instanceof PagingAdapter) {
      return ((PagingAdapter) wrappedAdapter).getPagingItemCount();
    }
    return getWrappedItemCount();
  }

  @Override
  protected HeaderViewRecyclerAdapter clone() throws CloneNotSupportedException {
    HeaderViewRecyclerAdapter that = new HeaderViewRecyclerAdapter(getWrappedAdapter());
    that.headerViews = this.headerViews;
    that.footerViews = this.footerViews;
    return that;
  }

  public interface CloneableHeaderView {
    View cloneHeader();
  }

  public static class StaticViewHolder extends RecyclerView.ViewHolder {
    public StaticViewHolder(View itemView) {
      super(itemView);
    }
  }

  private class IncludeChildMarginFrameLayout extends FrameLayout {

    public IncludeChildMarginFrameLayout(Context context) {
      super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      super.onMeasure(widthMeasureSpec, heightMeasureSpec);
      View child0;
      // ignore child's margins and remeasure the child
      if (getChildCount() > 0 && (child0 = getChildAt(0)).getVisibility() != GONE) {
        MarginLayoutParams lp = (MarginLayoutParams) getLayoutParams();
        int l = lp.leftMargin;
        int r = lp.rightMargin;
        int t = lp.topMargin;
        int b = lp.bottomMargin;
        lp.leftMargin = 0;
        lp.rightMargin = 0;
        lp.topMargin = 0;
        lp.bottomMargin = 0;
        measureChild(child0, widthMeasureSpec, heightMeasureSpec);
        int h = child0.getMeasuredHeight();
        int w = child0.getMeasuredWidth();
        lp.leftMargin = l;
        lp.rightMargin = r;
        lp.topMargin = t;
        lp.bottomMargin = b;
        setMeasuredDimension(w, h);
      }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
      super.onLayout(changed, left, top, right, bottom);
      View child0;
      // ignore child's margins
      if (getChildCount() > 0 && (child0 = getChildAt(0)).getVisibility() != GONE) {
        child0.layout(0, 0, child0.getWidth(), child0.getHeight());
      }
    }
  }
}