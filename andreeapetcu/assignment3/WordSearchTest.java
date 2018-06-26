import org.junit.Ignore;
import org.junit.Test;

import java.awt.*;
import java.util.*;
import java.util.List;

import static org.junit.Assert.*;

public class WordSearchTest {

  private WordSearch wordSearch;

  @org.junit.Before
  public void setUp() {
    wordSearch = new WordSearch();
  }

  @Test
  public void testGetNeighbours_normalCaseCornerElementPasses() {
    TrieNode[][] validTrieGrid = new TrieNode[][]{{new TrieNode('a'), new TrieNode('a'), new TrieNode('r')},
        {new TrieNode('t'), new TrieNode('c'), new TrieNode('d')}};
    ArrayList<Point> neighbours = wordSearch.getNeighbours(new Point(0, 0), validTrieGrid);
    ArrayList<Point> expectedNeighbours = new ArrayList<>();
    expectedNeighbours.add(new Point(0, 1));
    expectedNeighbours.add(new Point(1, 0));
    expectedNeighbours.add(new Point(1, 1));

    assertEquals(expectedNeighbours.size(), neighbours.size());
    assertEquals(expectedNeighbours, neighbours);
  }

  @Test
  public void testGetNeighbours_normalCaseMiddleElementPasses() {
    TrieNode[][] validTrieGrid = new TrieNode[][]{{new TrieNode('a'), new TrieNode('a'), new TrieNode('r')},
        {new TrieNode('t'), new TrieNode('c'), new TrieNode('d')}};
    ArrayList<Point> neighbours = wordSearch.getNeighbours(new Point(1, 1), validTrieGrid);
    ArrayList<Point> expectedNeighbours = new ArrayList<>();
    expectedNeighbours.add(new Point(0, 0));
    expectedNeighbours.add(new Point(0, 1));
    expectedNeighbours.add(new Point(0, 2));
    expectedNeighbours.add(new Point(1, 0));
    expectedNeighbours.add(new Point(1, 2));

    assertEquals(expectedNeighbours.size(), neighbours.size());
    assertEquals(expectedNeighbours, neighbours);
  }

  @Test(expected = AssertionError.class)
  public void testGetNeighbours_negativeRowOrColumnFails() {
    TrieNode[][] validTrieGrid = new TrieNode[][]{{new TrieNode('a'), new TrieNode('a'), new TrieNode('r')},
        {new TrieNode('t'), new TrieNode('c'), new TrieNode('d')}};
    wordSearch.getNeighbours(new Point(-1, 1), validTrieGrid);
  }

  @Test(expected = AssertionError.class)
  public void testGetNeighbours_nullGridFails() {
    wordSearch.getNeighbours(new Point(1, 1), null);
  }

  @Test
  public void testDepthFirstTraversal_validInputPasses() {
    TrieNode[][] validTrieGrid = new TrieNode[][]{{new TrieNode('a'), new TrieNode('a'), new TrieNode('r')},
        {new TrieNode('t'), new TrieNode('c'), new TrieNode('d')}};
    TrieDictionary validDict = new TrieDictionary(Arrays.asList("car", "card", "cart", "cat"));
    List<String> validWordsAccumulator = new ArrayList<>();
    boolean[][] validVisited = new boolean[validTrieGrid.length][validTrieGrid[0].length];
    wordSearch.depthFirstTraversal(new Point(1, 1), validTrieGrid, validDict, validVisited, validWordsAccumulator);

    ArrayList<String> expectedWords = new ArrayList<>();
    expectedWords.add("cat");
    expectedWords.add("car");
    expectedWords.add("card");
    expectedWords.add("cat");

    assertEquals(expectedWords.size(), validWordsAccumulator.size());
    assertEquals(expectedWords, validWordsAccumulator);
  }

