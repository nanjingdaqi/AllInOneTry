package org.peace.allinone.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.ServiceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.android.internal.view.IInputMethodManager;
import org.peace.allinone.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    IInputMethodManager imms;

    Method commitTextMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        imms = IInputMethodManager.Stub.asInterface(ServiceManager.getService(Context.INPUT_METHOD_SERVICE));
        try {
            commitTextMethod = IInputMethodManager.class.getMethod("commitText", String.class, int.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @OnClick(R.id.et)
    public void onClick(View v) {
        v.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    commitTextMethod.invoke(imms, "Hello Daqi", 0);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }, 3000);
    }

}
