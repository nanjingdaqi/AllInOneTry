package org.peace.allinone;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import me.ele.base.utils.AppLogger;

/**
 * Created by daqi on 17-10-11.
 */

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        AppLogger.e("on receive");
        ContentResolver cr = context.getContentResolver();
        Settings.Secure.putInt(cr, Settings.Secure.LOCATION_MODE, 2);
    }
}
