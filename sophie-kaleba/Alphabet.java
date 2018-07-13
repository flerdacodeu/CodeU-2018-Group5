import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Represents an alphabet class
 * An alphabet instance is bound to a dictionary (an array of strings)
 * This class contains a method to build an alphabet out of the bound dictionary
 */
public class Alphabet {

	public String[] dictionary;
	public Graph unorderedAlphabet;
	public enum VertexState {WHITE, GREY, BLACK};
	
	/**
	 * initialize an alphabet instance bound with a given dictionary
	 * @param dictionary - an ordered dictionary in the form of an array of strings
	 */
	public Alphabet(String[] dictionary) {
		this.dictionary = dictionary;
		this.unorderedAlphabet = new Graph();
	}
	
	/**
	 * build an alphabet out of a dictionary
	 * The alphabet is an ordered list of characters
	 * The dictionary used is an ordered array containing words (strings)
	 * This method will compare words of the dictionary 2 by 2;
	 * it will iterate over both of the words and will add the characters to the alphabet.
	 * @return the alphabet built from the dictionary
	 */
	public List<Character> build() {
		int dictSize = this.dictionary.length;
		if (dictSize == 0) {
			throw new IllegalArgumentException("The dictionary must not be empty");
		}
		
		//the alphabet order can follow the order of the word letters
		else if (dictSize == 1) {
			String uniqueWord = this.dictionary[0];
			List<Character> result = new ArrayList<Character>();
			for (int j = 0; j < uniqueWord.length() ; j++) {
				result.add(uniqueWord.charAt(j));
			}
			return result;
		}
		
		for (int i = 0; i < dictSize-1 ; i++) {
			this.compareWords(this.dictionary[i], this.dictionary[i+1]);
		}
		
		this.unorderedAlphabet.displayContent();
		return this.sort(this.unorderedAlphabet);
	}
	
	/**
	 * Topographically sort an alphabet
	 * @param unsortedAlphabet - an unordered alphabet in the form of a DAG
	 * @return - the ordered alphabet in the form of a List of characters
	 */
	public List<Character> sort(Graph unsortedAlphabet) {
		
		Stack<Character> stack = new Stack<Character>();
		List<Character> result = new ArrayList<Character>();
		Map<Character, VertexState> marked = new LinkedHashMap<Character, VertexState>();
		
		for (Character c : unsortedAlphabet.getContent().keySet()) {
			marked.put(c, VertexState.WHITE);
		}
		
		for (Character c : marked.keySet()) {
			if (marked.get(c) == VertexState.WHITE) {
				this.depthFirstSearch(unsortedAlphabet, marked, stack, c);
			}
		}
		
		while (!stack.isEmpty())
			result.add(stack.pop());
		
		return result;
	}
	
	/**
	 * Depth first search algorithm implementation used in the sort method
	 * @param unsortedAlphabet - an unordered alphabet in the form of a DAG
	 * @param marked - a map stating the state of each vertex in the DAG (BLACK, WHITE or GREY)
	 * @param result - the list containing the ordered alphabet
	 * @param c - the vertex currently browsed
	 */
	public void depthFirstSearch(Graph unsortedAlphabet, Map<Character, VertexState> marked, Stack<Character>result, char c){
		marked.put(c, VertexState.GREY);
		for (Character neighbour : unsortedAlphabet.getContent().get(c)) {
			if (marked.get(neighbour) == VertexState.WHITE) {
				this.depthFirstSearch(unsortedAlphabet, marked, result, neighbour);
			}
		}
		
		marked.put(c, VertexState.BLACK);
		result.add(c);
	}
	

	/**
	 * compare 2 words and (sometimes) add their characters to an unordered alphabet (attribute unknownAlphabet)
	 * A character is added in the unordered alphabet when 2 char are compared and:
	 * - they differ
	 * - they are not part of the alphabet yet
	 * @param word1 - first word to compare
	 * @param word2 - first word to compare
	 */
	public void compareWords(String word1, String word2) {
		if (word1.isEmpty() && word2.isEmpty()) {
			throw new IllegalArgumentException("one of the words is empty");
		}
		
		int sizeWord1 = word1.length();
		int sizeWord2 = word2.length();
		boolean firstDifference = true;
		int longestWord;
		int i = 0;
		
		for (; i < Math.min(sizeWord1, sizeWord2); i++) {
			char charWord1 = word1.charAt(i);
			char charWord2 = word2.charAt(i);
			if (!this.compareChar(charWord1, charWord2, firstDifference)) {
				firstDifference = false;
			}
		}
		
		// the last letters of the longest word has to be part of the alphabet
		if (sizeWord1 > sizeWord2) { longestWord = 1;}
		else {longestWord = 2;}
		
		if (longestWord == 1) this.browseLastLettersOfLongest(word1, sizeWord1, i);
		else if (longestWord == 2) this.browseLastLettersOfLongest(word2, sizeWord2, i);
		
		
	
	}
	
	/**
	 * compare 2 characters. If they differ, then it is possible to order them
	 * the first one is the one coming first in the given alphabet
	 * This method updates the edges for the alphabet being built
	 * @param c1 - the character coming from the first word
	 * @param c2 - the character coming from the second word
	 * @param firstDifference - true if it is the first time one encounters a difference in characters btwn the 2 words
	 */
	public boolean compareChar(char c1, char c2, boolean firstDifference) {
		System.out.println(c1+" VS. "+c2);
		this.unorderedAlphabet.addVertex(c1);
		this.unorderedAlphabet.addVertex(c2);
		
		if (firstDifference && (c1 != c2)) {
			this.unorderedAlphabet.updateEdge(c1, c2);
			return false;
		}	
		return true;
	}
	
	/**
	 * characters are processed and maybe added to the alphabet when 2 words have the same length
	 * if one is longer, this method iterates over the remaining characters and adds them in the alphabet if not already present
	 * @param word - a word from the dictionary
	 * @param wordSize - the size of this word
	 * @param i - the index where we stopped iterating over the words
	 */
	public void browseLastLettersOfLongest(String word, int wordSize, int i) {
			for (; i<wordSize; i++) {
				this.unorderedAlphabet.addVertex(word.charAt(i));
			}
	}
	
	
	
}
