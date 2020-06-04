package com.us.parkinglot.data;

import com.us.parkinglot.model.Vehicle;

import java.util.List;

public interface ParkingDataManager<T extends Vehicle> {

  int parkCar(T vehicle);

  boolean leaveCar(int slotNumber);

  List<String> getStatus();

  List<String> getRegNumberForColor(String color);

  List<Integer> getSlotNumbersFromColor(String colour);

  int getSlotNoFromRegistrationNo(String registrationNo);

}
