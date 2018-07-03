/**
 * represents a tile in a 2-dimensional map filled with booleans
 */
public class Tile {
	
	private int col; // the column index of the tile in the map
	private int row;  // the row index of the tile in the map
	
	public Tile (int col, int row) {
		this.col = col;
		this.row = row;
	}
	
	public int getCol() {
		return this.col;
	}
	
	public int getRow() {
		return this.row;
	}
	
	/**
	 * @return the tile potentially above this tile
	 */
	public Tile topNeighbour() {
		return new Tile(col, row-1);	
	}
		
	/**
	 * @return the tile potentially below this tile
	 */
	public Tile bottomNeighbour() {
		return new Tile(col, row+1);
		
	}
	
	/**
	 * @return the tile potentially on the right of this tile
	 */
	public Tile rightNeighbour() {
		return new Tile(col+1, row);
		
	}
	
	/**
	 * @return the tile potentially on the left of this tile
	 */
	public Tile leftNeighbour() {
		return new Tile(col-1, row);
	}
}
