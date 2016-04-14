package net.wequick.example.small.app.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mine_main);

    handleToolbar();

    String pkgName = getPackageName();
    Log.e("app.min", "package name: " + pkgName);

    findViewById(R.id.bundle_start).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        //Toast.makeText(MainActivity.this, com.example.mysmall.lib.style.R.string.style_hello, Toast.LENGTH_SHORT).show();
        //Toast.makeText(MainActivity.this, "New source code string", Toast.LENGTH_SHORT).show();

      }
    });

    findViewById(R.id.upgrade).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        new WeiboApi(getApplication());
      }
    });
  }

  private void handleToolbar() {
    Toolbar toolbar = (Toolbar) findViewById(R.id.mine_toolbar);
    setSupportActionBar(toolbar);

    getSupportActionBar().setTitle("Great Small Plugin");
  }
}
