package me.ele.components.recyclerview;

import android.view.View;

public interface EmptyViewController {
  View view();

  void attach(EMRecyclerView parent);

  void detach(EMRecyclerView parent);

  void show();

  void hide();
}
