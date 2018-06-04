import jdk.nashorn.internal.ir.BinaryNode;

/**
 * 
 *
 */
public class MyBinaryTree {

	private int value;
	private MyBinaryTree leftNode;
	private MyBinaryTree rightNode;

	/**
	 * Set up the root of a binary tree
	 * @param value
	 */
	public MyBinaryTree(int value) {
		this.value = value;
		this.leftNode = null;
		this.rightNode = null;
	}

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
	
	public int getNodeValue() {
		return this.value;
	}
	
	
	public static MyBinaryTree breadthTraversal(MyBinaryTree tree, int level) {
		if (level == 0) {
			return tree.value;
		}
	}


	/**
	 * add a node to a tree at first available position (left prevails)
	 * @param value - the value of the new node
	 */
	public void addNode(int value) {
		if (this.getLeftNode() == null) {
			this.leftNode = new MyBinaryTree(value);
		}
		
		else if (this.getRightNode() == null) {
			this.rightNode = new MyBinaryTree(value);
		}

		/* we have to go through the tree to find a free spot */

	}

}