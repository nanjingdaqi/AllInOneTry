package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setTitle("Customized Title");
        //toolbar.setSubtitle("Sub Title");
        //toolbar.setLogo(R.drawable.ic_launcher);
        //toolbar.setNavigationIcon(R.drawable.ic_menu);
        //toolbar.setNavigationContentDescription("Custom up description");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AppLogger.d("menu item is clicked: " + item.getTitle());
        return true;
    }

    @OnClick({R.id.start_btn})
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.start_btn) {

        }
    }
}
