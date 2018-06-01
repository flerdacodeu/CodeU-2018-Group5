import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

public class MyLinkedListTest {

	private LinkedList<Integer> myList;

	@Before
	public final void init(){
		this.myList = new LinkedList<Integer>();
		this.myList.add(1);
		this.myList.addLast(2);
		this.myList.addLast(3);
		this.myList.addLast(4);
		this.myList.addLast(5);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public final void testKthElementParameterTooBig() throws IndexOutOfBoundsException, IllegalArgumentException {
		MyLinkedList.kThToLastElement(this.myList, 8);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void testKthElementEmptyList() throws IndexOutOfBoundsException, IllegalArgumentException {
		this.myList = new LinkedList<Integer>();
		MyLinkedList.kThToLastElement(this.myList, 1);
	}

	@Test(expected = Exception.class)
	public final void testKthElementParameterTooSmall() throws IndexOutOfBoundsException,IllegalArgumentException {
		MyLinkedList.kThToLastElement(this.myList, -1);
	}

	@Test
	public final void testKthElementNotZero() throws IndexOutOfBoundsException, IllegalArgumentException {
		int value = MyLinkedList.kThToLastElement(this.myList, 1);
		assertTrue(value==4);
	}

	@Test
	public final void testKthElementZero() throws IndexOutOfBoundsException, IllegalArgumentException {
		int value = MyLinkedList.kThToLastElement(this.myList, 0);
		assertTrue(value==5);
	}

	@Test
	public final void testKthElementMax() throws IndexOutOfBoundsException, IllegalArgumentException {
		int value = MyLinkedList.kThToLastElement(this.myList, 4);
		assertTrue(value==1);
	}


}
