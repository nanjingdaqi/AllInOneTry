package org.peace.allinone.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.OnClick;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import me.ele.base.utils.AppLogger;
import org.peace.allinone.A;
import org.peace.allinone.MyApp;
import org.peace.allinone.MyRef;
import org.peace.allinone.R;

import static android.os.Build.VERSION_CODES.N;

public class MainActivity extends AppCompatActivity {

  enum Test {
    ENUM1, ENUM2
  }

  byte[] bytes;
  byte[] bytes2;
  byte[] byte3;
  byte[] bytes4;

  HashMap<String, String> hashMap = new HashMap<>();
  ArrayMap<String, String> arrayMap = new ArrayMap<>();

  HashMap<String, String> hashMap2 = new HashMap<>();
  ArrayMap<String, String> arrayMap2 = new ArrayMap<>();

  HashMap<String, String> hashMap3 = new HashMap<>();
  ArrayMap<String, String> arrayMap3 = new ArrayMap<>();

  HashSet<String> hashSet = new HashSet<>();
  ArraySet<String> arraySet = new ArraySet<>();

  HashSet<String> hashSet2 = new HashSet<>();
  ArraySet<String> arraySet2 = new ArraySet<>();

  HashSet<String> hashSet3 = new HashSet<>();
  ArraySet<String> arraySet3 = new ArraySet<>();

  Test t = Test.ENUM1;
  Test t2 = Test.ENUM2;

  public class H extends Handler {
    @Override public void handleMessage(Message msg) {
      super.handleMessage(msg);
      Toast.makeText(MainActivity.this, "handle msg", Toast.LENGTH_LONG).show();
      new Thread(new Runnable() {
        @Override public void run() {
          try {
            Thread.sleep(20 * 1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }).start();
    }
  }

  H h = new H();
  A a = new A();
    ReferenceQueue<A> queue = new ReferenceQueue<>();
  MyRef<A> rA = new MyRef(a, queue);

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);
    //init();
//    task.execute();
    AppLogger.e(rA.toString());


  }

  B b = new B(this);

  @Override
  protected void finalize() throws Throwable {
    AppLogger.e("" + b.ower.get());
    super.finalize();
    AppLogger.e("" + b.ower.get());
  }

  static class B {
    WeakReference<MainActivity> ower;

    B(MainActivity ins) {
        ower = new WeakReference<MainActivity>(ins);
    }
  }

  @OnClick(R.id.start_btn) public void onClick(View v) {
      startActivity(new Intent(this, SubActivity.class));
  }

  @OnClick(R.id.btn2) public void release(View v) {
//    A.a.activity = null;
      A.a = null;
  }

  AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      AppLogger.e("start");
      Log.e("peace", "start");
    }

    @Override
    protected Void doInBackground(Void... params) {
      try {
        AppLogger.e("doing");
        Log.e("peace", "doing");
        Thread.sleep(20 * 1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
      super.onPostExecute(aVoid);
      AppLogger.e("done");
      Log.e("peace", "done");
    }

    @Override
    protected void onCancelled(Void aVoid) {
      super.onCancelled(aVoid);
      Log.e("peace", "canceled");
    }
  };

  @Override
  protected void onDestroy() {
    super.onDestroy();
    task.cancel(true);
  }

  private void init() {
    inject(6, hashMap);
    inject(20, hashMap2);
    inject(100, hashMap3);

    inject(6, arrayMap);
    inject(20, arrayMap2);
    inject(100, arrayMap3);

    inject(6, hashSet);
    inject(20, hashSet2);
    inject(100, hashSet3);

    inject(6, arraySet);
    inject(20, arraySet2);
    inject(100, arraySet3);
  }

  private void inject(int n, Map<String, String> map) {
    for (int i = 0; i < n; ++i) {
      map.put("key-" + i, "val");
    }
  }
  private void inject(int n, Set<String> set) {
    for (int i = 0; i < n; ++i) {
      set.add("val-" + i);
    }
  }

  public void toast() {
    Toast.makeText(this, "fo", Toast.LENGTH_LONG).show();
  }

  public void testConnectivityManager() {
    getSystemService(CONNECTIVITY_SERVICE);
  }

  public void testWifiLeak() {
    getSystemService(WIFI_SERVICE);
  }

  public void gotoSub() {
    startActivity(new Intent(this, SubActivity.class));
  }

  public void leak() {
    h.obtainMessage().sendToTarget();
  }

  public void bitmapSize() {
    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
    AppLogger.e("bitmap size: " + bitmap.getByteCount());
  }

  public void alloc() {
    if (bytes == null) {
      bytes = new byte[10 * 1024 * 1024];
      return;
    }
    if (bytes2 == null) {
      bytes2 = new byte[10 * 1024 * 1024];
      return;
    }
    if (byte3 == null) {
      byte3 = new byte[200 * 1024 * 1024];
      return;
    }
    if (bytes4 == null) {
      bytes4 = new byte[600 * 1024 * 1024];
      return;
    }
  }
}
