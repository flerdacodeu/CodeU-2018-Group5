import java.util.*;

public class ParkingLot {
  	
  private static final Car NO_CAR = null;	
	
  /**
   * Generates a sequence of moves needed to rearrange the cars from a given start state into
   * a given end state.
   *
   * @param startState the initial state of the parking space
   * @param endState the final state of the parking space
   * @return the sequence of moves that rearrange the cars
   * @throws IllegalArgumentException if the start state or the end state are null
   */
  public List<Move> rearrangeCars(State startState, State endState) {
    //TODO: more input validation
    if (startState == null || endState == null) {
      throw new IllegalArgumentException();
    }

    List<Move> sequenceOfMoves = new ArrayList<>();
    rearrangeCars(startState, endState, sequenceOfMoves);
    return sequenceOfMoves;
  }

  /**
   * Helper method for generating the sequence of moves needed to rearrange the cars.
   *
   * Algorithm:
   *  1. compare the position of the empty parking space in the current state with the position of
   *  the empty parking space in the final state
   *  2. if they are the same then find in the current state the first car that is not parked in the
   *  right parking space and park it in the empty space
   *  3. while the positions are different, get the car from the final state that needs to be
   *  parked in the position of empty parking space in the current state and park it there
   *  4. repeat previous steps
   *
   * @param currentState the current state of the parking space
   * @param endState the final state of the parking space
   * @param sequenceOfMoves the sequence of moves that rearrange the cars
   */
  private void rearrangeCars(State currentState, State endState, List<Move> sequenceOfMoves) {
    ParkingSpace emptySpaceCurrentState = currentState.getEmptyParkingSpace();
    ParkingSpace emptySpaceEndState = endState.getEmptyParkingSpace();

    if (emptySpaceCurrentState.equals(emptySpaceEndState)) {
      Car carInWrongSpace = getFirstCarInWrongSpace(currentState, endState);
      // if NO_CAR is returned then all the cars are in the right parking space
      if (carInWrongSpace == NO_CAR) {
        return;
      }
      currentState.parkCarInSpace(carInWrongSpace, emptySpaceCurrentState);
      ParkingSpace carSpace = currentState.getParkingSpaceOfCar(carInWrongSpace);
      sequenceOfMoves.add(new Move(carInWrongSpace, carSpace, emptySpaceCurrentState));
    }
    while (!emptySpaceCurrentState.equals(emptySpaceEndState)) {
      Car expectedCar = endState.getCarParkedInSpace(emptySpaceCurrentState);
      ParkingSpace positionOfExpectedCar = currentState.getParkingSpaceOfCar(expectedCar);
      currentState.parkCarInSpace(expectedCar, emptySpaceCurrentState);
      sequenceOfMoves.add(new Move(expectedCar, positionOfExpectedCar, emptySpaceCurrentState));
      emptySpaceCurrentState = currentState.getEmptyParkingSpace();
      emptySpaceEndState = endState.getEmptyParkingSpace();
    }
    rearrangeCars(currentState, endState, sequenceOfMoves);
  }

  /**
   * Finds the first car in the given current state of the parking lot that is not parked in the space
   * it must be in the final state of the parking lot
   *
   * @param currentState the current state of the parking lot
   * @param endState the final state of the parking lot
   * @return the first car that is parked in the wrong place, or null if no such car is found
   */
  private Car getFirstCarInWrongSpace(State currentState, State endState) {
    for (ParkingSpace space : currentState.getAllParkingSpaces()) {
      Car currentCar = currentState.getCarParkedInSpace(space);
      Car expectedCar = endState.getCarParkedInSpace(space);
      if(currentCar != NO_CAR && !currentCar.equals(expectedCar)) {
        return currentCar;
      }
    }
    // all the cars are in the right parking space
    return NO_CAR;
  }
	
	
	
