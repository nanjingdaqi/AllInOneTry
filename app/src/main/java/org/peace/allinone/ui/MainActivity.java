package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.start_btn) Button mStartBtn;

  @BindView(R.id.view_pager) ViewPager viewPager;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

    setUpViewPager();
  }

  private void setUpViewPager() {
    FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
      @Override public Fragment getItem(int position) {
        return new MyFrag();
      }

      @Override public int getCount() {
        return 2;
      }
    };
    viewPager.setAdapter(adapter);
  }

  @OnClick(R.id.start_btn) public void onClick(View v) {

  }

  public static class MyFrag extends Fragment {

    @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
      View root = inflater.inflate(R.layout.myfrag_layout, container, false);
      return root;
    }
  }
}
