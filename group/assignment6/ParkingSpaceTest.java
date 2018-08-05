import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class ParkingSpaceTest {

	@Test(expected=IllegalArgumentException.class)
	public void testAddReservations_nullReservations() {
		ParkingSpace parkingSpace = new ParkingSpace(1);
		parkingSpace.addReservations(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testReserveSpace_nullCar() {
		ParkingSpace parkingSpace = new ParkingSpace(1);
		parkingSpace.reserveSpace(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testRemoveReservation_nullCar() {
		ParkingSpace parkingSpace = new ParkingSpace(1);
		parkingSpace.removeReservation(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRemoveReservation_carNotReserved() {
		ParkingSpace parkingSpace = new ParkingSpace(1);
		
		Car car = new Car(2);
		Set<Car> reservations = new HashSet<Car>();
		reservations.add(car);
		parkingSpace.addReservations(reservations);
		
		Car carNotReserved = new Car(6);
		parkingSpace.removeReservation(carNotReserved);
	}

}
