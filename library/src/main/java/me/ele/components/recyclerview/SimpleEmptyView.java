package me.ele.components.recyclerview;

import android.view.View;

public class SimpleEmptyView implements EmptyViewController {

  private View view;

  public SimpleEmptyView(View view) {
    this.view = view;
  }

  @Override public View view() {
    return view;
  }

  @Override public void attach(EMRecyclerView parent) {
  }

  @Override public void detach(EMRecyclerView parent) {

  }

  @Override public void show() {
    view.setVisibility(View.VISIBLE);
  }

  @Override public void hide() {
    view.setVisibility(View.GONE);
  }
}
