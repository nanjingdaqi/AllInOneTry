package org.peace.allinone.ui;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import java.net.URLEncoder;
import org.peace.allinone.R;

public class SubActivity extends AppCompatActivity {

  @InjectView(R.id.web_view)
  protected WebView webView;

  private final int START_SLEEP_URL = 3;

  private Handler handler = new Handler() {
    @Override
    public void handleMessage(Message msg) {
      switch (msg.what) {
        case START_SLEEP_URL:
          String url = (String) msg.obj;
          webView.loadUrl(url);
          break;
      }
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);

    try {
      init("http://mobi.ulucu.com/player/uluculive.html?m3u8Url=" +
      //    init("http://mobi.ulucu.com/m3u8.html?m3u8Url=" +
          URLEncoder.encode("http://139.224.32.32:9110/real.m3u8?channel_idx=1&device_id" +
              "=Ub0000000542866896QB&duration=86400&from_ip=182.254.136.157&" +
              "is_shared=1&rate=700&user_name=1000&ver=&token=CvYMQQOViAnUcmhk2" +
              "YAnZZ8vZOgV7Q4W_8Mm2A3ioYRTJH2qTHB5DewlLqYoDSyl-m5WLsqTkIH-0" +
              "Jf2n-42VQ&v_width=0&v_height=0&valid_time=1466242952","utf-8"));
          //URLEncoder.encode(
          //    "http://139.224.32.32:9110/real.m3u8?channel_idx=1&device_id=Ub0000000542866896QB&duration=86400&from_ip=182.254.136.157&is_shared=1&rate=700&user_name=1000&ver=&token=CvYMQQOViAnUcmhk2YAnZZ8vZOgV7Q4W_8Mm2A3ioYRTJH2qTHB5DewlLqYoDSyl-m5WLsqTkIH-0Jf2n-42VQ&v_width=0&v_height=0&valid_time=1466236280",
          //    "utf-8"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void onResume() {
    super.onResume();
  }

  //  private void init(String url) {
  //    WebSettings webSettings = webView.getSettings();
  //    webSettings.setJavaScriptEnabled(true);
  //    webSettings.setDefaultTextEncodingName("GBK");
  //    webSettings.setAllowFileAccess(true);
  //    webSettings.setAllowFileAccess(true);
  //    webSettings.setAllowContentAccess(true);
  //    //WebView加载web资源
  //    webView.loadUrl(url);
  //    //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
  //    webView.setWebViewClient(new WebViewClient() {
  //      @Override
  //      public boolean shouldOverrideUrlLoading(WebView view, String url) {
  //        // TODO Auto-generated method stub
  //        //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
  //        view.loadUrl(url);
  //        return true;
  //      }
  //    });
  //  }

  private void init(String url) {
    WebSettings webSettings = webView.getSettings();
    webSettings.setJavaScriptEnabled(true);
    webSettings.setDefaultTextEncodingName("UTF-8");
    webSettings.setAllowFileAccess(true);
    webSettings.setAllowFileAccess(true);
    webSettings.setAllowContentAccess(true);

    //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
    webView.setWebViewClient(new WebViewClient() {
      @Override
      public boolean shouldOverrideUrlLoading(WebView view, String url) {
        // TODO Auto-generated method stub
        //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
        view.loadUrl(url);
        return true;
      }

      @TargetApi(Build.VERSION_CODES.M)
      public void onReceivedError(WebView view, WebResourceRequest request,
          WebResourceError error) {
        //错误提示
        Toast toast = Toast.makeText(getBaseContext(), "Oh no! " + error.getDescription(),
            Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 0);
        toast.show();
      }

      public void onPageStarted(WebView view, String url, Bitmap favicon) {
        Log.i("hb", "onPageStarted url=" + url);
      }

      public void onPageFinished(WebView view, String url) {
        Log.i("hb", "onPageFinished url=" + url);
      }
    });

    //WebView加载web资源
    webView.loadUrl(url);
    //Message msg = new Message();
    //msg.what = START_SLEEP_URL;
    //msg.obj = url;
    //handler.sendMessageDelayed(msg, 10);
  }
}

