package com.us.parkinglot.model;

import com.us.parkinglot.enums.ColourEnum;

public class Car extends Vehicle {

  private String regNo;
  private ColourEnum colour;

  public Car(String regNo, ColourEnum colour) {
    super(regNo, colour);
  }

}
