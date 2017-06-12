package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.ele.base.utils.AppLogger;
import org.peace.allinone.R;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

  private String str = "";

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);
  }

  @OnClick(R.id.start_btn) public void onClick(View v) {
    AppLogger.e("class hash code: " + getClass().hashCode());
    AppLogger.e("classname hash code: " + getClass().getName().hashCode());
      Log.d("Peace", "on click");
    NonStaticClass obj = new NonStaticClass();
    AppLogger.e(obj.toString());
    try {
      new A().foo();
    } catch (MyException e) {

    }
  }

  public class NonStaticClass {

    public NonStaticClass() {
        str = "fuck";
    }

    public NonStaticClass(String arg) {

    }
  }
}
