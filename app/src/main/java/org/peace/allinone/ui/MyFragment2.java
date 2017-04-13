package org.peace.allinone.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.TextView;
import android.widget.Toast;
import me.ele.base.utils.AppLogger;
import org.peace.allinone.R;

/**
 * Created by peacepassion on 15/9/7.
 */
public class MyFragment2 extends DialogFragment implements View.OnClickListener{

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    AppLogger.e("onCreate");
  }

  @NonNull @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
    super.onCreateDialog(savedInstanceState);
    AppLogger.e("create");
    LayoutInflater inflater = LayoutInflater.from(getActivity());
    View view = inflater.inflate(R.layout.frag_dlg, null);
    //Checkable checkBox = (Checkable) view.findViewById(R.id.foo);
    //checkBox.setChecked(false);
    //((TextView) checkBox).setOnClickListener(this);
    //checkBox = (Checkable) view.findViewById(R.id.foo2);
    //checkBox.setChecked(true);
    //((TextView) checkBox).setOnClickListener(this);

    View cell = view.findViewById(R.id.cell1);
    cell.findViewById(R.id.foo).setOnClickListener(this);
    cell = view.findViewById(R.id.cell2);
    cell.findViewById(R.id.foo).setOnClickListener(this);

    return new AlertDialog.Builder(getActivity()).setView(view).create();
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    AppLogger.e("frag2 onSave");
  }

  @Override public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
    super.onViewStateRestored(savedInstanceState);
  }

  @Override public void onClick(View v) {
    ((Checkable) v).setChecked(!((Checkable) v).isChecked());
  }
}
