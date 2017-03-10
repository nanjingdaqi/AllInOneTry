package org.peace.allinone.ui;

import android.content.Context;

/**
 * Created by daqi on 17-3-10.
 */

public class FooClass {

  public boolean foo() {
    return true;
  }

  public boolean foo2(Context context) {
    return context.toString().equals("hello");
  }
}
