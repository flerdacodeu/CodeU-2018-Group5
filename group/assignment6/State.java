import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * Assumptions:
 * - a car identifier value of -1 indicates that the corresponding parking space should be empty
 * - car and parking space identifiers are non-zero positive integers
 */
public class State {
  private HashBiMap<ParkingSpace, Car> parkingLotState;
  private BiMap<Car, ParkingSpace> parkingLotStateInverse;
  private int numberOfParkingSpaces;
  private static final int EMPTY = -1;
  private static final Car NO_CAR = null;

  // the elements of carsIdentifiers indicate the order in which the cars are parked in the parking spaces
  public State(List<Integer> carsIdentifiers) {
	assert carsIdentifiers != null;
	numberOfParkingSpaces = carsIdentifiers.size();
	parkingLotState = HashBiMap.create(numberOfParkingSpaces);
	int parkingSpaceIdentifier = 1;
	for (Integer carIdentifier : carsIdentifiers) {
	  // the car will remain null if the current identifier is -1
	  Car car = null;
	  if (carIdentifier != EMPTY) {
		car = new Car(carIdentifier);
	  }
	  ParkingSpace parkingSpace = new ParkingSpace(parkingSpaceIdentifier++);
	  parkingLotState.put(parkingSpace, car);
	}
	parkingLotStateInverse = parkingLotState.inverse();
  }

  public List<ParkingSpace> getAllParkingSpaces() {
	return new ArrayList<>(parkingLotState.keySet());
  }

  public List<Car> getAllCars() {
	return new ArrayList<>(parkingLotStateInverse.keySet());
  }

  public ParkingSpace getParkingSpaceOfCar(Car car) {
	return parkingLotStateInverse.get(car);
  }

  public ParkingSpace getEmptyParkingSpace() {
	return parkingLotStateInverse.get(NO_CAR);
  }

  public Car getCarParkedInSpace(ParkingSpace parkingSpace) {
	return parkingLotState.get(parkingSpace);
  }

  public void parkCarInSpace(Car car, ParkingSpace parkingSpace) {
	emptyParkingSpace(getParkingSpaceOfCar(car));
	parkingLotState.forcePut(parkingSpace, car);
  }

  private void emptyParkingSpace(ParkingSpace parkingSpace) {
	parkingLotState.forcePut(parkingSpace, NO_CAR);
  }

  public int getNumberOfParkingSpaces() {
	return numberOfParkingSpaces;
  }

  public void addReservationsForParkingSpace(ParkingSpace space, Set<Car> reservations) {
	if (space == null || reservations == null || !parkingLotState.containsKey(space)) {
	  throw new IllegalArgumentException();
	}
	Car car = getCarParkedInSpace(space);
	space.addReservations(reservations);

	parkingLotStateInverse.replace(car, space);
  }

  /**
   * Copy a State instance from a list of cars
   * 
   * @param parkedCars - the cars and their position in the copied state
   * @return a state instance based on the list of cars
   */
  public State copy(List<Car> parkedCars) {
	List<Integer> parkedCarsIdentifiers = new ArrayList<Integer>();
	for (Car car : parkedCars) {
	  if (car == null) {
		parkedCarsIdentifiers.add(EMPTY);  
	  } else {
		parkedCarsIdentifiers.add(car.getIdentifier());
	  }
	}
	return new State(parkedCarsIdentifiers);

  }


  public void displayState() {
	System.out.println("I have "+this.numberOfParkingSpaces+" slots.");
	for(java.util.Map.Entry<ParkingSpace, Car>  entry : this.parkingLotState.entrySet()) {
	  ParkingSpace key = entry.getKey();
	  Car value = entry.getValue();
	  if (value == null) {
		System.out.println("Slot "+key.getIdentifier()+" is empty");
	  } else {
		System.out.println("Slot "+key.getIdentifier()+" = Car "+value.getIdentifier());
	  }
	}
  }
}
