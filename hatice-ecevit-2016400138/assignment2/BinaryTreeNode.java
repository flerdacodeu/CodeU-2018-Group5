import java.util.ArrayList;
public class BinaryTreeNode<T> {
    private T data;
    private BinaryTreeNode<T> right;
    private BinaryTreeNode<T> left;

    public BinaryTreeNode(T data, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public T getData() {
        return data;
    }

    public BinaryTreeNode<T> getRight() {
        return right;
    }

    public BinaryTreeNode<T> getLeft() {
        return left;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }

    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }

    /**
     * If the given key is repeated in the tree, this method will consider the one which is upper and the most left one.
     * @param key key of the node whose ancestors are going to be printed
     * @return ancestor list of key
     */
    ArrayList<T> findAncestors(T key) {
        // the point at which the key is found
        // constructs the empty ancestor list
        if (getData().equals(key)) {
            return new ArrayList<T>();
        }
        ArrayList<T> ancestorList = null;
        if (getLeft() != null) {
            ancestorList = getLeft().findAncestors(key);
        }
        // if key is on the left of the root & the key is found
        if (ancestorList != null) {
            ancestorList.add(getData());
        }
        else {
            if (getRight() != null) {
                ancestorList = getRight().findAncestors(key);
            }
            // if key is on the right of the root & the key is found
            if (ancestorList != null) {
                ancestorList.add(getData());
            }
            else {
                return null;
            }
        }
        return ancestorList;
    }

    /**
     * @param node1 is the first node
     * @param node2 is the second node
     * @return lowest common anctestor of node1 and node2, null if either node1 or node2 is null, null if either node1 or node2 is not in the binary tree.
     */
    BinaryTreeNode<T> findCommonAncestor (BinaryTreeNode<T> node1, BinaryTreeNode<T> node2) {
        if (node1 == null || node2 == null)
            return null;
        if (!inTree(node1) || !inTree(node2))
            return null;
        if (node1.equals(node2))
            return node1;
        return findCommonAncestor(this, node1, node2);
    }

    /**
     * Complexity of this method is O(N) in the worst case because each node in the tree is visited only once.
     * @param root is the root of the tree that is been traversed
     * @param node1 is the first node
     * @param node2 is the second node
     * @return the lowest common ancestor of node1 and node2, null if root is null.
     */
    BinaryTreeNode<T> findCommonAncestor (BinaryTreeNode<T> root,BinaryTreeNode<T> node1, BinaryTreeNode<T> node2){
        if (root == null)
            return null;
        if (node1.getData() == root.getData() || node2.getData() == root.getData())
            return root;
        BinaryTreeNode<T> leftCommonAncestor = findCommonAncestor(root.left, node1, node2);
        BinaryTreeNode<T> rightCommonAncestor = findCommonAncestor(root.right, node1, node2);
        if (leftCommonAncestor != null && rightCommonAncestor != null)
            return root;
        if (leftCommonAncestor != null)
            return leftCommonAncestor;
        return rightCommonAncestor;
    }

    boolean inTree (BinaryTreeNode<T> node) {
        if (node == null)
            return false;
        if (equals(node))
            return true;
        boolean inLeftSubtree = false;
        boolean inRightSubtree = false;
        if (getLeft() != null)
            inLeftSubtree = getLeft().inTree(node);
        if (getRight() != null)
            inRightSubtree = getRight().inTree(node);
        return inLeftSubtree || inRightSubtree;
    }
}
