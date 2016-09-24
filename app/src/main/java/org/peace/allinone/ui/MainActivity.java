package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
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

  static String URL = "http://localhost:3000/test";
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
              .method(Method.POST)
              .body(body.getBytes("UTF-8")),
          new Http.Callback() {
            @Override public void onStart() {
              Log.e(TAG, "onStart");
            }

            @Override public void onUpdate(float percent) {
              Log.e(TAG, "onUpdate");
            }

            @Override public void onSucc(Response response) {
              try {
                Log.e(TAG, "onSucc");

                byte[] body = response.body();
                String content = new String(body, "UTF-8");
                Log.e(TAG, "content: " + content);
                JSONObject object = new JSONObject(content);
                User user = new User();
                user.name = object.getString("name");
                Log.e(TAG, "user name: " + user.name);
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
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }
}
