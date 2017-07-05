package org.peace.allinone.ui;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import org.peace.allinone.R;
import org.peace.allinone.Test;
import org.peace.allinone.TestWrapper;

/**
 * Created by daqi on 17-7-5.
 */

public class SubActivity extends AppCompatActivity {

    Test t;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        ButterKnife.bind(this);

        Bundle bundle = getIntent().getBundleExtra("bundle");
        TestWrapper tw = bundle.getParcelable("binder");
        t = tw.t;
    }

    @OnClick(R.id.start_btn)
    public void onClick(View v) {
        try {
            t.foo("hello");
        } catch (RemoteException e) {
            Log.e("Peace", "fail to connect");
        }
    }
}
