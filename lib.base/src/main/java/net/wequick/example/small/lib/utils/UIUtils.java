package net.wequick.example.small.lib.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

public class UIUtils {

  public static void alert(final Context context, String tips) {
    new MaterialDialog.Builder(context)
        .title("base dialog")
        .content(tips)
        .positiveText("OK")
        .onPositive(
            new MaterialDialog.SingleButtonCallback() {
              @Override
              public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                Toast.makeText(context, "positive clicked", Toast.LENGTH_SHORT).show();
              }
            }).show();
  }
}
