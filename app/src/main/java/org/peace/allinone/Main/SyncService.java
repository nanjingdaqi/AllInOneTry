package org.peace.allinone.Main;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by daqi on 17-6-5.
 */

public class SyncService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(Sync.T, "on create, this: " + System.identityHashCode(this));
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(Sync.T, "on bind");
        return new Sync(this, true).getSyncAdapterBinder();
    }
}
