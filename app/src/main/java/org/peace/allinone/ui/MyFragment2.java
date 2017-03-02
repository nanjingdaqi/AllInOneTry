package org.peace.allinone.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.widget.Toast;
import me.ele.base.utils.AppLogger;

/**
 * Created by peacepassion on 15/9/7.
 */
public class MyFragment2 extends DialogFragment {

  @NonNull @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
    super.onCreateDialog(savedInstanceState);
    AppLogger.e("create");
    return new AlertDialog.Builder(getActivity()).setMessage("Hello").create();
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    AppLogger.e("frag2 onSave");
  }
}
