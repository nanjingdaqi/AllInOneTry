package org.peace.allinone.ui;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import org.peace.allinone.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.start_btn)
    public void onClick(View v) {
        Intent intent = new Intent();
        // intent.setPackage("com.miui.voiceassist");
        intent.setAction("miui.intent.action.APP_SETTINGS");
        PackageManager pm = getPackageManager();
        List<ResolveInfo> list = pm.queryIntentActivities(intent, 0);
        for (ResolveInfo info : list) {
            Log.d("daqi", "info: " + info);
        }
//        intent.setComponent(new ComponentName("com.android.settings", "TestSettings"));
//        intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.TestSettings"));
//        intent = new Intent(this, MainActivity.class);
//        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
//
//        Notification.Builder builder = new Notification.Builder(this);
//        builder.setContentTitle("daqi_title");
//        builder.setContentText("daqi-content");
//        builder.setContentIntent(pi);
//        builder.setSmallIcon(android.R.drawable.ic_popup_sync);
//        builder.setWhen(0);
//        builder.setOngoing(true);
//        builder.setAutoCancel(false);
//
//        Notification notification = builder.build();
//
//        ((NotificationManager) getSystemService(NOTIFICATION_SERVICE)).notify(100, notification);

        startActivity(intent);
    }
}
