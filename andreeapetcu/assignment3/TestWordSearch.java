import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestWordSearch {

  private WordSearch wordSearch;
  private char[][] validGrid;
  private TrieNode[][] validTrieGrid;
  private int numberRowsGrid;
  private int numberColumnsGrid;
  private TrieDictionary validDict;
  private List<String> validListOfWords;
  private char[][] nullGrid;
  private TrieDictionary nullDict;
  private TrieDictionary emptyDict;
  private List<String> emptyListOfDictionaryWords;
  private boolean[][] validVisited;
  private boolean[][] nullVisited;
  private ArrayList<String> validWordsAccumulator;
  private ArrayList<String> nullWordsAccumulator;
  private TrieNode validTrieNode;
  private TrieNode nullTrieNode;
  private Point validPoint;
  private Point invalidPoint;
  private Point nullPoint;
  private TrieNode[][] nullTrieGrid;


  /**
   * Checks if a list contains a given point.
   *
   * @param list the list to be searched
   * @param point the point of interest
   * @return true if the point is in the list, false otherwise
   */
  private boolean containsPoint(List<Point> list, Point point) {
    for (Point p : list) {
      if (p.x == point.x && p.y == point.y) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if two matrices of trie nodes are equal.
   *
   * @param grid1 the first matrix to be compared
   * @param grid2 the second matrix to be compared
   * @return true if they contains identical elements, false otherwise
   */
  private boolean equalMatrices(TrieNode[][] grid1, TrieNode[][] grid2) {
    assert grid1 != null && grid2 != null;

    if (grid1.length != grid2.length || grid1[0].length != grid2[0].length) {
      return false;
    }

    for (int row = 0; row < grid1.length; row++) {
      for (int column = 0; column < grid1[0].length; column++) {
        if (!grid1[row][column].equals(grid2[row][column])) {
          return false;
        }
      }
    }
    return true;
  }

  // general setup
  void generalSetUp() {
    wordSearch = new WordSearch();

    nullPoint = null;
    nullTrieNode = null;

    nullGrid = null;
    nullTrieGrid = null;
    nullDict = null;

    emptyListOfDictionaryWords = new ArrayList<>();
    emptyDict = new TrieDictionary(emptyListOfDictionaryWords);
  }

  void setUp1() {
    validGrid = new char[][]{{'a', 'a', 'r'}, {'t', 'c', 'd'}};
    validTrieGrid = new TrieNode[][]{{new TrieNode('a', 0, 0), new TrieNode('a', 0, 1), new TrieNode('r', 0, 2)},
        {new TrieNode('t', 1, 0), new TrieNode('c', 1, 1), new TrieNode('d', 1, 2)}};
    numberRowsGrid = validGrid.length;
    numberColumnsGrid = validGrid[0].length;
    validListOfWords = new ArrayList<>();
    validListOfWords.add("car");
    validListOfWords.add("card");
    validListOfWords.add("cart");
    validListOfWords.add("cat");
    validDict = new TrieDictionary(validListOfWords);

    validPoint = new Point(1,1);
    invalidPoint = new Point(numberRowsGrid + 1, 0);
  }

  void setUp2() {
    validGrid = new char[][]{{'l', 'm', 'a', 'p'}, {'o', 'o', 'x', 'a'}, {'d', 'r', 'v', 'r'}, {'q', 'e', 'x', 'e'}};
    validTrieGrid = new TrieNode[][]{{new TrieNode('l', 0, 0), new TrieNode('m', 0, 1), new TrieNode('a', 0, 2), new TrieNode('p', 0, 3)},
        {new TrieNode('o', 1, 0), new TrieNode('o', 1, 1), new TrieNode('x', 1, 2), new TrieNode('a', 1, 3)},
        {new TrieNode('d', 0, 0), new TrieNode('r', 0, 1), new TrieNode('v', 0, 2), new TrieNode('r', 0, 3)},
        {new TrieNode('q', 0, 0), new TrieNode('e', 0, 1), new TrieNode('x', 0, 2), new TrieNode('e', 0, 3)}};
    numberRowsGrid = validGrid.length;
    numberColumnsGrid = validGrid[0].length;
    validListOfWords = new ArrayList<>();
    validListOfWords.add("love");
    validListOfWords.add("more");
    validListOfWords.add("mood");
    validListOfWords.add("mode");
    validListOfWords.add("map");
    validListOfWords.add("ever");
    validListOfWords.add("drop");
    validListOfWords.add("remap");
    validDict = new TrieDictionary(validListOfWords);
  }

  // additional setup for depthFirstTraversal method
  void setUp_depthFirstTraversal() {
    validVisited = new boolean[numberRowsGrid][numberColumnsGrid];
    nullVisited = null;

    validWordsAccumulator = new ArrayList<>();
    nullWordsAccumulator = null;
  }

  // additional setup for getStringFromRootToNode method
  void setUp_getStringFromRootToNode() {
    TrieNode a = new TrieNode('a');
    TrieNode c = new TrieNode('c');
    TrieNode d = new TrieNode('d');
    TrieNode r = new TrieNode('r');
    TrieNode t = new TrieNode('r');

    //create trie that contains words "car", "cat", "card", "car"
    c.addChild(a);
    a.addChild(r);
    a.addChild(t);
    r.addChild(d);

    validTrieNode = d;
  }

  void testGetNeighbours_normalCaseCornerElementPasses() {
    boolean equalLists = true;

    ArrayList<Point> neighbours = wordSearch.getNeighbours(new Point(0,0), validTrieGrid);
    ArrayList<Point> expectedNeighbours = new ArrayList<>();
    expectedNeighbours.add(new Point(0,1));
    expectedNeighbours.add(new Point(1,0));
    expectedNeighbours.add(new Point(1,1));

    assert (neighbours.size() == expectedNeighbours.size());

    for (Point expNeighbour : expectedNeighbours) {
      equalLists = equalLists && containsPoint(neighbours, expNeighbour);
    }

    assert (equalLists);
  }

  void testGetNeighbours_normalCaseMiddleElementPasses() {
    boolean equal = true;

    ArrayList<Point> neighbours = wordSearch.getNeighbours(validPoint, validTrieGrid);
    ArrayList<Point> expectedNeighbours = new ArrayList<>();
    expectedNeighbours.add(new Point(0,0));
    expectedNeighbours.add(new Point(0,1));
    expectedNeighbours.add(new Point(0,2));
    expectedNeighbours.add(new Point(1,0));
    expectedNeighbours.add(new Point(1,2));

    assert (neighbours.size() == expectedNeighbours.size());

    for (Point expNeighbour : expectedNeighbours) {
      equal = equal && containsPoint(neighbours, expNeighbour);
    }

    assert (equal);
  }

  void testGetNeighbours_negativeRowOrColumnFails() {
    boolean throwsError = false;
    try {
      wordSearch.getNeighbours(new Point(-1,1), validTrieGrid);
    } catch (AssertionError e) {
      throwsError = true;
    }
    assert throwsError;
  }

  void testGetNeighbours_nullGridFails() {
    boolean throwsError = false;
    try {
      wordSearch.getNeighbours(validPoint, null);
    } catch (AssertionError e) {
      throwsError = true;
    }
    assert throwsError;
  }

  void testDepthFirstTraversal_validInputPasses() {
    boolean equalLists = true;
    wordSearch.depthFirstTraversal(validPoint, validTrieGrid, validDict, validVisited, validWordsAccumulator);

    ArrayList<String> expectedWords = new ArrayList<>();
    expectedWords.add("cat");
    expectedWords.add("cat");
    expectedWords.add("car");
    expectedWords.add("card");

    assert expectedWords.size() == validWordsAccumulator.size();

    for (String expectedWord : expectedWords) {
      equalLists = equalLists && validWordsAccumulator.contains(expectedWord);
    }
    assert equalLists;
  }

  void testDepthFirstTraversal_NullStartingPointFails() {
    boolean throwsError = false;
    try {
      wordSearch.depthFirstTraversal(nullPoint, validTrieGrid, validDict, validVisited, validWordsAccumulator);
    } catch (AssertionError e) {
      throwsError = true;
    }
    assert throwsError;
  }

  void testDepthFirstTraversal_NullGridFails() {
    boolean throwsError = false;
    try {
      wordSearch.depthFirstTraversal(validPoint, nullTrieGrid, validDict, validVisited, validWordsAccumulator);
    } catch (AssertionError e) {
      throwsError = true;
    }
    assert throwsError;
  }

  void testDepthFirstTraversal_NullDictionaryFails() {
    boolean throwsError = false;
    try {
      wordSearch.depthFirstTraversal(validPoint, validTrieGrid, nullDict, validVisited, validWordsAccumulator);
    } catch (AssertionError e) {
      throwsError = true;
    }
    assert throwsError;
  }

  void testDepthFirstTraversal_EmptyDictionaryPasses() {
    wordSearch.depthFirstTraversal(validPoint, validTrieGrid, emptyDict, validVisited, validWordsAccumulator);
    assert validWordsAccumulator.isEmpty();
  }

  void testDepthFirstTraversal_nullVisitedFails() {
    boolean throwsError = false;
    try {
      wordSearch.depthFirstTraversal(validPoint, validTrieGrid, validDict, nullVisited, validWordsAccumulator);
    } catch (AssertionError e) {
      throwsError = true;
    }
    assert throwsError;
  }

  public void testDepthFirstTraversal_nullWordsAccumulatorFails() {
    boolean throwsError = false;
    try {
      wordSearch.depthFirstTraversal(validPoint, validTrieGrid, validDict, validVisited, nullWordsAccumulator);
    } catch (AssertionError e) {
      throwsError = true;
    }
    assert throwsError;
  }

  void testCreateTrieGrid_negativeRowOrColumnFails() {
    boolean throwsError = false;
    try {
      wordSearch.createTrieGrid(validGrid, -numberRowsGrid, numberColumnsGrid);
    } catch (AssertionError e) {
      throwsError = true;
    }
    assert throwsError;
  }

  void testCreateTrieGrid_nullGridFails() {
    boolean throwsError = false;
    try {
      wordSearch.createTrieGrid(nullGrid, numberRowsGrid, numberColumnsGrid);
    } catch (AssertionError e) {
      throwsError = true;
    }
    assert throwsError;
  }

  void testCreateTrieGrid_validInputPasses() {
//    assert Arrays.equals(validTrieGrid, wordSearch.createTrieGrid(validGrid, numberRowsGrid, numberColumnsGrid));
    assert equalMatrices(validTrieGrid, wordSearch.createTrieGrid(validGrid, numberRowsGrid, numberColumnsGrid));
  }

  void testIsWithinBoundaries_negativeRowOrColumnFails() {
    assert !wordSearch.isWithinBoundaries(new Point(1,-1), numberRowsGrid, numberColumnsGrid);
  }

  void testIsWithinBoundaries_negativeRowOrColumnBoundaryFails() {
    boolean throwsError = false;
    try {
      wordSearch.isWithinBoundaries(validPoint, -numberRowsGrid, numberColumnsGrid);
    } catch (AssertionError e) {
      throwsError = true;
    }
    assert throwsError;
  }

  void testIsWithinBoundaries_RowGreaterThanRowBoundaryFails() {
    assert !wordSearch.isWithinBoundaries(invalidPoint, numberRowsGrid, numberColumnsGrid);
  }

  void testGetStringFromRootToNode_nullNodeFails() {
    boolean throwsError = false;
    try {
      wordSearch.getStringFromRootToNode(nullTrieNode);
    } catch (AssertionError e) {
      throwsError = true;
    }
    assert throwsError;
  }

  void testGetStringFromRootToNode_validNodePasses() {
    assert "card".equals(wordSearch.getStringFromRootToNode(validTrieNode));
  }

  void testWordSearch_nullGridFails() {
    boolean throwsError = false;
    try {
      wordSearch.wordSearch(nullGrid, validDict);
    } catch (IllegalArgumentException e) {
      throwsError = true;
    }
    assert throwsError;
  }

  void testWordSearch_nullDictionaryFails() {
    boolean throwsError = false;
    try {
      wordSearch.wordSearch(validGrid, nullDict);
    } catch (IllegalArgumentException e) {
      throwsError = true;
    }
    assert throwsError;
  }

  void testWordSearch_emptyDictionaryPasses() {
    assert wordSearch.wordSearch(validGrid, emptyDict).isEmpty();
  }

  void testWordSearch_validInputPassesSetUp1() {
    boolean equalSets = true;
    Set<String> words = wordSearch.wordSearch(validGrid, validDict);
    Set<String> expectedWords = new HashSet<>();
    expectedWords.add("car");
    expectedWords.add("cat");
    expectedWords.add("card");

    assert words.size() == expectedWords.size();

    ArrayList<String> wordsList = new ArrayList<>(words);
    for (String expectedWord : expectedWords) {
      equalSets = equalSets && wordsList.contains(expectedWord);
    }
    assert equalSets;
  }

  void testWordSearch_validInputPassesSetUp2() {
    boolean equalSets = true;
    Set<String> words = wordSearch.wordSearch(validGrid, validDict);
    Set<String> expectedWords = new HashSet<>();
    expectedWords.add("love");
    expectedWords.add("more");
    expectedWords.add("mood");
    expectedWords.add("mode");
    expectedWords.add("map");
    expectedWords.add("ever");

    assert words.size() == expectedWords.size();

    ArrayList<String> wordsList = new ArrayList<>(words);
    for (String expectedWord : expectedWords) {
      equalSets = equalSets && wordsList.contains(expectedWord);
    }
    assert equalSets;
  }
}
