package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import org.peace.allinone.R;
import android.os.Process;

public class MainActivity extends AppCompatActivity {

    static void L(Object msg) {
        Log.d("daqi", "" + msg + " tid: " + Process.myTid());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Observable.fromCallable(() -> {
            Log.d("daqi", "tid: " + Process.myTid());
            return "Hello";
        }).subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        L("subscribe");
                    }

                    @Override
                    public void onNext(String s) {
                        L("next");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        L("error");
                    }

                    @Override
                    public void onComplete() {
                        L("complete");
                    }
                });
    }

    @OnClick(R.id.start_btn)
    public void onClick(View v) {

    }
}
