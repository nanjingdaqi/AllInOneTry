package org.peace.allinone.ui;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

//        NotificationChannel channel = new NotificationChannel("daqi_id", "daqi", NotificationManager.IMPORTANCE_DEFAULT);
//        channel.setDescription("daqi_des");
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        notificationManager.createNotificationChannel(channel);
        notificationManager.deleteNotificationChannel("daqi_id");
    }

    @OnClick(R.id.start_btn)
    public void onClick(View v) {
//        Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
//        intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
//        intent.putExtra(Settings.EXTRA_CHANNEL_ID, "daqi_id");
//        startActivity(intent);

        Notification notification = new Notification.Builder(this)
                .setContentText("Haha")
                .setSmallIcon(R.drawable.ic_launcher)
                .build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(100, notification);
    }
}
