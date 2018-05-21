//Q1: Given two strings, determine if one is an anagram of the other.
//Parts: 1)stringSensitive()
//		 2)stringInsensitive()
//		 3)sentences()
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Anagrams {

	
	//-----------------first part of the question------------------------------
	public static boolean stringSensitive(String firstWord, String secondWord)
	{
		//we could first check if those words are the same length
		if(firstWord.length()==secondWord.length()) 
		{
			Map<Character, Integer> firstMap = createCharMap(firstWord);
			Map<Character, Integer> secondMap = createCharMap(secondWord);
			
			if(firstMap.equals(secondMap))
				return true;
			else
				return false;
		}
		return false;
	}
	
	//-------------------second part of the question-----------------------
	public static boolean stringInensitive(String firstWord, String secondWord)
	{
		//we don't want to distinguish lower and upper case letter, so we
		//just turn all of them to lower
		firstWord.toLowerCase();
		secondWord.toLowerCase();
		
		return stringSensitive(firstWord, secondWord);
	}
	
	//--------------third part of the question----------------------
	public static boolean sentences(String firstSentence, String secondSentence)
	{
		String[] firstSentenceWords = firstSentence.split("\\W+");
		String[] secondSentenceWords = secondSentence.split("\\W+");
		
		List<Map<Character, Integer>> firstListOfMaps = new ArrayList<Map<Character, Integer>>();
		List<Map<Character, Integer>> secondListOfMaps = new ArrayList<Map<Character, Integer>>();
		
		if(firstSentenceWords.length==firstSentenceWords.length)
		{
			//we know that sentences have the same length 
			for(int index=0; index < firstSentenceWords.length; index++)
			{
				firstListOfMaps.add(createCharMap(firstSentenceWords[index]));
				secondListOfMaps.add(createCharMap(secondSentenceWords[index]));
			}
			
			if(firstListOfMaps.containsAll(secondListOfMaps))
				return true;
			else
				return false;
		}
		return false;
	}
	
	//method helper for creating a hashmap for a word
	public static Map<Character, Integer> createCharMap(String word)
	{
		//variable for map, though, not needed :))
		Map<Character, Integer> mapCreated = new HashMap<Character,Integer>();
		
		//char array for characters in string
		char[] charInString = word.toCharArray();
		
		for(char charKey : charInString)
		{
			//if the character already appears in the string, we increase its value in the map
			if(mapCreated.containsKey(charKey))
			{
				mapCreated.put(charKey, mapCreated.get(charKey)+1);
			}
			else
			{
				mapCreated.put(charKey, 1);
			}
		}
		
		return mapCreated;
	}//createCharMap method
}
