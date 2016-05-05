package org.peace.allinone.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import org.peace.allinone.R;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class MyView extends LinearLayout {

  private BitmapDrawable bottomLine;
  private Bitmap bottomUnit;
  private ImageView bottomLineHolder;

  public MyView(Context context, AttributeSet attrs) {
    super(context, attrs);

    bottomUnit = BitmapFactory.decodeResource(getResources(), R.drawable.unit);
    bottomLine = new BitmapDrawable(getResources(), bottomUnit);
    bottomLine.setAntiAlias(true);
    //bottomLine.setDither(true);
    //bottomLine.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
    //bottomLine.setTileModeX(Shader.TileMode.REPEAT);
    //bottomLine.setTileModeY(Shader.TileMode.REPEAT);
  }

  @Override protected void dispatchDraw(Canvas canvas) {
    super.dispatchDraw(canvas);

    //if (bottomLineHolder == null) {
    //  bottomLineHolder = new ImageView(getContext());
    //  bottomLineHolder.setImageDrawable(bottomLine);
    //  bottomLineHolder.setLayoutParams(new LayoutParams(getMeasuredWidth(), WRAP_CONTENT));
    //  bottomLineHolder.setScaleType(ImageView.ScaleType.FIT_XY);
    //  bottomLineHolder.requestLayout();
    //}
    //
    //bottomLineHolder.draw(canvas);

    int mh = getMeasuredHeight();
    int mw = getMeasuredWidth();
    int gap = 6;
    //bottomLine.setBounds(0, mh - gap - bottomUnit.getHeight(), mw, mh - gap);
    //bottomLine.draw(canvas);

    int l = 0;
    while (l < mw) {
      canvas.drawBitmap(bottomUnit, l, mh - gap - bottomUnit.getHeight(), null);
      l += bottomUnit.getWidth();
    }
  }
}
