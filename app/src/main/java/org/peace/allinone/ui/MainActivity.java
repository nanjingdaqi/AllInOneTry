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
      paint.setAntiAlias(true);
      canvas.drawColor(getResources().getColor(android.R.color.black));
      paint.setStyle(Paint.Style.STROKE);
      canvas.translate(canvas.getWidth()/2, 300); //将位置移动画纸的坐标点:150,150
      canvas.drawCircle(0, 0, 100, paint); //画圆圈

      //使用path绘制路径文字
      canvas.save();
      canvas.translate(-75, -75);
      Path path = new Path();
      path.addArc(new RectF(0,0,150,150), -180, 180);
      Paint citePaint = new Paint(paint);
      citePaint.setTextSize(14);
      citePaint.setStrokeWidth(1);
      canvas.drawTextOnPath("http://www.android777.com", path, 28, 0, citePaint);
      canvas.restore();

      Paint tmpPaint = new Paint(paint); //小刻度画笔对象
      tmpPaint.setStrokeWidth(1);

      float  y=100;
      int count = 60; //总刻度数

      for(int i=0 ; i <count ; i++){
        if(i%5 == 0){
          canvas.drawLine(0f, y, 0, y+12f, paint);
          canvas.drawText(String.valueOf(i/5+1), -4f, y+25f, tmpPaint);

        }else{
          canvas.drawLine(0f, y, 0f, y +5f, tmpPaint);
        }
        canvas.rotate(360/count,0f,0f); //旋转画纸
      }

      //绘制指针
      tmpPaint.setColor(Color.GRAY);
      tmpPaint.setStrokeWidth(4);
      canvas.drawCircle(0, 0, 7, tmpPaint);
      tmpPaint.setStyle(Paint.Style.FILL);
      tmpPaint.setColor(Color.YELLOW);
      canvas.drawCircle(0, 0, 5, tmpPaint);
      canvas.drawLine(0, 10, 0, -65, paint);
    }
  }
}
