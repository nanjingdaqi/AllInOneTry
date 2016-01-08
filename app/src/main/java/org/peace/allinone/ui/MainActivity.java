package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import java.util.ArrayList;
import java.util.List;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @InjectView(R.id.start_btn) Button mStartBtn;
  @InjectView(R.id.icon_view) IconView iconView;
  List<CharacterIcon> icons = new ArrayList<>();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);

    for (int i = 0; i < 3; ++i) {
      icons.add(new CharacterIcon() {
        @Override public String getCharacter() {
          return "票";
        }

        @Override public int getBackgroundColor() {
          return getResources().getColor(android.R.color.holo_red_light);
        }
      });
    }

  }

  @OnClick({ R.id.start_btn }) public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.start_btn) {

      iconView.setIcons(icons);
    }
  }
}
