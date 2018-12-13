package org.peace.allinone.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.ele.base.utils.AppLogger;
import org.peace.allinone.MyReceiver;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

    public static class LR extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("daqi", "lr");
            try {
                Thread.sleep(5 * 1000);
            } catch (Exception e) {

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        new Thread(new Runnable() {
            @Override
            public void run() {
                LR receiver = new LR();
                for (int i = 0; i < 200000; i++) {
                    if (i % 1000 == 0) {
                        Log.d("daqi", "number: " + i);
                    }
                    IntentFilter filter = new IntentFilter("daqi");
                    registerReceiver(receiver, filter);
                }
            }
        }).start();
    }

    @OnClick(R.id.start_btn)
    public void onClick(View v) {
        Intent intent = new Intent("daqi");
        //intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(intent);
    }
}
