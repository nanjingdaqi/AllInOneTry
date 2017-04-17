package org.peace.allinone.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.ele.base.utils.AppLogger;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.start_btn) Button mStartBtn;
  MyFragment myFragment;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

    myFragment = (MyFragment) getSupportFragmentManager().findFragmentById(R.id.my_frag);
  }

  @OnClick({ R.id.start_btn }) public void onClick(View v) {
    Toast.makeText(this, "show later", Toast.LENGTH_LONG).show();
    v.postDelayed(new Runnable() {
      @Override public void run() {
        showDialogFrag();
      }
    }, 5 * 1000);
  }

  public void showDialogFrag() {
    MyFragment2 dlgFrag = new MyFragment2();
    dlgFrag.show(getFragmentManager(), "");
  }
}
