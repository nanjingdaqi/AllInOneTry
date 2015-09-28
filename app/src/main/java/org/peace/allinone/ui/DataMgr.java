package org.peace.allinone.ui;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.view.View;
import me.ele.commons.AppLogger;

/**
 * Created by peacepassion on 15/9/24.
 */
public class DataMgr extends BaseObservable {

  public ObservableField<String> data;

  @BindingAdapter("bind:paddingLeft")
  public static void setPaddingLeft(View v, int oldValue, int padding) {
    AppLogger.d("old v: " + oldValue);
    AppLogger.d("padding: " + padding);
    v.setPadding(100, 100, 0, 0);
  }

  public int getPaddingLeft() {
    return 100;
  }

}
