package org.peace.allinone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MySecretCodeReceiver extends BroadcastReceiver {
  @Override public void onReceive(Context context, Intent intent) {
    Toast.makeText(context, "Got", Toast.LENGTH_SHORT).show();
  }
}
