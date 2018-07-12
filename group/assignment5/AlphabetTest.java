import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class AlphabetTest {
	
	@Test(expected = IllegalArgumentException.class)
	public final void emptyDictionary() {
		String[] dictionary = {};
		Alphabet alpha = new Alphabet(dictionary);
		alpha.build();
	}
	
	
	@Test
	public final void oneWordDictionary() {
		String[] dictionary = {"act"};
		Alphabet alpha = new Alphabet(dictionary);
		List<Character> expectedResult = Arrays.asList('a','c','t');
		assertEquals(expectedResult, alpha.build());	
	}
	
	
	@Test
	public final void sameFirstLetterDictionary() {
		String[] dictionary = {"act", "aca", "aat", "art" };
		Alphabet alpha = new Alphabet(dictionary);
		List<Character> expectedResult = Arrays.asList('t','c','a','r');
		assertEquals(expectedResult, alpha.build());	
	}
	
	@Test
	/**
	 * the first letter of the alphabet is not the first letter of the first word of the dictionary
	 */
	public final void hiddenFirstCharacterDictionary() {
		String[] dictionary = {"aat", "rat", "czt", "cat" };
		Alphabet alpha = new Alphabet(dictionary);
		List<Character> expectedResult = Arrays.asList('z','t', 'a','r','c');
		assertEquals(expectedResult, alpha.build());			
	}
	
	
	@Test(expected= InvalidDictionaryException.class)
	public final void invalidDictionary() {
		String[] dictionary = {"art", "aat", "azt", "art" };
		Alphabet alpha = new Alphabet(dictionary);
		alpha.build();	
	}
	
	
	@Test
	public final void differentLengthWords() {
		String[] dictionary = {"aat", "raft", "czt", "catym" };
		Alphabet alpha = new Alphabet(dictionary);
		List<Character> expectedResult = Arrays.asList('m', 'y', 'z', 'f', 't', 'a', 'r', 'c');
		assertEquals(expectedResult, alpha.build());
	}
	
	
	
}