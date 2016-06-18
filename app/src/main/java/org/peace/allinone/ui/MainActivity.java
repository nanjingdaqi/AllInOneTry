package org.peace.allinone.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  @InjectView(R.id.video) VideoView videoView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);
  }

  @OnClick({ R.id.start_btn }) public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.start_btn) {
      //String str = "http://devimages.apple.com/iphone/samples/bipbopgear1.html";
      //String str = "http://techslides.com/demos/sample-videos/small.mp4";
      String str = "http://mobi.ulucu.com/m3u8.html?m3u8Url=http://139.224.32.32:9110/real.m3u8?channel_idx=1&device_id=Ub0000000542866896QB&duration=3600&from_ip=180.168.39.42&is_shared=1&rate=700&user_name=1000&ver=&token=-L-izAjxbeqWjRXrYam57AY87boewetQp6eHPZjtvqorYMRtSmpG0yucKZBL7FWypW0gDMRo5jNgjUpYVFkqVA&v_width=0&v_height=0&valid_time=1466162095";
      str = "http%3a%2f%2f139.224.32.32%3a9110%2freal.m3u8%3fchannel_idx%3d1%26device_id%3dUb0000000542866896QB%26duration%3d3600%26from_ip%3d115.159.86.201%26is_shared%3d1%26rate%3d700%26user_name%3d1000%26ver%3d%26token%3dXGIQnP7fyuW40Ivb7cDNTJmhLh5hHCJJAKUA9_MNlWW-Dqf-CXedmXaaDLz-i--aeASQNCjWWjTakJH4hQ6sfg%26v_width%3d0%26v_height%3d0%26valid_time%3d1466227514";
      str = "http://139.224.32.32:9110/real.m3u8?channel_idx=1&device_id=Ub0000000542866896QB&duration=3600&from_ip=115.159.86.201&is_shared=1&rate=700&user_name=1000&ver=&token=XGIQnP7fyuW40Ivb7cDNTJmhLh5hHCJJAKUA9_MNlWW-Dqf-CXedmXaaDLz-i--aeASQNCjWWjTakJH4hQ6sfg&v_width=0&v_height=0&valid_time=1466227514";
      Uri uri = Uri.parse(str);
      videoView.setVideoURI(uri);
      videoView.start();
    }
  }
}
