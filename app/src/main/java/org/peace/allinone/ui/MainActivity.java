package org.peace.allinone.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import net.wequick.small.Small;
import org.peace.allinone.R;

public class MainActivity extends Activity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    findViewById(R.id.start_btn).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Intent intent = new Intent();
        intent.putExtra(Small.KEY_ACTIVITY_URI, "app.b1");
        Small.launchBundleActivity(intent, MainActivity.this);
      }
    });
  }
}
