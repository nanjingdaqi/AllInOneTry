package me.ele.components.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EmptyViewControllerFactory {

  public EmptyViewController getEmptyView(ViewGroup container, int emptyId) {
    EmptyViewController emptyView = null;
    if (emptyId > 0) {
      View view = LayoutInflater.from(container.getContext()).inflate(emptyId, null);
      if (view instanceof EmptyViewController) {
        emptyView = (EmptyViewController) view;
      } else {
        container.addView(view);
        emptyView = new SimpleEmptyView(container);
      }
    }
    return emptyView;
  }
}
