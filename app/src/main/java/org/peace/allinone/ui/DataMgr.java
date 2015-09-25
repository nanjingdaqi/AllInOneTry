package org.peace.allinone.ui;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import org.peace.allinone.BR;

/**
 * Created by peacepassion on 15/9/24.
 */
public class DataMgr extends BaseObservable {

  @Bindable
  public String data;

  public void setData(String data) {
    this.data = data;
    notifyPropertyChanged(BR.data);
  }
}
