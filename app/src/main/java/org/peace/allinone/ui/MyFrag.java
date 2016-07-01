package org.peace.allinone.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.InjectView;
import org.peace.allinone.R;

public class MyFrag extends Fragment {

  public static int INDEX = 0;

  @InjectView(R.id.toolbar) Toolbar toolbar;

  @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.frag_main, container, false);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.inject(this, view);

    ViewCompat.setOnApplyWindowInsetsListener(toolbar, new OnApplyWindowInsetsListener() {
      @Override public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
        log("inset top: " + insets.getSystemWindowInsetTop());
        return insets.consumeSystemWindowInsets();
      }
    });

    int color;
    if (INDEX == 0) {
      color = Color.YELLOW;
    } else if (INDEX == 1) {
      color = Color.BLACK;
    } else {
      color = Color.BLUE;
    }
    toolbar.setTitle("Fragment " + INDEX);
    toolbar.setBackgroundColor(color);
    Log.e("MyFrag", "toolbar padding: " + toolbar.getPaddingTop());
    INDEX++;
  }

  public static void log(String msg) {
    Log.e("MyFrag", msg);
  }
}
