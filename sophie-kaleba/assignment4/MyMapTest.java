import static org.junit.Assert.*;

import org.junit.Test;

public class MyMapTest {
	
	@Test
	public void test3Islands() {
		boolean[][] map = new boolean[][]{
			  { false, true, false, true },
			  { true, true, false, false },
			  { false, false, true, false },
			  { false, false, true, false },
			};
		int res = MyMap.getNumberOfIslands(4, 4, map);
		assertEquals(res,3);
	}
	
	@Test
	public void testNoIsland() {
		boolean[][] map = new boolean[][]{
			  { false, false, false, false },
			  { false, false, false, false },
			  { false, false, false, false },
			  { false, false, false, false },
			};
		int res = MyMap.getNumberOfIslands(4, 4, map);
		assertEquals(res,0);
	}
	
	@Test
	public void testOneBigIsland() {
		boolean[][] map = new boolean[][]{
			  { true, true, true, true },
			  { true, true, true, true },
			  { true, true, true, true },
			  { true, true, true, true },
			};
		int res = MyMap.getNumberOfIslands(4, 4, map);
		assertEquals(res,1);
	}

}
