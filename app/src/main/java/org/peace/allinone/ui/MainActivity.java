package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.emotion) protected EmotionView emotionView;
  @BindView(R.id.address) protected AddressView addressView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);
    emotionView.showPromotion();
    addressView.setAddress("近铁城市广场");
  }

  @OnClick(R.id.start_btn) public void onClick(View v) {

  }
}
