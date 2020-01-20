package assignment.gojek.parkinglot.data.impl;

import assignment.gojek.parkinglot.data.ParkingStrategyManager;

import java.util.TreeSet;

public class ParkingStrategyManagerImpl implements ParkingStrategyManager {

  private TreeSet<Integer> freeSlots;

  public ParkingStrategyManagerImpl() {
    this.freeSlots = new TreeSet<>();
  }

  @Override
  public int getNextSlot() {

    return freeSlots.first();
  }

  @Override
  public void addSlot(Integer slotNo) {

    freeSlots.add(slotNo);
  }

  public  void removeFromAvailableSlot(Integer slotNo) {

    freeSlots.remove(slotNo);
  }
}
