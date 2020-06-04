package com.us.parkinglot.utils;

import com.us.parkinglot.enums.CommandEnum;
import com.us.parkinglot.exception.CustomException;

import java.util.HashMap;
import java.util.Map;

public class CommandUtil {

  private static Map<CommandEnum, Integer> commandParamCountMap;

  static {
    commandParamCountMap = new HashMap<>();
    commandParamCountMap.put(CommandEnum.CREATE_PARKING_LOT, 1);
    commandParamCountMap.put(CommandEnum.PARK, 2);
    commandParamCountMap.put(CommandEnum.LEAVE, 1);
    commandParamCountMap.put(CommandEnum.STATUS, 0);
    commandParamCountMap.put(CommandEnum.REG_NUMBER_FOR_CARS_WITH_COLOR, 1);
    commandParamCountMap.put(CommandEnum.SLOTS_NUMBER_FOR_CARS_WITH_COLOR, 1);
    commandParamCountMap.put(CommandEnum.SLOTS_NUMBER_FOR_REG_NUMBER, 1);
    commandParamCountMap.put(CommandEnum.EXIT, 0);
  }

  public static Integer getCommandParamCount(String command)
  {
    CommandEnum commandEnum = null;
    try {
      commandEnum = CommandEnum.getCommandByName(command);
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
      throw new CustomException("Invalid command: "+command);
    }

    return commandParamCountMap.get(commandEnum);
  }

  public static Map<CommandEnum, Integer> getAllCommands()
  {
    return commandParamCountMap;
  }

  public static void addCommand(String command, int paramCount)
  {
    try {
      CommandEnum commandEnum = CommandEnum.valueOf(command);
      commandParamCountMap.put(commandEnum, paramCount);
    } catch (Exception ex) {
      throw new CustomException("Invalid command: "+command);
    }

  }
}
