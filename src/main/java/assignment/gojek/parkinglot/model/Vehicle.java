package assignment.gojek.parkinglot.model;

import assignment.gojek.parkinglot.enums.ColourEnum;

public abstract class Vehicle {

  private String regNo;
  private ColourEnum colour;

  public Vehicle(String regNo, ColourEnum colour) {
    this.regNo = regNo;
    this.colour = colour;
  }

  @Override
  public String toString() {
    return "Vehicle{" + "regNo='" + regNo + '\'' + ", colour=" + colour.name() + '}';
  }

  public String getRegNo() {
    return regNo;
  }

  public void setRegNo(String regNo) {
    this.regNo = regNo;
  }

  public ColourEnum getColour() {
    return colour;
  }

  public void setColour(ColourEnum colour) {
    this.colour = colour;
  }
}
