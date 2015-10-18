package org.peace.allinone.ui;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by peacepassion on 15/10/17.
 */
public interface UserService {

  @GET("/")
  Call<User> getUser();

}
