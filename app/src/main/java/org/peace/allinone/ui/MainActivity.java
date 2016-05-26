package org.peace.allinone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import android.widget.FrameLayout;
import butterknife.ButterKnife;
import org.peace.allinone.R;
import me.ele.commons.AppLogger;
import org.peace.allinone.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.r_v) RecyclerView rv;
  @BindView(R.id.container) FrameLayout container;


  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    setupRV();
  }

  public void setupRV() {
    List<FoodCategory> foodCategories = new ArrayList<>(10);
    for (int i = 0; i < 10; i++) {
      FoodCategory data = new FoodCategory();
      data.name = "Category: " + i;
      List<String> lst = new ArrayList<>(10);
      for (int j = 0; j < 10; j++) {
        lst.add("Food: " + i + " - " + j);
      }
      data.foods = lst;
      foodCategories.add(data);
    }

    FoodListAdapter adapter = new FoodListAdapter(foodCategories);
    rv.setLayoutManager(new LinearLayoutManager(this));
    rv.setAdapter(adapter);
    rv.addItemDecoration(new PinnedItemDecoration(container));
  }

}