  @Test(expected = AssertionError.class)
  public void testDepthFirstTraversal_NullStartingPointFails() {
    TrieNode[][] validTrieGrid = new TrieNode[][]{{new TrieNode('a'), new TrieNode('a'), new TrieNode('r')},
        {new TrieNode('t'), new TrieNode('c'), new TrieNode('d')}};
    TrieDictionary validDict = new TrieDictionary(Arrays.asList("car", "card", "cart", "cat"));
    List<String> validWordsAccumulator = new ArrayList<>();
    boolean[][] validVisited = new boolean[validTrieGrid.length][validTrieGrid[0].length];

    wordSearch.depthFirstTraversal(null, validTrieGrid, validDict, validVisited, validWordsAccumulator);
  }

  @Test(expected = AssertionError.class)
  public void testDepthFirstTraversal_NullGridFails() {
    TrieDictionary validDict = new TrieDictionary(Arrays.asList("car", "card", "cart", "cat"));
    List<String> validWordsAccumulator = new ArrayList<>();
    boolean[][] validVisited = new boolean[2][3];
    wordSearch.depthFirstTraversal(new Point(1, 1), null, validDict, validVisited, validWordsAccumulator);
  }

  @Test(expected = AssertionError.class)
  public void testDepthFirstTraversal_NullDictionaryFails() {
    TrieNode[][] validTrieGrid = new TrieNode[][]{{new TrieNode('a'), new TrieNode('a'), new TrieNode('r')},
        {new TrieNode('t'), new TrieNode('c'), new TrieNode('d')}};
    List<String> validWordsAccumulator = new ArrayList<>();
    boolean[][] validVisited = new boolean[validTrieGrid.length][validTrieGrid[0].length];
    wordSearch.depthFirstTraversal(new Point(1, 1), validTrieGrid, null, validVisited, validWordsAccumulator);
  }

  @Test
  public void testDepthFirstTraversal_EmptyDictionaryPasses() {
    TrieNode[][] validTrieGrid = new TrieNode[][]{{new TrieNode('a'), new TrieNode('a'), new TrieNode('r')},
        {new TrieNode('t'), new TrieNode('c'), new TrieNode('d')}};
    TrieDictionary emptyDict = new TrieDictionary(new ArrayList<>());
    List<String> validWordsAccumulator = new ArrayList<>();
    boolean[][] validVisited = new boolean[validTrieGrid.length][validTrieGrid[0].length];
    wordSearch.depthFirstTraversal(new Point(1, 1), validTrieGrid, emptyDict, validVisited, validWordsAccumulator);
    assertTrue(validWordsAccumulator.isEmpty());
  }

  @Test(expected = AssertionError.class)
  public void testDepthFirstTraversal_nullVisitedFails() {
    TrieNode[][] validTrieGrid = new TrieNode[][]{{new TrieNode('a'), new TrieNode('a'), new TrieNode('r')},
        {new TrieNode('t'), new TrieNode('c'), new TrieNode('d')}};
    TrieDictionary validDict = new TrieDictionary(Arrays.asList("car", "card", "cart", "cat"));
    List<String> validWordsAccumulator = new ArrayList<>();
    wordSearch.depthFirstTraversal(new Point(1, 1), validTrieGrid, validDict, null, validWordsAccumulator);
  }

  @Test(expected = AssertionError.class)
  public void testDepthFirstTraversal_nullWordsAccumulatorFails() {
    TrieNode[][] validTrieGrid = new TrieNode[][]{{new TrieNode('a'), new TrieNode('a'), new TrieNode('r')},
        {new TrieNode('t'), new TrieNode('c'), new TrieNode('d')}};
    TrieDictionary validDict = new TrieDictionary(Arrays.asList("car", "card", "cart", "cat"));
    boolean[][] validVisited = new boolean[validTrieGrid.length][validTrieGrid[0].length];
    wordSearch.depthFirstTraversal(new Point(1, 1), validTrieGrid, validDict, validVisited, null);
  }

  @Test(expected = AssertionError.class)
  public void testGetStringFromRootToNode_nullNodeFails() {
    wordSearch.getStringFromRootToNode(null);
  }

  @Test
  public void testGetStringFromRootToNode_validNodePasses() {
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

    assertEquals("card", wordSearch.getStringFromRootToNode(d));
  }

