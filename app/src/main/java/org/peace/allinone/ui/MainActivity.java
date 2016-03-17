package org.peace.allinone.ui;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

    logTheme("main activity", getTheme());
  }

  @OnClick(R.id.start_btn) public void onClick(View v) {

  }

  private void logTheme(String tag, Resources.Theme theme) {
    TypedArray a = theme.obtainStyledAttributes(R.styleable.Theme);
    boolean notitle = a.getBoolean(R.styleable.Theme_windowNoTitle, false);
    Log.e(tag, "middle");
    TypedValue tv = new TypedValue();
    a.getValue(R.styleable.Theme_windowNoTitle, tv);
    Log.e(tag, "tv: " + tv);
    Log.e(tag, "middle");
    boolean actionbar = a.getBoolean(R.styleable.Theme_windowActionBar, false);
    Log.e(tag, "middle");
    boolean abOverlay = a.getBoolean(R.styleable.Theme_windowActionBarOverlay, false);
    a.recycle();
    Log.e(tag, "theme id: " + R.styleable.Theme + ", notitle id: " + R.styleable.Theme_windowNoTitle);
    Log.e(tag, "theme size: " + R.styleable.Theme.length);
    String content = "[";
    for (int i = 0; i < 10; i++) {
      content += R.styleable.Theme[i] + ", ";
    }
    Log.e(tag, "theme 0-9: " + content + "]");
    Log.e(tag, "notitle: " + notitle + ", actionbar: " + actionbar + ", abOverlay: " + abOverlay);
  }
}
