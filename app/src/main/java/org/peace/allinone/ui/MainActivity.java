package org.peace.allinone.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.BindView;
import me.ele.commons.AppLogger;
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

    textView.setBackground(new MyDrawable(bm));
    view.setBackground(new MyDrawable(bm));
  }
}
