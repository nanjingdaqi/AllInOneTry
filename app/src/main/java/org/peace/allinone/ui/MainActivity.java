package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Toast;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import net.wequick.small.Small;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  static String URL = "http://10.0.3.2:3001/app/src/main/assets/libnet_wequick_example_small_app_mine.bundle";
  static String TAG = MainActivity.class.getSimpleName();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    findViewById(R.id.start_btn).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Small.openUri("mine", MainActivity.this);
      }
    });

    findViewById(R.id.start_btn2).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startUpgrade();
      }
    });
  }

  private void startUpgrade() {
    OkHttpClient okHttpClient = new OkHttpClient();
    Request request = new Request.Builder().url(URL).build();
    Call call = okHttpClient.newCall(request);
    call.enqueue(new Callback() {
      @Override public void onFailure(Call call, IOException e) {
        Log.e(TAG, "request fail");
        e.printStackTrace();
      }

      @Override public void onResponse(Call call, Response response) throws IOException {
        String pkgName = "net.wequick.example.small.app.mine";
        net.wequick.small.Bundle bundle = net.wequick.small.Bundle.findByName(pkgName);
        File destFile = bundle.getPatchFile();
        if (destFile.exists()) {
          destFile.delete();
        }
        destFile.createNewFile();

        Log.e(TAG, "patch file path: " + destFile.getPath());

        Source source = response.body().source();
        BufferedSink sink = Okio.buffer(Okio.sink(destFile));
        sink.writeAll(source);
        sink.flush();
        sink.close();

        bundle.upgrade();

        Log.e(TAG, "download succ");
      }
    });
  }

  @Override protected void onStart() {
    super.onStart();
    Small.setUp(this, null);
    Toast.makeText(MainActivity.this, "set up OK", Toast.LENGTH_SHORT).show();
  }
}
