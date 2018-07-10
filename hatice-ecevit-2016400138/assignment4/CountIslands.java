public class CountIslands {
    /**
     * Count the number of islands in a map. The map is a grid where every space is either land or sea.
     * We assume that the grid is entirely surrounded by water, so anything on the edge has a water border so is
     * an island.
     * Running time is O(NM) where N is the number of rows and M is the number of columns.
     * @param map is the map of tiles given.
     * @return number of islands.
     * @throws IllegalArgumentException if the given map is null.
     */
    public static int findNumOfIslands (boolean[][] map) {
        if (map == null)
            throw new IllegalArgumentException();
        int row = map.length;
        int column =  map[0].length;
        int numOfIslands = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                boolean north = findNeighboursValue(i-1,j,row,column,map);
                boolean west = findNeighboursValue(i,j-1,row,column,map);
                boolean northWest = findNeighboursValue(i-1,j-1,row,column,map);
                if (map[i][j]) {
                    if (i == 0 && j == 0)
                        numOfIslands++;
                    else if (i != 0 && !north && j != 0 && !west)
                        numOfIslands++;
                    else if (i == 0 && j != 0 && !west)
                        numOfIslands++;
                    else if (j == 0 && i != 0 && !north)
                        numOfIslands++;
                    if (i > 0 && j > 0 && north && west && !northWest && !isLastTile(i ,j, row, column))
                        numOfIslands--;
                }
            }
        }
        return numOfIslands;
    }
    public static boolean isLastTile (int i, int j, int rowNumber, int columnNumber) {
        return  i == rowNumber - 1 && j == columnNumber - 1;
    }

    /**
     * This is a simple method to check whether the given indexes are inside of the map. If they are, it returns the value in that index on the map.
     * @param i index of the row.
     * @param j index of the column.
     * @param rowSize number of rows.
     * @param columnSize number of columns.
     * @param map given map.
     * @return false if the given indexes are not valid, value on the map otherwise.
     */
    public static boolean findNeighboursValue (int i, int j, int rowSize, int columnSize, boolean[][] map) {
        if (i >= rowSize || j >= columnSize || i < 0 || j < 0)
            return false;
        return map[i][j];
    }
}
