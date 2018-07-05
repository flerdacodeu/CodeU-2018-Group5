package task1;
/* 
 * Task: to find all possible words in the grid
 * 
 * We are given another class Dictionary that has 2 methods:
 * 1) isWord(string) returns whether the given string is a valid word
 * 2) isPrefix(string) returns whether the given string is a prefix of at least one word in the dictionary
 */

import java.util.HashSet;
import java.util.Set;


public class WordSearch {
	
	//since we are given a dictionary, we assume that words there are sorted
	public Set<String> getWordsSet(Cell[][] charsMatrix, Dictionary dictionary){
		
	Set<String> foundWords = new HashSet<String>();
	String stringForWord;
	
	for(int indexRow = 0; indexRow<charsMatrix.length; indexRow++) {
		for(int indexColumn = 0; indexColumn<charsMatrix[0].length; indexColumn++) {
			stringForWord = "";
			findWord(charsMatrix, dictionary, indexRow, indexColumn, foundWords, stringForWord);
		}
	}
		
	return foundWords;
	}
	/*
	 * Method that searches all possible words from a given cell
	 * @param charsMatrix given grid that contains the characters
	 * @param dictionary dictionary that contains valid words and prefixes
	 * @param posX column coordinate
	 * @param posY row coordinate
	 * @param wordsFromChar set of all found words from a given "start" char
	 * @param possibleWord list variable for storing characters and cheking if their string representation is a valid word
	 */
	public void findWord(Cell[][] charsMatrix, Dictionary dictionary, int posX, int posY, Set<String> wordsFromChar, String possibleWord) {
		
		possibleWord = possibleWord + charsMatrix[posX][posY].value;
		
		//we need to increase efficiency and not continue if word is not a prefix of any word in dictionary
		if(!dictionary.isPrefix(possibleWord)) {
			return;
		}
		//if it is a valid word -> we add it to the set
		if(dictionary.isWord(possibleWord)) {
			wordsFromChar.add(possibleWord);
		}
		
		charsMatrix[posX][posY].visited = true;
		
		//recursive calls for neighbour cells
		if(inGrid(posX-1, posY+1, charsMatrix)){
			findWord(charsMatrix, dictionary, posX-1, posY+1, wordsFromChar, possibleWord);			
		}
		if(inGrid(posX, posY+1, charsMatrix)){
			findWord(charsMatrix, dictionary, posX, posY+1, wordsFromChar, possibleWord);			
		}
		if(inGrid(posX+1, posY+1, charsMatrix)){
			findWord(charsMatrix, dictionary, posX+1, posY+1, wordsFromChar, possibleWord);			
		}
		if(inGrid(posX-1, posY, charsMatrix)){
			findWord(charsMatrix, dictionary, posX-1, posY, wordsFromChar, possibleWord);			
		}
		if(inGrid(posX+1, posY, charsMatrix)){
			findWord(charsMatrix, dictionary, posX+1, posY, wordsFromChar, possibleWord);			
		}
		if(inGrid(posX-1, posY-1, charsMatrix)){
			findWord(charsMatrix, dictionary, posX-1, posY-1, wordsFromChar, possibleWord);			
		}
		if(inGrid(posX, posY-1, charsMatrix)){
			findWord(charsMatrix, dictionary, posX, posY-1, wordsFromChar, possibleWord);			
		}
		if(inGrid(posX+1, posY-1, charsMatrix)){
			findWord(charsMatrix, dictionary, posX+1, posY-1, wordsFromChar, possibleWord);			
		}
		
		charsMatrix[posX][posY].visited = false;
	}
	/*
	 * Method for checking if given position is in grid
	 * which means that coordinates are not out of boundaries and
	 * cell has not been visited before
	 * 
	 * @return boolean returns true if position is in grid
	 * @param x column coordinate
	 * @param y row coordinate
	 * @param charsMatrix given grid
	 */
	public boolean inGrid(int x, int y, Cell[][] charsMatrix) {
		
		if(x>=0 && x<charsMatrix.length && y>=0 && y<charsMatrix[0].length && !charsMatrix[x][y].visited) {
			return true;
		}
		else {
			return false;
		}
	}
}
