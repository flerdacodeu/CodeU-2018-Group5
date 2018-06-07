import java.util.ArrayList;

public class Main {

  private static final int TESTS = 10;

  private static boolean equal (ArrayList<Integer> arr1, Integer[] arr2) {
    if (arr1.size() != arr2.length) {
      return false;
    }

    for (int i = 0; i < arr1.size(); i++) {
      if (arr1.get(i) != arr2[i]) {
        return false;
      }
    }

    return true;
  }

  public static void main(String[] args) {
    int passedTests = 0;
    Ancestors<Integer> ancestors = new Ancestors<>();

    BinaryTreeNode<Integer> n1 = new BinaryTreeNode<>(1);
    BinaryTreeNode<Integer> n2 = new BinaryTreeNode<>(2);
    BinaryTreeNode<Integer> n3 = new BinaryTreeNode<>(3);
    BinaryTreeNode<Integer> n4 = new BinaryTreeNode<>(4);
    BinaryTreeNode<Integer> n5 = new BinaryTreeNode<>(5);
    BinaryTreeNode<Integer> n6 = new BinaryTreeNode<>(6);
    BinaryTreeNode<Integer> n7 = new BinaryTreeNode<>(7);
    BinaryTreeNode<Integer> n8 = new BinaryTreeNode<>(8);

    n1.setParent(n2);
    n6.setParent(n2);
    n2.setParent(n3);
    n5.setParent(n3);
    n3.setParent(n7);
    n8.setParent(n4);
    n4.setParent(n7);

    n7.setLeft(n3);
    n7.setRight(n4);
    n3.setLeft(n2);
    n3.setRight(n5);
    n4.setRight(n8);
    n2.setLeft(n1);
    n2.setRight(n6);

    BinaryTreeNode<Integer> root = n7;

    // test 1
    // test for random node in the tree
    Ancestors.Result test1 = ancestors.findAncestors(root, 6, new Ancestors.Result());
    ArrayList<Integer> result1 = test1.ancestors;
    Integer[] expectedResult1 = {2,3,7};
    if (equal(result1, expectedResult1)) {
      System.out.println("Test 1 passed");
      passedTests++;
    }
    ancestors.printAncestors(root,6);

    // test 2
    // test for ancestors of root node
    Ancestors.Result test2 = ancestors.findAncestors(root, 7, new Ancestors.Result());
    ArrayList<Integer> result2 = test2.ancestors;
    if (result2.isEmpty() && test2.valueFound) {
      System.out.println("Test 2 passed");
      passedTests++;
    }
    ancestors.printAncestors(root,7);

    // test 3
    // test for nonexistent key in tree
    Ancestors.Result test3 = ancestors.findAncestors(root, 11, new Ancestors.Result());
    ArrayList<Integer> result3 = test3.ancestors;
    if (result3.isEmpty() && !test3.valueFound) {
      System.out.println("Test 3 passed");
      passedTests++;
    }
    ancestors.printAncestors(root,11);

    // test 4
    // test for null tree
    Ancestors.Result test4 = ancestors.findAncestors(null, 6, new Ancestors.Result());
    ArrayList<Integer> result4 = test4.ancestors;
    if (result4.isEmpty() && !test4.valueFound) {
      System.out.println("Test 4 passed");
      passedTests++;
    }
    ancestors.printAncestors(null,6);


    // test 5
    // test for common ancestor of two random nodes in the tree
    Integer test5 = ancestors.commonAncestor(n6, n5);
    Integer expectedValue5 = 3;
    if (test5 == expectedValue5) {
      System.out.println("Test 5 passed");
      passedTests++;
    }

    // test 6
    // test for a nonexistent node
    if (ancestors.commonAncestor(new BinaryTreeNode<>(12), n5) == null) {
      System.out.println("Test 6 passed");
      passedTests++;
    }

    // test 7
    // test for null node
    if (ancestors.commonAncestor(null, n5) == null) {
      System.out.println("Test 7 passed");
      passedTests++;
    }

    // test 8
    // test for common ancestor of the same node
    Integer test8 = ancestors.commonAncestor(n5, n5);
    Integer expectedValue8 = 5;
    if (test8 == expectedValue8) {
      System.out.println("Test 8 passed");
      passedTests++;
    }

    // test 9
    // test for a node and its parent
    Integer test9 = ancestors.commonAncestor(n2, n6);
    Integer expectedValue9 = 2;
    if (test9 == expectedValue9) {
      System.out.println("Test 9 passed");
      passedTests++;
    }

    // test 10
    // test for common ancestor of two random nodes in the tree
    Integer test10 = ancestors.commonAncestor(n1, n8);
    Integer expectedValue10 = 7;
    if (test10 == expectedValue10) {
      System.out.println("Test 10 passed");
      passedTests++;
    }

    System.out.println(passedTests + " out of " + TESTS + " tests passed");
  }
}
