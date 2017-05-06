package org.peace.allinone.ui;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.TextView;
import me.ele.base.utils.AppLogger;
import org.peace.allinone.R;

/**
 * Created by daqi on 17-5-4.
 */

public class MyTextView extends TextView {

  String text;

  public MyTextView(Context context) {
    super(context);
  }

  public MyTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override public Parcelable onSaveInstanceState() {
    AppLogger.e("on save");
    Parcelable p = super.onSaveInstanceState();
    SavedState ss = new SavedState(p);
    ss.text = getText();
    return ss;
  }

  @Override
  public void onRestoreInstanceState(Parcelable state) {
    super.onRestoreInstanceState(state);
    AppLogger.e("on restore");
    setText(((SavedState) state).text);
  }

  public static class SavedState extends BaseSavedState {

    CharSequence text;

    SavedState(Parcelable superState) {
      super(superState);
    }
  }
}
