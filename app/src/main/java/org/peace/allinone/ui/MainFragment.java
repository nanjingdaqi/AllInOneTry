package org.peace.allinone.ui;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import org.peace.allinone.R;

/**
 * Created by daqi on 17-2-26.
 */

public class MainFragment extends PreferenceFragment {

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    addPreferencesFromResource(R.xml.main);
  }
}
