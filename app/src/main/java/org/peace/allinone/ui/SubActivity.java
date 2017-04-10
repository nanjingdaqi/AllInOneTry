package org.peace.allinone.ui;

import android.view.View;
import me.ele.base.utils.AppLogger;

/**
 * Created by daqi on 17-4-10.
 */

public class SubActivity extends MainActivity {

  @Override public void onClick(View v) {
    //super.onClick(v);
    AppLogger.e("onClick");
    leak();

    v.postDelayed(new Runnable() {
      @Override public void run() {
        //throw new RuntimeException("kill");
      }
    }, 10 * 1000);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    AppLogger.e("onDestroy");
  }
}
