package org.peace.allinone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by daqi on 17-3-9.
 */

public class MyReceiver extends BroadcastReceiver {

  @Override public void onReceive(Context context, Intent intent) {
    Toast.makeText(context, "hello", Toast.LENGTH_SHORT).show();
  }
}
