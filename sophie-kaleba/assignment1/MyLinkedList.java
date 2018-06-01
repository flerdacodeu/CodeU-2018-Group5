import java.util.LinkedList;

/**
 * find the kth to last element of a singly linked list
 */
public class MyLinkedList {

	/**
	 * find the kth to last element of a singly linked list
	 * @param linkedList - the linked list we are going through
	 * @param k - index regarding the last element of the list
	 * @return - the kth to last element of the list
	 * @throws IndexOutOfBoundsException - if the parameter is out of bounds
	 * @throws IllegalArgumentException - if the list is empty
	 */
	public static int kThToLastElement(LinkedList<Integer> linkedList, int k) throws IndexOutOfBoundsException, IllegalArgumentException {
		int size = linkedList.size();
		if (linkedList.isEmpty()) throw new IllegalArgumentException("The provided list is empty");
		if (size < k || k < 0 ) throw new IndexOutOfBoundsException(String.format("size=%s, index=%s", size, k));

		int currentIndex = size - 1 - k;
		return linkedList.get(currentIndex);
	}

}
