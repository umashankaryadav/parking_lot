package assignment.gojek.parkinglot.service;

import assignment.gojek.parkinglot.model.Vehicle;

import java.util.Optional;

public interface ParkingLotService {

  void createParkingLot(int numberOfSlots);

  Optional<Integer> park(int level, Vehicle vehicle);

  void leavePark(int level, int slotNumber);

  void getStatus(int level);

  void getRegNumbersForColor(int level, String color);

  void getSlotNumbersFromColor(int level, String colour);

  int getSlotNoFromRegNo(int level, String regNo);

}