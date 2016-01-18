package org.peace.allinone.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
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
    et1.append("Hello world");

    //et1.clearFocus();

    //mStartBtn.setFocusable(true);
    //mStartBtn.setFocusableInTouchMode(true);
    //mStartBtn.requestFocus();
  }

  @OnClick({ R.id.start_btn })
  public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.start_btn) {

    }
  }

  void showIM(final View v) {
    imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    v.postDelayed(new Runnable() {
      @Override public void run() {
        v.requestFocus();
        imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
      }
    }, 100);
  }
}
