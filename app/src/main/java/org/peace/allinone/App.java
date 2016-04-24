package org.peace.allinone;

import android.app.Application;
import net.wequick.small.Small;

public class App extends Application{

  @Override public void onCreate() {
    super.onCreate();

    try {
      Small.setup(this);
    } catch (Small.SmallSetupException e) {
      e.printStackTrace();
    }
  }
}
