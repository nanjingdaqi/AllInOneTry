package org.peace.allinone.Main;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by daqi on 17-6-5.
 */

public class AuthService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new Auth(this).getIBinder();
    }
}