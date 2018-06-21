import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * The grid is not necessarily square
 * The grid only contains single ascii characters
 * The search is case insensitive
 *
 */
public class Grid {

	private List<String[]> content;
	private List<int[]> markedContent;
	private int width;
	private String currentWord;

	/**
	 * build a grid out of a file
	 * @param filename
	 */
	public Grid(String filename) {
		try {
			this.content = Grid.parseFile(filename);
		} catch (IOException | BadGridFileException e) {
			e.printStackTrace();
		}
	}

	/**
	 * build grid out of a string containing characters
	 * TODO - assumption about the good width
	 * TODO - add assertions
	 * @param prefilledGrid - the string to be turned into a grid
	 * @param wantedWidth - the wanted width of the grid
	 */
	public Grid(String prefilledGrid, int wantedWidth) {
		this.markedContent = new ArrayList<int[]>();
		this.width = wantedWidth;
		
		this.content = new ArrayList<String[]>();

		String[] allNumbers = prefilledGrid.split(" ");
		int gridSize = allNumbers.length;

		if (gridSize >= wantedWidth) {
			for (int i=0; i< gridSize ; i+=wantedWidth) {
				int[] myArray = new int[wantedWidth];
				this.markedContent.add(myArray);
				this.content.add(Arrays.copyOfRange(allNumbers, i, i+wantedWidth));
			}
			this.resetMarkedContent();
		}
		else {
			this.content.add(allNumbers);
		}
	}

	/**
	 * return the character at the given position in the grid
	 * @param row - the row index in the grid
	 * @param column - the column index in the grid
	 * @return the character at this position in the grid
	 * @throws IndexOutOfBoundsException
	 */
	String getCharAt(int row, int column) throws IndexOutOfBoundsException {
		if ((0>row && row >this.content.size()) && (0>column && column>this.width)) throw new IndexOutOfBoundsException();
		return this.content.get(row)[column];
	}


	/**
	 * mark a character of the grid as visited
	 * @param row - the row index in the marked grid
	 * @param column - the column index in the marked grid
	 */
	void mark(int row, int column) {
		this.markedContent.get(row)[column] = 1;
	}
	
	
	/**
	 * unmark a character of the grid
	 * @param row - the row index in the marked grid
	 * @param column - the column index in the marked grid
	 */
	void unmark(int row, int column) {
		this.markedContent.get(row)[column] = 0;
	}


	/**
	 * check is character of the grid has already been visited
	 * @param row - the row index in the marked grid
	 * @param column - the column index in the marked grid
	 * @return true if already visited, false otherwise
	 */
	boolean isMarked(int row, int column) {
		return this.markedContent.get(row)[column] == 1;
	}


	/**
	 * set all characters of this grid as non-visited
	 */
	void resetMarkedContent() {
		for (int i = 0 ; i < this.markedContent.size() ; i ++) {
			for (int j = 0 ; j < this.width ; j ++) {
				this.markedContent.get(i)[j] = 0;
			}
		}
	}

	/**
	 * Utter ugliness
	 * for a given character, return the indexes of all reachable neighbours
	 * @param row
	 * @param column
	 * @return the list of all reachable neighbours
	 */
	public List<int[]> allReachableNeighbours(int row, int column) {
		
		List<int[]> result = new ArrayList<int[]>();
		
		if (row - 1 >= 0) {
			result.add(new int[]{row - 1 , column});
			
			if (column - 1 >= 0) {
				result.add(new int[]{row - 1 , column - 1});
			}
			if ((column + 1 < this.width)) {
				result.add(new int[]{row - 1 , column + 1});
			}
		}
		
		if (row + 1 < this.content.size()){
			result.add(new int[]{row + 1 , column});
			
			if (column - 1 >= 0) {
				result.add(new int[]{row + 1 , column - 1});
			}
			if ((column + 1 < this.width)) {
				result.add(new int[]{row + 1 , column + 1});	
			}
		}
		
		if (column - 1 >= 0) {
			result.add(new int[]{row, column - 1});
		}
		if (column + 1 < this.width) {
			result.add(new int[]{row, column + 1});
		}
		
		return result;
	}
	
	
	
//	/**
//	 * 
//	 * @param dictionary
//	 * @param row
//	 * @param column
//	 * @return
//	 */
//	public void wordSearchAux(List<String> dictionary, int row, int column, List<String> foundWords, int startRow, int startColumn) {
//		this.mark(row, column);
//		String potentialWord = this.currentWord+this.getCharAt(row, column);
//		System.out.println(potentialWord);
//		
//		if (!Grid.isPrefix(dictionary, potentialWord)) {
//			this.resetMarkedContent();
//			this.mark(startRow, startColumn);
//			return;
//		}
//		else if (Grid.isWord(dictionary, potentialWord)) {
//			foundWords.add(potentialWord);
//		}
//		this.currentWord = potentialWord;
//
//		List<int[]> neigbours = this.allReachableNeighbours(row, column);
//		for (int[] coordAux : neigbours) {
//			int rowAux = coordAux[0];
//			int columnAux = coordAux[1];
//			
//			if (!this.isMarked(rowAux, columnAux)) {
//		
//				this.wordSearchAux(dictionary, rowAux, columnAux, foundWords, startRow, startColumn);
//			}
//		}
//	}
	
