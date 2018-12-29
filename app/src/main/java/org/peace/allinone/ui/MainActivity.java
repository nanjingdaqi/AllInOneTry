package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
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

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    static void L(Object msg) {
        Log.d("daqi", "" + msg + " tid: " + Process.myTid());
    }


    Executor e1 = new Executor() {
        @Override
        public void execute(@NonNull Runnable command) {
            Thread t1 = new Thread(command, "T1");
            t1.start();
        }
    };

    Executor e2 = new Executor() {
        @Override
        public void execute(@NonNull Runnable command) {
            Thread t2 = new Thread(command, "T2");
            t2.start();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Observable.fromCallable(() -> {
            Log.d("daqi", "Observable: " + Process.myTid() + ", thread name: " + Thread.currentThread().getName());
            return "Hello";
        })
                .subscribeOn(Schedulers.from(e1))
                .map(v -> {
                    Log.d("daqi", "map1: " + Process.myTid() + ", thread name: " + Thread.currentThread().getName());
                    return "Map result";
                })
                .subscribeOn(Schedulers.from(e2))
                .map(v -> {
                    Log.d("daqi", "map2: " + Process.myTid() + ", thread name: " + Thread.currentThread().getName());
                    return "Map2 result";
                })
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
