package me.ele.ecamera.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import me.ele.ecamera.R;

public class ECameraGuideActivity extends Activity {

  private ImageView closeView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);

    setContentView(R.layout.activity_camera_guide);

    closeView = (ImageView) findViewById(R.id.close);
    closeView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        finish();
      }
    });
  }

  @Override public void finish() {
    super.finish();
    overridePendingTransition(0, 0);
  }
}