	public void wordSearchAux(List<String> dictionary, int row, int column, List<String> foundWords, int startRow, int startColumn) {

		this.mark(row, column);
		System.out.println("Marking "+row+","+column);
		this.currentWord += this.getCharAt(row, column);
		System.out.println("Current word is: "+this.currentWord);
		
		System.out.println("Looking for "+row+","+column+" neigbours");
		List<int[]> neigbours = this.allReachableNeighbours(row, column);
		for (int[] coordAux : neigbours) {
			int rowAux = coordAux[0];
			int columnAux = coordAux[1];
			System.out.println("Current neighbour is: "+rowAux+","+columnAux);
			
			if (!this.isMarked(rowAux, columnAux)) {
				
				String potentialWord = this.currentWord+this.getCharAt(rowAux, columnAux);
				if (Grid.isPrefix(dictionary, potentialWord)) {
					if (Grid.isWord(dictionary, potentialWord)) {
						foundWords.add(potentialWord);
						System.out.println("adding: "+potentialWord);
					}

					this.wordSearchAux(dictionary, rowAux, columnAux, foundWords, startRow, startColumn);
				}
				else {
					System.out.println("Unmarking "+rowAux+","+columnAux);
					this.unmark(rowAux, columnAux);
					if (this.currentWord.length() >= 2) {
						this.currentWord = this.currentWord.substring(0, this.currentWord.length()-2);
						System.out.println("and tweaking current Word "+this.currentWord);
					}
				}
				
			}
			else {
				System.out.println(rowAux+","+columnAux+" was already marked");
			}
		}
	}
	
	
	public static boolean isPrefix(List<String> dictionary, String word) {
		for (String s : dictionary) {
			if (s.startsWith(word)) {
				return true;
			}
		}
		return false;
	}
	
	
	public static boolean isWord(List<String> dictionary, String word) {
		return dictionary.contains(word);
	}
	

	/**
	 * wanders through the grid to potentially find words from the dictionary
	 * @param dictionary
	 * @return a list if found words from the dictionary
	 */
	public List<List<String>> wordSearch(List<String> dictionary) {

		List<List<String>> result = new ArrayList<List<String>>();
		List<String> localResult = new ArrayList<String>();

		for (int i = 0; i<this.content.size() ; i++) {
			for (int j = 0; j < this.width ; j ++) {
				System.out.println("========================================================================");
				System.out.println("START "+i+" "+j);
				this.resetMarkedContent();
				this.currentWord = ""; 
				this.wordSearchAux(dictionary, i, j, localResult, i, j);
				if (!localResult.isEmpty()) result.add(localResult);
			}
		}
		return result;
	}






	/**
	 * builds a grid filled with Characters out of a file
	 * @param filename - the path to a file
	 * @return - the grid built from the file
	 * @throws IOException - error reading the file
	 * @throws BadGridFileException - the file was malformed (lines of different width)
	 */
	public static List<String[]> parseFile(String filename) throws IOException, BadGridFileException {

		List<String[]> finalGrid = new ArrayList<String[]>();

		try {
			/* open the file */
			File f = new File(filename);
			BufferedReader b = new BufferedReader(new FileReader(f));

			/* the line length must be the same for all lines */
			String line =  b.readLine();
			if (line == null) throw new FileNotFoundException();
			String[] arrayedLine = line.split(" ");
			int gridWidth = arrayedLine.length;

			while ((line = b.readLine()) != null) {
				arrayedLine = line.split(" ");
				int currentWidth = arrayedLine.length;
				if (currentWidth != gridWidth) throw new BadGridFileException("line width:"+currentWidth+", but must be: "+gridWidth);
				finalGrid.add(arrayedLine);
			}

			b.close();
			return finalGrid;

		} catch (IOException e) {
			e.printStackTrace();
			return finalGrid;
		}

	}
	
	
	public List<String[]> getContent() {
		return this.content;
	}
	
	public List<int[]> getMarked() {
		return this.markedContent;
	}
}