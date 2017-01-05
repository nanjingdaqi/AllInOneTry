package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.util.Map;
import me.ele.base.utils.AppLogger;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.start_btn) Button mStartBtn;

  static String jsonStr = "{\"key\": {\"k1\": {\"one\": 1, \"two\": 2}, \"k2\": {\"one\": 10, \"two\": 20}}}";

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);
  }

  @OnClick({ R.id.start_btn }) public void onClick(View v) {
    Gson gson = new Gson();
    M m = gson.fromJson(jsonStr, M.class);
    AppLogger.e("" + m);
  }

  enum Target {
    @SerializedName("test")
    OBJ
  }

  public static class M {
    @SerializedName("key") public Map<String, T> map;
  }

  public static class T {
    @SerializedName("one") public int one;
    @SerializedName("two") public int two;
  }
}
