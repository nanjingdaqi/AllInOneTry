package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.google.gson.annotations.SerializedName;
import me.ele.commons.AppLogger;
import org.peace.allinone.R;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

  @InjectView(R.id.start_btn) Button mStartBtn;

  UserService service;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);
    service = UserRest.getUserService();
  }

  @OnClick({ R.id.start_btn }) public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.start_btn) {
      tryRx();
    }
  }

  void tryCommon() {
    Call<User> userCall = service.getUser();
    userCall.enqueue(new Callback<User>() {
      @Override public void onResponse(Response<User> response, Retrofit retrofit) {
        User user = response.body();
        AppLogger.d("name: " + user.name);
      }

      @Override public void onFailure(Throwable throwable) {
        AppLogger.e("fail");
      }
    });
  }

  void tryRx() {
    Observable<User> o1 = service.getUserName();
    Observable<Integer> o2 = service.getUserAge();
    Observable.zip(o1, o2, new Func2<User, Integer, Void>() {
      @Override public Void call(User user, Integer user2) {
        AppLogger.e("name: " + user.name);
        AppLogger.e("age: " + user.age);
        return null;
      }
    })
        .observeOn(Schedulers.newThread())
        .subscribeOn(Schedulers.newThread())
        .subscribe(new Subscriber<Void>() {
          @Override public void onCompleted() {
            AppLogger.d("onComplete");
          }

          @Override public void onError(Throwable throwable) {
            AppLogger.e("error");
          }

          @Override public void onNext(Void aVoid) {
            AppLogger.d("onnext");
          }
        });
  }
}
