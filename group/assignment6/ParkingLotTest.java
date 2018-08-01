import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ParkingLotTest {
	
	public List<Car> generate6cars() {
		List<Car> cars = new ArrayList<Car>();
		cars.add(new Car(1));
		cars.add(new Car(2));
		cars.add(new Car(3));
		cars.add(new Car(4));
		cars.add(new Car(5));
		cars.add(new Car(6));
		return cars;
	}
	
	public List<ParkingSpace> generate6spaces() {
		List<ParkingSpace> spaces = new ArrayList<ParkingSpace>();
		spaces.add(new ParkingSpace(1));
		spaces.add(new ParkingSpace(2));
		spaces.add(new ParkingSpace(3));
		spaces.add(new ParkingSpace(4));
		spaces.add(new ParkingSpace(5));
		spaces.add(new ParkingSpace(6));
		return spaces;
	}
	

	@Test
	public void testRearrangeCars_emptyParkingSlot() {
		//should raise an exception if no car in the parking slot
		fail("Not yet implemented");
	}
	
	@Test
	public void testRearrangeCars_nullStartState() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testRearrangeCars_nullEndState() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testRearrangeCars_commonCase() {
		State startState = new State(Arrays.asList(1,2,-1,3));
		State endState = new State(Arrays.asList(3,1,2,-1));
		ParkingLot lot = new ParkingLot();
		List<Move> actualResult = lot.rearrangeCars(startState, endState);
		
		//expected result set-up
		List<Move> expectedResult = new ArrayList<Move>(); 
		List<Car> cars = this.generate6cars();
		List<ParkingSpace> spaces = this.generate6spaces();
		Move move1 = new Move(cars.get(1), spaces.get(1), spaces.get(2));
		Move move2 = new Move(cars.get(0), spaces.get(0), spaces.get(1));
		Move move3 = new Move(cars.get(2), spaces.get(3), spaces.get(0));
		expectedResult.add(move1);
		expectedResult.add(move2);
		expectedResult.add(move3);
		
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