	/*
	 * Iteration algorithm, we go through all slots and rearrange cars by emptying
	 * destination slot and moving car there
	 */
  public List<Move> rearrangeCars1(State startState, State endState) {
    //TODO: implement algorithm
    List<Move> sequenceOfMoves = new ArrayList<>();
    
    ParkingSpace emptySlot = startState.getEmptyParkingSpace();
    
    for(ParkingSpace slot: startState.getAllParkingSpaces()) {
    	if(startState.getCarParkedInSpace(slot)!=endState.getCarParkedInSpace(slot)) {
    		//if destination slot is occupied, we move car from the occupied slot to the empty one 
    		//and then move the first car to the destination slot
    		Car currentCar = startState.getCarParkedInSpace(slot);
    		ParkingSpace destinationSlot = endState.getParkingSpaceOfCar(currentCar);
    		Car carInDestinationSlot = startState.getCarParkedInSpace(destinationSlot);
    		if(carInDestinationSlot!=null) {
    			//remove car from destination slot
    			Move movement = new Move(carInDestinationSlot, destinationSlot, emptySlot);
    			sequenceOfMoves.add(movement);
    			//move currentCar to the destination
    			movement = new Move(currentCar, slot, destinationSlot);
    			sequenceOfMoves.add(movement);
    			//change the state
    			startState.parkCarInSpace(carInDestinationSlot, emptySlot);
    			startState.parkCarInSpace(currentCar, destinationSlot);
    			//make the current slot empty
    			emptySlot = slot;
    		}
    		//if cars destination slot is not empty, we move the car to that slot and make
    		//cars previous slot empty
    		else{
    			Move movement = new Move(currentCar, slot, emptySlot);
    			emptySlot = slot;
    			sequenceOfMoves.add(movement);
    		}	
    	}
    }
    
    return sequenceOfMoves;
  }
  /*
   * Another algorithm using empty slot and checking which car should belong there
   * Special case that we need to be aware of is when empty space should be empty, but cars are
   * still unsorted, EXAMPLE:
   * X-empty
   * wanted: A B C D X
   * current: B A C D X
   */
  public List<Move> rearrangeCars2(State startState, State endState) {
	    //TODO: implement algorithm
	    List<Move> sequenceOfMoves = new ArrayList<>();
	    
	    ParkingSpace emptySlot = startState.getEmptyParkingSpace();
	    ParkingSpace slotEmptyAfterSort = endState.getEmptyParkingSpace();
	    Set<Car> unprocessedCars = new HashSet<Car>();
	    
	    for(ParkingSpace slot: startState.getAllParkingSpaces()) {
	    	Car currentCar = startState.getCarParkedInSpace(slot);
	    	Car carInDestination = endState.getCarParkedInSpace(slot);
	    	//if it is not an empty car and it is not in its right place
	    	if(currentCar!=null && currentCar!=carInDestination) {
	    		unprocessedCars.add(currentCar);
	    	}
	    }
	    //while empty slot is not in its right place -> we need to fill it with wanted car
	    while(emptySlot!=slotEmptyAfterSort) {
	    	//we move car to an empty slot
	    	Car car = endState.getCarParkedInSpace(emptySlot);
	    	ParkingSpace previousSlot = startState.getParkingSpaceOfCar(car);
	    	Move movement = new Move(car, previousSlot, emptySlot);
	    	//we add the movement to the sequence
	    	sequenceOfMoves.add(movement);
	    	//remove car from unprocessedCars set
	    	unprocessedCars.remove(car);
	    	emptySlot = previousSlot;
	    }
	    //by now the cars should be sorted, unless we reach the special case mentioned above
	    while(!unprocessedCars.isEmpty()) {
	    	//we still have some cars to be sorted
	    	//Options:
	    	//1. wrap this while around the while above and randomly switch one of the 
	    	//unprocessed cars to an empty space and continue
	    	//2. use first algorithm, so the while loop would act only as optimisation, but not 
	    	//necessary solve the problem
	    	//3. there are many other ways to solve it, but it seems to be not the very best
	    	//solution
	    	
	    }
	    
	    return sequenceOfMoves;
  }
  /*
   * Cycle sort algorithm
   */
  public List<Move> rearrangeCars3(State startState, State endState) {
	    //TODO: implement algorithm
	    List<Move> sequenceOfMoves = new ArrayList<>();  
	    
	    
	    Set<Car> unprocessedCars = new HashSet<Car>();
	    //we find a set of all cycles that need to be followed
	    for(ParkingSpace slot: startState.getAllParkingSpaces()) {
	    	Car currentCar = startState.getCarParkedInSpace(slot);
	    	Car carInDestination = endState.getCarParkedInSpace(slot);
	    	//if it is not an empty car and it is not in its right place
	    	if(currentCar!=null && currentCar!=carInDestination) {
	    		unprocessedCars.add(currentCar);
	    	}
	    }
	    cycleHelper(startState, endState, sequenceOfMoves, unprocessedCars);
	    //so this is basically the same approach as the zero one, and the solution for the
	    //special case would be sort the cycles, but before that, add the empty slot
	    //to the cycle
	    while(!unprocessedCars.isEmpty()) {
	    	//current empty slot
	    	ParkingSpace emptySlot = startState.getEmptyParkingSpace();
	    	//we take a random car and move it to the empty space, so that we
	    	//can follow the cycle and sort the cars
	    	//at the end of it, empty slot should be returned to its start position
	    	Iterator<Car> iterator = unprocessedCars.iterator();
	    	Car randomCar = iterator.next();
	    	ParkingSpace previousSlot = startState.getParkingSpaceOfCar(randomCar);	    	
	    	Move movement = new Move(randomCar, previousSlot, emptySlot);
	    	sequenceOfMoves.add(movement);
	    	
	    	cycleHelper(startState, endState, sequenceOfMoves, unprocessedCars);	    	
	    }
	    
	    return sequenceOfMoves;
  }
  //could make sequenceOfMoves, unprocessedCars global variables
  public void cycleHelper(State startState, State endState, List<Move> sequenceOfMoves, Set<Car> unprocessedCars) {
	    ParkingSpace emptySlot = startState.getEmptyParkingSpace();
	    ParkingSpace endEmptySlot = endState.getEmptyParkingSpace();
	    
	    while(emptySlot!=endEmptySlot) {
	    	//we move car to an empty slot
	    	Car car = endState.getCarParkedInSpace(emptySlot);
	    	ParkingSpace previousSlot = startState.getParkingSpaceOfCar(car);
	    	Move movement = new Move(car, previousSlot, emptySlot);
	    	//we add the movement to the sequence
	    	sequenceOfMoves.add(movement);
	    	//remove car from unprocessedCars set
	    	unprocessedCars.remove(car);
	    	emptySlot = previousSlot;
	    }
  }
}
