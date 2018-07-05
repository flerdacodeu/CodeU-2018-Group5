/*
 * Generic class for Nodes
 * 
 * Here we override toString that is used while testing the methods
 */
public class Node<T> 
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