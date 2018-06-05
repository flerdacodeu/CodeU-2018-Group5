/**
*Class BinaryTreeAncestors is the solution for the first question in second assignment 
*Here I have implemented my own tree where only unique values are available
*/
import java.util.Queue;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class BinaryTreeAncestors
{
	public static void main(String[] args)
	{
		BTree<Integer> test = new BTree<Integer>();
		test.treeRoot = new Node<Integer>(0);
		test.insert(test.treeRoot, 1);
		test.insert(test.treeRoot, 2);
		test.insert(test.treeRoot, 3);
		test.insert(test.treeRoot, 4);
		test.insert(test.treeRoot, 5);
		test.insert(test.treeRoot, 6);
		
		//test.findAncestors(test, new Node<Integer>(3));
		
		//Node<Integer> node = new Node<Integer>(null);
		//node = test.findCommonAncestor(test, test.treeRoot.left.treeRoot.left.treeRoot, test.treeRoot.right.treeRoot);
		//System.out.println(node);
		//System.out.println(test.findCommonAncestor2(test, test.treeRoot.left.treeRoot.left.treeRoot, test.treeRoot.right.treeRoot.right.treeRoot));
	}
}
/*
 * Generic class for Nodes
 * 
 * Here we override toString that is used while testing the methods
 */
class Node<T> 
{
	public T value;
	public BTree<T> left = null, right = null;
	
	public Node(T valueToSet)
	{
		this.value = valueToSet;
	}
	
	@Override
	public String toString()
	{
		return "" + value;
	}
}
/*
 * Binary tree implementation with insertion and ancestors methods
 */
class BTree<T>
{
	Node<T> treeRoot = null;
	
	/*
	 * Method for filling levels of tree
	 * we keep a queue of nodes that don't have two children yet, every time we add a node
	 * we add it to the end of the queue and make it a child of a first node in a queue
	 * once the node in front has to children, we remove the node from the list
	 * 
	 * @param tree The tree into which we want to insert a new node
	 * @param valueToInsert The value that we want to insert
	 * 
	 * @throws NoSuchElementException Exception can be thrown if queue is empty
	 */
	public void insert(Node<T> tree, T valueToInsert) throws NoSuchElementException
	{
		Queue<Node<T>> nodesQueue = new LinkedList<Node<T>>();
        nodesQueue.add(tree);
 
        while (!nodesQueue.isEmpty()) 
        {
        	//we take first node that doesn't have two children in the list
        	Node<T> firstNode = nodesQueue.peek();
        	nodesQueue.remove();
        	
        	//we start filling from left to right
            if (firstNode.left == null)
            {
            	firstNode.left = new BTree<T>();
                firstNode.left.treeRoot = new Node<T>(valueToInsert);
                break;
            }
            else
            	nodesQueue.add(firstNode.left.treeRoot);
            
            //if left child is not empty, we try to fill the right node
            if (firstNode.right == null)
            {
            	firstNode.right = new BTree<T>();
                firstNode.right.treeRoot = new Node<T>(valueToInsert);
                break;
            }
            else
            	nodesQueue.add(firstNode.right.treeRoot);
   
        }
	}

	/*
	 * Method for finding a list of all node ancestors
	 * 
	 * @param givenTree The tree in which we want to find all ancestors
	 * @param nodeToFind The node whose ancestors we want to find in the tree
	 * 
	 * @return list including the node itself (first element in the list), the list is empty 
	 * if node is not in the tree
	 */
	public boolean findAncestors(BTree<T> givenTree, Node<T> nodeToFind)
	{
		
		//if we are given a null node or tree to search for/in
		if(nodeToFind  == null || givenTree == null)
			return false;
		
		//base case - we found the node we have been looking, so we add it to the list
		if(givenTree.treeRoot.value == nodeToFind.value)
		{	
			System.out.print("Node: " + givenTree.treeRoot.value + " ancestors: ");
			return true;
		}
		
		//climbing up from the base case and printing out the nodes
		if(findAncestors(givenTree.treeRoot.left, nodeToFind)
			|| findAncestors(givenTree.treeRoot.right, nodeToFind))
		{
			System.out.print(" " + givenTree.treeRoot.value);
			return true;
		}
		
		return false;
	}
	/*
	 * For second part of the task we could edit the first method and make it find list of all nodes, rather 
	 * than printing them out
	 * 
	 * @param givenTree The tree in which we will be searching
	 * @param nodeToFind The node that we will be searching
	 * @param list The list that we will be filling with the path from the node to the "main" root of the tree
	 * 
	 * 
	 */
	
