/**
 * Contains two different approaches to solving the counting islands assignment:
 * 1) countingIslands - non-recursive algorithm, using a disjoint set data structure
 * 2) countIslands - recursive flood fill algorithm
 */

import java.util.ArrayList;
import java.util.List;

public class CountingIslands {
  // land is represented as true, water is represented as false on the map
  private static final boolean WATER = false;

  /**
   * Counts the number of islands on a given map.
   *
   * NON-RECURSIVE ALGORITHM - main method
   *
   * @param rows    the number of rows in the map
   * @param columns the number of columns in the map
   * @param map     a two-dimensional map of tiles
   * @return the number of islands
   * @throws IllegalArgumentException if the map is null or the number of rows/columns is negative
   */
  public int countingIslands(int rows, int columns, boolean[][] map) {
    if (map == null || rows < 0 || columns < 0) {
      throw new IllegalArgumentException();
    }

    // if the map is empty then the number of islands is 0
    if (rows == 0) {
      return 0;
    }

    UnionFind islandUnions = mergeTilesIntoIslands(rows, columns, map);
    return findNumberOfIslands(islandUnions, map, rows, columns);
  }

  /**
   * Merges neighbour tiles that represent land on the map into islands.
   *
   * NON-RECURSIVE ALGORITHM - helper method
   *
   * @param rows    the number of rows in the map
   * @param columns the number of columns in the map
   * @param map     a two-dimensional map of tiles
   * @return a disjoint set of the unions of tiles into islands
   */
  UnionFind mergeTilesIntoIslands(int rows, int columns, boolean[][] map) {
    assert rows > 0 && columns > 0 && map != null;

    UnionFind islandUnions = new UnionFind(rows * columns);
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        Tile currentTile = new Tile(i, j);
        int currentTileIndex = i * columns + j;

        if (isLand(map, currentTile)) {
          Tile leftNeighbour = new Tile(i, j - 1);
          Tile upperNeighbour = new Tile(i - 1, j);

          if (isValidTile(upperNeighbour, rows, columns)) {
            if (isLand(map, upperNeighbour)) {
              int upperNeighbourIndex = (i - 1) * columns + j;
              islandUnions.union(currentTileIndex, upperNeighbourIndex);
            }
          }

          if (isValidTile(leftNeighbour, rows, columns)) {
            if (isLand(map, leftNeighbour)) {
              int leftNeighbourIndex = i * columns + (j - 1);
              if (islandUnions.find(currentTileIndex) != islandUnions.find(leftNeighbourIndex)) {
                islandUnions.union(currentTileIndex, leftNeighbourIndex);
              }
            }
          }
        }
      }
    }
    return islandUnions;
  }

  /**
   * Counts the number of islands on a map given a set of disjoint unions of land tiles.
   *
   * NON-RECURSIVE ALGORITHM - helper method
   *
   * @param unions  set of disjoint unions of land tiles
   * @param map     the two-dimensional map of tiles
   * @param rows    the number of rows in the map
   * @param columns the number of columns in the map
   * @return the number of islands on the map
   */
  private int findNumberOfIslands(UnionFind unions, boolean[][] map, int rows, int columns) {
    assert unions != null && map != null && rows >= 0 && columns >= 0;

    int numberOfIslands = 0;
    int[] frequencyOfRoots = new int[rows * columns];
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        int root = unions.find(i * columns + j);
        if (isLand(map, new Tile(i, j))) {
          if (frequencyOfRoots[root] == 0) {
            numberOfIslands++;
          }
        }
        frequencyOfRoots[root]++;
      }
    }
    return numberOfIslands;
  }

  /**
   * Counts the number of islands on a given map
   *
   * RECURSIVE ALGORITHM - main method
   *
   * @param rows    the number of rows in the map
   * @param columns the number of columns in the map
   * @param map     a two-dimensional map of tiles
   * @return the number of islands
   * @throws IllegalArgumentException if the map is null or the number of rows/columns is negative
   */
  public int countIslands(int rows, int columns, boolean[][] map) {
    if (rows < 0 || columns < 0 || map == null) {
      throw new IllegalArgumentException();
    }

    // if the map is empty then the number of islands is 0
    if (rows == 0) {
      return 0;
    }

    int numberOfIslands = 0;

    for (int row = 0; row < rows; row++) {
      for (int column = 0; column < columns; column++) {
        Tile currentTile = new Tile(row, column);
        if (isLand(map, currentTile)) {
          numberOfIslands++;
          discoverIsland(map, currentTile, new ArrayList<>());
        }
      }
    }
    return numberOfIslands;
  }

  /**
   * Floods an island on the map starting from a given land tile.
   *
   * RECURSIVE ALGORITHM - helper function
   *
   * @param map         a two-dimensional map of tiles
   * @param currentTile the starting tile of the flood
   * @param islandTiles accumulator of the tiles that are flooded
   */
  void discoverIsland(boolean[][] map, Tile currentTile, List<Tile> islandTiles) {
    assert map != null && currentTile != null && islandTiles != null;

    if (map.length == 0) {
      return;
    }

    if (isLand(map, currentTile)) {
      flood(map, currentTile);
      islandTiles.add(new Tile(currentTile.row, currentTile.column));
      for (Tile neighbour : getNeighbours(map, currentTile)) {
        if (isLand(map, neighbour)) {
          discoverIsland(map, neighbour, islandTiles);
        }
      }
    }
  }

  /**
   * Checks if a given tile on the map is land.
   *
   * @param map  the map the tile belongs to
   * @param tile the tile to be checked
   * @return true if the tile is land, false if the tile is water
   */
  private boolean isLand(boolean[][] map, Tile tile) {
    assert map != null && tile != null;
    return map[tile.row][tile.column];
  }

  /**
   * Changes a land tile into water tile (floods the land tile).
   *
   * RECURSIVE ALGORITHM - helper function
   *
   * @param map  the map the land tile belongs to
   * @param land the tile to be flooded
   */
  private void flood(boolean[][] map, Tile land) {
    map[land.row][land.column] = WATER;
  }

  /**
   * Finds the neighbours of a given tile on the map.
   *
   * RECURSIVE ALGORITHM - helper function
   *
   * @param map         the map that contains the tiles
   * @param currentTile the tile whose neighbours are searched for
   * @return list of the neighbours of the given tile
   */
  ArrayList<Tile> getNeighbours(boolean[][] map, Tile currentTile) {
    assert map != null && currentTile != null;

    ArrayList<Tile> neighbours = new ArrayList<>();
    int maxRows = map.length;
    if (maxRows == 0) {
      return neighbours;
    }
    int maxColumns = map[0].length;

    assert isValidTile(currentTile, map.length, map[0].length);

    if (isValidTile(new CountingIslands.Tile(currentTile.row - 1, currentTile.column), maxRows, maxColumns)) {
      neighbours.add(new CountingIslands.Tile(currentTile.row - 1, currentTile.column));
    }

    if (isValidTile(new CountingIslands.Tile(currentTile.row, currentTile.column - 1), maxRows, maxColumns)) {
      neighbours.add(new CountingIslands.Tile(currentTile.row, currentTile.column - 1));
    }

    if (isValidTile(new CountingIslands.Tile(currentTile.row, currentTile.column + 1), maxRows, maxColumns)) {
      neighbours.add(new CountingIslands.Tile(currentTile.row, currentTile.column + 1));
    }

    if (isValidTile(new CountingIslands.Tile(currentTile.row + 1, currentTile.column), maxRows, maxColumns)) {
      neighbours.add(new CountingIslands.Tile(currentTile.row + 1, currentTile.column));
    }

    return neighbours;
  }

  /**
   * Check if the tile is a valid tile(the row and column indexes of the tile are valid) on the map
   *
   * @param tile       the tile of interest
   * @param maxRows    the number of rows of the map
   * @param maxColumns the number of columns of the map
   * @return true if the tile is valid, false otherwise
   */
  boolean isValidTile(Tile tile, int maxRows, int maxColumns) {
    assert tile != null && maxRows > 0 && maxColumns > 0;
    if (tile.row < 0 || tile.column < 0 || tile.row >= maxRows || tile.column >= maxColumns) {
      return false;
    }
    return true;
  }

  /**
   * Abstraction of an element of a 2-dimensional matrix: represents a land/water tile on the map.
   * Contains the row index and the column index of the element in the matrix.
   */
  static class Tile {
    int row;
    int column;

    Tile(int row, int column) {
      this.row = row;
      this.column = column;
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == null || this.getClass() != obj.getClass()) {
        return false;
      }

      Tile tile = (Tile) obj;
      return this.row == tile.row && this.column == tile.column;
    }

    @Override
    public int hashCode() {
      int result = 17;
      result = 31 * result + row;
      result = 31 * result + column;
      return result;
    }
  }
}
