package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.gson.annotations.SerializedName;
import me.ele.base.utils.AppLogger;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.start_btn) Button mStartBtn;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);
  }

  @OnClick({ R.id.start_btn }) public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.start_btn) {
      try {

        Target t = Target.OBJ;

        String name = t.name();
        SerializedName serializedName =
            Target.class.getField(name).getAnnotation(SerializedName.class);
        AppLogger.d("value: " + serializedName.value());
      } catch (NoSuchFieldException e) {
        e.printStackTrace();
      }
    }
  }

  enum Target {
    @SerializedName("test")
    OBJ
  }
}
