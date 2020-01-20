package assignment.gojek.parkinglot.processor;

import assignment.gojek.parkinglot.constants.Constants;
import assignment.gojek.parkinglot.enums.ColourEnum;
import assignment.gojek.parkinglot.enums.CommandEnum;
import assignment.gojek.parkinglot.exception.CustomException;
import assignment.gojek.parkinglot.model.Car;
import assignment.gojek.parkinglot.service.ParkingLotService;
import assignment.gojek.parkinglot.service.impl.ParkingLotServiceImpl;

public class CommandProcessor implements Processor {

  private ParkingLotService parkingLotService;

  public CommandProcessor() {
    this.parkingLotService = new ParkingLotServiceImpl();
  }

  @Override
  public void execute(String command) throws CustomException {
    int level = 1;
    String[] inputs = command.split(" ");
    String baseCommand = inputs[0];
    CommandEnum commandEnum = CommandEnum.getCommandByName(baseCommand);
    switch (commandEnum)
    {
      case CREATE_PARKING_LOT:
        try
        {
          int numberOfSlots = Integer.parseInt(inputs[1]);
          parkingLotService.createParkingLot(numberOfSlots);
        } catch (NumberFormatException ex) {
          throw new CustomException(Constants.INVALID_SLOT_ARGUMENT + inputs[1]);
        }
        break;
      case PARK:
        try {
          ColourEnum colourEnum = ColourEnum.getColourByName(inputs[2]);
          if (colourEnum != null) {
            parkingLotService.park(level, new Car(inputs[1], colourEnum));
          } else {
            throw new CustomException(Constants.INVALID_COLOUR_ARGUMENT + inputs[2]);
          }
        } catch (Exception ex) {
          throw new CustomException(Constants.INVALID_COLOUR_ARGUMENT + inputs[2]);
        }
        break;
      case LEAVE:
        try
        {
          int slotNumber = Integer.parseInt(inputs[1]);
          parkingLotService.leavePark(level, slotNumber);
        } catch (NumberFormatException e) {
          throw new CustomException(Constants.INVALID_SLOT_ARGUMENT + inputs[1]);
        }
        break;
      case STATUS:
        parkingLotService.getStatus(level);
        break;
      case REG_NUMBER_FOR_CARS_WITH_COLOR:
        parkingLotService.getRegNumbersForColor(level, inputs[1]);
        break;
      case SLOTS_NUMBER_FOR_CARS_WITH_COLOR:
        parkingLotService.getSlotNumbersFromColor(level, inputs[1]);
        break;
      case SLOTS_NUMBER_FOR_REG_NUMBER:
        parkingLotService.getSlotNoFromRegNo(level, inputs[1]);
        break;
      case EXIT:
        System.exit(0);
      default:
        System.out.println(Constants.INVALID_COMMAND);
        break;
    }
  }
}
