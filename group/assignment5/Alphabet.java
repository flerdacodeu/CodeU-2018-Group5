import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * 
 * @author sophie
 *
 */
public class Alphabet {

	public String[] dictionary;
	public Graph unknownAlphabet;
	public enum VertexState {WHITE, GREY, BLACK};
	
	public Alphabet(String[] dictionary) {
		this.dictionary = dictionary;
		this.unknownAlphabet = new Graph();
	}
	
	/**
	 * 
	 * @return
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
		
		this.unknownAlphabet.displayContent();
		return this.sort(this.unknownAlphabet);
	}
	
	/**
	 * 
	 * @param unsortedAlphabet
	 * @return
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
	 * 
	 * @param unsortedAlphabet
	 * @param marked
	 * @param result
	 * @param c
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
	 * 
	 * @param word1
	 * @param word2
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
		
		if (sizeWord1 > sizeWord2) { longestWord = 1;}
		else {longestWord = 2;}
		
		for (; i < Math.min(sizeWord1, sizeWord2); i++) {
			char charWord1 = word1.charAt(i);
			char charWord2 = word2.charAt(i);
			if (!this.compareChar(charWord1, charWord2, firstDifference)) {
				firstDifference = false;
			}
		}
		
		// the last letters of the longest word has to be part of the alphabet
		if (longestWord == 1) this.browseLastLettersOfLongest(word1, sizeWord1, i);
		else if (longestWord == 2) this.browseLastLettersOfLongest(word2, sizeWord2, i);
		
		
	
	}
	
	/**
	 * 
	 * @param c1
	 * @param c2
	 * @param firstDifference
	 */
	public boolean compareChar(char c1, char c2, boolean firstDifference) {
		System.out.println(c1+" VS. "+c2);
		this.unknownAlphabet.addVertex(c1);
		this.unknownAlphabet.addVertex(c2);
		
		if (firstDifference && (c1 != c2)) {
			this.unknownAlphabet.updateEdge(c1, c2);
			return false;
		}	
		return true;
	}
	
	/**
	 * 
	 * @param word
	 * @param wordSize
	 * @param i
	 */
	public void browseLastLettersOfLongest(String word, int wordSize, int i) {
			for (; i<wordSize; i++) {
				this.unknownAlphabet.addVertex(word.charAt(i));
			}
	}
	
	
	
}
