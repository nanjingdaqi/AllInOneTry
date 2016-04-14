package org.peace.allinone.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import me.ele.commons.AppLogger;
import me.ele.commons.HexConverter;
import org.peace.allinone.R;

import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      Window window = getWindow();

      window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
          WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    getWindow().getDecorView().setSystemUiVisibility(
        View.SYSTEM_UI_FLAG_LOW_PROFILE |
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
  }

  @Override public void setTheme(int resid) {
    AppLogger.e("AppTheme id: " + R.style.AppTheme + ", MyAppTheme: " + R.style.MyAppTheme);
    AppLogger.e("setTheme get called, res id: " + resid);
    super.setTheme(resid);
  }

  @OnClick(R.id.start_btn) public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.start_btn) {
      View t = v;
      while (t.getParent() != null && View.class.isAssignableFrom(t.getParent().getClass())) {
        View p = (View) t.getParent();
        String bg = null;
        Drawable drawable = p.getBackground();
        if (drawable instanceof ColorDrawable) {
          ColorDrawable colorDrawable = (ColorDrawable) drawable;
          bg = "" + colorDrawable.getColor();
        }
        AppLogger.e("p id: " + p.getId() + ", type: " + p.getClass().getSimpleName() + ", bg: " + bg);
        t = p;
      }
    }
    getSupportActionBar().setTitle("All in one try");
  }
}
