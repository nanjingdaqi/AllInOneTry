package org.peace.allinone;

import android.app.Application;
import android.widget.Toast;
import net.wequick.small.Small;

/**
 * Created by peacepassion on 15/8/11.
 */
public class MyApp extends Application {

  @Override public void onCreate() {
    super.onCreate();

    try {
      Small.setup(this);
      Toast.makeText(this, "set up OK", Toast.LENGTH_SHORT).show();
    } catch (Small.SmallSetupException e) {
      e.printStackTrace();
    }
  }
}
