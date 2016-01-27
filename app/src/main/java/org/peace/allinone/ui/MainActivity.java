package org.peace.allinone.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.OnClick;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @InjectView(R.id.start_btn) Button mStartBtn;
  MyFragment myFragment;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);

    myFragment = (MyFragment) getSupportFragmentManager().findFragmentById(R.id.my_frag);
    myFragment.foo();
  }

  @OnClick({ R.id.start_btn }) public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.start_btn) {
    }
  }

  public static class MyFragment extends Fragment {

    @InjectView(R.id.vp) ViewPager vp;

    @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
      super.onCreateView(inflater, container, savedInstanceState);
      View root = inflater.inflate(R.layout.frag_my, container, false);
      ButterKnife.inject(this, root);
      return root;
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      FragmentPagerAdapter adapter = new FragmentPagerAdapter(getChildFragmentManager()) {
        @Override public Fragment getItem(int position) {
          return new SubFrag();
        }

        @Override public int getCount() {
          return 2;
        }
      };
      vp.setAdapter(adapter);
      AppLogger.w("after set adapter");
    }

    void foo() {
      AppLogger.w("myfrag foo");
    }
  }

  public static class SubFrag extends Fragment {

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {
      super.onCreateView(inflater, container, savedInstanceState);
      return inflater.inflate(R.layout.frag_sub, container, false);
    }

    @Override public void onAttach(Activity activity) {
      super.onAttach(activity);
      AppLogger.w("on attach activity");
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      AppLogger.w("on create");
    }

    void bar() {
      AppLogger.w("subfrag bar");
    }
  }
}
