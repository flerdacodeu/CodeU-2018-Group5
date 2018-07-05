package task1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

public class Testing {
@Test
public void testExample() {
	WordSearch example = new WordSearch();
	 
	Cell[][] grid = { 	{ new Cell('a'), new Cell('a'), new Cell('r')}, 
						{ new Cell('t'), new Cell('c'), new Cell('d')}};

	Dictionary dictionary1 = new Dictionary();
	dictionary1.putFromRoot("car");
	dictionary1.putFromRoot("card");
	dictionary1.putFromRoot("cart");
	dictionary1.putFromRoot("cat");
	dictionary1.putFromRoot("dog");
	dictionary1.putFromRoot("gabi");
	 
	 Set<String> foundWords = example.getWordsSet(grid, dictionary1);
	 
	 //assert that there are 3 found words (cat, car, card)
	 assertEquals(3, foundWords.size());
	 
	 //check with words that cannot be found on the grid
	 assertFalse(foundWords.contains("nope"));
	 assertFalse(foundWords.contains("hello"));
	 
	 //check various cell combinations
	 assertTrue(foundWords.contains("car"));
	 assertTrue(foundWords.contains("cat"));
	 assertTrue(foundWords.contains("card"));
}
@Test
public void testInGrid() {
	WordSearch example = new WordSearch();
	 
	Cell[][] grid = { 	{ new Cell('a'), new Cell('a'), new Cell('r')}, 
						{ new Cell('t'), new Cell('c'), new Cell('d')}};
	
	//check if x is illegal
	assertFalse(example.inGrid(-1, 1, grid));
	//check if y is illegal
	assertFalse(example.inGrid(1, -1, grid));
	//check if both x and y are illegal
	assertFalse(example.inGrid(-1, -1, grid));
	//check if parameters are legal
	assertTrue(example.inGrid(1, 1, grid));
	
	//test if it is not allowed to use the cell if it was visited
	grid[0][0].visited=true;
	assertFalse(example.inGrid(0,0, grid));
}
@Test
public void testIsPrefix() {
	Dictionary dictionary = new Dictionary();
	dictionary.putFromRoot("happy");
	dictionary.putFromRoot("kitten");
	dictionary.putFromRoot("avocado");
	dictionary.putFromRoot("yes");
	dictionary.putFromRoot("dog");
	
	//check simple prefix
	assertTrue(dictionary.isPrefix("ha"));
	assertTrue(dictionary.isPrefix("a"));
	//check if a word in a dictionary is a prefix
	assertTrue(dictionary.isPrefix("dog"));
	//check that program acts sensibly when given a longer word
	assertFalse(dictionary.isPrefix("doggy"));
}
@Test
public void testIsWord() {
	Dictionary dictionary = new Dictionary();
	dictionary.putFromRoot("happy");
	dictionary.putFromRoot("kitten");
	dictionary.putFromRoot("avocado");
	dictionary.putFromRoot("yes");
	dictionary.putFromRoot("dog");
	
	///check if words are found
	assertTrue(dictionary.isPrefix("happy"));
	assertTrue(dictionary.isPrefix("avocado"));
	assertTrue(dictionary.isPrefix("dog"));
	//check if method returns false for word not from dictionary
	assertFalse(dictionary.isPrefix("doggy"));
}
 
}
