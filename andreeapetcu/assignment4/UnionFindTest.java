import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnionFindTest {

  @Test
  public void testUnion_validInputPasses1() {
    UnionFind uf = new UnionFind(16);
    int[] expected = {0, 5, 2, 3, 5, 5, 6, 7, 8, 9, 14, 11, 12, 13, 14, 15};
    uf.union(5, 1);
    uf.union(5, 4);
    uf.union(14, 10);
    assertArrayEquals(expected, uf.getUnions());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFind_invalidElementFails() {
    UnionFind uf = new UnionFind(16);
    uf.union(5, 1);
    uf.union(5, 4);
    uf.union(14, 10);
    uf.find(18);
  }

  @Test
  public void testFind_validElementPasses1() {
    UnionFind uf = new UnionFind(16);
    uf.union(5, 1);
    uf.union(5, 4);
    uf.union(14, 10);
    assertEquals(5, uf.find(1));
  }

  @Test
  public void testFind_validElementPasses2() {
    UnionFind uf = new UnionFind(16);
    uf.union(5, 1);
    uf.union(5, 4);
    uf.union(14, 10);
    assertEquals(11, uf.find(11));
  }

  @Test
  public void testUnion_validInputPasses2() {
    UnionFind uf = new UnionFind(9);
    int[] expected = {1, 2, 2, 2, 2, 2, 2, 2, 2};
    uf.union(1, 0);
    uf.union(2, 1);
    uf.union(3, 0);
    uf.union(4, 1);
    uf.union(5, 2);
    uf.union(6, 3);
    uf.union(7, 4);
    uf.union(8, 5);
    assertArrayEquals(expected, uf.getUnions());
  }
}
