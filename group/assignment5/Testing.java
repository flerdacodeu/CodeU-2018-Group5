package task1;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import junit.framework.Assert;

public class Testing {

	@Test
	public void testGetAlphabet1() {
		List<Character[]> dic = new ArrayList<Character[]>();
		
		//we should test these cases with the following dictionary:
		//1. two words start from the same letter (word4 and word3)
		//2. few word have the same letter 'c' but it does not change the ordering
		//3. 'f' could not be compared to any letter, so should appear at the end of the list
		//4. word2 has double 'd' which should be add to the graph once
		Character[] word4 = {'a','b','c'};
		Character[] word3 = {'a','a','c'};
		Character[] word2 = {'d','d','c'};
		Character[] word1 = {'e','d','f'};
		
		
		dic.add(word4);
		dic.add(word3);
		dic.add(word2);
		dic.add(word1);
		
		Alphabet test = new Alphabet();
		List<Character> result = test.getAlphabet(dic);	
		
		List<Character> expected = new ArrayList<Character>();
		expected.add('e');
		expected.add('d');
		expected.add('a');
		expected.add('b');
		expected.add('c');
		expected.add('f');
		
		Assert.assertEquals(expected, result);
		
		
	}
	
}
