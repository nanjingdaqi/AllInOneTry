package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import org.peace.allinone.R;

/**
 * Created by daqi on 17-5-6.
 */

public class MyFragment3 extends MyFragment {

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    TextView tv = (TextView) view.findViewById(R.id.text);
    tv.setText("Frag 3");
  }
}
