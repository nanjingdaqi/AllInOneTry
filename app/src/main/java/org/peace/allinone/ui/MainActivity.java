package org.peace.allinone.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.peace.allinone.R;

public class MainActivity extends Activity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    TextView tv = findViewById(R.id.tv1);
    tv.setText(getText(R.string.foo1));

    TextView tv2 = findViewById(R.id.tv2);
    tv2.setText(getText(R.string.foo2));

    TextView tv3 = findViewById(R.id.tv3);
    tv3.setText(getText(R.string.foo3));
  }
}
