package org.peace.allinone.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.np)
  NumberPicker np;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

    np.setMaxValue(10);
    np.setMinValue(0);
  }

  @OnClick(R.id.start_btn) public void onClick(View v) {
      track();
      startActivity(new Intent(this, AccessibilityNodeProviderActivity.class));
  }

  public static void track() {
    Exception e = new Exception();
    String method = e.getStackTrace()[1].getMethodName();
    Log.d("peace", "calling: " + method);
  }
}
