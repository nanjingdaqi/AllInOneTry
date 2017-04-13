package org.peace.allinone.ui;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import java.lang.reflect.Field;
import me.ele.base.utils.AppLogger;

/**
 * Created by daqi on 17-4-13.
 */

public class MyCB extends CheckedTextView {
  public MyCB(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override public Parcelable onSaveInstanceState() {
    AppLogger.e("on save");

    return super.onSaveInstanceState();
  }

  @Override public void onRestoreInstanceState(Parcelable state) {
    AppLogger.e("on restore");
    Field[] fields = state.getClass().getDeclaredFields();
    for (Field field : fields) {
      AppLogger.e("filed: " + field.getName());
    }
    super.onRestoreInstanceState(state);
  }
}
