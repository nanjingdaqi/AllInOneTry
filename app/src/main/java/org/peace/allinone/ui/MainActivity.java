package org.peace.allinone.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import java.util.regex.Pattern;
import me.ele.commons.AppLogger;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  private String before = "15251852719";
  private String test = "152 5185 2719";

  private static final String CELL_PHONE_REGEX = "^1\\d{10}$";
  private static final String FORMMATED_CELL_HONE_REGEX = "^1\\d{2} \\d{4} \\d{4}$"; // e.g. 188 8888 8888

  @InjectView(R.id.start_btn) Button mStartBtn;

  @InjectView(R.id.et_1) EditText et1;

  //@InjectView(R.id.et_2) EditText et2;

  private InputMethodManager imm;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);

    et1.addTextChangedListener(new TextWatcher() {
      @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      @Override public void afterTextChanged(Editable s) {
        AppLogger.e("org");
      }
    });

    et1.addTextChangedListener(new TextWatcher() {
      @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      @Override public void afterTextChanged(Editable s) {
        format(s);
      }
    });

    et1.setText(before);
    et1.setSelection(before.length());
  }

  void format(Editable s) {
    String str = s.toString().trim();
    AppLogger.e("org: " + str + ".");
    if (Pattern.matches(FORMMATED_CELL_HONE_REGEX, str)) {
      return;
    }
    String tmp = str.replace(" ", "");
    if (isPhoneNumber(tmp)) {
      StringBuilder sb = new StringBuilder(tmp);
      sb.insert(3, " ");
      sb.insert(8, " ");
      String after = sb.toString();
      AppLogger.e("after: " + after + ".");
      s.replace(0, s.length(), after);
      et1.post(new Runnable() {
        @Override public void run() {
         et1.setSelection(et1.length());
        }
      });
    }
  }

  void checkTrim() {
    AppLogger.e("after trim: " + et1.getText().toString().trim());
  }

  void checkPhoneNumber() {
    AppLogger.e("is phone number: " + isPhoneNumber(et1.getText().toString().trim()));
  }

  boolean isPhoneNumber(String str) {
    if (TextUtils.isEmpty(str)) {
      return false;
    }
    if (Pattern.matches(CELL_PHONE_REGEX, str)) {
      return true;
    }
    return false;
  }

  @OnClick({ R.id.start_btn })
  public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.start_btn) {
      AppLogger.e("test result: " + Pattern.matches(FORMMATED_CELL_HONE_REGEX, test));
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
