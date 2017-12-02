package org.peace.allinone.ui;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.ele.base.utils.AppLogger;
import org.peace.allinone.MyApp;
import org.peace.allinone.R;
import android.util.Log;

import static org.peace.allinone.MyApp.doSth;

public class MainActivity extends Activity {

  @BindView(R.id.start_btn)
  Button btn;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

//    startActivity(new Intent(this, SubActivity.class));
//    overridePendingTransition(R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_bottom);
//      finish();
//    overridePendingTransition(R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_bottom);

//    doSth(5);
  }

  @Override protected void onResume() {
    super.onResume();
    AppLogger.e("main onresume");
  }

  @Override
  public void onWindowFocusChanged(boolean hasFocus) {
    super.onWindowFocusChanged(hasFocus);
  }

  @Override protected void onPostResume() {
    super.onPostResume();
    AppLogger.e("main onpostresume");
  }

  @Override
  public void onBackPressed() {
    Log.e("Peace", "on back pressed");
      finish();
  }

  @OnClick({ R.id.start_btn }) public void onClick(View v) {
    //startActivity(new Intent(this, SingleInstanceActivity.class));
    //Intent intent = new Intent();
    //intent.setAction("foo");
    //startActivity(intent);

    //Intent intent = new Intent();
    //intent.setComponent(new ComponentName(getPackageName(), "Foo"));
    //startActivity(intent);

//    v.postDelayed(new Runnable() {
//      @Override public void run() {
//        postDialog();
//      }
//    }, 10 * 1000);

//    Intent intent = new Intent("android.settings.SYNC_SETTINGS");
    //Intent intent = new Intent("foo");
    //startActivity(intent);

//    Bundle options = new Bundle();
//    options.putInt("android:activity.animExitRes", 0);
//    options.putInt("android:activity.animEnterRes", 0);
//    options.putInt("android:activity.animType", 1);

//    Intent intent = new Intent(this, SubActivity.class);
//    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

//    startActivity(intent);

//    overridePendingTransition(0, 0);

//      btn.invalidate();

    launchMainSettings();

//    Intent intent = new Intent(this, SubActivity.class);
//    startActivity(intent);
//    overridePendingTransition(R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_bottom);
//    finish();
  }

  void launchMainSettings() {
    Intent intent = new Intent();
    ComponentName cn = new ComponentName("com.android.settings", "com.android.settings.MainSettings");
    intent.setComponent(cn);
    startActivity(intent);
  }

  public void postDialog() {
    new AlertDialog.Builder(this).setTitle("Peace").show();
  }
}
