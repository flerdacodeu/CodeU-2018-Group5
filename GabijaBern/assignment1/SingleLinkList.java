//Q2 Implement an algorithm to find the kth to last element of a singly linked list
public class SingleLinkList<Type> 
{
	private Node<Type> head = null;
	int numberOfNodes=0;
	
	//testing the program
	public static void main(String[] args)
	{
		SingleLinkList<String> test1 = new SingleLinkList<String>();
		
		test1.addAtHead(new Node<String>("Last"));
		test1.addAtHead(new Node<String>("4th"));
		test1.addAtHead(new Node<String>("3th"));
		test1.addAtHead(new Node<String>("2th"));
		test1.addAtHead(new Node<String>("1th"));
		
		
		Node<String> nodeTest = test1.getTheElementFromEnd(test1.head, 3);
		nodeTest.printNode();
	}
	
	//method for adding nodes in front of the list
	public void addAtHead(Node<Type> newHead)
	{
		newHead.setNext(head);
		head = newHead;
	}
	
	//recursive method for getting the element from the back
	//we go to the end of the list and then "move" back
	//and check if the index we are looking matches the numberOfNodes
	//which is a variable that we increment as we go back to the 
	//beginning of the list
	public Node<Type> getTheElementFromEnd(Node<Type> head, int index)
	{
		Node<Type> resultNode = null;
		
		if(head!=null)
		{
			resultNode = getTheElementFromEnd(head.next, index);
			
			if(numberOfNodes++==index)
				resultNode = head;
		}
		return resultNode;
	}
}

//class for Node objects
class Node<T>
{
	public final Node<T> next;
	private final T value;
	
	public Node(T data)
	{
		this.value = data;
	}
	
	//method for connecting to next node
	public void setNext(Node<T> nextNode)
	{
		this.next = nextNode;
	}
	
	public Type getValue()
	{
		return value;
	}
	
	public void printNode()
	{
		System.out.println("Node value is: " + value);
	}
}
