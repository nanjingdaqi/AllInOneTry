package org.peace.allinone.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import org.peace.allinone.R;
import org.peace.allinone.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

  Button mStartBtn;

  DataMgr dataMgr;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    binding.setDataMgr(dataMgr);
  }

  public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.start_btn) {
    }
  }
}
