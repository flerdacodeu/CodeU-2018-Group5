import java.util.LinkedList;

/**
 * Question 2
 * Implement an algorithm to find the kth to last element of a singly linked list
 *
 */
public class MyLinkedList {
	
	/**
	 * find the kth to last element of a singly linked list
	 * @param linkedList - the linked list we are going through
	 * @param k - index regarding the last element of the list
	 * @return - the kth to last element of the list
	 * @throws Exception - if the parameter is out of bounds
	 */
	public int kThToLastElement(LinkedList<Integer> linkedList, int k) throws Exception {
		int size = linkedList.size();
		if (size < k || k < 0 ) throw new Exception("k is not valid. It must be part of the interval [0;size of list[");
		
		int currentIndex = 0;
		while (k+currentIndex != size-1) {
			currentIndex++;			
		}
		return linkedList.get(currentIndex);
	}

}
