package org.peace.allinone.ui;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import butterknife.ButterKnife;
import butterknife.BindView;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.icon) ImageView iconView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
    Bitmap newBitmap =
        Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
    Canvas canvas = new Canvas(newBitmap);
    Paint paint = new Paint();
    float[] src = new float[] {
        0.5f, 0, 0, 0, 0,
        0, 0.5f, 0, 0, 0,
        0, 0, 0.5f, 0, 0,
        0, 0, 0, 1, 0
    };
    ColorMatrix cm = new ColorMatrix(src);
    paint.setColorFilter(new ColorMatrixColorFilter(cm));
    canvas.drawBitmap(bitmap, new Matrix(), paint);
    iconView.setImageBitmap(newBitmap);
  }
}
