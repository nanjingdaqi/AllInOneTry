package org.peace.allinone.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import org.peace.allinone.R;
import org.reactivestreams.Subscription;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
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
        Log.d("daqi", "exec cmd at e2 tid: " + myTid());
        Thread t2 = new Thread(() -> {
            Log.d("daqi", "T2 runs at tid: " + myTid());
            command.run();
        }, "T2");
        t2.start();
    };

    Executor e3 = command -> {
        Log.d("daqi", "exec cmd at e3 tid: " + myTid());
        Thread t3 = new Thread(() -> {
            Log.d("daqi", "T3 runs at tid: " + myTid());
            command.run();
        }, "T3");
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
        CyclicBarrier barrier = new CyclicBarrier(5);
        Observable<Integer> source = Observable.fromArray(1, 2, 3, 4, 5);
        Function<Integer, Observable<Integer>> fm = val -> {
            Log.d("daqi", "flat_map for: " + val + ", thread_name: " + Thread.currentThread().getName());
//            asyncJob(200 - val * 10);
//            asyncJob(val * 1);
//            asyncJob(1);observe
//            cpt(100);
            Observable<Integer> s2 = Observable.create(emitter -> {
                e3.execute(() -> {
//                    try {
//                        barrier.await();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    } catch (BrokenBarrierException e) {
//                        e.printStackTrace();
//                    }
                    Log.d("daqi", "emit val: " + val);
                    emitter.onNext(val);
                    emitter.onNext(val * val);
                    emitter.onComplete();
                });
            });
            return s2;
        };

        source
                .observeOn(Schedulers.from(e1))
//                .flatMap(fm)
                .concatMap(fm)
                .observeOn(Schedulers.from(e2))
                .subscribe((integer) -> {
                    cpt(100);
                    Log.d("daqi", "" + integer + "  " + myTid() + ", tname: " + Thread.currentThread().getName());
                });
    }

    public void testFlowable() {
        Flowable<Integer> s = Flowable.create(emitter -> {
            for (int i = 1; i <= 5; ++i) {
                emitter.onNext(i);
            }
            emitter.onComplete();
        }, BackpressureStrategy.LATEST);

        s.subscribe(new FlowableSubscriber<Integer>() {

            Subscription s;

            @Override
            public void onSubscribe(Subscription s) {
                this.s = s;
                Log.d("daqi", "onSubScribe with s: " + s.getClass().getSimpleName());
                s.request(3);
            }

            @Override
            public void onNext(Integer integer) {
                Log.d("daqi", "int: " + integer);
//                if (integer == 3) {
//                    s.request(1);
//                }

                if (integer == 3) {
                    new Handler(getMainLooper()).post(() -> s.request(1));
                }
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
                Log.d("daqi", "onComplete");
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

//        testThread();

//        testFlatMap();

        testFlowable();
    }

    @OnClick(R.id.start_btn)
    public void onClick(View v) {

    }
}
