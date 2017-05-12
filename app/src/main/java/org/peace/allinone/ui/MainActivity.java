package org.peace.allinone.ui;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import me.ele.base.utils.AppLogger;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.img) ImageView imageView;

  Bitmap orgBitmap;
  Bitmap newBitmap;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

    try {
      BitmapDrawable bitmapDrawable =
          (BitmapDrawable) getPackageManager().getApplicationIcon("com.android.settings");

      imageView.setImageDrawable(bitmapDrawable);
      AppLogger.e("icon w: " + bitmapDrawable.getIntrinsicWidth() + ", h: " + bitmapDrawable.getIntrinsicHeight());
      AppLogger.e("icon byte size: " + bitmapDrawable.getBitmap().getByteCount());

      shrinkDrawable(bitmapDrawable);
      AppLogger.e("new icon w: " + bitmapDrawable.getIntrinsicWidth() + ", h: " + bitmapDrawable.getIntrinsicHeight());
      AppLogger.e("new icon byte size: " + bitmapDrawable.getBitmap().getByteCount());
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }
  }

  void shrinkDrawable(BitmapDrawable bitmapDrawable) {
    Matrix matrix = new Matrix();
    matrix.setScale(0.5f, 0.5f);
    orgBitmap = bitmapDrawable.getBitmap();
    newBitmap = Bitmap.createBitmap(orgBitmap, 0, 0, orgBitmap.getWidth(), orgBitmap.getHeight(), matrix, true);
    try {
      Method method = BitmapDrawable.class.getDeclaredMethod("setBitmap", Bitmap.class);
      method.setAccessible(true);
      method.invoke(bitmapDrawable, newBitmap);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
