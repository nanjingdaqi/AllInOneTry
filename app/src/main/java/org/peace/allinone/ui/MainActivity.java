package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.ele.base.utils.AppLogger;
import org.peace.allinone.R;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.text) TextView tv;

    static String T = "daqi";

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

//      Locale locale = new Locale("es", "MX");
//      Log.d(T, "locale: " + locale);
//
//      java.text.DateFormat fmt = java.text.DateFormat.getDateInstance(DateFormat.LONG, locale);
//      String tm = fmt.format(Calendar.getInstance().getTime());
//      Log.d(T, "mx tm: " + tm);
//
//      locale = new Locale("en", "ZA");
//      fmt = DateFormat.getDateInstance(DateFormat.LONG, locale);
//      tm = fmt.format(Calendar.getInstance().getTime());
//      Log.d(T, "za tm: " + tm);
//
//      locale = new Locale("en", "US");
//      fmt = DateFormat.getDateInstance(DateFormat.LONG, locale);
//      tm = fmt.format(Calendar.getInstance().getTime());
//      Log.d(T, "us tm: " + tm);

      Locale locale = getResources().getConfiguration().locale;
    Log.d(T, "locale: " + locale);
  }

  @OnClick(R.id.start_btn) public void onClick(View v) {
    Locale l1 = new Locale("zh", "cn");
    AppLogger.e("l1: " + l1);
    Locale l2 = new Locale("zh_CN");
    AppLogger.e("l2: " + l2);
    Locale l3 = new Locale(l1.toString());
    AppLogger.e("l3: " + l3);
    AppLogger.e("for tag l1: " + Locale.forLanguageTag(l1.toString()));
    AppLogger.e("for tag l2: " + Locale.forLanguageTag(l2.toString()));
  }
}
