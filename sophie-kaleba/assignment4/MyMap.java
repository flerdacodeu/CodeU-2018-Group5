
public class MyMap {
	
	public static int getNumberOfIslands(int rows, int columns, boolean[][] map) {
		
		int numberOfIslands = 0;
		
		for (int i = 0 ; i < rows ; i++) {
			for (int j = 0 ; j < columns ; j++) {
				
				Point currentPoint = new Point(j,i);
				if (MyMap.isLand(currentPoint,  map)) {
					MyMap.fill(currentPoint, rows, columns, map);
					numberOfIslands+=1;
				}		
			}
		}
		return numberOfIslands;
	}

	public static void fill(Point point, int rows, int columns, boolean[][] map) {
		
		
		if (MyMap.isValid(point, rows, columns) && MyMap.isLand(point, map)) {
			map[point.getRow()][point.getCol()] = false;
			MyMap.fill(point.topNeighbour(), rows, columns, map);
			MyMap.fill(point.leftNeighbour(), rows, columns, map);
			MyMap.fill(point.rightNeighbour(), rows, columns, map);
			MyMap.fill(point.bottomNeighbour(), rows, columns, map);
		}
	}
	
	
	public static boolean isValid(Point point, int rows, int columns) {
		return point.getCol()>=0 && point.getCol()<columns && point.getRow()>=0 && point.getRow()<rows;   	
	}

	
	public static boolean isLand(Point point, boolean[][] map) {
		return map[point.getRow()][point.getCol()];
	}

	

}