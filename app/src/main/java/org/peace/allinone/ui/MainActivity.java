package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.ele.base.utils.AppLogger;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

    public int bb = 10;
    
    public volatile boolean bl;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

    final int fi = new Integer(100);
    Object app = new Object() {

        public int foo() {
            return fi;
        }
    };

  }

  @OnClick(R.id.start_btn) public void onClick(View v) {
//      foo();
//      A.foo();
//      A a = new A();
//      bb = a.aa;
      foo2();
  }

  synchronized public void foo() {
      synchronized (this) {
          Toast.makeText(this, "foo" + A.foo(10, 10), Toast.LENGTH_SHORT).show();
      }
  }

  public static void foo2() {
      Object obj = MainActivity.class;
      AppLogger.e("foo2 invoke");
      AppLogger.e("" + A.b);
  }
}
