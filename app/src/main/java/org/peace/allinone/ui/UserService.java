package org.peace.allinone.ui;

import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by peacepassion on 15/10/17.
 */
public interface UserService {

  @GET("/") Call<User> getUser();

  @GET("/")
  Observable<User> getUserName();

  @GET("/error")
  Observable<User> getUserAge();

  @GET("/")
  Call<Void> getVoid();

}
