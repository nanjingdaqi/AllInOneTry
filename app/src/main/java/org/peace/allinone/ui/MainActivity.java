package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import me.ele.commons.AppLogger;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    try {
      setContentView(R.layout.activity_main);

      ButterKnife.inject(this);

      try {
        BufferedSource source = Okio.buffer(Okio.source(getAssets().open("peace.txt")));
        AppLogger.e(source.readString(Charset.defaultCharset()));
      } catch (IOException e) {
        e.printStackTrace();
      }

      File lib = new File(getFilesDir().getParentFile(), "lib");
      if (!lib.exists()) {
        lib.mkdirs();
      }

      File test = new File(lib, "test.txt");
      Log.e("MainActivity", test.getAbsolutePath());
      if (!test.exists()) {
        test.createNewFile();
      }
      BufferedSink sink = Okio.buffer(Okio.sink(test));
      sink.writeUtf8("Hello world");
      sink.flush();
      sink.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @OnClick({ R.id.start_btn }) public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.start_btn) {

    }
  }
}
