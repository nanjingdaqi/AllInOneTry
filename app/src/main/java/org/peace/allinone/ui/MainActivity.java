package org.peace.allinone.ui;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.List;
import org.peace.allinone.R;

public class MainActivity extends PreferenceActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override public void onBuildHeaders(List<Header> target) {
    super.onBuildHeaders(target);
    loadHeadersFromResource(R.xml.header, target);
  }

  @Override protected boolean isValidFragment(String fragmentName) {
    return true;
  }
}
