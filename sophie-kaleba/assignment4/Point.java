
public class Point {
	
	int col;
	int row;
	
	public Point (int col, int row) {
		this.col = col;
		this.row = row;
	}
	
	int getCol() {
		return this.col;
	}
	
	int getRow() {
		return this.row;
	}
	
	public Point topNeighbour() {
		return new Point(col, row-1);
		
	}
	
	
	public Point bottomNeighbour() {
		return new Point(col, row+1);
		
	}
	
	public Point rightNeighbour() {
		return new Point(col+1, row);
		
	}
	
	public Point leftNeighbour() {
		return new Point(col-1, row);
	}
}
