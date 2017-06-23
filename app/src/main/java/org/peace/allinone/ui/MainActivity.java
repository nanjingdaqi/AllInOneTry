package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import org.peace.allinone.R;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

  final Object obj = new Object();
    final int i = 100;
  final A a = new A();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);
  }

  @OnClick(R.id.start_btn) public void onClick(View v) {
//      Log.d("Peace", "obj: " + obj);
    Log.d("Peace", "i: " + i);
      Log.d("Peace", "a m: " + a.m);
    try {
      Field f = MainActivity.class.getDeclaredField("a");
      f.setAccessible(true);
      A a2 = new A();
      a2.m = 200;
      f.set(this, a2);
    } catch (Exception e) {
      e.printStackTrace();
    }
    Log.d("Peace", "a m2: " + a.m);
  }
}
