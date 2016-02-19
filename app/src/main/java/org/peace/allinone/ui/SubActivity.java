package org.peace.allinone.ui;

import android.os.Bundle;
import me.ele.commons.AppLogger;

public class SubActivity extends MainActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTitle("SubActivity");
    AppLogger.e("sub onCreate");
  }
}
