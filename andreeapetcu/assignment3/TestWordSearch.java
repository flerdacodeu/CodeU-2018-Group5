import java.util.*;

public class TestWordSearch {

  private WordSearch wordSearch;
  private char[][] validGrid;
  private TrieNode[][] validTrieGrid;
  private int numberRowsGrid;
  private int numberColumnsGrid;
  private Dictionary validDict;
  private List<String> validListOfWords;
  private char[][] nullGrid;
  private Dictionary nullDict;
  private Dictionary emptyDict;
  private List<String> emptyListOfDictionaryWords;
  private boolean[][] validVisited;
  private boolean[][] nullVisited;
  private ArrayList<String> validWordsAccumulator;
  private ArrayList<String> nullWordsAccumulator;
  private TrieNode validTrieNode;
  private TrieNode nullTrieNode;
  private TrieNode[][] nullTrieGrid;


  /**
   * Checks if a list contains a given trie node.
   *
   * @param list the list to be searched
   * @param node the node of interest
   * @return true if the node is in the list, false otherwise
   */
  private boolean containsNode (LinkedList<TrieNode> list, TrieNode node) {
    for (TrieNode n : list) {
      if (n.getRow() == node.getRow() && n.getColumn() == node.getColumn() && n.getValue() ==
          node.getValue()) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if a list contains the given string.
   *
   * @param list the list to be searched in
   * @param word the word to be searched for in the list
   * @return true if the list contains the string, false otherwise
   */
  private boolean containsWord (List<String> list, String word) {
    for (String w : list) {
      if (w.equals(word)) {
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
  private boolean equalMatrices (TrieNode[][] grid1, TrieNode[][] grid2) {
    assert grid1 != null && grid2 != null;

    if (grid1.length != grid2.length || grid1[0].length != grid2[0].length) {
      return false;
    }

    for (int row = 0; row < grid1.length; row++) {
      for (int column = 0; column < grid1[0].length; column++) {
        if (grid1[row][column].getValue() != grid2[row][column].getValue() ||
            grid1[row][column].getRow() != grid2[row][column].getRow() ||
            grid1[row][column].getColumn() != grid2[row][column].getColumn()) {
          return false;
        }
      }
    }
    return true;
  }

    // general setup
  void generalSetUp() {
    wordSearch = new WordSearch();

    nullTrieNode = null;

    nullGrid = null;
    nullTrieGrid = null;
    nullDict = null;

    emptyListOfDictionaryWords = new LinkedList<>();
    emptyDict = new Dictionary(emptyListOfDictionaryWords);
  }
  
  void setUp1() {
    validGrid = new char[][]{{'a','a','r'}, {'t','c','d'} };
    validTrieGrid = new TrieNode[][]{{new TrieNode('a', 0, 0), new TrieNode('a', 0, 1), new TrieNode('r', 0, 2)},
        {new TrieNode('t', 1, 0), new TrieNode('c', 1, 1), new TrieNode('d', 1, 2)}};
    numberRowsGrid = validGrid.length;
    numberColumnsGrid = validGrid[0].length;
    validListOfWords = new LinkedList<>();
    validListOfWords.add("car");
    validListOfWords.add("card");
    validListOfWords.add("cart");
    validListOfWords.add("cat");
    validDict = new Dictionary(validListOfWords);

    validTrieNode = new TrieNode('c', 1,1);
  }
  
  void setUp2() {
    validGrid = new char[][]{{'l','m','a','p'}, {'o','o','x', 'a'}, {'d', 'r', 'v', 'r'}, {'q', 'e', 'x', 'e'}};
    validTrieGrid = new TrieNode[][]{{new TrieNode('l', 0, 0), new TrieNode('m', 0, 1), new TrieNode('a', 0, 2), new TrieNode('p',0,3)},
        {new TrieNode('o', 1, 0), new TrieNode('o', 1, 1), new TrieNode('x', 1, 2), new TrieNode('a',1,3)},
        {new TrieNode('d', 0, 0), new TrieNode('r', 0, 1), new TrieNode('v', 0, 2), new TrieNode('r',0,3)},
        {new TrieNode('q', 0, 0), new TrieNode('e', 0, 1), new TrieNode('x', 0, 2), new TrieNode('e',0,3)}};
    numberRowsGrid = validGrid.length;
    numberColumnsGrid = validGrid[0].length;
    validListOfWords = new LinkedList<>();
    validListOfWords.add("love");
    validListOfWords.add("more");
    validListOfWords.add("mood");
    validListOfWords.add("mode");
    validListOfWords.add("map");
    validListOfWords.add("ever");
    validListOfWords.add("drop");
    validListOfWords.add("remap");
    validDict = new Dictionary(validListOfWords);
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

    LinkedList<TrieNode> neighbours = wordSearch.getNeighbours(0,0, validTrieGrid);
    LinkedList<TrieNode> expectedNeighbours = new LinkedList<>();
    expectedNeighbours.add(new TrieNode('a',0,1));
    expectedNeighbours.add(new TrieNode('t', 1, 0));
    expectedNeighbours.add( new TrieNode('c', 1, 1));

    assert (neighbours.size() == expectedNeighbours.size());

    for (TrieNode expNeighbour : expectedNeighbours) {
      equalLists = equalLists && containsNode(neighbours, expNeighbour);
    }

    assert(equalLists);
  }

  void testGetNeighbours_normalCaseMiddleElementPasses() {
    boolean equal = true;

    LinkedList<TrieNode> neighbours = wordSearch.getNeighbours(1,1, validTrieGrid);
    LinkedList<TrieNode> expectedNeighbours = new LinkedList<>();
    expectedNeighbours.add(new TrieNode('a',0,0));
    expectedNeighbours.add(new TrieNode('a',0,1));
    expectedNeighbours.add(new TrieNode('r',0,2));
    expectedNeighbours.add(new TrieNode('t', 1, 0));
    expectedNeighbours.add( new TrieNode('d', 1, 2));

    assert (neighbours.size() == expectedNeighbours.size());

    for (TrieNode expNeighbour : expectedNeighbours) {
      equal = equal && containsNode(neighbours, expNeighbour);
    }

    assert (equal);
  }

  void testGetNeighbours_negativeRowOrColumnFails() {
    boolean throwsError = false;
    try {
      wordSearch.getNeighbours(-1,1, validTrieGrid);
    } catch (AssertionError e) {
      throwsError = true;
    }
    assert throwsError;
  }

  void testGetNeighbours_nullGridFails() {
    boolean throwsError = false;
    try {
      wordSearch.getNeighbours(1,1, null);
    } catch (AssertionError e) {
      throwsError = true;
    }
    assert throwsError;
  }

  void testDepthFirstTraversal_validInputPasses() {
    boolean equalLists = true;
    wordSearch.depthFirstTraversal(validTrieNode, validTrieGrid, validDict, validVisited, validWordsAccumulator);

    LinkedList<String> expectedWords = new LinkedList<>();
    expectedWords.add("cat");
    expectedWords.add("cat");
    expectedWords.add("car");
    expectedWords.add("card");

    assert expectedWords.size() == validWordsAccumulator.size();

    for (String expectedWord : expectedWords ) {
      equalLists = equalLists && containsWord(validWordsAccumulator, expectedWord);
    }
    assert equalLists;
  }

  void testDepthFirstTraversal_NullStartingPointFails() {
    boolean throwsError = false;
    try {
      wordSearch.depthFirstTraversal(nullTrieNode, validTrieGrid, validDict, validVisited, validWordsAccumulator);
    } catch (AssertionError e) {
      throwsError = true;
    }
    assert throwsError;
  }

  void testDepthFirstTraversal_NullGridFails() {
    boolean throwsError = false;
    try {
      wordSearch.depthFirstTraversal(validTrieNode, nullTrieGrid, validDict, validVisited, validWordsAccumulator);
    } catch (AssertionError e) {
      throwsError = true;
    }
    assert throwsError;
  }

  void testDepthFirstTraversal_NullDictionaryFails() {
    boolean throwsError = false;
    try {
      wordSearch.depthFirstTraversal(validTrieNode, validTrieGrid, nullDict, validVisited, validWordsAccumulator);
    } catch (AssertionError e) {
      throwsError = true;
    }
    assert throwsError;
  }

  void testDepthFirstTraversal_EmptyDictionaryPasses() {
    wordSearch.depthFirstTraversal(validTrieNode, validTrieGrid, emptyDict, validVisited, validWordsAccumulator);
    assert validWordsAccumulator.isEmpty();
  }

  void testDepthFirstTraversal_nullVisitedFails() {
    boolean throwsError = false;
    try {
      wordSearch.depthFirstTraversal(validTrieNode, validTrieGrid, validDict, nullVisited, validWordsAccumulator);
    } catch (AssertionError e) {
      throwsError = true;
    }
    assert throwsError;
  }

  public void testDepthFirstTraversal_nullWordsAccumulatorFails() {
    boolean throwsError = false;
    try {
      wordSearch.depthFirstTraversal(validTrieNode, validTrieGrid, validDict, validVisited, nullWordsAccumulator);
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
    assert equalMatrices(validTrieGrid, wordSearch.createTrieGrid(validGrid, numberRowsGrid, numberColumnsGrid));
  }

  void testIsWithinBoundaries_negativeRowOrColumnFails() {
    assert !wordSearch.isWithinBoundaries(-1, 1, numberRowsGrid, numberColumnsGrid);
  }

  void testIsWithinBoundaries_negativeRowOrColumnBoundaryFails() {
    boolean throwsError = false;
    try {
      wordSearch.isWithinBoundaries(1,1, -numberRowsGrid, numberColumnsGrid);
    } catch (AssertionError e) {
      throwsError = true;
    }
    assert throwsError;
  }

  void testIsWithinBoundaries_RowGreaterThanRowBoundaryFails() {
    assert !wordSearch.isWithinBoundaries(numberRowsGrid + 1, 0, numberRowsGrid, numberColumnsGrid);
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

    LinkedList<String> wordsList = new LinkedList<>(words);
    for (String expectedWord : expectedWords) {
      equalSets = equalSets && containsWord(wordsList, expectedWord);
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
    
    LinkedList<String> wordsList = new LinkedList<>(words);
    for (String expectedWord : expectedWords) {
      equalSets = equalSets && containsWord(wordsList, expectedWord);
    }
    assert equalSets;
  }
}
