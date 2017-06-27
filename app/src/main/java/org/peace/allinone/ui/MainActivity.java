package org.peace.allinone.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import org.peace.allinone.R;

import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener{

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    findViewById(R.id.start_btn).setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    Toast.makeText(this, getString(R.string.test), Toast.LENGTH_LONG).show();
  }
}
