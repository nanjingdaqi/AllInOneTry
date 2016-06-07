package org.peace.allinone.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import me.ele.commons.AppLogger;

public class MyView extends TextView {
  public MyView(Context context) {
    super(context);
  }

  public MyView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    //AppLogger.e(new Exception("onMeasure"));
  }

  @Override public boolean dispatchKeyEvent(KeyEvent event) {
    AppLogger.e("view event, code: " + event.getKeyCode() + ", action: " + event.getAction());
    return super.dispatchKeyEvent(event);
  }
}
