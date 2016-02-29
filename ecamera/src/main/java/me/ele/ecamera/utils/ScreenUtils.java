package me.ele.ecamera.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.Window;

public class ScreenUtils {

    private static final float PADDING = 0.5f;
    
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }
    
    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + PADDING);
    }

    public static int getStateBarHeight(Activity activity) {
        int result = 0;
        int resourceId = activity.getResources().getIdentifier(
                "status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = activity.getResources().getDimensionPixelSize(resourceId);
        } else {
            Rect rectgle = new Rect();
            Window window = activity.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(rectgle);
            result = rectgle.top;
        }
        return result;
    }
}
