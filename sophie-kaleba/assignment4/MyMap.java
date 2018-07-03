/**
 * Count the number of islands in a map.
 * The map is a 2 dimensional array containing booleans.
 * Each tile is either water (value=false) or land (value=true).
 * Two tiles belong to the same island if they are both land and are adjacent horizontally or
vertically, but not diagonally.
 * The map is not necessarily square.
 */
public class MyMap {
	
	/**
	 * counts the number of islands in a map
	 * @param rows - the map total number of rows
	 * @param columns - the map total number of columns
	 * @param map - the map
	 * @return the total number of islands
	 */
	public static int getNumberOfIslands(int rows, int columns, boolean[][] map) {
		
		int numberOfIslands = 0;
		
		for (int r = 0 ; r < rows ; r++) {
			for (int c = 0 ; c < columns ; c++) {
				
				Tile currentTile = new Tile(c,r);
				if (MyMap.isLand(currentTile,  map)) {
					MyMap.fill(currentTile, rows, columns, map);
					numberOfIslands+=1;
				}		
			}
		}
		return numberOfIslands;
	}

	/**
	 * for a whole island, turns all the tiles to water (i.e, to "false")
	 * @param tile - the current tile
	 * @param rows - the map total number of rows
	 * @param columns - the map total number of colums
	 * @param map - the map
	 */
	public static void fill(Tile tile, int rows, int columns, boolean[][] map) {
		
		if (MyMap.isValid(tile, rows, columns) && MyMap.isLand(tile, map)) {
			map[tile.getRow()][tile.getCol()] = false;
			MyMap.fill(tile.topNeighbour(), rows, columns, map);
			MyMap.fill(tile.leftNeighbour(), rows, columns, map);
			MyMap.fill(tile.rightNeighbour(), rows, columns, map);
			MyMap.fill(tile.bottomNeighbour(), rows, columns, map);
		}
	}
	
	/**
	 * checks if the given tile exists in the map
	 * @param tile - a tile
	 * @param rows - the map total number of rows
	 * @param columns - the map total number of columns
	 * @return
	 */
	public static boolean isValid(Tile tile, int rows, int columns) {
		return tile.getCol()>=0 && tile.getCol()<columns && tile.getRow()>=0 && tile.getRow()<rows;   	
	}

	
	/**
	 * checks the given tile is a land tile in the map
	 * @param tile - a tile
	 * @param map - the map containing the tile
	 * @return true if the tile is a land tile, false otherwise
	 */
	public static boolean isLand(Tile tile, boolean[][] map) {
		return map[tile.getRow()][tile.getCol()];
	}

	

}