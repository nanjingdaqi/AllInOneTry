package org.peace.allinone.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.view.ViewClickEvent;
import me.ele.commons.AppLogger;
import org.peace.allinone.R;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

  @InjectView(R.id.start) Button btn;
  @InjectView(android.R.id.content) View cv;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);

    Dialog dlg = new Dialog(this);
    Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();

    RxView.clickEvents(btn).subscribe(viewClickEvent -> {
      WindowManager wm = getWindowManager();
      wm.removeView(cv);
    });

  }
}
