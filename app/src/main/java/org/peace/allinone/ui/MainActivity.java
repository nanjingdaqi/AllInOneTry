package org.peace.allinone.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import me.ele.commons.AppLogger;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @InjectView(R.id.start_btn) Button mStartBtn;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);
  }

  @OnClick({ R.id.start_btn }) public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.start_btn) {

    }
  }

  public static class MyView extends View {

    Paint paint;

    public MyView(Context context) {
      super(context);
      init();
    }

    public MyView(Context context, AttributeSet attrs) {
      super(context, attrs);
      init();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
      init();
    }

    private void init() {
      paint = new Paint(Paint.ANTI_ALIAS_FLAG);
      paint.setColor(getResources().getColor(android.R.color.holo_red_light));
      paint.setStyle(Paint.Style.FILL);
    }

    @Override protected void onDraw(Canvas canvas) {
      super.onDraw(canvas);

      int x = getMeasuredWidth(), y = getMeasuredHeight();
      int r = 200;
      canvas.save();
      canvas.translate(x / 2, y / 2);
      canvas.restore();
      canvas.drawCircle(0, 0, r, paint);
      paint.setColor(getResources().getColor(android.R.color.holo_blue_light));
      canvas.drawCircle(x / 2, y / 2, r, paint);
    }
  }
}
