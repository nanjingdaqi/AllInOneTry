package org.peace.allinone;

import android.app.Application;
import android.os.StrictMode;
import me.ele.base.utils.AppLogger;
import me.ele.base.utils.DimenUtil;
import me.ele.base.utils.ResourceUtil;

/**
 * Created by peacepassion on 15/8/11.
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppLogger.debug = true;
        DimenUtil.init(this);
        ResourceUtil.init(this);

        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .penaltyDropBox()
                .build());

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .build());
    }
}
