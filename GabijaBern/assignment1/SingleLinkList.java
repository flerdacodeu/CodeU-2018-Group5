package Task1;
/*SingleLinkList class for implement an algorithm to find the kth to last element of a singly linked list
 * 
 */
public class SingleLinkList<T> 
{
	private Node<T> head = null;
	int numberOfNodes=0;
	
	/* main method for testing the program
	 * 
	 */
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
	
	/* addHead method for adding nodes in front of the list
	 * @param newHead given Node reference, this node is "put" in front of the list
	 */
	public void addAtHead(Node<T> newHead)
	{
		newHead.setNext(head);
		head = newHead;
	}
	
	/* getTheElementFromEnd recursive method for getting the element from the back
	*we go to the end of the list and then "move" back
	*and check if the index we are looking matches the numberOfNodes
	*which is a variable that we increment as we go back to the 
	*beginning of the list
	*
	*@param head the head of the linkedlist from which the element should be returned
	*@param index indicates the kth index from the back of the list that we want to return
	*
	*@return Node<T> method returns the kth element from the back of the list
	*/
	public Node<T> getTheElementFromEnd(Node<T> head, int index)
	{
		Node<T> resultNode = null;
		
		if(head!=null)
		{
			resultNode = getTheElementFromEnd(head.next, index);
			
			if(numberOfNodes++==index)
				resultNode = head;
		}
		return resultNode;
	}

}