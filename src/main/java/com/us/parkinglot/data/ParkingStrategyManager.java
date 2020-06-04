package com.us.parkinglot.data;

public interface ParkingStrategyManager {

  int getNextSlot();

  void addSlot(Integer slotNo);

  void removeFromAvailableSlot(Integer slotNo);
}
