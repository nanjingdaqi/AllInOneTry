package org.peace.allinone.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import org.peace.allinone.R;
import org.peace.debug.DebugActivity;


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
            startActivity(new Intent(this, DebugActivity.class));
        }
    }
}
