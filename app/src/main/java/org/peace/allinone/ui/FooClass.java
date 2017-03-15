package org.peace.allinone.ui;

import android.content.Context;
import android.support.annotation.VisibleForTesting;
import android.view.LayoutInflater;

/**
 * Created by daqi on 17-3-10.
 */

public class FooClass {

  public boolean foo() {
    return true;
  }

  @VisibleForTesting
  public boolean foo2(Context context) {
    return context.toString().equals("hello");
  }

  public boolean foo3(Context context) {
    LayoutInflater inflater = LayoutInflater.from(context);
    return inflater.toString().equals("hello");
  }
}
