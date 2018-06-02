public class BinaryTreeNode<T> {
  public T value;
  public BinaryTreeNode<T> left, right, parent;

  public BinaryTreeNode(T value) {
    this.value = value;
    left = null;
    right = null;
  }

  public BinaryTreeNode(T value, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
    this.value = value;
    this.left = left;
    this.right = right;
  }

  /**
   * Computes the height of the tree.
   *
   * @param treeNode the root of the tree whose height is to be computed
   * @return the height of the tree
   */
  public int getHeight (BinaryTreeNode<T> treeNode) {
    if (treeNode == null) {
      return 0;
    }
    return 1 + Math.max(getHeight(treeNode.left), getHeight(treeNode.right));
  }

  /**
   * Inserts a new node in the binary tree as follows: if the height of the left subtree is lower
   * or equal to the height of the right subtree then the node is inserted into the left subtree.
   *
   * @param value the value of the node to be inserted
   */
  public void insert (T value) {
    if (left == null) {
      left = new BinaryTreeNode<>(value);
      return;
    }

    if (right == null) {
      right = new BinaryTreeNode<>(value);
      return;
    }

    if (getHeight(left) <= getHeight(right)) {
      left.insert(value);
    } else {
      right.insert(value);
    }
  }

  /**
   * Traverses the tree and prints the values of nodes in post order.
   *
   * @param root the start node of traversal
   */
  public void postOrderPrint(BinaryTreeNode<T> root) {
    if (root != null) {
      postOrderPrint(root.left);
      postOrderPrint(root.right);
      System.out.println(root.value);
    }
  }

  /**
   * Computes the depth of a given node.
   *
   * @param node the node whose depth is to be computed
   * @return the depth of the given node
   */
  public static int getNodeDepth(BinaryTreeNode node) {
    int depth = 0;
    while (node != null) {
      node = node.parent;
      depth++;
    }
    return depth;
  }
}
