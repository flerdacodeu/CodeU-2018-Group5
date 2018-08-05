import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ParkingLotTest {

	public List<Car> generateXcars(int numberOfCars) {
		List<Car> cars = new ArrayList<Car>();
		for (int i = 1; i<=numberOfCars ; i++) {
			cars.add(new Car(i));
		}
		return cars;
	}

	public List<ParkingSpace> generateXspaces(int numberOfSpaces) {
		List<ParkingSpace> spaces = new ArrayList<ParkingSpace>();
		for (int i = 1; i<=numberOfSpaces ; i++) {
			spaces.add(new ParkingSpace(i));
		}
		return spaces;
	}


	@Test(expected=IllegalArgumentException.class)
	public void testRearrangeCars_nullStartState() {
		State startState = null;
		State endState = new State(Arrays.asList(1,2,-1,3));
		ParkingLot lot = new ParkingLot();
		lot.rearrangeCars(startState, endState);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testRearrangeCars_nullEndState() {
		State startState = new State(Arrays.asList(1,2,-1,3));
		State endState = null;
		ParkingLot lot = new ParkingLot();
		lot.rearrangeCars(startState, endState);
	}

	@Test
	public void testRearrangeCars_commonCase() {
		State startState = new State(Arrays.asList(1,2,-1,3));
		State endState = new State(Arrays.asList(3,1,2,-1));
		ParkingLot lot = new ParkingLot();
		List<Move> actualResult = lot.rearrangeCars(startState, endState);

		//expected result set-up
		List<Move> expectedResult = new ArrayList<Move>(); 
		List<Car> cars = this.generateXcars(3);
		List<ParkingSpace> spaces = this.generateXspaces(4);
		Move move1 = new Move(cars.get(1), spaces.get(1), spaces.get(2));
		Move move2 = new Move(cars.get(0), spaces.get(0), spaces.get(1));
		Move move3 = new Move(cars.get(2), spaces.get(3), spaces.get(0));
		expectedResult.add(move1);
		expectedResult.add(move2);
		expectedResult.add(move3);

		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testRearrangeCars_sameEmptySpot() {
		State startState = new State(Arrays.asList(1,2,-1,3));
		State endState = new State(Arrays.asList(3,1,-1,2));
		ParkingLot lot = new ParkingLot();
		List<Move> actualResult = lot.rearrangeCars(startState, endState);

		//expected result set-up
		List<Move> expectedResult = new ArrayList<Move>(); 
		List<Car> cars = this.generateXcars(3);
		List<ParkingSpace> spaces = this.generateXspaces(4);
		Move move1 = new Move(cars.get(0), spaces.get(0), spaces.get(2));
		Move move2 = new Move(cars.get(2), spaces.get(3), spaces.get(0));
		Move move3 = new Move(cars.get(1), spaces.get(1), spaces.get(3));
		Move move4 = new Move(cars.get(0), spaces.get(2), spaces.get(1));
		expectedResult.add(move1);
		expectedResult.add(move2);
		expectedResult.add(move3);
		expectedResult.add(move4);

		assertEquals(expectedResult, actualResult);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testRearrangeCars_moreThanOneEmptySpace() {
		State startState = new State(Arrays.asList(1,2,-1,3,-1));
		State endState = new State(Arrays.asList(3,1,-1,2,-1));
		ParkingLot lot = new ParkingLot();
		lot.rearrangeCars(startState, endState);
	}


	@Test(expected=IllegalArgumentException.class)
	public void testRearrangeCars_inconsistentNumberOfSpacesAndCars() {
		State startState = new State(Arrays.asList(1,2,-1,3,4));
		State endState = new State(Arrays.asList(3,1,-1,2));
		ParkingLot lot = new ParkingLot();
		lot.rearrangeCars(startState, endState);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testRearrangeCars_inconsistentCarIdentifiers() {
		State startState = new State(Arrays.asList(1,2,-1,3));
		State endState = new State(Arrays.asList(3,1,4,-1));
		ParkingLot lot = new ParkingLot();
		lot.rearrangeCars(startState, endState);
	}

	@Test
	public void testComputeAllPossibleSequences_commonCase() {
		State startState = new State(Arrays.asList(1,2,-1));
		State endState = new State(Arrays.asList(2,-1,1));
		ParkingLot lot = new ParkingLot();
		List<List<Move>> actualResult = lot.computeAllPossibleSequences(startState, endState);

		//expected result set-up
		List<List<Move>> expectedResult = new ArrayList<List<Move>>(); 
		List<Move> firstSequence = new ArrayList<Move>();
		List<Move> secondSequence = new ArrayList<Move>();
		List<Car> cars = this.generateXcars(2);
		List<ParkingSpace> spaces = this.generateXspaces(3);

		Move move1_1 = new Move(cars.get(0), spaces.get(0), spaces.get(2));
		Move move2_1 = new Move(cars.get(1), spaces.get(1), spaces.get(0));
		firstSequence.add(move1_1);
		firstSequence.add(move2_1);

		Move move1_2 = new Move(cars.get(1), spaces.get(1), spaces.get(2));
		Move move2_2 = new Move(cars.get(0), spaces.get(0), spaces.get(1));
		Move move3_2 = new Move(cars.get(1), spaces.get(2), spaces.get(0));
		Move move4_2 = new Move(cars.get(0), spaces.get(1), spaces.get(2));
		secondSequence.add(move1_2);
		secondSequence.add(move2_2);
		secondSequence.add(move3_2);
		secondSequence.add(move4_2);

		expectedResult.add(firstSequence);
		expectedResult.add(secondSequence);

		assertEquals(expectedResult, actualResult);
	}


}
