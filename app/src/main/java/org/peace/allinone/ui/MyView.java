package org.peace.allinone.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {
  public MyView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    Rect rect = new Rect(0, 0, 100, 600);

    Paint paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
    paint1.setStyle(Paint.Style.FILL);
    paint1.setColor(Color.RED);
    canvas.drawRect(rect, paint1);

    paint1.setStyle(Paint.Style.STROKE);
    paint1.setColor(Color.BLUE);
    paint1.setStrokeWidth(10);
    canvas.translate(0, 200);
    //canvas.drawRect(rect, paint1);

    canvas.translate(0, 200);
    paint1.setColor(Color.GREEN);
    rect = new Rect(5, 5, 95, 595);
    canvas.drawRect(rect, paint1);
  }
}
