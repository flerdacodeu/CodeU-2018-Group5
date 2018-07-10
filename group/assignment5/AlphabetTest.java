import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

public class AlphabetTest {
	
	@Test
	public final void emptyDictionary() {
		String[] dictionary = {};
		assertTrue(false);
	}
	
	
	@Test
	public final void oneWordDictionary() {
		String[] dictionary = {"act"};
		assertTrue(false);		
	}
	
	
	@Test
	public final void sameFirstLetterDictionary() {
		String[] dictionary = {"act", "aca", "aat", "art" };
		//TODO - only valid result = c,t,a,r
		assertTrue(false);		
	}
	
	@Test
	/**
	 * the first letter of the alphabet is not the first letter of the first word of the dictionary
	 */
	public final void hiddenFirstCharacterDictionary() {
		String[] dictionary = {"aat", "rat", "czt", "cat" };
		//TODO - possible result = z,a,r,c,t
		assertTrue(false);		
	}
	
	
	@Test
	public final void invalidDictionary() {
		String[] dictionary = {"art", "aat", "azt", "art" };
		assertTrue(false);
	}
	
	
	@Test
	public final void differentLengthWords() {
		String[] dictionary = {"aat", "raft", "czt", "catym" };
		//TODO - possible result = z,a,r,c,t,f,m
		assertTrue(false);
	}
	
	
	
}