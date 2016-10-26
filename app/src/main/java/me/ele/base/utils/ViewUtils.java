package me.ele.base.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Bitmap.Config;
import android.support.annotation.Nullable;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ViewUtils {
  private static Method viewSetMeasureDimensionMethod;

  private ViewUtils() {
  }

  public static void setMeasureDimension(View v, int width, int height) {
    try {
      viewSetMeasureDimensionMethod.invoke(v,
          new Object[] { Integer.valueOf(width), Integer.valueOf(height) });
    } catch (IllegalAccessException var4) {
      var4.printStackTrace();
    } catch (InvocationTargetException var5) {
      var5.printStackTrace();
    }
  }

  public static Activity getActivity(View v) {
    Context ctx = v.getContext();
    return ctx instanceof Activity ? (Activity) ctx
        : (ctx instanceof ContextWrapper ? getContextActivity((ContextWrapper) ctx) : null);
  }

  private static Activity getContextActivity(ContextWrapper ctxWrapper) {
    Context baseCtx = ctxWrapper.getBaseContext();
    return baseCtx == null ? null : (baseCtx instanceof Activity ? (Activity) baseCtx
        : (baseCtx instanceof ContextWrapper ? getContextActivity((ContextWrapper) baseCtx)
            : null));
  }

  @Nullable
  public static Bitmap loadBitmap(View v) {
    if (v.getWidth() != 0 && v.getHeight() != 0) {
      Bitmap b = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Config.ARGB_8888);
      Canvas c = new Canvas(b);
      v.draw(c);
      return b;
    } else {
      return null;
    }
  }

  static {
    try {
      viewSetMeasureDimensionMethod = View.class.getDeclaredMethod("setMeasuredDimension",
          new Class[] { Integer.TYPE, Integer.TYPE });
      viewSetMeasureDimensionMethod.setAccessible(true);
    } catch (NoSuchMethodException var1) {
      var1.printStackTrace();
    }
  }
}
