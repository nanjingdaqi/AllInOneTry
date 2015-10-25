package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.InjectView;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @InjectView(R.id.ptr) MyPTR ptr;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);

    getWindow().getDecorView().postDelayed(() -> ptr.startRefresh(), 1000);
    getWindow().getDecorView().postDelayed(() -> ptr.stopRefresh(), 3000);
  }
}
