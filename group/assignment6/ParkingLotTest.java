import static org.junit.Assert.*;

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
	

	@Test
	public void testRearrangeCars_emptyParkingSlot() {
		//should raise an exception if no car in the parking slot
		fail("Not yet implemented");
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
	
	@Test
	public void testRearrangeCars_inconsistentStates() {
		//should raise an exception if the start and end states are inconsistent
		// possible inconsistencies:
		//- not the same amount of parking spots
		//- same amount of spots but non-matching spot identifiers btwn the states
		//- not the same amount of cars 
		//- same amount of cars but non-matching car identifiers btwn the states
		//(so potentially 4 test cases for 4 inconsistent states)
		fail("Not yet implemented");
	}

	
	@Test
	public void testGetFirstCarInWrongSpace_commonCase() {
		//just to test the behavior over a standard case
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetFirstCarInWrongSpace_nullStartState() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetFirstCarInWrongSpace_nullEndState() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetFirstCarInWrongSpace_emptyParkingSlot() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetFirstCarInWrongSpace_allCardsInRightSpot() {
		fail("Not yet implemented");
	}

}
