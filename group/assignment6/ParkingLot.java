import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

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
		if (!isValidInput(currentState, endState)) {
			throw new IllegalArgumentException();
		}

		List<Move> sequenceOfMoves = new ArrayList<>();
		Queue<Car> carsInWrongSpace = getCarsInWrongSpaces(currentState, endState);
		ParkingSpace emptySpaceEndState = endState.getEmptyParkingSpace();

		while (!carsInWrongSpace.isEmpty()) {
			ParkingSpace emptySpaceCurrentState = currentState.getEmptyParkingSpace();

			if (emptySpaceCurrentState.equals(emptySpaceEndState)) {
				dealWithReservations(carsInWrongSpace, currentState, sequenceOfMoves);
				// due to changes in the previous method, the position of the empty space needs to be updated
				emptySpaceCurrentState = currentState.getEmptyParkingSpace();
			}

			while (!emptySpaceCurrentState.equals(emptySpaceEndState)) {
				Car expectedCar = endState.getCarParkedInSpace(emptySpaceCurrentState);
				ParkingSpace positionOfExpectedCar = currentState.getParkingSpaceOfCar(expectedCar);
				moveCar(expectedCar, positionOfExpectedCar, emptySpaceCurrentState, currentState, sequenceOfMoves);
				carsInWrongSpace.remove(expectedCar);
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
	 * Moves a car into the current empty space of the parking lot; the car that is moved depends on
	 * the reservations for the current empty space.
	 *
	 * @param carsInWrongSpace cars that are not parked in the space they need to be in the final state
	 * @param currentState the current state of the parking lot
	 * @param moves the sequence of car moves needed to rearrange the cars
	 */
	private void dealWithReservations(Queue<Car> carsInWrongSpace, State currentState, List<Move> moves) {
		ParkingSpace emptySpace = currentState.getEmptyParkingSpace();
		Set<Car> reservations = emptySpace.getReservations();
		// if there are no reservations, move the first car in the queue in the empty space
		if (reservations.isEmpty()) {
			Car carInWrongSpace = carsInWrongSpace.poll();
			ParkingSpace carSpace = currentState.getParkingSpaceOfCar(carInWrongSpace);
			moveCar(carInWrongSpace, carSpace, emptySpace, currentState, moves);
			return;
		}

		// otherwise search for the first car in the queue that can be parked in the space of a car from
		// the list of reservations for the current empty space; the car from the reservations list is
		// moved to the empty space, the car in the queue is parked into the space of the car in the
		// reservations list
		for (Car allowedCar : reservations) {
			ParkingSpace allowedCarSpace = currentState.getParkingSpaceOfCar(allowedCar);
			Set<Car> spaceReservations = allowedCarSpace.getReservations();

			for (int i = 0; i < carsInWrongSpace.size(); i++) {
				Car carInWrongSpace = carsInWrongSpace.poll();
				if (spaceReservations.isEmpty() || spaceReservations.contains(carInWrongSpace)) {
					ParkingSpace carSpace = currentState.getParkingSpaceOfCar(carInWrongSpace);
					moveCar(carInWrongSpace, carSpace, allowedCarSpace, currentState, moves);
					return;
				} else {
					carsInWrongSpace.add(carInWrongSpace);
				}
			}
		}
		//if no moves have been made and if there still are cars in wrong parking spaces
		if (!carsInWrongSpace.isEmpty()) {
			throw new IllegalArgumentException("Invalid reservations of parking spaces: cars cannot be rearranged");
		}
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
		if (!isValidInput(currentState, endState)) {
			throw new IllegalArgumentException();
		}
		List<Move> sequenceOfMoves = new ArrayList<>();
		List<ParkingSpace> parkingSpaces = currentState.getAllParkingSpaces();
		for (ParkingSpace currentSpace : parkingSpaces) {
			Car currentCar = currentState.getCarParkedInSpace(currentSpace);
			ParkingSpace destinationSpace = endState.getParkingSpaceOfCar(currentCar);
			if (!currentSpace.equals(destinationSpace)) {
				moveCar(currentCar, currentSpace, destinationSpace, currentState, sequenceOfMoves);
			}
		}
		return sequenceOfMoves;
	}


	public List<List<Move>> computeAllPossibleSequences(State startState, State endState) {
		//input validation
		List<Car> allCars = startState.getAllCars();
		List<List<Move>> allPossibleSequences = new ArrayList<List<Move>>();
		State start = startState.copy(allCars);
		//if it is not good already
		for (Car car : allCars) {
			if (car != null) {
				System.out.println("=================START=================");
				List<Move> moves = new ArrayList<Move>();
				allPossibleSequences.add(this.arrangeWithoutDuplicate(car, startState, endState, allCars, moves));
			}
			startState = start;
		}
		return allPossibleSequences;
	}

	/**
	 * 
	 * @param car the car to be moved
	 * @param startState
	 * @param endState
	 * @return
	 */
	public List<Move> arrangeWithoutDuplicate(Car movedCar, State currentState, State endState, List<Car> allCars, List<Move> moves) {
		currentState.displayState();
		Queue<Car> wrongParked = this.getCarsInWrongSpaces(currentState, endState);
		if (wrongParked.isEmpty()) {
			return moves;
		}

		this.moveCar(movedCar, currentState.getParkingSpaceOfCar(movedCar), currentState.getEmptyParkingSpace(), currentState, moves);
		for (Car car : allCars) {
			if (car !=null && !car.equals(movedCar)) {
				this.arrangeWithoutDuplicate(car, currentState, endState, allCars, moves);
			}
		}
		return moves;
	}



	/**
	 * Moves a car from its current parking space to the specified parking space.
	 *
	 * @param car the car to be moved
	 * @param origin the current parking space of the car
	 * @param destination desired parking space of the car
	 * @param currentState the state of the parking lot
	 * @param moves sequence of moves needed to rearrange the cars
	 */
	private void moveCar(Car car, ParkingSpace origin, ParkingSpace destination, State currentState, List<Move> moves) {
		Car carInDestinationSpace = currentState.getCarParkedInSpace(destination);
		if (carInDestinationSpace == NO_CAR) {
			currentState.parkCarInSpace(car, destination);
			moves.add(new Move(car, origin, destination));
		} else {
			ParkingSpace emptySpace = currentState.getEmptyParkingSpace();
			currentState.parkCarInSpace(carInDestinationSpace, emptySpace);
			moves.add(new Move(carInDestinationSpace, destination, emptySpace));
			currentState.parkCarInSpace(car, destination);
			moves.add(new Move(car, origin, destination));
		}
	}


	/**
	 * Checks the validity of the start state and end state of the parking lot.
	 *
	 * @param startState the start configuration of the parking lot
	 * @param endState the final configuration of the parking lot
	 * @return true if the states are valid, otherwise false
	 */
	private boolean isValidInput(State startState, State endState) {
		if (startState == null || endState == null
				|| startState.getNumberOfParkingSpaces() != endState.getNumberOfParkingSpaces()
				|| !containSameCars(startState, endState)) {
			return false;
		}
		return true;
	}

	/**
	 * Checks if the given parking lots contain the same cars.
	 *
	 * @param startState the start configuration of the parking lot
	 * @param endState the final configuration of the parking lot
	 * @return true if the parking lots contain the same cars, otherwise false
	 */
	private boolean containSameCars(State startState, State endState) {
		List<Car> startCars = startState.getAllCars();
		List<Car> endCars = endState.getAllCars();
		// the lists startCars/endCars will never contain duplicates (condition imposed by the HashBiMap)
		return startCars.size() == endCars.size() && endCars.containsAll(startCars);
	}
}
