import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class MyBinaryTreeTest {

	public MyBinaryTree tree;

	@Before
	public final void init() {
		this.tree = new MyBinaryTree(7);
		MyBinaryTree.addNode(this.tree, new MyBinaryTree(3));
		MyBinaryTree.addNode(this.tree, new MyBinaryTree(4));
		MyBinaryTree.addNode(this.tree, new MyBinaryTree(2));
	}

	@Test
	public final void addNewNodeTest() {
		MyBinaryTree.addNode(this.tree, new MyBinaryTree(5));
		int value = this.tree.getLeftNode().getRightNode().getNodeValue();
		assertEquals(value,5);		
	}

	@Test
	public final void printAncestorOKTest() {
		MyBinaryTree.findAncestors(this.tree, 2, true, tree.getPath());
		assertEquals(tree.getPath(), Arrays.asList(2,3,7));
	}

	@Test
	public final void printAncestorRoot() {
		MyBinaryTree.findAncestors(this.tree, 7, true, tree.getPath());
		assertEquals(tree.getPath(), Arrays.asList(7));
	}


	@Test 
	public final void printAncestorNodeNotFoundTest() {
		assertFalse(MyBinaryTree.findAncestors(this.tree, 8, true, tree.getPath()));
	}

	@Test
	public final void findAncestor() {
		MyBinaryTree.addNode(this.tree, new MyBinaryTree(5));
		MyBinaryTree.addNode(this.tree, new MyBinaryTree(1));
		MyBinaryTree.addNode(this.tree, new MyBinaryTree(6));

		assertEquals(MyBinaryTree.lowestCommonAncestor(this.tree, 6, 5), 7);
	}
	
	@Test
	/**
	 * When there are duplicates, the priority is given to the leftest, 
	 * highest (ie closer to the root) node
	 *                        7
	 *                      /  \
	 *                     3    4
	 *                    / \  / \ 
	 *                   2  5  1  3
	 */
	public final void findAncestorDuplicateValues() {
		MyBinaryTree.addNode(this.tree, new MyBinaryTree(5));
		MyBinaryTree.addNode(this.tree, new MyBinaryTree(1));
		MyBinaryTree.addNode(this.tree, new MyBinaryTree(3));

		assertEquals(MyBinaryTree.lowestCommonAncestor(this.tree, 3, 4), 7);
	}


}
