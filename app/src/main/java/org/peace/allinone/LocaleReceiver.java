package org.peace.allinone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by daqi on 17-3-7.
 */

public class LocaleReceiver extends BroadcastReceiver {

  @Override public void onReceive(Context context, Intent intent) {
    Toast.makeText(context, context.getResources().getString(R.string.str), Toast.LENGTH_LONG).show();
  }
}
