package assignment.gojek.parkinglot.data.impl;

import assignment.gojek.parkinglot.data.ParkingDataManager;
import assignment.gojek.parkinglot.data.ParkingStrategyManager;
import assignment.gojek.parkinglot.model.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryParkingDataManager<T extends Vehicle> implements ParkingDataManager<T> {

  private AtomicInteger level = new AtomicInteger();

  private AtomicInteger noOfParkingSlots = new AtomicInteger();

  private AtomicInteger availability = new AtomicInteger();

  private ParkingStrategyManager parkingStrategy;

  private Map<Integer, Optional<T>> slotVehicleMap;

  private static InMemoryParkingDataManager parkingLotInstance = null;

  @SuppressWarnings("unchecked")
  public static <T extends Vehicle> InMemoryParkingDataManager<T> createParkingLot(Integer level,
      Integer noOfParkingSlots, ParkingStrategyManager parkingStrategy) {
    if (parkingLotInstance == null) {
      synchronized (InMemoryParkingDataManager.class) {
        if (parkingLotInstance == null) {
          parkingLotInstance = new InMemoryParkingDataManager<T>(level, noOfParkingSlots, parkingStrategy);
        }
      }
    }

    return parkingLotInstance;
  }

  private InMemoryParkingDataManager(int level, int noOfParkingSlots, ParkingStrategyManager parkingStrategy) {
    this.level.set(level);
    this.noOfParkingSlots.set(noOfParkingSlots);
    this.availability.set(noOfParkingSlots);
    this.parkingStrategy = parkingStrategy;
    if (parkingStrategy == null)
      parkingStrategy = new ParkingStrategyManagerImpl();

    slotVehicleMap = new ConcurrentHashMap<>(); //Thread safe
    for (int i = 1; i <= noOfParkingSlots; i++) {
      slotVehicleMap.put(i, Optional.empty());
      parkingStrategy.addSlot(i);
    }
  }

  @Override
  public int parkCar(T vehicle) {
    int availableSlot = -1;
    if (availability.get() == 0) {
      return availableSlot;
    } else {
      availableSlot = parkingStrategy.getNextSlot();
      //if (slotVehicleMap.containsValue(Optional.of(vehicle)))

      slotVehicleMap.put(availableSlot, Optional.of(vehicle));
      availability.decrementAndGet();
      parkingStrategy.removeFromAvailableSlot(availableSlot);
    }

    return availableSlot;
  }

  @Override
  public boolean leaveCar(int slotNumber) {
    // Checking if the slot already empty
    if (!slotVehicleMap.get(slotNumber).isPresent())
      return false;

    availability.incrementAndGet();
    parkingStrategy.addSlot(slotNumber);
    slotVehicleMap.put(slotNumber, Optional.empty());

    return true;
  }

  @Override
  public List<String> getStatus() {
    List<String> statusList = new ArrayList<>();
    for (int slotNo = 1; slotNo <= noOfParkingSlots.get(); slotNo++) {
      Optional<T> vehicle = slotVehicleMap.get(slotNo);
      if (vehicle.isPresent()) {
        statusList.add(
            slotNo + "\t\t" + vehicle.get().getRegNo() + "\t\t" + vehicle.get().getColour()
                .getColourName());
      }
    }

    return statusList;
  }

  @Override
  public List<String> getRegNumberForColor(String color) {
    List<String> statusList = new ArrayList<>();
    for (int slotNo = 1; slotNo <= noOfParkingSlots.get(); slotNo++) {
      Optional<T> vehicle = slotVehicleMap.get(slotNo);
      if (vehicle.isPresent() && color
          .equalsIgnoreCase(vehicle.get().getColour().getColourName())) {
        statusList.add(vehicle.get().getRegNo());
      }
    }

    return statusList;
  }

  @Override
  public List<Integer> getSlotNumbersFromColor(String colour) {
    List<Integer> slotList = new ArrayList<>();
    for (int slotNo = 1; slotNo <= noOfParkingSlots.get(); slotNo++)
    {
      Optional<T> vehicle = slotVehicleMap.get(slotNo);
      if (vehicle.isPresent() && colour.equalsIgnoreCase(vehicle.get().getColour().getColourName()))
      {
        slotList.add(slotNo);
      }
    }

    return slotList;
  }

  @Override
  public int getSlotNoFromRegistrationNo(String regNo) {
    int result = -1;
    for (int slotNo = 1; slotNo <= noOfParkingSlots.get(); slotNo++)
    {
      Optional<T> vehicle = slotVehicleMap.get(slotNo);
      if (vehicle.isPresent() && regNo.equalsIgnoreCase(vehicle.get().getRegNo()))
      {
        result = slotNo;
        break;
      }
    }

    return result;
  }

}
