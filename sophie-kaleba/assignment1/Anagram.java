import java.util.Arrays;
import java.util.List;

/**
 * Question 1 
 * Given two strings, determine if one is an anagram of the other.
 * Two words are anagrams of each other if they are made of the same letters in a different order.
 * Optional follow-ups:
 * - make the algorithm able to handle both case sensitive and case insensitive anagrams
 * - make the algorithm able to handle anagrams of sentences, where each word in the resulting sentence is an anagram of one of the wod in the original sentence
 */
public class Anagram {
	
	/**
	* Check if the two arguments given as parameter are anagrams. Handle both case sensitive and case insensitive comparisons.
	* example: areAnagrams(listen, silent, false) returns True, areAnagrams(Listen, silent, true) returns False, areAnagrams(apple, pabble, true) returns False
	* @param element1 - the 1st element
	* @param element2 - the 2d element
	* @param caseSenstive - true if the letter case is important ("listen" != "Listen")
	* @return true if the 2 elements are anagrams, false otherwise
	*
	*/
	public static boolean areAnagrams(String element1, String element2, boolean caseSensitive) {
	    /* the element can be a sentence, split it according the standard control characters*/
		String[] splittedElement1 = element1.split(",|\\;| |\\:|\\.");
		String[] splittedElement2 = element2.split(",|\\;| |\\:|\\.");
		
		/* if the element returned by split contains at least 2 words, this is a sentence */
		if (splittedElement1.length>1 || splittedElement2.length>1 ) {
		    return Anagram.AreAnagramsSentences(splittedElement1, splittedElement2);
		}
		return Anagram.AreAnagramsWords(splittedElement1[0], splittedElement2[0], caseSensitive);
	}

	
	/**
	 * compares 2 sentences and check if they are anagrams
	 * @param sentence1 - the 1st sentence
	 * @param sentence2 - the 2d sentence
	 * @return true if the 2 sentences are anagrams, false otherwise
	 */
	public static boolean AreAnagramsSentences(String[] sentence1, String[] sentence2) {
		List<String> s1 = Arrays.asList(sentence1);
		List<String> s2 = Arrays.asList(sentence2);
		
		/* must contains the same amount of words to be anagrams */
		if (s1.size() == s2.size()) {
			for (String word : sentence1) {
				int i = 0;
				boolean found = false;
				while (!found) {
					if (i==s2.size()) return false; /* the anagram of this word is not in the resulting sentence */
					found = Anagram.AreAnagramsWords(word, s2.get(i), false);
					i++;
				}
			}
			return true;
		}
	    return false;
	} 
	
	/**
	 * compares 2 words and check if they are anagrams
	 * @param word1 - the 1st word
 	 * @param word2 - the 2d word
	 * @param caseSenstive - true if the letter case is important ("listen" != "Listen")
	 * @return true if the 2 words are anagrams, false otherwise
	 */
	public static boolean AreAnagramsWords(String word1, String word2, boolean caseSensitive) {
	    /* if case insensitive, then convert the 2 strings to lower case*/
	    if (!caseSensitive) {
			word1 = word1.toLowerCase();
			word2 = word2.toLowerCase();
		}
		
		/* sort the 2 strings */ 
		char[] word1InChar = word1.toCharArray();
		char[] word2InChar = word2.toCharArray();
		Arrays.sort(word1InChar);
		Arrays.sort(word2InChar);
		
		/* compare the 2 strings */
        return Arrays.equals(word1InChar,word2InChar);
	} 


}
