package assignment.gojek;

import assignment.gojek.parkinglot.constants.Constants;
import assignment.gojek.parkinglot.exception.CustomException;
import assignment.gojek.parkinglot.processor.Processor;
import assignment.gojek.parkinglot.processor.CommandProcessor;

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
      if (args.length == Constants.ARGS_LENGTH_FOR_FILE_INPUT) {
        File inputFile = new File(args[0]);
        bufferReader = new BufferedReader(new FileReader(inputFile));
        String command = bufferReader.readLine();
        while (command != null) {
          command = command.trim();
          if (processor.validate(command)) {
            processor.execute(command);
          } else {
            throw new CustomException(Constants.INVALID_COMMAND + command);
          }
        }
      } else if (args.length == Constants.ARGS_LENGTH_FOR_INTERACTIVE) {
        bufferReader = new BufferedReader(new InputStreamReader(System.in));
        String command = bufferReader.readLine().trim();
        System.out.println(command);
        if (processor.validate(command)) {
          processor.execute(command);
        } else {
          throw new CustomException(Constants.INVALID_COMMAND + command);
        }
      } else {
        throw new CustomException(Constants.INVALID_RUN_COMMAND_ARGS);
      }
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
  }
}
