package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  private LinearLayout iconGrandParentView;

  private int[] icons = {
      R.drawable.font_setting_icon_1, R.drawable.font_setting_icon_2,
      R.drawable.font_setting_icon_3, R.drawable.font_setting_icon_4,
      R.drawable.font_setting_icon_5, R.drawable.font_setting_icon_6,
      R.drawable.font_setting_icon_7, R.drawable.font_setting_icon_8,
      R.drawable.font_setting_icon_9, R.drawable.font_setting_icon_10,
      R.drawable.font_setting_icon_11, R.drawable.font_setting_icon_12
  };

  private int[] texts = {
      R.string.app_name,
      R.string.app_name,
      R.string.app_name,
      R.string.app_name,
      R.string.app_name,
      R.string.app_name,
      R.string.app_name,
      R.string.app_name,
      R.string.app_name,
      R.string.app_name,
      R.string.app_name,
      R.string.app_name
  };

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    iconGrandParentView = (LinearLayout) findViewById(R.id.icon_grand_parent);
    for (int i = 0; i < 3; ++i) {
      LinearLayout iconParentView = (LinearLayout) iconGrandParentView.getChildAt(i);
      for (int j = 0; j < 4; ++j) {
        LinearLayout iconCell = (LinearLayout) iconParentView.getChildAt(j);
        ImageView iconView = (ImageView) iconCell.findViewById(R.id.icon);
        TextView textView = (TextView) iconCell.findViewById(R.id.text);
        iconView.setImageDrawable(getResources().getDrawable(icons[i * 4 + j]));
        textView.setText(getString(texts[i * 4 + j]));
      }
    }
  }
}
