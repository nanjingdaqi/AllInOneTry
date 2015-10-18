package org.peace.allinone.ui;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by peacepassion on 15/10/17.
 */
public class UserRest {

  public static UserService getUserService() {
    Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.3.2:3003")
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    return retrofit.create(UserService.class);
  }
}
