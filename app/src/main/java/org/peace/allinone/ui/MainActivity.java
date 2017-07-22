package org.peace.allinone.ui;

import android.app.ActivityManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import org.peace.allinone.R;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);
  }

  @OnClick(R.id.start_btn) public void onClick(View v) {
      try {
          Class class_ActivityManagerNative = Class.forName("android.app.ActivityManagerNative");
          Method method_getDefault = class_ActivityManagerNative.getDeclaredMethod("getDefault");
          Object obj_IActivityManager = method_getDefault.invoke(class_ActivityManagerNative);
          Method method_updatePersistentConfiguration = obj_IActivityManager.getClass().getDeclaredMethod("updatePersistentConfiguration", Configuration.class);
          Method method_getConfiguration = obj_IActivityManager.getClass().getDeclaredMethod("getConfiguration");
          Configuration cfg = (Configuration) method_getConfiguration.invoke(obj_IActivityManager);
          method_updatePersistentConfiguration.invoke(obj_IActivityManager, );
      } catch (Exception e) {
          e.printStackTrace();
      }
  }
}
