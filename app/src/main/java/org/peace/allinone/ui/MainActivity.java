package org.peace.allinone.ui;

import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import me.ele.commons.AppLogger;
import me.ele.commons.HexConverter;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity  {

  @Override protected void onCreate(Bundle savedInstanceState) {
    setTheme(R.style.MyAppTheme);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
  }

  @Override public Resources.Theme getTheme() {
    AppLogger.e("get theme");
    return super.getTheme();
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
  }
}
