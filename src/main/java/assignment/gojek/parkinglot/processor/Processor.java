package assignment.gojek.parkinglot.processor;

import assignment.gojek.parkinglot.exception.CustomException;
import assignment.gojek.parkinglot.utils.CommandUtil;

public interface Processor {

  default boolean validate(String inputCommand) {
    boolean valid = false;
    String[] inputs = inputCommand.split(" ");
    if (inputs.length > 0) {
      int paramCount = CommandUtil.getCommandParamCount(inputs[0]);
      if ((inputs.length - 1) == paramCount) {
        valid = true;
      }
    }

    return valid;
  }

  void execute(String command) throws CustomException;
}
