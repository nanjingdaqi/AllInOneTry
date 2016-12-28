package org.peace.allinone.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import me.ele.base.utils.ResourceUtil;
import org.peace.allinone.R;

public class MyView4 extends View{

  public MyView4(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
    canvas.drawBitmap(bitmap, null, new Rect(0, 0, getMeasuredWidth(), getMeasuredHeight()), null);
  }
}
