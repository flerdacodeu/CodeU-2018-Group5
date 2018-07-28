import com.google.common.collect.HashBiMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Assumptions:
 * - a car identifier value of -1 indicates that the corresponding parking space should be empty
 * - car and parking space identifiers are non-zero positive integers
 */
public class State {
  // the key of the map is the unique identifier of a car
  // the value of the map is the identifier of a parking space
  private HashBiMap<ParkingSpace, Car> parkingLotState;
  private static final int EMPTY = -1;
  private static final Car NO_CAR = null;
  private int numberOfParkingSpaces;

  // the elements of carsIdentifiers indicate the order in which the cars are parked in the parking spaces
  public State(List<Integer> carsIdentifiers) {
    numberOfParkingSpaces = carsIdentifiers.size();
    parkingLotState = HashBiMap.create(carsIdentifiers.size());
    int parkingSpaceIdentifier = 1;
    for (Integer carIdentifier : carsIdentifiers) {
      // the car will remain null if the current identifier is -1
      Car car = null;
      if (carIdentifier != EMPTY) {
        car = new Car(carIdentifier);
      }
      ParkingSpace parkingSpace = new ParkingSpace(parkingSpaceIdentifier);
      parkingLotState.put(parkingSpace, car);
      parkingSpaceIdentifier++;
    }
  }

  public List<ParkingSpace> getAllParkingSpaces() {
    return new ArrayList<>(parkingLotState.keySet());
  }

  public ParkingSpace getParkingSpaceOfCar(Car car) {
    return parkingLotState.inverse().get(car);
  }

  public ParkingSpace getEmptyParkingSpace() {
    return parkingLotState.inverse().get(NO_CAR);
  }

  public Car getCarParkedInSpace(ParkingSpace parkingSpace) {
    return parkingLotState.get(parkingSpace);
  }

  public void parkCarInSpace(Car car, ParkingSpace parkingSpace) {
    parkingLotState.forcePut(parkingSpace, car);
  }

  public void emptyParkingSpace(ParkingSpace parkingSpace) {
    parkingLotState.forcePut(parkingSpace, null);
  }

  public int getNumberOfParkingSpaces() {
    return numberOfParkingSpaces;
  }
}
