package org.peace.allinone.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.ele.base.utils.AppLogger;
import org.peace.allinone.R;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);
  }

  @Override protected void onResume() {
    super.onResume();
    AppLogger.e("main onresume");
  }

  @Override protected void onPostResume() {
    super.onPostResume();
    AppLogger.e("main onpostresume");
  }

  @Override
  public void onBackPressed() {
    Log.e("Peace", "on back pressed");
      finish();
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

    Intent intent = new Intent("android.settings.SYNC_SETTINGS");
    startActivity(intent);
  }

  public void postDialog() {
    new AlertDialog.Builder(this).setTitle("Peace").show();
  }
}
