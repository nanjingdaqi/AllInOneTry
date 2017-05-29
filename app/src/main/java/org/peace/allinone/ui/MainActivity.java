package org.peace.allinone.ui;

import android.os.Bundle;
import android.os.Debug;
import android.os.Trace;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.OnClick;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);
  }

  @OnClick(R.id.start_btn) public void onClick(View v) {
    Trace.beginSection("Peace");
    Debug.startMethodTracing("peace2");
    doSth();
    Debug.stopMethodTracing();
    Trace.endSection();
  }

  void doSth() {
    doSth2();
    do3();
  }

  void doSth2() {
    Toast.makeText(this, "Hello", Toast.LENGTH_LONG).show();
  }

  void do3() {
    try {
      Thread.sleep(1 * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
