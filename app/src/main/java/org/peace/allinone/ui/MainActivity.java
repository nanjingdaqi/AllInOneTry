package org.peace.allinone.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
//import android.support.v4.app.DialogFragment;
//import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.internal.DebouncingOnClickListener;
import org.peace.allinone.R;


public class MainActivity extends Activity {

    @BindView(R.id.start_btn) public View startBtn;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // new MainActivity_ViewBinding(this, getWindow().getDecorView());

      // Log.d("daqi", "daqi");

    ButterKnife.bind(this);

//    new MainActivity_ViewBinding(this, getWindow().getDecorView());

      // new MyDlgFrag().show(getSupportFragmentManager(), "");

//       registerCb();

//      startBtn.setOnClickListener(new MyClickListener(this));

      /**
      startBtn.setOnClickListener(new DebouncingOnClickListener() {
          @Override
          public void doClick(View view) {
              leak();
          }
      });
      **/
  }

  public void registerCb() {
      startBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
//               leak();
          }
      });

  }

    @Override
    protected void onResume() {
        super.onResume();
//        leak();
    }

    public void leak() {
      TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
      SmsManager sm = SmsManager.getDefault();
      sm.sendDataMessage(tm.getDeviceId(), "", (short) 0, null, null, null);
  }

  @OnClick(R.id.start_btn) public void onClick(View v) {
      leak();
  }

//  public class MyDlgFrag extends DialogFragment {
//
//      @NonNull
//      @Override
//      public Dialog onCreateDialog(Bundle savedInstanceState) {
//          return new AlertDialog.Builder(getActivity())
//                  .setOnCancelListener(new DialogInterface.OnCancelListener() {
//                      @Override
//                      public void onCancel(DialogInterface dialogInterface) {
//                          leak();
//                      }
//                  })
//                  .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                      @Override
//                      public void onClick(DialogInterface dialogInterface, int i) {
//                          leak();
//                      }
//                  })
//                  .create();
//      }
//  }
}
