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
      Call<User> userCall = service.getUser();
      userCall.enqueue(new Callback<User>() {
        @Override public void onResponse(Response<User> response, Retrofit retrofit) {
          User user = response.body();
          AppLogger.d("name: " + user.name);
        }

        @Override public void onFailure(Throwable throwable) {
          AppLogger.e("fail");
          AppLogger.e(throwable.getCause());
          AppLogger.e(throwable.getMessage());
          AppLogger.e(throwable.getLocalizedMessage());
        }
      });
    }
  }
}
