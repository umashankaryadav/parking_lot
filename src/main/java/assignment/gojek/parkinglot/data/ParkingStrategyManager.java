package assignment.gojek.parkinglot.data;

public interface ParkingStrategyManager {

  int getNextSlot();

  void addSlot(Integer slotNo);

  void removeFromAvailableSlot(Integer slotNo);
}
