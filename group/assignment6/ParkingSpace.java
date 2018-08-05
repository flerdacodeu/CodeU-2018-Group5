import java.util.HashSet;
import java.util.Set;

/**
 * Assumptions:
 * - the unique identifier of a parking space is a non-zero positive integer
 * - for an empty parking space the car attribute is null
 */
public class ParkingSpace {

  private final int identifier;
  // set of the cars that can be parked on this space
  private Set<Car> reservations;

  public ParkingSpace(int identifier) {
	assert identifier > 0;
	this.identifier = identifier;
	reservations = new HashSet<>();
  }

  public ParkingSpace(int identifier, Set<Car> reservations) {
	assert identifier > 0 && reservations != null;
	this.identifier = identifier;
	this.reservations = reservations;
  }

  public int getIdentifier() {
	return identifier;
  }

  public Set<Car> getReservations() {
	return reservations;
  }

  public void addReservations(Set<Car> reservations) {
	if (reservations == null) {
	  throw new IllegalArgumentException();
	}
	this.reservations.addAll(reservations);
  }

  public void reserveSpace(Car car) {
	if (car == null) {
	  throw new IllegalArgumentException();
	}
	reservations.add(car);
  }

  public void removeReservation(Car car) {
	if (car == null || !reservations.contains(car)) {
	  throw new IllegalArgumentException();
	}
	reservations.remove(car);
  }

  @Override
  public boolean equals(Object obj) {
	if (obj == null || this.getClass() != obj.getClass()) {
	  return false;
	}

	ParkingSpace parkingSpace = (ParkingSpace) obj;

	boolean equal = this.identifier == parkingSpace.identifier;
	if (reservations != null) {
	  equal = equal && reservations.equals(parkingSpace.getReservations());
	}
	return equal;
  }

  @Override
  public int hashCode() {
	int result = 17;
	result = 31 * result + identifier;
	if (!reservations.isEmpty()) {
	  for (Car car : reservations) {
		result = 31 * result + car.hashCode();
	  }
	}
	return result;
  }
}
