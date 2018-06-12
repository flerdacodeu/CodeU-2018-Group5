package task1;

public class Cell {
	public char value;
	public boolean visited;
	
	public Cell(char character) {
		value = character;
		visited = false;
	}
	public boolean isVisited() {
		return visited;
	}
}
