package org.peace.allinone.ui;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.TextView;
import me.ele.base.utils.AppLogger;

/**
 * Created by daqi on 17-5-6.
 */

public class MyView extends TextView {
  public MyView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override public Parcelable onSaveInstanceState() {
    AppLogger.e("view save");
    return super.onSaveInstanceState();
  }

  @Override public void onRestoreInstanceState(Parcelable state) {
    super.onRestoreInstanceState(state);
    AppLogger.e("view restore");
  }
}
