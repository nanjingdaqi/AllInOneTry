package org.peace.allinone.ui;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by peacepassion on 15/10/17.
 */
public class UserRest {

  public static UserService getUserService() {
    Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.3.2:3000")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build();
    return retrofit.create(UserService.class);
  }
}
