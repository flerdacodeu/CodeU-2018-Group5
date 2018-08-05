import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.Test;

public class StateTest {


	@Test
	public void testParkCarInSpace_emptySpot() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testParkCarInSpace_takenSpot() {
		fail("Not yet implemented");
	}
	

	@Test(expected=IllegalArgumentException.class)
	public void testStateBuilder_emptyParkingSlot() {
		new State(Arrays.asList(-1,-1,-1,-1));
	}

}
