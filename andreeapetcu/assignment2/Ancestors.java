/**
 * ASSUMPTIONS : the binary tree does not contain duplicate values
 */

import java.util.ArrayList;

public class Ancestors<T> {

  /**
   * Wrapper class that contains the ancestors of a node and a flag for the existence of a node
   * with the given key.
   */
  static class Result {
    public ArrayList ancestors;
    public boolean valueFound;

    public Result () {
      this.ancestors = new ArrayList<>();
      this.valueFound = false;
    }
  }

  /**
   * Finds the values of the nodes on the path from the node with the given key to the root.
   *
   * @param tree the root of the binary tree
   * @param key the value of the node whose ancestors are to be printed
   * @param result wrapper class that contains the ancestors and flag for existence of the key
   * @return wrapper class with ancestors of the given key
   */
  protected Result findAncestors(BinaryTreeNode<T> tree, T key, Result result) {
    if (tree == null) {
      return result;
    }
    
    // the node with the given value has been found
    // indicate this by setting the flag to true
    if (tree.getValue().equals(key)) {
      result.valueFound = true;
      return result;
    }

    // add the root value to ancestors if the node is either in the left or right subtree
    if (findAncestors(tree.getLeft(), key, result).valueFound
        || findAncestors(tree.getRight(), key, result).valueFound) {
      result.ancestors.add(tree.getValue());
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
    if (tree == null) {
      return;
    }
    Result result = findAncestors(tree, key, new Result());
    if (result == null) {
      return;
    }
    ArrayList<T> ancestors = result.ancestors;
    for (T i : ancestors){
      System.out.println(i);
    }
  }

  /**
   * Returns the first common parent node of two given child nodes in a binary tree.
   *
   * @param node1 the first child node
   * @param node2 the second child node
   * @return value of the first common parent
   */
  public T commonAncestor (BinaryTreeNode<T> node1, BinaryTreeNode<T> node2) {
    if (node1 == null || node2 == null) {
      return null;
    }
    
    int depthDifference = BinaryTreeNode.getNodeDepth(node1) - BinaryTreeNode.getNodeDepth(node2);

    // move up the tree until the deeper node has the same depth as the shallow one
    if (depthDifference > 0) {
      // if node1 has greater depth, move it up by depthDifference
      node1 = moveUp(node1, depthDifference);
    } else if (depthDifference < 0) {
      // else move node2 up by depthDifference
      node2 = moveUp(node2, Math.abs(depthDifference));
    }

    // move up the tree until the nodes intersect
    // the node of intersection is the common ancestor
    while (node1 != node2 && node1 != null && node2 != null) {
      node1 = node1.getParent();
      node2 = node2.getParent();
    }
    
    // return null in the case where there is no common ancestor
    // otherwise, return the value of the common ancestor
    return node1 == null || node2 == null ? null : node1.getValue();
  }
  
    /**
   * Moves the deeper node up by a specified number of times
   * 
   * @param node the node to be moved up the tree
   * @param moveUpBy the number of times the node is moved up
   * @return the modified node
   */
  private BinaryTreeNode<T> moveUp (BinaryTreeNode<T> node, int moveUpBy) {
    while (moveUpBy != 0) {
      node = node.getParent();
      moveUpBy--;
    }
    return node;
  }
}
