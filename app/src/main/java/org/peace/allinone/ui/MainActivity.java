package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.start_btn)
    Button mStartBtn;
    @BindView(R.id.frag_container)
    FrameLayout fragContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);
    }

    @OnClick({R.id.start_btn})
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.start_btn) {
            MyFragment frag = new MyFragment();
            MyFragment frag2 = new MyFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.frag_container, frag, "TAG").commitAllowingStateLoss();
//            getSupportFragmentManager().beginTransaction().add(R.id.frag_container, frag).commitAllowingStateLoss();
            getSupportFragmentManager().executePendingTransactions();
            Fragment target = getSupportFragmentManager().findFragmentByTag("TAG");
            AppLogger.d("target == null: " + (target == null));
        }
    }
}
