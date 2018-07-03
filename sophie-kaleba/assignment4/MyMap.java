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
 * Represents a grid of characters
 */
public class MyMap {

	private List<boolean[]> content;
	private int width; /* number of columns in the grid */


	/**
	 * build grid out of a string containing characters
	 * @param prefilledGrid - the string to be turned into a grid
	 * @param wantedWidth - the wanted width of the grid
	 */
	public MyMap(String prefilledGrid, int wantedWidth) throws IllegalArgumentException {
		if (prefilledGrid.isEmpty() || prefilledGrid == " " || (wantedWidth == 0)) {
			throw new IllegalArgumentException();
		}
		
		this.content = new ArrayList<boolean[]>();
		this.width = wantedWidth;
		
		String[] allChar = prefilledGrid.split(" ");
		int gridSize = allChar.length;
		boolean[] allBooleans = this.stringArraytoBoolean(allChar, gridSize);

		if (gridSize >= wantedWidth){
				if (gridSize % wantedWidth == 0) {
					this.initContents(gridSize, wantedWidth, allBooleans);
				}
				/* the wanted width would result in unbalanced number of columns by row*/
				else {
					int newWidth = 2;
					while (gridSize % newWidth != 0) {
						newWidth += 1;
					}
					this.initContents(gridSize, newWidth, allBooleans);			
				}
		}
		else {
			this.content.add(allBooleans);
		}
	}
	
	public boolean[] stringArraytoBoolean(String[] content, int size) {
		boolean[] result = new boolean[size];
		for (int i=0; i < size ; i++) {
			result[i] = Boolean.getBoolean(content[i]);
		}
		return result;	
	}

	
	/**
	 * initialize the grid content and the marked grid content
	 * @param gridSize - the total number of chars in the grid
	 * @param width - the number of columns in the grid
	 * @param allChars - the chars that will fill the grid
	 */
	public void initContents(int gridSize, int width, boolean[] allBooleans) {
		for (int i=0; i< gridSize ; i+=width) {
			int[] myArray = new int[width];
			this.content.add(Arrays.copyOfRange(allBooleans, i, i+width));
		}
	}
		
	
	public static int getNumberOfIslands(int rows, int columns, boolean[][] map) {
		
		int numberOfIslands = 0;
		
		for (int i = 0 ; i < columns ; i++) {
			for (int j = 0 ; j < rows ; j++) {
				
				Point currentPoint = new Point(i,j);
				if (MyMap.isLand(currentPoint,  map)) {
					MyMap.fill(currentPoint, rows, columns, map);
					numberOfIslands+=1;
				}		
			}
		}
		return numberOfIslands;
	}

	public static void fill(Point point, int rows, int columns, boolean[][] map) {
		
		if (MyMap.isValid(point, rows, columns)) {
			map[point.x][point.y] = false;
			MyMap.fill(point.topNeighbour(), rows, columns, map);
			MyMap.fill(point.leftNeighbour(), rows, columns, map);
			MyMap.fill(point.rightNeighbour(), rows, columns, map);
			MyMap.fill(point.bottomNeighbour(), rows, columns, map);
		}
	}
	
	
	public static boolean isValid(Point point, int rows, int columns) {
		return point.getX()>=0 && point.getX()<columns && point.getY()>=0 && point.getY()<rows;   	
	}

	
	public static boolean isLand(Point point, boolean[][] map) {
		return map[point.getY()][point.getX()];
	}

	

}