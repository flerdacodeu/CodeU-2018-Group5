import java.util.ArrayList;
import java.util.List;

public class Alphabet {

	public String[] dictionary;
	public List<Character> unknownAlphabet;
	
	public Alphabet(String[] dictionary) {
		this.dictionary = dictionary;
		this.unknownAlphabet = new ArrayList<Character>();
	}
	
	public List<Character> build() {
		int dictSize = this.dictionary.length;
		
		for (int i = 0; i < dictSize-1 ; i++) {
			char[] auxOrder = this.compareWords(this.dictionary[i], this.dictionary[i+1]);
			this.insertOrder(auxOrder);
		}
		return this.unknownAlphabet;
	}
	
	public void insertOrder(char[] order) {
		
	}
	
	public char[] compareWords(String word1, String word2) {
		if (word1.isEmpty() && word2.isEmpty()) {
			throw new IllegalArgumentException("one of the words is empty");
		}
		
		int i = 0;
		boolean inBounds = true;
		int sizeWord1 = word1.length();
		int sizeWord2 = word2.length();
		char charWord1 = word1.charAt(i);
		char charWord2 = word2.charAt(i);
		
		while ( (charWord1 == charWord2) && inBounds ) {
			i++;
			charWord1 = word1.charAt(i);
			charWord2 = word2.charAt(i);
			if (i > sizeWord1 && i > sizeWord2) {
				i--;
				charWord1 = word1.charAt(i);
				charWord2 = word2.charAt(i);
				inBounds = false;	
			}
		}	
		
		int comparison = charWord1 - charWord2;
		if (comparison > 0) return new char[]{charWord1, charWord2};
		else if (comparison == 0) return new char[]{charWord1};
		else return new char[]{charWord2, charWord1};
	}
	
}
