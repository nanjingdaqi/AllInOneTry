package org.peace.allinone.ui;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import org.peace.allinone.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);
  }

  private static final int Notification_SMALL_ICON = 0xffffffff; // empty drawable

  @OnClick(R.id.start_btn) public void onClick(View v) {
    Notification.Builder builder = new Notification.Builder(this);
    builder.setContentTitle("daqi_title");
    builder.setContentText("daqi-content");
    builder.setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0));
    builder.setSmallIcon(android.R.drawable.ic_popup_sync);
//    builder.setSmallIcon(Notification_SMALL_ICON);
//    builder.setSmallIcon(R.drawable.ic_popup_sync_1);
    builder.setLargeIcon(((BitmapDrawable) getResources().getDrawable(R.drawable.ic_launcher)).getBitmap());
    builder.setWhen(0);
    builder.setOngoing(true);
    builder.setAutoCancel(false);

    Notification notification = builder.build();

//    try {
//      Class MiuiNotification_cls = Class.forName("android.app.MiuiNotification");
//      Object MiuiNotification_obj = MiuiNotification_cls.newInstance();
//      Method setCustomizedIcon_mth = MiuiNotification_cls.getDeclaredMethod("setCustomizedIcon", boolean.class);
//      setCustomizedIcon_mth.invoke(MiuiNotification_obj, true);
//      Field extraNotification_fld = notification.getClass().getDeclaredField("extraNotification");
//      extraNotification_fld.setAccessible(true);
//      extraNotification_fld.set(notification, MiuiNotification_obj);
//    } catch (Exception e) {
//      throw new RuntimeException(e);
//    }

    ((NotificationManager) getSystemService(NOTIFICATION_SERVICE)).notify(0, notification);
  }
}
