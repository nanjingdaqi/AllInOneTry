package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.HashMap;
import java.util.Map;
import me.ele.commons.AppLogger;
import org.peace.allinone.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

  @InjectView(R.id.start_btn) Button mStartBtn;
  @InjectView(R.id.name) TextView name;
  @InjectView(R.id.age) TextView age;

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
      //tryVoid();
      //tryCommon();
      tryBodyPart();
    }
  }

  private void tryVoid() {
    Call<Void> voidCall = service.getVoid();
    voidCall.enqueue(new Callback<Void>() {
      @Override public void onResponse(Response<Void> response) {
        Void v = response.body();
        AppLogger.e("response: " + v);
        if (v == null) {
          AppLogger.e("response is null");
        }
      }

      @Override public void onFailure(Throwable t) {
        AppLogger.e("fail");
      }
    });
  }

  private void tryBuildFromTree() {
    User user = new User();
    user.age = 20;
    user.name = "Daqi";
    Bar bar = new Bar();
    bar.content = "Bar Content";
    Gson gson = new Gson();
    JsonObject jsonObject = gson.toJsonTree(user).getAsJsonObject();
    jsonObject.add("bar", gson.toJsonTree(bar));
    AppLogger.d(jsonObject.toString());
  }

  void tryCommon() {
    Call<User> userCall = service.getUser();
    userCall.enqueue(new Callback<User>() {
      @Override public void onResponse(Response<User> response) {
        User user = response.body();
        AppLogger.d("name: " + user.name);
      }

      @Override public void onFailure(Throwable throwable) {
        AppLogger.e("fail");
      }
    });
  }

  void tryBodyPart() {
    Map<String, Object> map = new HashMap<>();
    map.put("name", "Jiangxiaoyue");
    map.put("age", 27);
    Call<User> userCall = service.postUser(map);

    userCall.enqueue(new Callback<User>() {
      @Override public void onResponse(Response<User> response) {
        User user = response.body();
        AppLogger.d("name: " + user.name);
      }

      @Override public void onFailure(Throwable t) {

      }
    });
  }

  void tryRx() {
    Observable<User> o1 = service.getUserName();
    Observable<User> o2 = service.getUserAge();
    Observable.zip(o1, o2, new Func2<User, User, User>() {
      @Override public User call(User user, User user2) {
        AppLogger.e("name: " + user.name);
        AppLogger.e("age: " + user.age);
        return user;
      }
    })
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.newThread())
        .subscribe(new Subscriber<User>() {
          @Override public void onCompleted() {
            AppLogger.d("onComplete");
          }

          @Override public void onError(Throwable throwable) {
            AppLogger.e("error");
          }

          @Override public void onNext(User user) {
            name.setText(user.name);
            age.setText(String.valueOf(user.age));
          }
        });
  }
}
