package org.peace.allinone.ui;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import org.json.JSONObject;
import org.peace.allinone.R;
import org.peace.allinone.http.Error;
import org.peace.allinone.http.Http;
import org.peace.allinone.http.Method;
import org.peace.allinone.http.Request;
import org.peace.allinone.http.Response;

public class MainActivity extends AppCompatActivity {

  static String URL = "http://download.ele.me/android_53964a92fe8659aadbe27524e9520d8f-rewrite";
  static String TAG = "Peace";

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);
  }

  @OnClick(R.id.start_btn) public void onClick(View v) {
    try {
      String body = "{\"age\": 30}";
      Http.performRequest(Request.newRequest(URL)
              .method(Method.GET),
          new Http.Callback() {
            @Override public void onStart() {
              Log.e(TAG, "onStart");
            }

            @Override public void onUpdate(float percent) {
              Log.e(TAG, "onUpdate, percent: " + percent);
            }

            @Override public void onSucc(Response response) {
              try {
                Log.e(TAG, "onSucc");

                byte[] body = response.body();
                File apkFile = new File(Environment.getExternalStorageDirectory(), "amigo_patch.apk");
                if (apkFile.exists()) {
                  apkFile.delete();
                }
                FileOutputStream fos = new FileOutputStream(apkFile);
                fos.write(body);
                fos.close();
              } catch (Exception e) {
                e.printStackTrace();
              }
            }

            @Override public void onFail(Error error) {
              Log.e(TAG, "onFail");
            }

            @Override public void onComplete() {
              Log.e(TAG, "onComplete");
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
