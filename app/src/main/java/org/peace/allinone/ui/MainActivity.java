package org.peace.allinone.ui;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.list_view) ListView listView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);
  }

  @OnClick(R.id.start_btn) public void onClick(View v) {

    String[] cols = { UserDictionary.Words._ID, UserDictionary.Words.WORD, UserDictionary.Words.LOCALE };
    Cursor cursor =
        getContentResolver().query(UserDictionary.Words.CONTENT_URI, cols, null, null, null);
    String[] cols2 = {UserDictionary.Words.WORD, UserDictionary.Words.LOCALE };
    int[] ids = { R.id.word, R.id.local };
    SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.layout_cell, cursor, cols2, ids);
    listView.setAdapter(adapter);
  }
}
