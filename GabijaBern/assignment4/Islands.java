/*
 * Assignment 4 for searching all islands on the tiles map
 * In the map of tiles, water is represented as FALSE and land as TRUE
 * Lands can be connected in 4 directions (adjacent horizontally or vertically, but not diagonally)
 */
public class Islands {

	/*
	 * Method for finding all islands on the tilesMap
	 * 
	 * @return returns the number of islands on the map
	 * 
	 * @param tilesMap given tiles map that stores the water and land tiles (True- land, false- water)
	 */
	public int numberOfIslands(boolean[][] tilesMap) {
		
		int howManyIslands = 0;
		
		if(tilesMap == null) {
			return 0;
		}
		
		if(tilesMap.length==0 && tilesMap[0].length==0) {
			return 0;
		}
		for(int row=0; row<tilesMap.length; row++) {
			for(int column=0; column<tilesMap[0].length; column++ ) {
				if(tilesMap[row][column]) {
					howManyIslands++;
					recursiveSearch(row, column, tilesMap);
				}
			}
		}
		
		return howManyIslands;
	}
	/*
	 * method that is called if we find the land tile, it finds other cells connected to that land 
	 * tile and turns them into water tiles, because they become irrelevant in searching for new islands
	 * 
	 * @param rowIndex index of the row where we found a new land tile
	 * @param columnIndex index of the column where we found a new land tile
	 * @param tilesMap map of tiles in which land is stored as TRUE and water as FALSE
	 */
	public void recursiveSearch(int rowIndex, int columnIndex, boolean[][] tilesMap) {
		
		if(rowIndex>=tilesMap.length || rowIndex< 0 || columnIndex>=tilesMap[0].length
			|| columnIndex<0 || !tilesMap[rowIndex][columnIndex]) {
			
			return;
		}
		
		tilesMap[rowIndex][columnIndex]=false;
		
		recursiveSearch(rowIndex+1, columnIndex, tilesMap);
		recursiveSearch(rowIndex-1, columnIndex, tilesMap);
		recursiveSearch(rowIndex, columnIndex+1, tilesMap);
		recursiveSearch(rowIndex, columnIndex-1, tilesMap);
	}
}
