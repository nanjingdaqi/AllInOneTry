package org.peace.allinone.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import me.ele.base.utils.AppLogger;

public class SingleInstanceActivity extends Activity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    AppLogger.e("onCreate");
  }

  @Override protected void onNewIntent(Intent intent) {
    super.onNewIntent(intent);
    AppLogger.e("onNewIntent");
  }
}
