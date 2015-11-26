package org.peace.allinone.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import me.ele.commons.AppLogger;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

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

    }
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
