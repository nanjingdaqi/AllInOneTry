package org.peace.allinone.ui;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.AppLaunchChecker;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.ele.base.utils.AppLogger;
import org.peace.allinone.R;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        AppLogger.e(System.getProperty("java.library.path"));

        AppLogger.e(System.getProperty("java.class.path"));
    }

    @OnClick(R.id.start_btn)
    public void onClick(View v) {
        try {
            long start = System.currentTimeMillis();
            Context phoneCtx = createPackageContext("com.android.phone", Context.CONTEXT_IGNORE_SECURITY | CONTEXT_INCLUDE_CODE);
            AppLogger.e("create ctx: " + (System.currentTimeMillis() - start));
            start = System.currentTimeMillis();
            Resources phoneRes = phoneCtx.getResources();
            int resId = phoneRes.getIdentifier("phoneAppLabel", "string", "com.android.phone");
            AppLogger.e("get id: " + (System.currentTimeMillis() - start));
            Toast.makeText(this, phoneRes.getString(resId), Toast.LENGTH_LONG).show();

//            Class PhoneLog_cls = Class.forName("com.android.services.telephony.Log", true, phoneCtx.getClassLoader());
//            Method pii_mtd = PhoneLog_cls.getDeclaredMethod("pii", Object.class);
//            String result = (String) pii_mtd.invoke(PhoneLog_cls, this);
//            AppLogger.e("result: " + result);

            Class SimActivationActivity_cls = Class.forName("com.android.services.telephony.activation.SimActivationActivity", true, phoneCtx.getClassLoader());
            Constructor SimActivationActivity_ctr = SimActivationActivity_cls.getDeclaredConstructor();
            SimActivationActivity_ctr.setAccessible(true);
            activity = (Activity) SimActivationActivity_cls.newInstance();
            AppLogger.e("activity: " + activity);

            ClassLoader parentCl = getClassLoader().getParent();
            AppLogger.e("parent cl: " + parentCl);
            Class ZygoteInit_cls = parentCl.loadClass("com.android.internal.os.ZygoteInit");
            AppLogger.e("zygote_init: " + ZygoteInit_cls);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
