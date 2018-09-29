package org.peace.allinone.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.LinkedList;
import java.util.List;

import me.ele.base.utils.DimenUtil;
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
        Dialog dlg = new Dialog(this);
        dlg.setContentView(R.layout.dlg);
        Window w = dlg.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.flags = lp.flags | WindowManager.LayoutParams.FLAG_DIM_BEHIND;
//      lp.flags = lp.flags & ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lp.dimAmount = .8f;

        w.setAttributes(lp);
        dlg.show();
    }
}

