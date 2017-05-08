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
import android.view.MotionEvent;
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

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

    MyFragment fg1 = new MyFragment();
    getFragmentManager().beginTransaction()
        .add(R.id.content, fg1)
        .addToBackStack("frag1")
        .commit();
  }

  @Override protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
  }

  @Override public void onBackPressed() {
    AppLogger.e("onBackPressed");
    getSupportFragmentManager().popBackStackImmediate();
    super.onBackPressed();
  }

  public void showDialogFrag() {
    MyFragment2 dlgFrag = new MyFragment2();
    dlgFrag.show(getFragmentManager(), "");
  }
}
