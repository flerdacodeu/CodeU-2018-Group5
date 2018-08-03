import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class UnknownAlphabetTest {

  @Test
  public void testExtractAlphabet_emptyDictionary() {
    UnknownAlphabet unknownAlphabet = new UnknownAlphabet();
    List<Character> alphabet = unknownAlphabet.extractAlphabet(new ArrayList<>());
    assertTrue(alphabet.isEmpty());
  }

  @Test
  public void testExtractAlphabet_oneLetterWords() {
    UnknownAlphabet unknownAlphabet = new UnknownAlphabet();
    List<Character> alphabet = unknownAlphabet.extractAlphabet(Arrays.asList("q","g","m"));
    List<Character> expectedAlphabet = Arrays.asList('q', 'g', 'm');
    assertEquals(expectedAlphabet, alphabet);
  }
  
  @Test
  public void testExtractAlphabet_oneWordDictionary() {
    UnknownAlphabet unknownAlphabet = new UnknownAlphabet();
    List<Character> alphabet = unknownAlphabet.extractAlphabet(Arrays.asList("art"));
    List<Character> expectedAlphabet = Arrays.asList('a', 'r', 't');
    assertEquals(expectedAlphabet, alphabet);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExtractAlphabet_nullDictionary() {
    UnknownAlphabet unknownAlphabet = new UnknownAlphabet();
    unknownAlphabet.extractAlphabet(null);
  }
  
  @Test
  public void testExtractAlphabet_inconsistentDictionary() {
    UnknownAlphabet unknownAlphabet = new UnknownAlphabet();
    List<String> dictionary = Arrays.asList("arm", "arcade", "card", "mad");
    List<Character> alphabet = unknownAlphabet.extractAlphabet(dictionary);
    assertNull(alphabet);
  }

  @Test
  public void testExtractAlphabet_validDictionary() {
    UnknownAlphabet unknownAlphabet = new UnknownAlphabet();
    List<Character> alphabet = unknownAlphabet.extractAlphabet(Arrays.asList("art", "rat", "cat", "car"));
    List<Character> expectedAlphabet = Arrays.asList('a', 't', 'r', 'c');
    assertEquals(expectedAlphabet, alphabet);
  }
  
  @Test
  public void testExtractAlphabet_validDictionary2() {
    UnknownAlphabet unknownAlphabet = new UnknownAlphabet();
    List<Character> alphabet = unknownAlphabet.extractAlphabet(Arrays.asList("art", "try","rat", "ray", "cat", "car"));
    List<Character> expectedAlphabet = Arrays.asList('a', 't', 'r', 'y', 'c');
    assertEquals(expectedAlphabet, alphabet);
  }

  @Test
  public void testExtractAllAlphabets_emptyDictionary() {
    UnknownAlphabet unknownAlphabet = new UnknownAlphabet();
    List<List<Character>> alphabets = unknownAlphabet.extractAllAlphabets(new ArrayList<>());
    assertTrue(alphabets.isEmpty());
  }

  @Test
  public void testExtractAllAlphabets_oneLetterWords() {
    UnknownAlphabet unknownAlphabet = new UnknownAlphabet();
    List<List<Character>> alphabets = unknownAlphabet.extractAllAlphabets(Arrays.asList("a","b","c"));
    List<List<Character>> expectedAlphabets = Arrays.asList(Arrays.asList('a', 'b', 'c'));
    assertEquals(expectedAlphabets, alphabets);
  }
  
  @Test
  public void testExtractAllAlphabets_oneWordDictionary() {
    UnknownAlphabet unknownAlphabet = new UnknownAlphabet();
    List<List<Character>> alphabets = unknownAlphabet.extractAllAlphabets(Arrays.asList("art"));
    List<List<Character>> expectedAlphabets = Arrays.asList(Arrays.asList('a', 'r', 't'), Arrays.asList('a', 't', 'r'),
                                                           Arrays.asList('r', 'a', 't'), Arrays.asList('r', 't', 'a'),
                                                           Arrays.asList('t', 'a', 'r'), Arrays.asList('t', 'r', 'a'));
    assertEquals(expectedAlphabets, alphabets);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExtractAllAlphabets_nullDictionary() {
    UnknownAlphabet unknownAlphabet = new UnknownAlphabet();
    unknownAlphabet.extractAllAlphabets(null);
  }

  @Test
  public void testExtractAllAlphabets_validDictionary() {
    UnknownAlphabet a = new UnknownAlphabet();
    List<List<Character>> alphabets = a.extractAllAlphabets(Arrays.asList("art", "rat", "cat", "car"));
    List<Character> expectedOrder1 = Arrays.asList('a', 't', 'r', 'c');
    List<Character> expectedOrder2 = Arrays.asList('t', 'a', 'r', 'c');
    List<List<Character>> expectedOrderLists = new ArrayList<>();
    expectedOrderLists.add(expectedOrder1);
    expectedOrderLists.add(expectedOrder2);
    assertEquals(expectedOrderLists, alphabets);
  }

   @Test
  public void testIsConsistentDictionary_emptyDictionary() {
    UnknownAlphabet unknownAlphabet = new UnknownAlphabet();
    assertTrue(unknownAlphabet.isConsistentDictionary(new ArrayList<>()));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIsConsistentDictionary_nullDictionary() {
    UnknownAlphabet unknownAlphabet = new UnknownAlphabet();
    assertTrue(unknownAlphabet.isConsistentDictionary(null));
  }

  @Test
  public void testIsConsistentDictionary_inconsistentDictionary() {
    UnknownAlphabet unknownAlphabet = new UnknownAlphabet();
    List<String> dictionary = Arrays.asList("cat", "talk", "tattoo", "lasagna");
    assertFalse(unknownAlphabet.isConsistentDictionary(dictionary));
  }
  
  @Test
  public void testIsConsistentDictionary_inconsistentDictionary1() {
    UnknownAlphabet unknownAlphabet = new UnknownAlphabet();
    List<String> dictionary = Arrays.asList("raspberry", "pineapple", "apple", "apricot");
    assertFalse(unknownAlphabet.isConsistentDictionary(dictionary));
  }

  @Test
  public void testIsConsistentDictionary_consistentDictionary() {
    UnknownAlphabet unknownAlphabet = new UnknownAlphabet();
    List<String> dictionary = Arrays.asList("cat", "car", "tattoo", "alpaca");
    assertTrue(unknownAlphabet.isConsistentDictionary(dictionary));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMakeDictionaryConsistent_nullDictionary() {
    UnknownAlphabet unknownAlphabet = new UnknownAlphabet();
    unknownAlphabet.makeDictionaryConsistent(null);
  }

  @Test
  public void testMakeDictionaryConsistent_emptyDictionary() {
    UnknownAlphabet unknownAlphabet = new UnknownAlphabet();
    List<String> emptyDictionary = new ArrayList<>();
    assertTrue(unknownAlphabet.makeDictionaryConsistent(emptyDictionary).isEmpty());
  }

  @Test
  public void testMakeDictionaryConsistent_consistentDictionary() {
    UnknownAlphabet unknownAlphabet = new UnknownAlphabet();
    List<String> dictionary = Arrays.asList("mother", "father", "grandmother", "grandfather", "uncle", "aunt");
    List<String> newDict = unknownAlphabet.makeDictionaryConsistent(dictionary);
    List<String> expected = Arrays.asList("mother", "father", "grandmother", "grandfather", "uncle", "aunt");
    assertEquals(expected, newDict);
  }

  @Test
  public void testMakeDictionaryConsistent_oneInconsistency() {
    UnknownAlphabet unknownAlphabet = new UnknownAlphabet();
    // cycle generated by rules: r before p and p before r
    List<String> dictionary = Arrays.asList("rope", "rock", "raspberry", "pineapple", "rat", "pin", "apple", "apricot");
    List<String> newDict = unknownAlphabet.makeDictionaryConsistent(dictionary);
    List<String> expected = Arrays.asList("rope", "rock", "raspberry", "rat", "pin", "apricot");
    assertEquals(expected, newDict);
  }

  @Test
  public void testMakeDictionaryConsistent_twoInconsistencies() {
    UnknownAlphabet unknownAlphabet = new UnknownAlphabet();
    // first cycle generated by rules: r before p and p before r
    // second cycle generated by rules: r before a and a before r
    List<String> dictionary = Arrays.asList("rope", "rock", "raspberry", "pineapple", "rat", "pin", "apple", "riddle", "apricot");
    List<String> newDict = unknownAlphabet.makeDictionaryConsistent(dictionary);
    List<String> expected = Arrays.asList("rope", "rock", "raspberry", "rat", "riddle", "apricot");
    assertEquals(expected, newDict);
  }

  @Test
  public void testMakeDictionaryConsistent_threeInconsistencies() {
    UnknownAlphabet unknownAlphabet = new UnknownAlphabet();
    // first cycle generated by rules: i before n and n before i
    // second cycle generated by rules: t before l and l before t
    // third cycle generated by rules: r before t and t before r
    List<String> dictionary = Arrays.asList("daisy", "dandelion", "napkin", "ivy", "island", "rose", "tetris", "term", "lily", "lilac", "literature");
    List<String> newDict = unknownAlphabet.makeDictionaryConsistent(dictionary);
    List<String> expected = Arrays.asList("dandelion", "napkin", "ivy", "island", "rose", "lily", "lilac", "literature");
    assertEquals(expected, newDict);
  }
}
