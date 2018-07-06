import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Testing {
 @Test
 public void testNumberOfIslands() {
	 
	 Islands test = new Islands();
	 
	 //this map should test these various islands:
	 //1. island made of one tile
	 //2. island made of few tiles
	 //3. case where two islands "connect" with corners, but are separate islands
	 //4. no islands
	 boolean[][] mapExample = { {false, true, false},
			 					{false, false, false},
			 					{true, false, false},
			 					{true, false, false},
			 					{false, true, true},
			 					{false, true, false}
	 };
	 
	 boolean[][] mapExample2 = {{false, true, false},
								{false, false, false}
	 };
	 
	 boolean[][] mapExample3 = {{false, false, false},
								{false, false, false},
								{false, false, false}
	 };
	 
	 
	 boolean[][] mapExample4 = null;
	 
	 boolean[][] mapExample5 = {{true, true, true},
								{true, true, true}
	 };
	 
	 assertEquals("Number of islands: ", 3, test.numberOfIslands(mapExample));
	 assertEquals("Number of islands: ", 1, test.numberOfIslands(mapExample2));
	 assertEquals("Number of islands: ", 0, test.numberOfIslands(mapExample3));
	 assertEquals("Number of islands: ", 0, test.numberOfIslands(mapExample4));
	 assertEquals("Number of islands: ", 1, test.numberOfIslands(mapExample5));
 }
}
