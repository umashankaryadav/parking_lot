package assignment.gojek.parkinglot;

import static org.junit.Assert.assertTrue;

import assignment.gojek.parkinglot.service.ParkingLotService;
import assignment.gojek.parkinglot.service.impl.ParkingLotServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Unit test for Parking Lot
 */
public class ParkingLotTest {

  private final ByteArrayOutputStream	outContent	= new ByteArrayOutputStream();

  @Before
  public void init()
  {
    System.setOut(new PrintStream(outContent));
  }

  @Test
  public void createParkingLot() throws Exception
  {
    ParkingLotService instance = new ParkingLotServiceImpl();
    instance.createParkingLot(1, 10);
    assertTrue("created parking lot with 10 slots".equalsIgnoreCase(outContent.toString().trim()));
  }

  //TODO: All the other functional features can be tested.
}
