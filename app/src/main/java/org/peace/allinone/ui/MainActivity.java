package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.OnClick;
import javax.inject.Inject;
import org.peace.allinone.MyApp;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @Inject FooClass myFoo;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ((MyApp) getApplication()).myAppComponent.inject(this);

    ButterKnife.bind(this);
  }

  @OnClick(R.id.start_btn) public void onClick(View v) {
    Toast.makeText(this, myFoo.foo4(), Toast.LENGTH_LONG).show();;
  }

  public void setMyFoo(FooClass foo) {
    this.myFoo = foo;
  }

  public boolean foo() {
    return myFoo.foo();
  }

  public boolean foo2() {
    FooClass foo = new FooClass();
    foo.foo2(this);
    return foo.foo();
  }
}
