import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * The grid is a NxM matrix.
 *
 * The algorithm for finding the words from the dictionary that can be formed in the grid works as
 * follows :
 * - if an element of the grid is a valid one-character prefix, a depth-first traversal is performed
 * starting from the element (a trie that has the element as root is also created in order to
 * efficiently check if an element forms a valid prefix with all the elements on the
 * path from the starting element)
 * - as the traversal is performed, a list of prefixes that are valid words is also created
 */

public class WordSearch {

  /**
   * Finds all the words from the dictionary that can be formed in the grid
   *
   * @param grid the grid where the words are searched for
   * @param dict the dictionary that contains valid words
   * @return a set of words from the dictionary that were found in the grid
   * @throws IllegalArgumentException if the given grid or the dictionary are null
   */
  public Set<String> wordSearch (char[][] grid, Dictionary dict) {
    if (grid == null || dict == null) {
      throw new IllegalArgumentException("null grid or dictionary");
    }
    int numerRows = grid.length;
    int numberColumns = grid[0].length;

    ArrayList<String> wordsList = new ArrayList<>();

    for (int row = 0; row < numerRows; row++) {
      for (int column = 0; column < numberColumns; column++) {
        if (dict.isPrefix(String.valueOf(grid[row][column]))) {
          boolean[][] visited = new boolean[numerRows][numberColumns];
          TrieNode[][] newGrid = createTrieGrid(grid, numerRows, numberColumns);
          depthFirstTraversal(newGrid[row][column], newGrid, dict, visited, wordsList);
        }
      }
    }
    return new HashSet<>(wordsList);
  }

  /**
   * Creates a grid of trie nodes that have as values the elements of the original grid.
   *
   * @param grid the original grid
   * @param numberRows the number of rows in the original grid
   * @param numberColumns the number of columns in the original grid
   * @return a new grid of trie nodes
   */
  TrieNode[][] createTrieGrid(char[][] grid, int numberRows, int numberColumns) {
    assert grid != null && numberRows > 0 && numberColumns > 0;
    
    TrieNode[][] newGrid = new TrieNode[numberRows][numberColumns];
    for (int row = 0; row < numberRows; row++) {
      for (int column = 0; column < numberColumns; column++) {
        newGrid[row][column] = new TrieNode(grid[row][column], row, column);
      }
    }
    return newGrid;
  }

  /**
   * Traverses the grid starting from a given element and creates a list of words that
   * start with the value of the given element.
   *
   * @param node the element where the traversal starts
   * @param grid the matrix that contains all the elements
   * @param dict a dictionary of valid words in the given grid
   * @param visited agenda of elements that exist in a word
   * @param words the list of valid words that the algorithm finds
   */
  void depthFirstTraversal (TrieNode node, TrieNode[][] grid, Dictionary dict, boolean[][] visited, ArrayList<String> words) {
    assert node != null && grid != null && dict != null && visited != null && words != null;
   
    LinkedList<TrieNode> neighbours = getNeighbours(node.getRow(), node.getColumn(), grid);
    for (TrieNode neighbour : neighbours) {
      if (!visited[neighbour.getRow()][neighbour.getColumn()]) {
        String prefix = getStringFromRootToNode(node) + String.valueOf(neighbour.getValue());
        if (dict.isPrefix(prefix)) {
          /* create a trie of valid prefixes found in the grid that start with the given element;
           * an element of the grid is added in the trie only if the prefix it forms with the
           * ancestors is valid
           */
          node.addChild(neighbour);
          visited[node.getRow()][node.getColumn()] = true;
          if (dict.isWord(prefix)) {
            neighbour.setIsWord();
            words.add(prefix);
          }
          depthFirstTraversal(neighbour, grid, dict, visited, words);
        }
      }
    }
    // unvisit a grid element such that the same element can be used in two different words
    visited[node.getRow()][node.getColumn()] = false;
  }

  /**
   * Check if the indexes of an element in a grid are valid
   *
   * @param row the row index of the element
   * @param column the column index of the element
   * @param rowBoundary the number of rows in the grid
   * @param columnBoundary the number of columns in a grid
   * @return true if the element has valid indexes within the grid, false otherwise
   */
  boolean isWithinBoundaries (int row, int column, int rowBoundary, int columnBoundary) {
    assert rowBoundary > 0 && columnBoundary > 0;
    if (row >= rowBoundary || column >= columnBoundary || row < 0 || column < 0) {
      return false;
    }
    return true;
  }

  /**
   * Finds the neighbours of a given element in the grid
   *
   * @param row the row of the element
   * @param column the column of the element
   * @param grid the grid that contains the elements
   * @return the neighbours of the element
   */
  LinkedList<TrieNode> getNeighbours(int row, int column, TrieNode[][] grid) {
    assert row >= 0 && column >= 0 && grid != null;
    
    int numerRows = grid.length;
    int numberColumns = grid[0].length;

    LinkedList<TrieNode> neighbours = new LinkedList<>();
    for (int i = row-1; i <= row + 1; i++) {
      for (int j = column-1; j <= column+1; j++) {
        // skip the element whose neighbours are searched for
        if (i == row && j == column) {
          continue;
        }
        if (isWithinBoundaries(i, j, numerRows, numberColumns)) {
          neighbours.add(grid[i][j]);
        }
      }
    }
    return neighbours;
  }

  /**
   * Finds the string starting at the root and ending at the current node
   *
   * @param node the current node
   * @return the prefix up to the current node
   */
  String getStringFromRootToNode(TrieNode node) {
    assert node != null;
    
    StringBuilder prefix = new StringBuilder();
    while (node != null) {
      prefix.insert(0,node.getValue());
      node = node.getParent();
    }
    return prefix.toString();
  }
}
