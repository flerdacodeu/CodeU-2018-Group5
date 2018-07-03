
public class Point {
	
	int x;
	int y;
	
	public Point (int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point topNeighbour() {
		return new Point(x, y-1);
		
	}
	
	
	public Point bottomNeighbour() {
		return new Point(x, y+1);
		
	}
	
	public Point rightNeighbour() {
		return new Point(x+1, y);
		
	}
	
	public Point leftNeighbour() {
		return new Point(x-1, y);
	}
}
