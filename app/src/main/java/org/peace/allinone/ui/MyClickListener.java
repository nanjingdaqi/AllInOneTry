package org.peace.allinone.ui;

import android.view.View;

public class MyClickListener implements View.OnClickListener {

    public MainActivity activity;

    public MyClickListener(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View view) {
        activity.leak();
    }
}
