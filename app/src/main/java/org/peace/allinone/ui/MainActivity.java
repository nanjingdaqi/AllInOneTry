package org.peace.allinone.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import me.ele.commons.AppLogger;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @InjectView(R.id.start_btn) Button mStartBtn;

  @InjectView(R.id.et_1) EditText et1;

  //@InjectView(R.id.et_2) EditText et2;

  private InputMethodManager imm;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);

    boolean res = et1.requestFocus();
    AppLogger.d("et1 request focus result: " + res);

    imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

    //if (res) {
      //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
      et1.postDelayed(new Runnable() {
        @Override public void run() {
         imm.showSoftInput(et1, InputMethodManager.SHOW_IMPLICIT);
        }
      }, 100);
    //}
  }

  @OnClick({ R.id.start_btn })
  public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.start_btn) {
      //if (et2.requestFocus()) {
      //  //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
      //  imm.showSoftInput(et1, InputMethodManager.SHOW_IMPLICIT);
      //}
    }
  }
}
