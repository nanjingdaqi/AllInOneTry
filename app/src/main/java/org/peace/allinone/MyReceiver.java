package org.peace.allinone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import me.ele.base.utils.AppLogger;

/**
 * Created by daqi on 17-3-9.
 */

public class MyReceiver extends BroadcastReceiver {

  public String value = "MyReceiver";

  @Override public void onReceive(Context context, Intent intent) {
      Log.d("daqi", "my_recei");

      try {
          Thread.sleep(5 * 1000);
      } catch (Exception e) {

      }
  }
}
