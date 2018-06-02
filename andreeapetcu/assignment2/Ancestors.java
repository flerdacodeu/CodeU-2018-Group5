/**
 * ASSUMPTIONS : the binary tree does not contain duplicate values
 */

import java.util.ArrayList;

public class Ancestors<T> {

  /**
   * Wraper class that contains the ancestors of a node and a flag for the existence of a node
   * with the given key.
   */
  class Result {
    public ArrayList<T> ancestors;
    public boolean flag;

    public Result () {
      this.ancestors = new ArrayList<>();
      this.flag = false;
    }
  }

  /**
   * Finds the values of the nodes on the path from the node with the given key to the root.
   *
   * @param tree the root of the binary tree
   * @param key the value of the node whose ancestors are to be printed
   * @param result wraper class that contains the ancestors and flag for existence of the key
   * @return wraper class with ancestors of the given key
   */
  protected Result printAncestorsAuxiliar(BinaryTreeNode<T> tree, T key, Result result) {
    if (tree == null) {
      return result;
    }

    if ((tree.value).equals(key)) {
      result.flag = true;
      return result;
    }

    if (printAncestorsAuxiliar(tree.left, key, result).flag
        || printAncestorsAuxiliar(tree.right, key, result).flag) {
      result.ancestors.add(tree.value);
      result.flag = true;
      return result;
    }

    return result;
  }

  /**
   * Prints the ancestors of the node with the given key
   *
   * @param tree the root of the tree
   * @param key the value of the node
   */
  public void printAncestors (BinaryTreeNode<T> tree, T key) {
    ArrayList<T> ancestors = printAncestorsAuxiliar(tree, key, new Result()).ancestors;
    for (T i : ancestors){
      System.out.println(i);
    }
  }

  /**
   * Returns the first common parent node of two given child nodes in a binary tree.
   *
   * @param node1 the first child node
   * @param node2 the second child node
   * @return the first common parent
   */
  public T commonAncestor (BinaryTreeNode<T> node1, BinaryTreeNode<T> node2) {
    int depthDifference = BinaryTreeNode.getNodeDepth(node1) - BinaryTreeNode.getNodeDepth(node2);

    // move up the tree until the deeper node has the same depth as the shallow one
    if (depthDifference > 0) {
      while (depthDifference != 0) {
        node1 = node1.parent;
        depthDifference--;
      }
    } else if (depthDifference < 0) {
      while (depthDifference != 0) {
        node2 = node2.parent;
        depthDifference++;
      }
    }

    // move up the tree until the nodes intersect
    // the node of intersection is the common ancestor
    while (node1 != node2 && node1 != null && node2 != null) {
      node1 = node1.parent;
      node2 = node2.parent;
    }

    return node1.value;
  }
}
