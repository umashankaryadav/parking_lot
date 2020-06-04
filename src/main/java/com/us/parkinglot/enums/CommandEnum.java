package com.us.parkinglot.enums;

import java.util.HashMap;
import java.util.Map;

public enum CommandEnum {

  CREATE_PARKING_LOT("create_parking_lot"),
  PARK("park"),
  LEAVE("leave"),
  STATUS("status"),
  REG_NUMBER_FOR_CARS_WITH_COLOR("registration_numbers_for_cars_with_colour"),
  SLOTS_NUMBER_FOR_CARS_WITH_COLOR("slot_numbers_for_cars_with_colour"),
  SLOTS_NUMBER_FOR_REG_NUMBER("slot_number_for_registration_number"),
  EXIT("exit");

  private static final Map<String, CommandEnum> COMMAND_ENUM_MAP = new HashMap<>();
  static {
    for (CommandEnum command : values()) {
      COMMAND_ENUM_MAP.put(command.getCommandName(), command);
    }
  }

  private String command;

  CommandEnum(String commandName){
    this.command = commandName;
  }

  public static CommandEnum getCommandByName(String commandName) {

    return COMMAND_ENUM_MAP.get(commandName);
  }

  public String getCommandName() {
    return this.command;
  }
}
