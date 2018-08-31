package org.peace.allinone.ui;

import android.app.ActivityThread;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.IPackageManager;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import org.peace.allinone.R;

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

    @OnClick(R.id.start_btn)
    public void onClick(View v) {
        IPackageManager pm = ActivityThread.getPackageManager();
        try {
            pm.installPackageAsUser("/data/sdcard/app-debug.apk", null, 0, "", 0);
            pm.installPackageAsUser("/data/sdcard/复现Demo.apk", null, 0, "", 0);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
