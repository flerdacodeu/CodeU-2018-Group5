import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class AlphabetTest {
	
	@Test(expected = IllegalArgumentException.class)
	public final void emptyDictionary() {
		String[] dictionary = {};
		Alphabet.build(dictionary)
	}
	
	
	@Test
	public final void oneWordDictionary() {
		String[] dictionary = {"act"};
		List<Character> expectedResult = Arrays.asList('a','c','t');
		assertEquals(Alphabet.build(dictionary), expectedResult);	
	}
	
	
	@Test
	public final void sameFirstLetterDictionary() {
		String[] dictionary = {"act", "aca", "aat", "art" };
		List<Character> expectedResult = Arrays.asList('c','t','a','r'); //only valid result = c,t,a,r
		assertEquals(Alphabet.build(dictionary), expectedResult);	
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