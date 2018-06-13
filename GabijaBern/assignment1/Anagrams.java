package Task1;
/*Q1: Given two strings, determine if one is an anagram of the other.
*Parts: 1)stringSensitive()
*		 2)stringInsensitive()
*		 3)sentences()
*/
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Anagrams {

/* stringSensitive is a method for checking if two given words are anagrams of each other
 * @return returns true if given words are anagrams, upper case letter is not the same as lower case letter
 * @param firstWord String holding first word
 * @param secondWord String holding second word for comparings
 */
public static boolean stringSensitive(String firstWord, String secondWord)
{
	//we could first check if those words are the same length
	if(firstWord.length()!=secondWord.length()){ 
		return false;
	}
	
	Map<Character, Integer> firstMap = createCharMap(firstWord);
	Map<Character, Integer> secondMap = createCharMap(secondWord);
		
	return firstMap.equals(secondMap);
}

/*stringInsensitive method for checking if two words are anagrams 
 * @return boolean returns true if given two words are anagrams of each other ignoring upper case letters
 */
public static boolean stringInsensitive(String firstWord, String secondWord){

	//we don't want to distinguish lower and upper case letter, so we
	//just turn all of them to lower
	firstWord = firstWord.toLowerCase();
	secondWord = secondWord.toLowerCase();
	
	return stringSensitive(firstWord, secondWord);	
}

/*
 * Method for checking if given two sentences are anagrams of each other
 * 
 * @return returns true if two sentences are anagrams of each other
 */
public static boolean sentences(String firstSentence, String secondSentence){
	
	int howManyWordsMatched = 0;
	
	String[] firstSentenceWords = firstSentence.split("\\W+");
	String[] secondSentenceWords = secondSentence.split("\\W+");
		
	List<Map<Character, Integer>> firstListOfMaps = new ArrayList<Map<Character, Integer>>();
	List<Map<Character, Integer>> secondListOfMaps = new ArrayList<Map<Character, Integer>>();
		
	if(firstSentenceWords.length==firstSentenceWords.length){
		//we know that sentences have the same length 
		for(int index=0; index < firstSentenceWords.length; index++){
			firstListOfMaps.add(createCharMap(firstSentenceWords[index]));
			secondListOfMaps.add(createCharMap(secondSentenceWords[index]));
		}
		
		for(int index=0; index<firstSentenceWords.length; index++){
			secondListOfMaps.remove(firstListOfMaps.get(index));
			howManyWordsMatched++;
		}
		
		if(firstSentenceWords.length==howManyWordsMatched) {
			return true;
		}
	}
	return false;
}
	
/*
 * Method helper for creating a hashmap for a word
 * @return Map<Character, Integer> returns a map with characters and their frequency
 */
public static Map<Character, Integer> createCharMap(String word){
	//variable for map, though, not needed :))
	Map<Character, Integer> mapCreated = new HashMap<Character,Integer>();
		
	//char array for characters in string
	char[] charInString = word.toCharArray();
		
	for(char charKey : charInString){
		//if the character already appears in the string, we increase its value in the map
		if(mapCreated.containsKey(charKey)){
			mapCreated.put(charKey, mapCreated.get(charKey)+1);
		}
		else {
			mapCreated.put(charKey, 1);
		}
	}
		
	return mapCreated;
}//createCharMap method
}