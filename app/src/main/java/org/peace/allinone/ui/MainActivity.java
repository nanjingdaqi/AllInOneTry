package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.OnClick;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

    public int bb = 10;
    
    public volatile boolean bl;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);
  }

  @OnClick(R.id.start_btn) public void onClick(View v) {
//      foo();
//      A.foo();
      A a = new A();
      bb = a.aa;
  }
  
  synchronized public void foo() {
      synchronized (this) {
          Toast.makeText(this, "foo" + A.foo(10, 10), Toast.LENGTH_SHORT).show();
      }
  }
}
