import java.util.Arrays;

public class Anagram {
	
	/**
	* Check if the two arguments given as parameter are anagrams. Handle both case sensitive and case insensitive comparisons.
	* example: areAnagrams(listen, silent, false) returns True, areAnagrams(Listen, silent, true) returns False, areAnagrams(apple, pabble, true) returns False
	* @param element1 -
	* @param element2 -
	* @param caseSensitive - 
	* @return
	*
	*/
	public static boolean areAnagrams(String element1, String element2, boolean caseSensitive) {
	    /* the element can be a sentence, split it according the standard control characters*/
		String[] splittedElement1 = element1.split(",|\\;| |\\:|\\.");
		String[] splittedElement2 = element2.split(",|\\;| |\\:|\\.");
		
		/* the element returned by split contains at least 2 words, this is a sentence */
		if (splittedElement1.length>1 || splittedElement2.length>1 ) {
		    return Anagram.AreAnagramsSentences(splittedElement1, splittedElement2);
		}
		return Anagram.AreAnagramsWords(splittedElement1[0], splittedElement2[0], caseSensitive);
	}

	public static boolean AreAnagramsSentences(String[] sentence1, String[] sentence2) {
	    return true;
	} 
	
	public static boolean AreAnagramsWords(String word1, String word2, boolean caseSensitive) {
	    /* if case insensitive, then convert the 2 strings to lower case*/
	    if (!caseSensitive) {
			word1.toLowerCase();
			word2.toLowerCase();
		}
		
		/* sort the 2 strings */ 
		char[] word1InChar = word1.toCharArray();
		char[] word2InChar = word2.toCharArray();
		Arrays.sort(word1InChar);
		Arrays.sort(word2InChar);
		
		/* compare the 2 strings */
        return Arrays.equals(word1InChar,word2InChar);
	} 


	public static void main(String[] args) {
		System.out.println(Anagram.areAnagrams("Listen", "silent", true));
	}


}
