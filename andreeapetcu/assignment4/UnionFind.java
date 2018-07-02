/**
 * Disjoint set data structure
 */
public class UnionFind {
  private int[] parents;
  private int[] depth;

  public UnionFind(int size) {
    assert size != 0;
    parents = new int[size];
    depth = new int[size];
    for (int i = 0; i < size; i++) {
      parents[i] = i;
    }
  }

  /**
   * Finds the root parent of a given element.
   *
   * @param element the element of interest
   * @return the parent of the element
   */
  private int root(int element) {
    assert element >= 0 && element < parents.length;

    while (element != parents[element]) {
      element = parents[element];
    }
    return element;
  }

  /**
   * Finds the set that contains the given element.
   *
   * @param element the element of interest
   * @return the root parent of the set that contains the element
   * @throws IllegalArgumentException if the element is invalid
   */
  public int find(int element) {
    if (element >= parents.length) {
      throw new IllegalArgumentException();
    }

    return root(element);
  }

  /**
   * Merges two sets given elements in the sets of interest.
   *
   * @param element1 element in the first set
   * @param element2 element in the second set
   * @throws IllegalArgumentException if the given elements are invalid
   */
  public void union(int element1, int element2) {
    if (element1 < 0 || element2 < 0 || element1 >= parents.length || element2 >= parents.length) {
      throw new IllegalArgumentException();
    }

    int root1 = root(element1);
    int root2 = root(element2);
    if (depth[root1] < depth[root2]) {
      parents[root1] = root2;
      depth[root2] += depth[root1];
    } else {
      parents[root2] = root1;
      depth[root1] = root2;
    }
  }

  public int[] getUnions() {
    return parents;
  }
}
