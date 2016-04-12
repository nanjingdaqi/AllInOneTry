package net.wequick.example.small.app.mine;

import android.app.Application;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

public class WeiboApi  {

  static {
    System.loadLibrary("weibosdkcore");
  }

  public static final String WEIBO_REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";
  public static final String SCOPE = "follow_app_official_microblog";
  private static final String LOGIN_FAILED = "微博登陆失败";

  private static final String UID = "uid";
  private static final String WEIBO_PACKAGE = "com.sina.weibo";
  private IWeiboShareAPI mWeiboShareAPI;
  private AuthInfo mWeiboAuth;
  private SsoHandler mSsoHandler;
  private Oauth2AccessToken mAccessToken;

  public WeiboApi(Application application) {
    // 创建微博实例
    String weiboAppId = "abc";
    mWeiboAuth = new AuthInfo(application, weiboAppId, WEIBO_REDIRECT_URL, SCOPE);
    mWeiboShareAPI = WeiboShareSDK.createWeiboAPI(application, weiboAppId);
    mWeiboShareAPI.registerApp();
  }
}
