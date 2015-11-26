package org.peace.allinone.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import me.ele.commons.AppLogger;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @InjectView(R.id.image_view) ImageView imageView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);
  }

  @OnClick({ R.id.scan_btn, R.id.generate }) public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.scan_btn) {
      startScan();
    }
    if (id == R.id.generate) {
      imageView.setImageBitmap(generateQRBitmap("Hello World"));
    }
  }

  private Bitmap generateQRBitmap(String content) {
    BitMatrix result = null;
    try {
      result =
          new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, imageView.getMeasuredWidth(),
              imageView.getMeasuredHeight(), null);
    } catch (WriterException e) {
      e.printStackTrace();
    }
    int black = createPixel(0, 0, 0, 255), white = createPixel(255, 255, 255, 255);
    int w = result.getWidth(), h = result.getHeight();
    int[] pxs = new int[w * h];
    for (int y = 0; y < h; ++y) {
      int offset = y * w;
      for (int x = 0; x < w; ++x) {
        pxs[x + offset] = result.get(x, y) ? black : white;
      }
    }
    Bitmap bp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
    bp.setPixels(pxs, 0, w, 0, 0, w, h);
    return bp;
  }

  int createPixel(int r, int g, int b, int a) {
    return (a<<24) | (r<<16) | (g<<8) | b;
  }

  private void startScan() {
    IntentIntegrator integrator = new IntentIntegrator(this);
    integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
    integrator.initiateScan();
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
    if (result == null) {
      AppLogger.e("result is null");
      return;
    }
    String content = result.getContents();
    if (TextUtils.isEmpty(content)) {
      AppLogger.e("content is null");
      return;
    }
    AppLogger.d("scan result: " + content);
  }
}