	public boolean findAncestors2(BTree<T> givenTree, Node<T> nodeToFind, List<Node<T>> list)
	{
		
		//if we are given a null node or tree to search for/in
		if(nodeToFind  == null || givenTree == null)
			return false;
		
		//base case - we found the node we have been looking, so we add it to the list
		if(givenTree.treeRoot.value == nodeToFind.value)
		{	
			list.add(givenTree.treeRoot);
			return true;
		}
		
		//climbing up the tree from the base case and adding the rest of the nodes
		if(findAncestors2(givenTree.treeRoot.left, nodeToFind, list)
			|| findAncestors2(givenTree.treeRoot.right, nodeToFind, list))
		{
			list.add(givenTree.treeRoot);
			return true;
		}
		
		return false;
	}
	
	/*
	 * Method for finding the closest common ancestor, since I am not overriding compareTo, all object are unique and if same values
	 * appear in the tree, I don't have to worry, since I am comparing objects
	 * 
	 * @param tree The tree in which I should search for the nodes
	 * @param firstNode The first node that we are going to search
	 * @param secondNode The second node that we are going to search for and them find closest common ancestor for it and first one
	 * 
	 * @return Node<T> Method returns null if the tree doesn't exist, all other cases should be okay, since the main root can always
	 * be the common ancestor
	 * 
	 * In this method I use edited version of first question part that finds list of "path" from one node to the "main" root
	 * Then I find the intersection between two lists and first element in those lists should be the common ancestor
	 */
	public Node<T> findCommonAncestor(BTree<T> tree, Node<T> firstNode, Node<T> secondNode)
	{
		List<Node<T>> firstAncestors = new ArrayList<Node<T>>(); 
		List<Node<T>> secondAncestors = new ArrayList<Node<T>>(); 
		
		findAncestors2(tree, firstNode, firstAncestors);
		findAncestors2(tree, secondNode, secondAncestors);
		
		firstAncestors.retainAll(secondAncestors);
		
		if(firstAncestors.isEmpty())
			return null;
		else
			return firstAncestors.get(0);
	}
	
	/*
	 * Alternative solution to the task, since I have noticed that it is not recommended to store any other data structure
	 * 
	 * @return Node<T> The node that is the lowest common ancestor
	 * @param tree The tree in which we are searching for nodes ancestors
	 * @param firstNode The first given node
	 * @param secondNode The second given node
	 */
	public Node<T> findCommonAncestor2(BTree<T> tree, Node<T> firstNode, Node<T> secondNode)
	{
		Node<T> parent = tree.treeRoot;
		
		if(isParent(tree.treeRoot.left.treeRoot, firstNode) && isParent(tree.treeRoot.left.treeRoot, secondNode)) {
			parent = findCommonAncestor2(parent.left, firstNode, secondNode);
		}
		else if(isParent(tree.treeRoot.right.treeRoot, firstNode) && isParent(tree.treeRoot.right.treeRoot, secondNode)){
			parent = findCommonAncestor2(parent.right, firstNode, secondNode);
		}

		return parent;
	}
	
	/*
	 * isParent method to check if node is connected to the child: 	1.is parent 
	 * 																2. is equal to a child
	 * @return boolean If two nodes are connected in parent-child or child-child connect method returns true
	 * @param parent Node that might be a parent 
	 * @param child Node that might be a child
	 */
	public boolean isParent(Node<T> parent, Node<T> child)
	{
		if(parent!=null){
			if(parent==child) {
				return true;
			}
			else{
			if(parent.left!=null && parent.left.treeRoot!=null && isParent(parent.left.treeRoot, child)) {
				return true;
			}
			if(parent.right!=null && parent.right.treeRoot!=null && isParent(parent.right.treeRoot, child)) {
				return true;	
			}
			}
		}
		return false;
		
	}
}