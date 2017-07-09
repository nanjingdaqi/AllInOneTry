package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.OnClick;
import org.peace.allinone.Daqi;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("native-lib");
    }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);
  }

  @OnClick(R.id.start_btn) public void onClick(View v) {
//      Toast.makeText(this, stringFromJNI(), Toast.LENGTH_SHORT).show();
      try {
//          Toast.makeText(this, native_hello(), Toast.LENGTH_SHORT).show();
      } catch (RuntimeException e) {
          e.printStackTrace();
      }

//      alloc();

      fromJava(daqi.msg);
  }

  Daqi daqi = new Daqi();

  @OnClick(R.id.btn2) public void click2(View v) {
      op_obj();
  }

  public native void fromJava(String obj);

    public native String stringFromJNI();

    public native void alloc();

    public native void op_obj();

    public static String forNative() {
        return "Hello JAVA";
    }

    public native String native_hello();
}
