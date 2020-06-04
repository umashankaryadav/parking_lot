package com.us.parkinglot.processor;

import com.us.parkinglot.constants.Constants;
import com.us.parkinglot.enums.ColourEnum;
import com.us.parkinglot.enums.CommandEnum;
import com.us.parkinglot.exception.CustomException;
import com.us.parkinglot.model.Car;
import com.us.parkinglot.service.ParkingLotService;
import com.us.parkinglot.service.impl.ParkingLotServiceImpl;

public class CommandProcessor implements Processor {

  private ParkingLotService parkingLotService;

  public CommandProcessor() {
    this.parkingLotService = new ParkingLotServiceImpl();
  }

  @Override
  public void execute(String command) throws CustomException {
    int level = 1; //Assuming single floor parking as per problem statement
    String[] inputs = command.split(" ");
    String baseCommand = inputs[0];
    CommandEnum commandEnum = CommandEnum.getCommandByName(baseCommand);
    switch (commandEnum)
    {
      case CREATE_PARKING_LOT:
        try
        {
          int numberOfSlots = Integer.parseInt(inputs[1]);
          parkingLotService.createParkingLot(level, numberOfSlots);
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
