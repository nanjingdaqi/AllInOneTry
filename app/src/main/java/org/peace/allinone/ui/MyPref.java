package org.peace.allinone.ui;

import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;
import me.ele.base.utils.AppLogger;

/**
 * Created by daqi on 17-4-27.
 */

public class MyPref extends Preference {
  public MyPref(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public MyPref(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public MyPref(Context context) {
    super(context);
  }

  @Override protected void onPrepareForRemoval() {
    super.onPrepareForRemoval();
    AppLogger.e("on prepare");
  }
}
