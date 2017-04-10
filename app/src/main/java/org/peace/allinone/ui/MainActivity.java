package org.peace.allinone.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.ele.base.utils.AppLogger;
import org.peace.allinone.A;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  byte[] bytes;
  byte[] bytes2;
  byte[] byte3;
  byte[] bytes4;

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

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);
  }

  A a = new A();

  @OnClick(R.id.start_btn) public void onClick(View v) {
    //alloc();
    //bitmapSize();
    //a.i++;
    //leak();
    gotoSub();
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
