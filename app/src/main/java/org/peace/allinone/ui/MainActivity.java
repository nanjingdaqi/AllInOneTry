package org.peace.allinone.ui;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @InjectView(R.id.start_btn) Button mStartBtn;
  @InjectView(R.id.container) ViewGroup container;
  LayoutTransition layoutTransition;

  View btnView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);
    layoutTransition = new LayoutTransition();
    ObjectAnimator anim = ObjectAnimator.ofFloat(null, "rotationX", 90, 0).setDuration(2000);
    layoutTransition.setAnimator(LayoutTransition.APPEARING, anim);
    container.setLayoutTransition(layoutTransition);
  }

  @OnClick({ R.id.start_btn }) public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.start_btn) {
      btnView = LayoutInflater.from(this).inflate(R.layout.btn, null, false);
      container.addView(btnView, 0);
    }
  }
}
