package assignment.gojek.parkinglot.model;

import assignment.gojek.parkinglot.enums.ColourEnum;

public class Car extends Vehicle {

  private String regNo;
  private ColourEnum colour;

  public Car(String regNo, ColourEnum colour) {
    super(regNo, colour);
  }

}
