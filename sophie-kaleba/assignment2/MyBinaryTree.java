import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * assumptions : 
 * The tree is not necessarily full nor balanced
 * There can be duplicated nodes. 
 * In case of duplicates, the leftest, closest to the root duplicate node is picked
 */
public class MyBinaryTree {

	private int value;
	private MyBinaryTree leftNode;
	private MyBinaryTree rightNode;
	private List<Integer> pathNode;

	/**
	 * Set up the root of a binary tree
	 * @param value
	 */
	public MyBinaryTree(int value) {
		this.value = value;
		this.leftNode = null;
		this.rightNode = null;
		this.pathNode = new ArrayList<Integer>();
	}


	/**
	 * add a node or a tree to a tree at first available position (left prevails)
	 * This insertion method ensures the binary tree is balanced when possible
	 * ie. no new level as long a the current level is not full
	 * @param tree - the tree in which one wants to add a new node
	 * @param value - the value of the new node
	 */
	public static void addNode(MyBinaryTree tree, MyBinaryTree newNode) {

		Queue<MyBinaryTree> nodes = new LinkedList<MyBinaryTree>();
		nodes.add(tree);

		while (!nodes.isEmpty()){
			MyBinaryTree subtree = nodes.poll();

			if (subtree.getLeftNode() == null ) {
				subtree.setLeftNode(newNode);
				break;
			}

			else if (subtree.getRightNode() == null ) {
				subtree.setRightNode(newNode);
				break;
			}

			else {
				nodes.add(subtree.getLeftNode());
				nodes.add(subtree.getRightNode());
			}
		}
	}


	/**
	 * prints the ancestor of a node in a binary tree
	 * as "value -> all node up to the root"
	 * @param tree - the given tree
	 * @param value - the starting node
	 * @param ancestors - the list of ancestors of the node
	 * @return true if the node is ancestor of the starting node, false otherwise
	 * @throws BinaryTreeException - when the tree is empty or the value is not found
	 */
	public static boolean findAncestors(MyBinaryTree tree, int value, boolean print, List<Integer> ancestors) {

		if (tree == null) {
			return false;
		}

		if (tree.getNodeValue() == value) {
			if (print) System.out.print(value+" -> ");
			ancestors.add(value);
			return true;
		}

		if (MyBinaryTree.findAncestors(tree.getLeftNode(), value, print, ancestors) 
				|| MyBinaryTree.findAncestors(tree.getRightNode(),value, print, ancestors)) {
			if (print) System.out.print(tree.getNodeValue()+ " ");
			ancestors.add(tree.getNodeValue());
			return true;
		}	
		return false;		
	}


	/**
	 * Returns the lowest common ancestor between 2 nodes
	 * @param tree - the tree containing the 2 nodes
	 * @param node1 - the first node
	 * @param node2 - the 2d node
	 * @return the lowest (ie closer to the leaves) ancestor between 2 nodes
	 */
	public static int lowestCommonAncestor(MyBinaryTree tree, int node1, int node2) {

		List<Integer> pathNode1 = new ArrayList<Integer>();
		MyBinaryTree.findAncestors(tree, node1, true, pathNode1);

		List<Integer> pathNode2 = new ArrayList<Integer>();
		MyBinaryTree.findAncestors(tree, node2, true, pathNode2);


		int i = pathNode1.size()-1;
		int j = pathNode2.size()-1;
		boolean different = false;
		int lastCommonNode = pathNode1.get(i);

		while (!different && i>=0 && j>=0) {
			if (pathNode2.get(j) == pathNode1.get(i)) {
				lastCommonNode = pathNode2.get(j); 
			}
			i--;
			j--;
		}

		return lastCommonNode;

	}

	/*
	 * ==================================================================
	 * GETTERS AND SETTERS
	 * ================================================================== 
	 */


	/** 
	 * @return the left node of the tree
	 */
	public MyBinaryTree getLeftNode() {
		return this.leftNode;
	}

	/**
	 * @return the right node of the tree
	 */
	public MyBinaryTree getRightNode() {
		return this.rightNode;
	}

	/** 
	 * @return the left node of the tree
	 */
	public MyBinaryTree setLeftNode(MyBinaryTree newNode) {
		return this.leftNode = newNode;
	}

	/**
	 * @return the right node of the tree
	 */
	public MyBinaryTree setRightNode(MyBinaryTree newNode) {
		return this.rightNode = newNode;
	}

	/**
	 * @return the value of a given node
	 */
	public int getNodeValue() {
		return this.value;
	}

	/** 
	 * @return the potential path from a node up to the root
	 */
	public List<Integer> getPath() {
		return this.pathNode;
	}



}
