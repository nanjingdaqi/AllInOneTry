package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import me.ele.commons.AppLogger;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity  {

  @Override protected void onCreate(Bundle savedInstanceState) {
    setTheme(R.style.MyAppTheme);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
  }

  @Override public void setTheme(int resid) {
    AppLogger.e("AppTheme id: " + R.style.AppTheme + ", MyAppTheme: " + R.style.MyAppTheme);
    AppLogger.e("setTheme get called, res id: " + resid);
    super.setTheme(resid);
  }

  @OnClick(R.id.start_btn) public void onClick(View v) {

  }
}
