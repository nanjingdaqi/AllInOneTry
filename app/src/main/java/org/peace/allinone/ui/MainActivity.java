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

public class MainActivity extends AppCompatActivity implements ServiceConnection {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    public CountDownLatch latch = new CountDownLatch(1);

    public class MyHandler extends Handler {
        MyHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            latch.countDown();
        }
    }


    HandlerThread handlerThread = new HandlerThread("handler_thread");

    MyHandler myHandler;

    public Messenger messenger;

    @OnClick(R.id.start_btn)
    public void onClick(View v) {
        Intent intent = new Intent(this, MyService.class);
//    startService(intent);

        handlerThread.start();
        myHandler = new MyHandler(handlerThread.getLooper());
        messenger = new Messenger(myHandler);
        intent.putExtra("messenger", messenger);
        bindService(intent, this, BIND_AUTO_CREATE);
        AppLogger.e("start waiting");
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        AppLogger.e("waiting finish");
    }

    @OnClick(R.id.stop_btn)
    public void onStop(View v) {
//    stopService(new Intent(this, MyService.class));
        unbindService(this);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        Toast.makeText(this, "Connected", Toast.LENGTH_LONG).show();
        IFoo foo = IFoo.Stub.asInterface(service);
        try {
            foo.setP(new Messenger(myHandler));
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        Toast.makeText(this, "DisConnected", Toast.LENGTH_LONG).show();
    }
}
