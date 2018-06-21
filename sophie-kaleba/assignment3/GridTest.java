import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class GridTest {
	
	private Grid testGrid;
	private List<String> testDictionary;

	@Before
	public final void init() {
		this.testGrid = new Grid("A A R T C D", 3);
		this.testDictionary = Arrays.asList("CAR", "CARD", "CART", "CAT");
	}
	
	@Test
	public final void buildFromString() {
		List<String[]> expectedContent = new ArrayList<String[]>();
		expectedContent.add(new String[]{"A" , "A" , "R"});
		expectedContent.add(new String[]{"T" , "C" , "D"});

		assertEquals(expectedContent.size(), this.testGrid.getContent().size());
		for (int i = 0; i < expectedContent.size() ; i ++) {
			assertArrayEquals(expectedContent.get(i), this.testGrid.getContent().get(i));
		}
		
	}
	
	
//	@Test
//	public final void wordSearchEmptyGrid(){
//		
//	}
//	
//	@Test
//	public final void wordSearchEmptyDict() {
//		
//	}
	@Test
	public final void testGetCharAt() {
		assertEquals("C", this.testGrid.getCharAt(1, 1));
	}
	
	@Test
	public final void testNeighbours() {
		List<int[]> expectedResult = new ArrayList<int[]>();
		expectedResult.add(new int[]{1 , 0});
		expectedResult.add(new int[]{1 , 1});
		expectedResult.add(new int[]{0 , 1});
		int size = expectedResult.size();
		
		List<int[]> actualResult = this.testGrid.allReachableNeighbours(0, 0);
		assertEquals(expectedResult.size(), actualResult.size());
		for (int i = 0; i < size ; i++) {
			assertArrayEquals(expectedResult.get(i), actualResult.get(i));
		}
	}
	
	
	@Test
	public final void testResetMarked() {
		List<int[]> expectedResult = new ArrayList<int[]>();
		expectedResult.add(new int[]{0, 0, 0});
		expectedResult.add(new int[]{0, 0, 0});
		this.testGrid.wordSearch(this.testDictionary);
		assertEquals(expectedResult.size(), this.testGrid.getMarked().size());
		this.testGrid.resetMarkedContent();
		for (int i = 0; i < expectedResult.size() ; i++) {
			assertArrayEquals(expectedResult.get(i), this.testGrid.getMarked().get(i));
		}
		
	}
	
	@Test
	public final void wordSearch3Words() {
		List<List<String>> expectedResult = new ArrayList<List<String>>();
		List<List<String>> result = this.testGrid.wordSearch(this.testDictionary);
		for (int i=0 ; i < result.size() ; i++) {
			for (int j = 0; j < result.get(i).size() ; j++) {
				System.out.println(result.get(i).get(j));
			}
		}
	}
}