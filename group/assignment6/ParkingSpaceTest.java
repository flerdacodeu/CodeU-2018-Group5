import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class ParkingSpaceTest {

	@Test(expected=IllegalArgumentException.class)
	public void testAddReservations_nullReservations() {
		ParkingSpace parkingSpace = new ParkingSpace(1);
		parkingSpace.addReservations(null);
	}

	@Test
	public void testAddReservations_commonCase() {
		ParkingSpace parkingSpace = new ParkingSpace(1);
		Car car1 = new Car(1);
		Car car2 = new Car(2);
		Car car4 = new Car(4);
		Set<Car> reservations = new HashSet<Car>();
		reservations.add(car1);
		reservations.add(car2);
		reservations.add(car4);
		parkingSpace.addReservations(reservations);

		assertEquals(reservations, parkingSpace.getReservations());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testReserveSpace_nullCar() {
		ParkingSpace parkingSpace = new ParkingSpace(1);
		parkingSpace.reserveSpace(null);
	}

	@Test
	public void testReserveSpace_commonCase() {
		ParkingSpace parkingSpace = new ParkingSpace(1);
		Car car = new Car(2);
		parkingSpace.reserveSpace(car);
		Set<Car> reservations = new HashSet<Car>();
		reservations.add(car);
		assertEquals(reservations, parkingSpace.getReservations());
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

	@Test
	public void testRemoveReservation_commonCase() {
		ParkingSpace parkingSpace = new ParkingSpace(1);

		Car car1 = new Car(1);
		Car car2 = new Car(2);
		Set<Car> reservations = new HashSet<Car>();
		reservations.add(car1);
		reservations.add(car2);
		parkingSpace.addReservations(reservations);

		parkingSpace.removeReservation(car1);
		reservations.remove(car1);
		assertEquals(reservations, parkingSpace.getReservations());
	}

}
