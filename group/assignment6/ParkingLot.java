import java.util.*;

public class ParkingLot {

  private static final Car NO_CAR = null;
  
  /**
   * Generates a sequence of moves needed to rearrange the cars from a given start state into
   * a given end state (optimal algorithm that uses fewer moves).
   *
   * @param currentState the initial state of the parking space
   * @param endState   the final state of the parking space
   * @return the sequence of moves that rearrange the cars
   * @throws IllegalArgumentException if the start state or the end state are null
   */
  public List<Move> rearrangeCars(State currentState, State endState) {
    //TODO: more input validation
    if (currentState == null || endState == null) {
      throw new IllegalArgumentException();
    }
    
    List<Move> sequenceOfMoves = new ArrayList<>();
    Queue<Car> carsInWrongSpace = getCarsInWrongSpaces(currentState, endState);
    ParkingSpace emptySpaceEndState = endState.getEmptyParkingSpace();
    
    while (!carsInWrongSpace.isEmpty()) {
      ParkingSpace emptySpaceCurrentState = currentState.getEmptyParkingSpace();

      if (emptySpaceCurrentState.equals(emptySpaceEndState)) {
        Car carInWrongSpace = carsInWrongSpace.poll();
        ParkingSpace carSpace = currentState.getParkingSpaceOfCar(carInWrongSpace);
        currentState.parkCarInSpace(carInWrongSpace, emptySpaceCurrentState);
        sequenceOfMoves.add(new Move(carInWrongSpace, carSpace, emptySpaceCurrentState));
      }
      
      while (!emptySpaceCurrentState.equals(emptySpaceEndState)) {
        Car expectedCar = endState.getCarParkedInSpace(emptySpaceCurrentState);
        ParkingSpace positionOfExpectedCar = currentState.getParkingSpaceOfCar(expectedCar);
        currentState.parkCarInSpace(expectedCar, emptySpaceCurrentState);
        carsInWrongSpace.remove(expectedCar);
        sequenceOfMoves.add(new Move(expectedCar, positionOfExpectedCar, emptySpaceCurrentState));
        emptySpaceCurrentState = currentState.getEmptyParkingSpace();
      }
    }
    return sequenceOfMoves;
  }
  
  /**
   * Finds all the cars in the current state of the parking lot that are not parked in the space where
   * they need to be in the final state of the parking lot.
   *
   * @param currentState the current configuration of the parking lot
   * @param endState the final configuration of the parking lot
   * @return the cars that are parked in the wrong parking space
   */
  private Queue<Car> getCarsInWrongSpaces(State currentState, State endState) {
    Queue<Car> carsInWrongSpace = new LinkedList<>();
    for (ParkingSpace space : currentState.getAllParkingSpaces()) {
      Car currentCar = currentState.getCarParkedInSpace(space);
      Car expectedCar = endState.getCarParkedInSpace(space);
      if (currentCar != null && !currentCar.equals(expectedCar)) {
        // keep track of cars that are not parked in the right place
        carsInWrongSpace.add(currentCar);
      }
    }
    return carsInWrongSpace;
  }

  /**
   * Generates a sequence of moves needed to rearrange the cars from a given start state into
   * a given end state (using the straightforward algorithm).
   *
   * @param currentState the initial state of the parking space
   * @param endState     the final state of the parking space
   * @return the sequence of moves that rearrange the cars
   * @throws IllegalArgumentException if the start state or the end state are null
   */
  public List<Move> rearrangeCarsStraightforward(State currentState, State endState) {
    if (currentState == null || endState == null) {
      throw new IllegalArgumentException();
    }
    List<Move> sequenceOfMoves = new ArrayList<>();
    List<ParkingSpace> parkingSpaces = currentState.getAllParkingSpaces();
    for (ParkingSpace currentSpace : parkingSpaces) {
      Car currentCar = currentState.getCarParkedInSpace(currentSpace);
      ParkingSpace destinationSpace = endState.getParkingSpaceOfCar(currentCar);
      if (!currentSpace.equals(destinationSpace)) {
        //if destination slot is occupied, we move car from the occupied slot to the empty one 
        //and then move the first car to the destination slot
        Car carInDestinationSpace = currentState.getCarParkedInSpace(destinationSpace);
        if (carInDestinationSpace != NO_CAR) {
          ParkingSpace emptySpace = currentState.getEmptyParkingSpace();
          //remove car from destination slot
          currentState.parkCarInSpace(carInDestinationSpace, emptySpace);
          sequenceOfMoves.add(new Move(carInDestinationSpace, destinationSpace, emptySpace));
          //move currentCar to the destination
          currentState.parkCarInSpace(currentCar, destinationSpace);
          sequenceOfMoves.add(new Move(currentCar, currentSpace, destinationSpace));
        } else {
          currentState.parkCarInSpace(currentCar, destinationSpace);
          sequenceOfMoves.add(new Move(currentCar, currentSpace, destinationSpace));
        }
      }
    }
    return sequenceOfMoves;
  }
}
