import java.util.List;

public class Alphabet {

	public String[] dictionary;
	public Graph unknownAlphabet;
	
	public Alphabet(String[] dictionary) {
		this.dictionary = dictionary;
		this.unknownAlphabet = new Graph();
	}
	
	public List<Character> build() {
		int dictSize = this.dictionary.length;
		
		for (int i = 0; i < dictSize-1 ; i++) {
			this.compareWords(this.dictionary[i], this.dictionary[i+1]);
		}
		return this.sort(this.unknownAlphabet);
	}
	
	public List<Character> sort(Graph unsortedAlphabet) {
		
	}
	

	
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
			this.compareChar(charWord1, charWord2, firstDifference);
		}
		
		// the last letters of the longest word has to be part of the alphabet
		if (longestWord == 1) this.browseLastLettersOfLongest(word1, sizeWord1, i);
		else if (longestWord == 2) this.browseLastLettersOfLongest(word2, sizeWord2, i);
		
		
	
	}
	
	public void compareChar(char c1, char c2, boolean firstDifference) {
		this.unknownAlphabet.addVertex(c1);
		this.unknownAlphabet.addVertex(c2);
		
		if (firstDifference && (c1 != c2)) {
			this.unknownAlphabet.updateEdge(c1, c2);
			firstDifference = false;
		}			
	}
	
	
	public void browseLastLettersOfLongest(String word, int wordSize, int i) {
			for (; i<wordSize; i++) {
				this.unknownAlphabet.addVertex(word.charAt(i));
			}
	}
	
	
	
}
