package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.facebook.rebound.BaseSpringSystem;
import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringListener;
import com.facebook.rebound.SpringSystem;
import com.facebook.rebound.SpringUtil;
import java.lang.annotation.Target;
import me.ele.commons.AppLogger;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @InjectView(R.id.start_btn) Button mStartBtn;
  @InjectView(R.id.target) View target;

  Spring spring;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);
    BaseSpringSystem springSystem = SpringSystem.create();
    spring = springSystem.createSpring();

    SpringConfig springConfig = new SpringConfig(100, 20);
    spring.setEndValue(1).setSpringConfig(springConfig);
  }

  @OnClick({ R.id.start_btn }) public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.start_btn) {
      spring.addListener(new SpringListener());
      spring.setEndValue(0);
    }
  }

  class SpringListener extends SimpleSpringListener {
    @Override public void onSpringUpdate(Spring spring) {
      float v = (float) spring.getCurrentValue();
      AppLogger.d("value: " + v);
      float mappedValue =
          (float) SpringUtil.mapValueFromRangeToRange(spring.getCurrentValue(), 0, 1, 0,
              target.getMeasuredWidth());
      target.setTranslationX(mappedValue);
    }
  }
}
