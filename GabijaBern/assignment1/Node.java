package Task1;
/* Node class for creating a list
 * it holds a reference to next Node and has a variable for value of current node
 */
public class Node<T>
{
	public Node<T> next;
	private final T value;
	
	public Node(T data)
	{
		this.value = data;
	}
	
	/* method for connecting to next node
	 * @param Node<T> next node that needs to be added to the list
	 */
	public void setNext(Node<T> nextNode)
	{
		this.next = nextNode;
	}
	
	public T getValue()
	{
		return value;
	}
	
	public void printNode()
	{
		System.out.println("Node value is: " + value);
	}
}
