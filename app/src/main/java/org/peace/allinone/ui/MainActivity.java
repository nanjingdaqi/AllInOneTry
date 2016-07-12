package org.peace.allinone.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.ele.base.utils.AppLogger;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.icon) ImageView iconView;
  @BindView(R.id.tv) TextView textView;
  @BindView(R.id.view) View view;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

    Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
    AppLogger.e("bm w: " + bm.getWidth() + ", bm h: " + bm.getHeight());
  }
}
