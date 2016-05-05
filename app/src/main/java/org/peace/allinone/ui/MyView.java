package org.peace.allinone.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import org.peace.allinone.R;

public class MyView extends LinearLayout {

  public MyView(Context context, AttributeSet attrs) {
    super(context, attrs);
    inflate(context, R.layout.sub, this);

    findViewById(R.id.sub_tv).setOnClickListener(new OnClickListener() {
      @Override public void onClick(View v) {
        Toast.makeText(context, "Find sub_tv", Toast.LENGTH_SHORT).show();
      }
    });
  }
}
