package net.wequick.example.small.app.mine;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import net.wequick.example.small.lib.utils.UIUtils;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mine_main);

    ButterKnife.inject(this);

    handleToolbar();

    String pkgName = getPackageName();
    Log.e("app.min", "package name: " + pkgName);

    findViewById(R.id.bundle_start).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        //Toast.makeText(MainActivity.this, com.example.mysmall.lib.style.R.string.style_hello, Toast.LENGTH_SHORT).show();
        //Toast.makeText(MainActivity.this, "New source code string", Toast.LENGTH_SHORT).show();

      }
    });

    ApplicationInfo appInfo = getApplicationInfo();
    Log.e("app.mine", "app info: " + appInfo);
    Application app = getApplication();
    Log.e("app.mine", "app: " + app);
    Context ctx = getApplicationContext();
    Log.e("app.mine", "app ctx: " + ctx);

    findViewById(R.id.upgrade).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        new WeiboApi(getApplication());
      }
    });
  }

  @OnClick(R.id.aar0) public void onClick() {
    UIUtils.alert(this, "Called by Mine module");
  }

  private void handleToolbar() {
    Toolbar toolbar = (Toolbar) findViewById(R.id.mine_toolbar);
    setSupportActionBar(toolbar);

    getSupportActionBar().setTitle("Great Small Plugin");
  }
}
