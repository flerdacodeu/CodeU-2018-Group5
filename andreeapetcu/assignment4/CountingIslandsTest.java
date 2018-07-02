import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class CountingIslandsTest {

  @Test
  public void testCountIslands_emptyMapPasses() {
    boolean[][] map = {};
    assertEquals("countingIslands failed", 0, CountingIslands.countingIslands(0, map.length, map));
    assertEquals("countIslands failed", 0, CountingIslands.countIslands(0, map.length, map));
  }

  @Test
  public void testCountIslands_validInputPasses1() {
    boolean[][] map = {{false, true, false, true},
        {true, true, false, false},
        {false, false, true, false},
        {false, false, true, false}};
    assertEquals("countingIslands failed", 3, CountingIslands.countingIslands(map.length, map[0].length, map));
    assertEquals("countIslands failed", 3, CountingIslands.countIslands(map.length, map[0].length, map));
  }

  @Test
  public void testCountIslands_validInputPasses2() {
    boolean[][] map = {{false, false, false, true},
        {false, false, true, false},
        {false, true, false, false},
        {true, false, false, false}};
    assertEquals("countingIslands failed", 4, CountingIslands.countingIslands(map.length, map[0].length, map));
    assertEquals("countIslands failed", 4, CountingIslands.countIslands(map.length, map[0].length, map));
  }

  @Test
  public void testCountIslands_validInputPasses3() {
    boolean[][] map = {{false, false, false, false},
        {false, false, false, false},
        {false, false, false, false},
        {false, false, false, false}};
    assertEquals("countingIslands failed", 0, CountingIslands.countingIslands(map.length, map[0].length, map));
    assertEquals("countIslands failed", 0, CountingIslands.countIslands(map.length, map[0].length, map));
  }

  @Test
  public void testCountIslands_validInputPasses4() {
    boolean[][] map = {{true, true, true, true},
        {true, true, true, true},
        {true, true, true, true},
        {true, true, true, true}};
    assertEquals("countingIslands failed", 1, CountingIslands.countingIslands(map.length, map[0].length, map));
    assertEquals("countIslands failed", 1, CountingIslands.countIslands(map.length, map[0].length, map));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCountIslands_nullMapFails() {
    CountingIslands.countingIslands(4, 4, null);
    CountingIslands.countIslands(4, 4, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCountIslands_negativeRowsNumberFails() {
    boolean[][] map = {{true, false}, {true, false}};
    CountingIslands.countingIslands(-1, 4, map);
    CountingIslands.countIslands(-1, 4, map);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCountIslands_negativeColumnsNumberFails() {
    boolean[][] map = {{true, false}, {true, false}};
    CountingIslands.countingIslands(4, -1, map);
    CountingIslands.countIslands(4, -1, map);
  }

  @Test
  public void testMergeTilesIntoIslands_validInputPasses1() {
    boolean[][] map = {{false, true, false, true},
        {true, true, false, false},
        {false, false, true, false},
        {false, false, true, false}};
    UnionFind uf = CountingIslands.mergeTilesIntoIslands(map.length, map[0].length, map);
    int[] expected = {0, 5, 2, 3, 5, 5, 6, 7, 8, 9, 14, 11, 12, 13, 14, 15};
    assertArrayEquals(expected, uf.getUnions());
  }

  @Test
  public void testMergeTilesIntoIslands_validInputPasses2() {
    boolean[][] map = {{true, true, true},
        {true, true, true},
        {true, true, true}};
    UnionFind uf = CountingIslands.mergeTilesIntoIslands(map.length, map[0].length, map);
    int[] expected = {1, 2, 2, 2, 2, 2, 2, 2, 2};
    assertArrayEquals(expected, uf.getUnions());
  }

  @Test
  public void testDiscoverIsland_emptyMapPasses() {
    ArrayList<CountingIslands.Tile> islandTiles = new ArrayList<>();
    boolean[][] map = {};
    CountingIslands.discoverIsland(map, new CountingIslands.Tile(0, 0), islandTiles);
    assertTrue(islandTiles.isEmpty());
  }

  @Test
  public void testDiscoverIsland_validInputPasses1() {
    boolean[][] map = {{false, true, false, true},
        {true, true, false, false},
        {false, false, true, false},
        {false, false, true, false}};
    CountingIslands.Tile currentTile = new CountingIslands.Tile(0, 1);
    ArrayList<CountingIslands.Tile> islandTiles = new ArrayList<>();
    CountingIslands.discoverIsland(map, currentTile, islandTiles);
    ArrayList<CountingIslands.Tile> expectedIslandTiles = new ArrayList<>();
    expectedIslandTiles.add(new CountingIslands.Tile(0, 1));
    expectedIslandTiles.add(new CountingIslands.Tile(1, 1));
    expectedIslandTiles.add(new CountingIslands.Tile(1, 0));
    assertEquals(expectedIslandTiles, islandTiles);
  }

  @Test
  public void testDiscoverIsland_validInputPasses2() {
    boolean[][] map = {{false, true, false, true},
        {true, true, false, false},
        {false, false, true, false},
        {false, false, true, false}};
    CountingIslands.Tile currentTile = new CountingIslands.Tile(0, 3);
    ArrayList<CountingIslands.Tile> islandTiles = new ArrayList<>();
    CountingIslands.discoverIsland(map, currentTile, islandTiles);
    ArrayList<CountingIslands.Tile> expectedIslandTiles = new ArrayList<>();
    expectedIslandTiles.add(new CountingIslands.Tile(0, 3));
    assertEquals(expectedIslandTiles, islandTiles);
  }

  @Test
  public void testDiscoverIsland_validInputPasses3() {
    boolean[][] map = {{false, true, false, true},
        {true, true, false, false},
        {false, false, true, false},
        {false, false, true, false}};
    CountingIslands.Tile currentTile = new CountingIslands.Tile(2, 2);
    ArrayList<CountingIslands.Tile> islandTiles = new ArrayList<>();
    CountingIslands.discoverIsland(map, currentTile, islandTiles);
    ArrayList<CountingIslands.Tile> expectedIslandTiles = new ArrayList<>();
    expectedIslandTiles.add(new CountingIslands.Tile(2, 2));
    expectedIslandTiles.add(new CountingIslands.Tile(3, 2));
    assertEquals(expectedIslandTiles, islandTiles);
  }

  @Test(expected = AssertionError.class)
  public void testDiscoverIsland_nullMapFails() {
    CountingIslands.discoverIsland(null, new CountingIslands.Tile(0, 0), new ArrayList<>());
  }

  @Test(expected = AssertionError.class)
  public void testDiscoverIsland_nullTileFails() {
    boolean[][] map = {{true, false}, {true, false}};
    CountingIslands.discoverIsland(map, null, new ArrayList<>());
  }

  @Test(expected = AssertionError.class)
  public void testDiscoverIsland_nullAccumulatorFails() {
    boolean[][] map = {{true, false}, {true, false}};
    CountingIslands.discoverIsland(map, new CountingIslands.Tile(0, 0), null);
  }

  @Test
  public void testGetNeighbours_validCornerTilePasses() {
    boolean[][] map = {{false, true, false, true},
        {true, true, false, false},
        {false, false, true, false},
        {false, false, true, false}};
    CountingIslands.Tile tile = new CountingIslands.Tile(0, 0);
    ArrayList<CountingIslands.Tile> neighbours = CountingIslands.getNeighbours(map, tile);
    ArrayList<CountingIslands.Tile> expectedNeighbours = new ArrayList<>();
    expectedNeighbours.add(new CountingIslands.Tile(0, 1));
    expectedNeighbours.add(new CountingIslands.Tile(1, 0));
    assertEquals(expectedNeighbours, neighbours);
  }

  @Test
  public void testGetNeighbours_validMiddleTilePasses() {
    boolean[][] map = {{false, true, false, true},
        {true, true, false, false},
        {false, false, true, false},
        {false, false, true, false}};
    CountingIslands.Tile tile = new CountingIslands.Tile(1, 1);
    ArrayList<CountingIslands.Tile> neighbours = CountingIslands.getNeighbours(map, tile);
    ArrayList<CountingIslands.Tile> expectedNeighbours = new ArrayList<>();
    expectedNeighbours.add(new CountingIslands.Tile(0, 1));
    expectedNeighbours.add(new CountingIslands.Tile(1, 0));
    expectedNeighbours.add(new CountingIslands.Tile(1, 2));
    expectedNeighbours.add(new CountingIslands.Tile(2, 1));
    assertEquals(expectedNeighbours, neighbours);
  }

  @Test(expected = AssertionError.class)
  public void testGetNeighbours_nullMapFails() {
    CountingIslands.getNeighbours(null, new CountingIslands.Tile(0, 0));
  }

  @Test
  public void testGetNeighbours_emptyMapPasses() {
    boolean[][] map = {};
    assertTrue(CountingIslands.getNeighbours(map, new CountingIslands.Tile(0, 0)).isEmpty());
  }

  @Test(expected = AssertionError.class)
  public void testGetNeighbours_nullTileFails() {
    boolean[][] map = {{false, true, false, true},
        {true, true, false, false},
        {false, false, true, false},
        {false, false, true, false}};
    CountingIslands.getNeighbours(map, null);
  }

  @Test(expected = AssertionError.class)
  public void testGetNeighbours_outOfBoundsTileFails() {
    boolean[][] map = {{false, true, false, true},
        {true, true, false, false},
        {false, false, true, false},
        {false, false, true, false}};
    CountingIslands.Tile tile = new CountingIslands.Tile(5, 5);
    CountingIslands.getNeighbours(map, tile);
  }

  @Test
  public void testIsValidTile_validCornerTilePasses1() {
    assertTrue(CountingIslands.isValidTile(new CountingIslands.Tile(0, 0), 4, 4));
  }

  @Test
  public void testIsValidTile_validCornerTilePasses2() {
    assertTrue(CountingIslands.isValidTile(new CountingIslands.Tile(3, 3), 4, 4));
  }

  @Test
  public void testIsValidTile_validMiddleTilePasses2() {
    assertTrue(CountingIslands.isValidTile(new CountingIslands.Tile(2, 2), 4, 4));
  }


  @Test(expected = AssertionError.class)
  public void testIsValidTile_nullTileFails() {
    CountingIslands.isValidTile(null, 4, 4);
  }

  @Test
  public void testIsValidTile_outOfBoundsTileFails1() {
    assertFalse(CountingIslands.isValidTile(new CountingIslands.Tile(4, 4), 4, 4));
  }

  @Test
  public void testIsValidTile_outOfBoundsTileFails2() {
    assertFalse(CountingIslands.isValidTile(new CountingIslands.Tile(-1, 4), 4, 4));
  }

  @Test(expected = AssertionError.class)
  public void testIsValidTile_zeroMaxRowFails() {
    assertFalse(CountingIslands.isValidTile(new CountingIslands.Tile(3, 3), 0, 4));
  }

  @Test(expected = AssertionError.class)
  public void testIsValidTile_zeroMaxColumnsFails() {
    assertFalse(CountingIslands.isValidTile(new CountingIslands.Tile(3, 3), 4, 0));
  }

}
