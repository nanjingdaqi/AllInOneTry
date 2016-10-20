package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import java.util.ArrayList;
import java.util.List;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @InjectView(R.id.search_key_words) SearchKeyWordsView searchKeyWordsView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);

  }

  @OnClick(R.id.start_btn) public void onClick(View v) {
    List<String> keyWords = new ArrayList<>();
    for (int i = 0; i < 20; i++) {
      keyWords.add("foo");
    }
    searchKeyWordsView.setKeyWords(keyWords);
  }
}
