package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.example.peacepassion.library.WeixinTabLayout;
import org.peace.allinone.R;


public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.start_btn)
    Button mStartBtn;

    @InjectView(R.id.weixin_tab_layout)
    WeixinTabLayout weixinTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        weixinTabLayout.addUnit(getResources().getDrawable(R.drawable.shopping_home_tab_take_out), "外卖");
        weixinTabLayout.addUnit(getResources().getDrawable(R.drawable.shopping_home_tab_order), "订单");
    }

    @OnClick({R.id.start_btn})
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.start_btn) {

        }
    }
}
