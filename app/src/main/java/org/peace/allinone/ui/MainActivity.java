package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.example.peacepassion.library.DataHolder;
import com.example.peacepassion.library.WeixinTabLayout;
import org.peace.allinone.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.weixin_tab_layout)
    WeixinTabLayout weixinTabLayout;
    @InjectView(R.id.view_pager)
    ViewPager viewPager;
    Fragment[] fragments = {SampleFragment.newInstance(0), SampleFragment.newInstance(1), SampleFragment.newInstance(2), SampleFragment.newInstance(3)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        initViews();
    }

    private void initViews() {
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }
        };
        viewPager.setAdapter(adapter);
        List<DataHolder> dataHolders = new ArrayList<>();
        for (int i = 0; i < fragments.length; ++i) {
            dataHolders.add(new DataHolder(getResources().getDrawable(R.drawable.shopping_home_tab_personal),
                getResources().getDrawable(R.drawable.shopping_home_tab_personal_selected), "我"));
        }
        weixinTabLayout.setUpViewPager(viewPager, adapter, dataHolders);
    }

}
