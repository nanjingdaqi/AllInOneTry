package org.peace.allinone.ui;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.View;
import org.peace.allinone.R;


public class MainActivity extends Activity {

  public static abstract class WrappingListener implements View.OnClickListener {

    @Override
    public void onClick(View view) {
      // do some handling
      doClick(view);
    }

    public abstract void doClick(View v);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    findViewById(R.id.start_btn).setOnClickListener(new WrappingListener() {
      @Override
      public void doClick(View v) {
        leak();
      }
    });
  }

    public void leak() {
      TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
      SmsManager sm = SmsManager.getDefault();
      sm.sendDataMessage(tm.getDeviceId(), "", (short) 0, null, null, null);
  }
}
