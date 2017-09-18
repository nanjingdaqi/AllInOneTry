package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import me.ele.base.utils.AppLogger;
import org.peace.allinone.A;
import org.peace.allinone.MyApp;

/**
 * Created by daqi on 17-4-10.
 */

public class SubActivity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTitle("SubActivity");

    A.a.activity = this;
//    A a = new A();
//    a.activity = this;
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    AppLogger.e("onDestroy");
  }
}
