package com.us.parkinglot.enums;

import java.util.HashMap;
import java.util.Map;

public enum ColourEnum {
  WHITE("White"),
  BLACK("Black"),
  RED("Red"),
  BLUE("Blue");

  private static final Map<String, ColourEnum> COLOUR_ENUM_MAP = new HashMap<>();
  static {
    for (ColourEnum colourEnum : values()) {
      COLOUR_ENUM_MAP.put(colourEnum.getColourName(), colourEnum);
    }
  }

  private String colour;

  ColourEnum(String colour) {
    this.colour = colour;
  }

  public static ColourEnum getColourByName(String colourName) {

    return COLOUR_ENUM_MAP.get(colourName);
  }

  public String getColourName() {
    return this.colour;
  }
}
