package org.peace.allinone.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.ele.base.utils.AppLogger;
import org.peace.allinone.IFoo;
import org.peace.allinone.MyService;
import org.peace.allinone.R;

import java.lang.reflect.Method;
import java.util.concurrent.CountDownLatch;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    ServiceConnection con = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d("daqi", "service_connected");
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d("daqi", "service_dis_connected");
            }
        };

    @OnClick(R.id.start_btn)
    public void onBind(View v) {
        bindService(new Intent(this, MyService.class), con, BIND_AUTO_CREATE);
    }

    @OnClick(R.id.start_btn2)
    public void onStart(View v) {
        startService(new Intent(this, MyService.class));
    }

    @OnClick(R.id.stop_btn)
    public void onStop(View v) {
        unbindService(con);
    }
}
