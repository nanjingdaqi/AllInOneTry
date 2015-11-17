package org.peace.allinone.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.start_btn)
    public void onClick(View v) {
        int id = v.getId();
        new MaterialDialog.Builder(this).progress(true, 100)
                .content("Loading...")
                .theme(Theme.DARK)
                .show();
    }

    public static class MyDlg extends Dialog {
        public MyDlg(Context context) {
            super(context, R.style.ProcessDialog);
            setContentView(R.layout.layout_dlg);
        }
    }
}
