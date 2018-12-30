package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import org.peace.allinone.R;

import java.util.concurrent.Executor;

import static android.os.Process.myTid;

public class MainActivity extends AppCompatActivity {

    static void L(Object msg) {
        Log.d("daqi", "" + msg + " tid: " + myTid() + ", thread_name: " + Thread.currentThread().getName());
    }


    Executor e1 = command -> {
        Log.d("daqi", "exec cmd at e1 tid: " + myTid());
        Thread t1 = new Thread(() -> {
            Log.d("daqi", "T1 runs at tid: " + myTid());
            command.run();
        }, "T1");
        t1.start();
    };

    Executor e2 = command -> {
        Log.d("daqi", "exec cmd at e2 tid: " + myTid(), new Exception());
        Thread t2 = new Thread(() -> {
            Log.d("daqi", "T2 runs at tid: " + myTid());
            command.run();
        }, "T2");
        t2.start();
    };

    Executor e3 = command -> {
        Thread t3 = new Thread(command, "T3");
        t3.start();
    };

    public void testThread() {
        Observable.fromCallable(() -> {
            Log.d("daqi", "Observable: " + myTid() + ", thread name: " + Thread.currentThread().getName());
            return "Hello";
        })
                .subscribeOn(Schedulers.from(e1))
                .observeOn(Schedulers.from(e2))
                .map(v -> {
                    Log.d("daqi", "map1: " + myTid() + ", thread name: " + Thread.currentThread().getName());
                    return "Map result";
                })
                .subscribeOn(Schedulers.from(e1))
                .observeOn(Schedulers.from(e3))
                .map(v -> {
                    Log.d("daqi", "map2: " + myTid() + ", thread name: " + Thread.currentThread().getName());
                    return "Map2 result";
                })
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

    public void asyncJob(int snds) {
        try {
            Thread.sleep(snds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void cpt(int snd) {
        long tm = System.currentTimeMillis();
        while (System.currentTimeMillis() - tm <= snd) {

        }
    }

    public void testFlatMap() {
        Observable<Integer> source = Observable.fromArray(1, 2, 3, 4, 5);
        Function<Integer, Observable<Integer>> fm = val -> {
            Log.d("daqi", "flat_map for: " + val + ", thread_name: " + Thread.currentThread().getName());
//            asyncJob(200 - val * 10);
//            asyncJob(val * 1);
//            asyncJob(1);observe
//            cpt(100);
            return Observable.fromArray(val, val * val);
        };

        source
                .observeOn(Schedulers.from(e1))
                .flatMap(fm)
                .observeOn(Schedulers.from(e2))
//                .concatMap(fm)
                .subscribe((integer) -> {
                    cpt(100);
                    Log.d("daqi", "" + integer + "  " + myTid() + ", tname: " + Thread.currentThread().getName());
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

//        testThread();

        testFlatMap();
    }

    @OnClick(R.id.start_btn)
    public void onClick(View v) {

    }
}
