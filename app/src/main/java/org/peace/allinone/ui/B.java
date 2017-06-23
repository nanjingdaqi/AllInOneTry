package org.peace.allinone.ui;

import android.os.Bundle;

/**
 * Created by daqi on 17-6-21.
 */

public class B extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Sing.getInstance(this);
    }
}
