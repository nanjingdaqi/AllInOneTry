package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import me.ele.commons.AppLogger;
import org.peace.allinone.R;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

  @InjectView(R.id.start_btn) Button mStartBtn;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);
  }

  @OnClick(R.id.start_btn) public void onClick(View v) {
    doTask(new MyCallback() {
      @Override public void onSucc() {

      }

      @Override public void onFail(String s) {

      }
    });
  }

  void doTask(MyCallback callback) {
    Observable.create(new Observable.OnSubscribe<Void>() {
      @Override public void call(Subscriber<? super Void> subscriber) {
        task1(new MyCallback() {
          @Override public void onSucc() {
            subscriber.onNext(null);
            subscriber.onCompleted();
          }

          @Override public void onFail(String s) {
            subscriber.onError(new Exception(s));
          }
        });
      }
    })
        .flatMap(new Func1<Void, Observable<Void>>() {
          @Override public Observable<Void> call(Void aVoid) {
            return Observable.create(new Observable.OnSubscribe<Void>() {
              @Override public void call(Subscriber<? super Void> subscriber) {
                task2(new MyCallback() {
                  @Override public void onSucc() {
                    subscriber.onNext(null);
                    subscriber.onCompleted();
                  }

                  @Override public void onFail(String s) {
                    subscriber.onError(new Exception(s));
                  }
                });
              }
            });
          }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<Void>() {
          @Override public void onStart() {
            AppLogger.e("onStart ");
          }

          @Override public void onCompleted() {
            AppLogger.e("onComplete ");
          }

          @Override public void onError(Throwable e) {

            AppLogger.e("onError ");
          }

          @Override public void onNext(Void aVoid) {

            AppLogger.e("onNext ");
          }
        });
  }

  void task1(MyCallback callback) {
    try {
      Thread.sleep(1000);
      //callback.onSucc();
      callback.onFail("fail1");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  void task2(MyCallback callback) {
    try {
      Thread.sleep(1000);
      //callback.onSucc();
      callback.onFail("fail2");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public interface MyCallback {
    void onSucc();

    void onFail(String s);
  }
}
