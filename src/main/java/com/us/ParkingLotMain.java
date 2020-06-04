package com.us;

import com.us.parkinglot.constants.Constants;
import com.us.parkinglot.exception.CustomException;
import com.us.parkinglot.processor.Processor;
import com.us.parkinglot.processor.CommandProcessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;

/**
 * Parking lot main class
 */
public class ParkingLotMain {

  public static void main(String[] args) {

    Processor processor = new CommandProcessor();
    BufferedReader bufferReader = null;
    try {
      String command = null;
      switch (args.length) {
        case Constants.ARGS_LENGTH_FOR_FILE_INPUT :
          File inputFile = new File(args[0]);
          bufferReader = new BufferedReader(new FileReader(inputFile));
          //command = bufferReader.readLine();
          while ((command = bufferReader.readLine()) != null) {
            command = command.trim();
            if (processor.validate(command)) {
              processor.execute(command);
            } else {
              throw new CustomException(Constants.INVALID_COMMAND + command);
            }
          }
          break;
        case Constants.ARGS_LENGTH_FOR_INTERACTIVE :
          while (true)
          {
            System.out.print("$ ");
            bufferReader = new BufferedReader(new InputStreamReader(System.in));
            command = bufferReader.readLine().trim();
            if (processor.validate(command)) {
              processor.execute(command);
            } else {
              throw new CustomException(Constants.INVALID_COMMAND + command);
            }
          }
        default:
          throw new CustomException(Constants.INVALID_RUN_COMMAND_ARGS);
      }
    } catch (Exception ex) {
      //System.out.println(Constants.INVALID_RUN_COMMAND_ARGS);
    }
  }
}
