package org.peace.allinone.ui;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import org.peace.allinone.R;

/**
 * Created by daqi on 17-2-26.
 */

public class MainFragment extends PreferenceFragment {

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    addPreferencesFromResource(R.xml.main);
  }

  @Override
  public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
    if (preference.getKey().equals("check_box")) {
      preferenceScreen.removePreference(findPreference("my_pref"));
      return true;
    }
    return super.onPreferenceTreeClick(preferenceScreen, preference);
  }
}
