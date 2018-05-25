package org.peace.allinone.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.ele.base.utils.AppLogger;
import org.peace.allinone.MyApp;
import org.peace.allinone.R;

public class SubActivity extends AppCompatActivity {

  @BindView(R.id.start_btn)
  Button startBtn;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);



//    MyApp.doSth(5);
//
//    getWindow().getDecorView().postDelayed(new Runnable() {
//      @Override
//      public void run() {
//        setContentView(R.layout.activity_main);
//      }
//    }, 5000);
//
//      AppLogger.e("on create");

//    ButterKnife.bind(this);

//    startBtn.setText("SubActivity");
  }

//  @OnClick({ R.id.start_btn }) public void onClick(View v) {
//      Intent intent = new Intent(this, MainActivity.class);
//    startActivity(intent);
//  }

  @Override
  protected void onNewIntent(Intent intent) {
    super.onNewIntent(intent);
    AppLogger.e("on new Intent");
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    AppLogger.e("sub destroy");
  }
}
