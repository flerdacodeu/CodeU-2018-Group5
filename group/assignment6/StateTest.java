import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class StateTest {


  @Test
  public void testParkCarInSpace_emptySpot() {
	State state = new State(Arrays.asList(1,2,-1,3));
	Car car = new Car(1);
	ParkingSpace parkingSpace = new ParkingSpace(3);
	state.parkCarInSpace(car, parkingSpace);
	assertEquals(state.getEmptyParkingSpace().getIdentifier(),1);
  }

  @Test
  public void testParkCarInSpace_takenSpot() {
	State state = new State(Arrays.asList(1,2,-1,3));
	Car car = new Car(1);
	ParkingSpace parkingSpace = new ParkingSpace(4);
	state.parkCarInSpace(car, parkingSpace);
	assertEquals(state.getEmptyParkingSpace().getIdentifier(),1);
	assertEquals(state.getCarParkedInSpace(parkingSpace).getIdentifier(),1);
  }


  @Test(expected=IllegalArgumentException.class)
  public void testStateBuilder_emptyParkingSlot() {
	new State(Arrays.asList(-1,-1,-1,-1));
  }

  @Test(expected=IllegalArgumentException.class)
  public void testAddReservationsForParkingSpace_nullSpace() {
	State state = new State(Arrays.asList(1,2,-1,3));
	Set<Car> reservations = new HashSet<Car>();
	state.addReservationsForParkingSpace(null, reservations);
  }

  @Test(expected=IllegalArgumentException.class)
  public void testAddReservationsForParkingSpace_nullReservations() {
	State state = new State(Arrays.asList(1,2,-1,3));
	ParkingSpace parkingSpace = new ParkingSpace(4);
	state.addReservationsForParkingSpace(parkingSpace, null);
  }

  @Test(expected=IllegalArgumentException.class)
  public void testAddReservationsForParkingSpace_spaceNotInLot() {
	State state = new State(Arrays.asList(1,2,-1,3));
	ParkingSpace parkingSpace = new ParkingSpace(6);
	Set<Car> reservations = new HashSet<Car>();
	state.addReservationsForParkingSpace(parkingSpace, reservations);
  }

}
