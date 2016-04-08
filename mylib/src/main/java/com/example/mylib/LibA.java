package com.example.mylib;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton  public class LibA {

  @Inject public LibB b;

}
