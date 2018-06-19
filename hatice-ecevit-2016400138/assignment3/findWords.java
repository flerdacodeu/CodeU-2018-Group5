import java.util.ArrayList;

public class findWords {
    /**
     * This method
     * @param grid is the grid of letters.
     * @param rowSize is the number of rows that the grid has.
     * @param columnSize is the number of columns that the grid has.
     * @return an arraylist filled with words that has obtained from the grid.
     */
    public static ArrayList<String> wordSearch (char[][] grid, int rowSize, int columnSize){
        boolean[][] visited = new boolean[rowSize][columnSize];
        String word = "";
        ArrayList<String> words = new ArrayList<String>();
        for (int i = 0; i < rowSize; i++){
            for (int j = 0; j < columnSize; j++){
                wordSearch(i, j, grid, word, visited, words, rowSize, columnSize);
                visited = new boolean[rowSize][columnSize];
            }
        }
        return words;
    }

    /**
     *
     * @param row index of the row.
     * @param column index of the column.
     * @param grid grid of characters.
     * @param word sequence of letters that has been obtained by the grid recursively.
     * @param visited a boolean array that stores the information if a character has been visited or not.
     * @param words arraylist of words that has been formed using grid.
     * @param rowSize number of rows.
     * @param columnSize number of columns.
     */
    public static void wordSearch(int row, int column, char[][] grid, String word, boolean[][] visited, ArrayList<String> words, int rowSize, int columnSize){
        Dict dictionary = new Dict();
        String s = word + grid[row][column];
        if (!dictionary.isPrefix(s))
            return;
        if (dictionary.isWord(s) && !words.contains(s)){
            words.add(s);
        }
        word = s;
        visited[row][column] = true;
        for (int i = row - 1; i <= row + 1 && i < rowSize; i++) {
            for (int j = column -1; j <= column + 1 && j < columnSize; j++) {
                if ( i >= 0 && j >= 0 && !visited[i][j]) {
                    wordSearch(i, j, grid, word, visited, words, rowSize, columnSize);
                    visited[i][j] = false;
                }
            }
        }
    }
}
