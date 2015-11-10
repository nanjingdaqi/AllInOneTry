package org.peace.allinone.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import me.ele.commons.AppLogger;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @InjectView(R.id.start_btn) Button mStartBtn;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);

    List<NameValuePair> params = new ArrayList<>();
    params.add(new BasicNameValuePair("k1", "v1"));
    params.add(new BasicNameValuePair("k2", "中文"));
    params.add(new BasicNameValuePair("k3", "~!@#$%^&"));
    try {
      URI uri = URIUtils.createURI("http", "localhost", 3000, "foo/bar",
          URLEncodedUtils.format(params, "utf-8"), "chapter-1");
      AppLogger.d("uri: " + uri.toString());
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }

    Uri uri2 = new Uri.Builder().scheme("scheme")
        .authority("authority")
        .appendPath("foot/bar")
        .appendQueryParameter("k1", "v1")
        .appendQueryParameter("k2", "中文")
        .appendQueryParameter("k3", "~!@#$%^&")
        .fragment("chapter-1")
        .opaquePart("opaquePart").build();
    AppLogger.d("uri2: " + uri2.toString());

    Uri uri3 = new Uri.Builder().scheme("scheme")
        .authority("authority")
        .appendPath("foot/bar")
        .appendQueryParameter("k1", "v1")
        .appendQueryParameter("k2", "中文")
        .appendQueryParameter("k3", "~!@#$%^&")
        .fragment("chapter-1")
        .build();
    AppLogger.d("uri3: " + uri3.toString());

    Uri uri4 = new Uri.Builder().scheme("scheme")
        .authority("authority")
        .appendPath("foo")
        .appendPath("bar")
        .appendQueryParameter("k1", "v1")
        .appendQueryParameter("k2", "中文")
        .appendQueryParameter("k3", "~!@#$%^&")
        .fragment("chapter-1")
        .build();
    AppLogger.d("uri4: " + uri4.toString());
  }

  @OnClick({ R.id.start_btn }) public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.start_btn) {

    }
  }
}
