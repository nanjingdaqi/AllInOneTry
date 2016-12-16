package org.peace.allinone.ui;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;
import org.parceler.ParcelProperty;

@Parcel(Parcel.Serialization.BEAN)
public class Person {

  String name;
  Job job;
  Hobby hobby;

  @Parcel
  public static class Job {
    String type;
  }

  @Parcel(Parcel.Serialization.BEAN)
  public static class Hobby {
    @ParcelProperty("name") String name;

    @ParcelConstructor
    public Hobby(@ParcelProperty("name") String name) {
      this.name = name;
    }
  }
}
