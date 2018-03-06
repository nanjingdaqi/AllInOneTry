package org.peace.allinone.ui;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import butterknife.ButterKnife;
import butterknife.OnClick;
import org.peace.allinone.R;
import org.peace.allinone.Test;
import org.peace.allinone.TestWrapper;

public class MainActivity extends AppCompatActivity {

    Test test = new Test.Stub() {
        @Override
        public void foo(String arg) throws RemoteException {
            Log.e("Peace", "it works, calling pid: " + Binder.getCallingPid() + ", calling uid: " + Binder.getCallingUid());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.start_btn)
    public void onClick(View v) {
        Intent intent = new Intent(this, SubActivity.class);
        Bundle bundle = new Bundle();
        TestWrapper t = new TestWrapper(test);
        bundle.putParcelable("binder", t);
        intent.putExtra("bundle", bundle);
        startActivity(intent);
    }
}