  @Test
  public void testIsWithinBoundaries_negativeRowOrColumnFails() {
    assertFalse(wordSearch.isWithinBoundaries(new Point(1, -1), 2, 3));
  }

  @Test(expected = AssertionError.class)
  public void testIsWithinBoundaries_negativeRowOrColumnBoundaryFails() {
    wordSearch.isWithinBoundaries(new Point(1, 1), -2, 3);
  }

  @Test
  public void testIsWithinBoundaries_RowGreaterThanRowBoundaryFails() {
    assertFalse(wordSearch.isWithinBoundaries(new Point(3, 0), 2, 3));
  }

  @Test(expected = AssertionError.class)
  public void testCreateTrieGrid_negativeRowOrColumnFails() {
    char[][] validGrid = new char[][]{{'a', 'a', 'r'}, {'t', 'c', 'd'}};
    wordSearch.createTrieGrid(validGrid, -validGrid.length, validGrid[0].length);
  }

  @Test(expected = AssertionError.class)
  public void testCreateTrieGrid_nullGridFails() {
    wordSearch.createTrieGrid(null, 2, 3);
  }

  @Ignore
  @Test
  public void testCreateTrieGrid_validInputPasses() {
    char[][] validGrid = new char[][]{{'a', 'a', 'r'}, {'t', 'c', 'd'}};
    TrieNode[][] validTrieGrid = new TrieNode[][]{{new TrieNode('a'), new TrieNode('a'), new TrieNode('r')},
        {new TrieNode('t'), new TrieNode('c'), new TrieNode('d')}};
    assertTrue(Arrays.equals(validTrieGrid, wordSearch.createTrieGrid(validGrid, 2, 3)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWordSearch_nullGridFails() {
    TrieDictionary validDict = new TrieDictionary(Arrays.asList("car", "card", "cart", "cat"));
    wordSearch.wordSearch(null, validDict);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWordSearch_nullDictionaryFails() {
    char[][] validGrid = new char[][]{{'a', 'a', 'r'}, {'t', 'c', 'd'}};
    wordSearch.wordSearch(validGrid, null);
  }

  @Test
  public void testWordSearch_emptyDictionaryPasses() {
    char[][] validGrid = new char[][]{{'a', 'a', 'r'}, {'t', 'c', 'd'}};
    TrieDictionary emptyDict = new TrieDictionary(new ArrayList<>());
    assertTrue(wordSearch.wordSearch(validGrid, emptyDict).isEmpty());
  }

  @Test
  public void testWordSearch_validInputPasses1() {
    char[][] validGrid = new char[][]{{'a', 'a', 'r'}, {'t', 'c', 'd'}};
    TrieDictionary validDict = new TrieDictionary(Arrays.asList("car", "card", "cart", "cat"));

    Set<String> words = wordSearch.wordSearch(validGrid, validDict);
    Set<String> expectedWords = new HashSet<>();
    expectedWords.add("car");
    expectedWords.add("cat");
    expectedWords.add("card");

    assertEquals(expectedWords.size(), words.size());
    assertEquals(expectedWords, words);
  }

  @Test
  public void testWordSearch_validInputPasses2() {
    char[][] validGrid = new char[][]{{'l', 'm', 'a', 'p'}, {'o', 'o', 'x', 'a'}, {'d', 'r', 'v', 'r'}, {'q', 'e', 'x', 'e'}};
    TrieDictionary validDict = new TrieDictionary(Arrays.asList("love", "more", "mood", "mode", "map", "ever", "drop", "remap"));

    Set<String> words = wordSearch.wordSearch(validGrid, validDict);
    Set<String> expectedWords = new HashSet<>();
    expectedWords.add("love");
    expectedWords.add("more");
    expectedWords.add("mood");
    expectedWords.add("mode");
    expectedWords.add("map");
    expectedWords.add("ever");

    assertEquals(expectedWords.size(), words.size());
    assertEquals(expectedWords, words);
  }
}
