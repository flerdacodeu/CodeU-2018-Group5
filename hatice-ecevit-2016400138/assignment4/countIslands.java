
public class countIslands {
    /**
     *
     * @param map is the map of tiles given.
     * @param row number of rows.
     * @param column number of columns.
     * @return -1 if the map is null, number of islands otherwise.
     */
    public static int findNumOfIslands (boolean[][] map, int row, int column) {
        if (map == null)
            return -1;
        int numOfIslands = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (map[i][j]) {
                    if (i > 0 && j > 0 && map[i-1][j] && map[i][j-1])
                        numOfIslands--;
                    if (i != 0 && !map[i-1][j] && j != 0 && !map[i][j-1])
                        numOfIslands++;
                    if (i == 0 && j != 0 && !map[i][j-1])
                        numOfIslands++;
                    if (j == 0 && i != 0 && !map[i-1][j])
                        numOfIslands++;
                    if (i == 0 && j == 0)
                        numOfIslands++;
                }
            }
        }
        return numOfIslands;
    }
}
