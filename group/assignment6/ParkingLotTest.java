import static org.junit.Assert.*;

import org.junit.Test;

public class ParkingLotTest {

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
		//just to test the behavior over a standard case
		fail("Not yet implemented");
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
