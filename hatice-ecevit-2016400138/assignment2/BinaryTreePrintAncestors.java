public class BinaryTreePrintAncestors {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> n1, n2, n3, n4, n5, n6, n7, root;
        n1 = new BinaryTreeNode<>(1, null, null);
        n2 = new BinaryTreeNode<>(6, null, null);
        n3 = new BinaryTreeNode<>(2, n1, n2);
        n4 = new BinaryTreeNode<>(5, null, null);
        n5 = new BinaryTreeNode<>(3, n3, n4);
        n6 = new BinaryTreeNode<>(8, null, null);
        n7 = new BinaryTreeNode<>(4, null, n6);
        root = new BinaryTreeNode<>(7, n5, n7);
        System.out.println(root.findAncestors(n2.getData()));
        System.out.println(root.findCommonAncestor(n2,n4).getData());
    }
}
