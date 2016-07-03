package org.peace.allinone.ui;

import android.graphics.Color;
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
  @InjectView(R.id.icon) FoodIconView foodIconView1;
  @InjectView(R.id.icon2) FoodIconView foodIconView2;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);

    foodIconView1.iconBuilder(
        FoodIconView.iconBuilder("近铁城市广场")
            .radius(0)
            .textSize(200)
            .padding(6)
            .bgFillColor(Color.GREEN)
            .strokeWidth(10)
            .bgStrokeColor(Color.DKGRAY));

    List<FoodIconView.IconBuilder> iconBuilders = new ArrayList<>();
    for (int i = 0; i < 5; ++i) {
      iconBuilders.add(FoodIconView.iconBuilder("招牌")
          .bgStrokeColor(Color.RED)
          .strokeWidth(1)
          .textSize(100)
          .bold(true)
          .maxRadius(true)
          .textColor(Color.BLACK));
    }
    foodIconView2.iconBuilders(iconBuilders);
  }

  @OnClick({ R.id.start_btn }) public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.start_btn) {

    }
  }
}
