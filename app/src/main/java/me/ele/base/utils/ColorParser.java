package me.ele.base.utils;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.text.TextUtils;
import java.util.regex.Pattern;

public final class ColorParser {

  private static final int DEFAULT_COLOR = Color.parseColor("#333333");

  private static final Pattern HEX_PATTERN = Pattern.compile("^#?([A-Fa-f0-9]{6}|[A-Fa-f0-9]{8})$");

  private ColorParser() {

  }

  /**
   * support types : RRGGBB AARRGGBB #RRGGBB #AARRGGBB
   */
  public static @ColorInt int parse(String colorString) {
    return parse(colorString, DEFAULT_COLOR);
  }

  public static @ColorInt int parse(String colorString, int defaultColor) {
    if (!isColorValid(colorString)) {
      return defaultColor;
    }
    if (!colorString.startsWith("#")) {
      colorString = "#" + colorString;
    }
    try {
      return Color.parseColor(colorString);
    } catch (IllegalArgumentException e) {
      return DEFAULT_COLOR;
    }
  }

  public static boolean isColorValid(String colorString) {
    if (TextUtils.isEmpty(colorString)) {
      return false;
    }
    return isHexadecimal(colorString);
  }

  private static boolean isHexadecimal(String str) {
    return HEX_PATTERN.matcher(str).matches();
  }

  public static int getColorWithAlpha(int color, float alpha) {
    int a = Math.round(Color.alpha(color) * alpha);
    int r = Color.red(color);
    int g = Color.green(color);
    int b = Color.blue(color);
    return Color.argb(a, r, g, b);
  }

  public static int getColorWithAlpha(String colorString, float alpha) {
    int originalColor = parse(colorString);
    return getColorWithAlpha(originalColor, alpha);
  }
}
