package org.peace.allinone.ui;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;

/**
 * Created by peacepassion on 15/10/17.
 */
public class UserRest {

  public static UserService getUserService() {
    Retrofit retrofit = new Retrofit.Builder().baseUrl("http://localhost:3000")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build();
    return retrofit.create(UserService.class);
  }
}
