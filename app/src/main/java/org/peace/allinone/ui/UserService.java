package org.peace.allinone.ui;

import java.util.Map;
import retrofit2.Call;
import retrofit2.http.JSONBodyPart;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

  @POST("/user") Call<User> postUser(@JSONBodyPart Map<String, Object> map);

}
