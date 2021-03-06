package com.us.parkinglot.service.impl;

import com.us.parkinglot.constants.Constants;
import com.us.parkinglot.data.ParkingDataManager;
import com.us.parkinglot.data.impl.InMemoryParkingDataManager;
import com.us.parkinglot.data.impl.ParkingStrategyManagerImpl;
import com.us.parkinglot.exception.CustomException;
import com.us.parkinglot.model.Vehicle;
import com.us.parkinglot.service.ParkingLotService;

import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

public class ParkingLotServiceImpl implements ParkingLotService {

  private ParkingDataManager<Vehicle> dataManager = null;

  @Override
  public void createParkingLot(int level, int numberOfSlots) {
    if (dataManager != null)
      throw new CustomException(Constants.PARKING_LOT_ALREADY_EXIST);

    this.dataManager = InMemoryParkingDataManager.createParkingLot(level, numberOfSlots, new ParkingStrategyManagerImpl());
    System.out.println("Created parking lot with " + numberOfSlots + " slots");
  }

  @Override
  public Optional<Integer> park(int level, Vehicle vehicle) {
    checkIfParkingLotExist();
    Optional<Integer> value = Optional.empty();
    value = Optional.of(dataManager.parkCar(vehicle));
    if (value.get() == -1)
      System.out.println("Sorry, parking lot is full");
    else
      System.out.println("Allocated slot number: " + value.get());

    return value;
  }

  @Override
  public void leavePark(int level, int slotNumber) {
    checkIfParkingLotExist();
    if (dataManager.leaveCar(slotNumber))
      System.out.println("Slot number " + slotNumber + " is free");
    else
      System.out.println("Slot number is Empty Already.");
  }

  @Override
  public void getStatus(int level) {
    checkIfParkingLotExist();
    System.out.println("Slot No.\tRegistration No\t\tColor");
    List<String> statusList = dataManager.getStatus();
    if (statusList.size() == 0)
      System.out.println("Sorry, parking lot is empty.");
    else
    {
      for (String statusSting : statusList)
      {
        System.out.println(statusSting);
      }
    }
  }

  @Override
  public void getRegNumbersForColor(int level, String color) {
    List<String> registrationList = dataManager.getRegNumberForColor(color);
    if (registrationList.size() == 0)
      System.out.println("Not Found");
    else
      System.out.println(String.join(",", registrationList));
  }

  @Override
  public void getSlotNumbersFromColor(int level, String colour) {
    List<Integer> slotList = dataManager.getSlotNumbersFromColor(colour);
    if (slotList.size() == 0)
      System.out.println("Not Found");

    StringJoiner joiner = new StringJoiner(",");
    for (Integer slot : slotList)
    {
      joiner.add(slot + "");
    }
    System.out.println(joiner.toString());
  }

  @Override
  public int getSlotNoFromRegNo(int level, String regNo) {
    int slotNo = dataManager.getSlotNoFromRegistrationNo(regNo);
    System.out.println(slotNo != -1 ? slotNo : "Not Found");

    return slotNo;
  }

  private void checkIfParkingLotExist(){
    if (dataManager == null)
    {
      throw new CustomException(Constants.PARKING_LOT_DOESNOT_EXIST);
    }
  }
}
