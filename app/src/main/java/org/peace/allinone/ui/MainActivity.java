package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);
  }

  @OnClick(R.id.start_btn) public void onClick(View v) {
    String url = "https://restapi.ele.me/shopping/v1/banner/new_user";
    String ua =
        "Rajax/1 MX4_Pro/meizu_mx4pro Android/5.1.1 Display/Flyme_5.1.11.1A Eleme/7.2 ID/49df5fda-66e1-34d5-811d-8c6bcb681c2d; KERNEL_VERSION:3.10.9-user-00051-gb478b65-cI758f4ee API_Level:22 Hardware:ca8d5a27845d54848708ab8efaee3ed8";
    String xd = "aW1laTo4NjU4NjMwMjMxNDg4MDcgc2VyaWFsOjAxMjM0NTY3ODlBQkNERUYgYW5kcm9pZF9pZDplOTY2MDZhMTgzMTY4YzUyIGJyYW5kOk1laXp1IG1vZGVsOk1YNF9Qcm8gbWFjQWRkcmVzczozOF9iY18xYV9iY19jYV83YSBuZXRUeXBlOldJRkkgc2ltU3RhdGU6MCBjaWQ6ODYzNzQ2MSBsYWM6NjE4MCB3aWZpTGlzdDowMF83OF84OF82OF9iZV9iZCwwMF83OF84OF80Zl82N18xZiwwMF83OF84OF80Zl82N18xZSwwMF83OF84OF80Zl82N18xZCwwMF83OF84OF82OF9iZV9iZSwwMF83OF84OF82OF9iZV9iZiwwMF83OF84OF8wN181Y19jZCwwMF83OF84OF8wN181Y19jZiwwMF83OF84OF85Ml8zNV83ZSwwMF83OF84OF85Ml8zNV83ZCBoYXZlQmx1ZXRvb3RoOnRydWUgdHJhY2tfaWQ6IG1lbW9yeToxODcxIGVuZXJneV9wZXJjZW50OjEwMCBmaXJzdF9vcGVuOjE0ODMwMDgxMTIgbGFzdF9vcGVuOjE0ODMwMDgxMzggbmV0X3R5cGU6d2lmaSBoYXJkd2FyZV9pZDpjYThkNWEyNzg0NWQ1NDg0ODcwOGFiOGVmYWVlM2VkOA==";
    Request request = new Request.Builder()
        .addHeader("User-Agent", ua)
        .addHeader("X-DeviceInfo", xd)
        .url(url)
        .build();
    okHttpClient.newCall(request).enqueue(new Callback() {
      @Override public void onFailure(Call call, IOException e) {
        runOnUiThread(new Runnable() {
          @Override public void run() {
            Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_LONG).show();
          }
        });
      }

      @Override public void onResponse(Call call, Response response) throws IOException {
        runOnUiThread(new Runnable() {
          @Override public void run() {
            Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_LONG).show();
          }
        });
      }
    });
  }
}
