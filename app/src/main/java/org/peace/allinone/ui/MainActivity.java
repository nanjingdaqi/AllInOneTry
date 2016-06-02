package org.peace.allinone.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.OnClick;
import java.util.ArrayList;
import java.util.List;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.food_icon) FoodIconView foodIconView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);
  }

  @OnClick({ R.id.start_btn }) public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.start_btn) {
      List<FoodIconView.Icon> icons = new ArrayList<>(2);
      icons.add(new FoodIconView.Icon() {
        @Override public String text() {
          return "特";
        }

        @Override public int color() {
          return Color.BLACK;
        }
      });
      icons.add(new FoodIconView.Icon() {
        @Override public String text() {
          return "新蔡";
        }

        @Override public int color() {
          return Color.RED;
        }
      });
      foodIconView.setIcons(icons);
    }
  }
}
