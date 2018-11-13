package org.peace.allinone.ui;

import android.annotation.TargetApi;
import android.app.ActivityThread;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.IPackageManager;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.pm.PackageParser;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import org.peace.allinone.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

//        try {
//            Class AppGlobals_CLS = Class.forName("android.app.AppGlobals");
//            Method getPackageManager_MTD = AppGlobals_CLS.getDeclaredMethod("getPackageManager");
//            Object IPackageManager_OBJ = getPackageManager_MTD.invoke(AppGlobals_CLS);
//            Method setApplicationEnabledSetting_MTD = IPackageManager_OBJ.getClass()
//                    .getDeclaredMethod("setApplicationEnabledSetting", String.class, int.class, int.class, int.class, String.class);
//            setApplicationEnabledSetting_MTD.invoke(IPackageManager_OBJ, "com.android.documentsui", 1, 0, 0, getPackageName());
//
//            Method setComponentEnabledSetting_MTD = IPackageManager_OBJ.getClass()
//                    .getDeclaredMethod("setComponentEnabledSetting", ComponentName.class, int.class, int.class, int.class);
//            setComponentEnabledSetting_MTD.invoke(IPackageManager_OBJ, new ComponentName(getPackageName(), SubActivity.class.getName()), 2, 0, 0);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }

    File apk = new File("/sdcard/wechat.apk");
//    File dm = new File("/sdcard/wechat.dm");

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.start_btn)
    public void onClick(View v) {
        try {
            PackageManager pm = getPackageManager();
            PackageInstaller.SessionParams params = new PackageInstaller.SessionParams(
                    PackageInstaller.SessionParams.MODE_FULL_INSTALL);
            params.setInstallLocation(PackageInfo.INSTALL_LOCATION_INTERNAL_ONLY);
            PackageInstaller pi = pm.getPackageInstaller();
            int sessionId  = pi.createSession(params);
            PackageInstaller.Session session = pi.openSession(sessionId);

            OutputStream os = session.openWrite("wechat.apk", 0, apk.length());
            InputStream is = new FileInputStream(apk);
            byte[] buffer = new byte[4086];
            int len = 0;
            while ((len = is.read(buffer)) > 0) {
                os.write(buffer, 0, len);
            }
            session.fsync(os);
            is.close();
            os.close();
            if (System.currentTimeMillis() > 0)
                return;

//            os = session.openWrite("wechat.dm", 0, dm.length());
//            is = new FileInputStream(dm);
//            while ((len = is.read(buffer)) > 0) {
//                os.write(buffer, 0, len);
//            }
//            session.fsync(os);
//            is.close();
//            os.close();

            Intent broadcastIntent = new Intent("org.peace");
            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    this /*context*/,
                    sessionId,
                    broadcastIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);

            session.commit(pendingIntent.getIntentSender());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
