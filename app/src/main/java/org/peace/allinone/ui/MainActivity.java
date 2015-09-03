package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import me.ele.commons.AppLogger;
import org.peace.allinone.R;


public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.start_btn)
    Button mStartBtn;

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
            mStartBtn.setVisibility(View.GONE);
            if (mStartBtn.getVisibility() == View.GONE) {
                AppLogger.d("btn is gone");
            } else if (mStartBtn.getVisibility() == View.VISIBLE) {
                AppLogger.d("btn is visible");
            }
        }
    }
}
