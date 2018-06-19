/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author andrada
 */
public class WordGridTest {
    
    public WordGridTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of words method, of class WordGrid.
     */
    @Test
    public void testWords() {
        System.out.println("words");
        char[][] chars = {{'a','a','r'}, {'t', 'c', 'd'}};
        WordGrid instance = new WordGrid();
        
        ArrayList<String> result = instance.words(chars, new String[]{"cat", "cart", "card", "car"});
        
        /*Setting expected result */
        ArrayList<String> expResult = new ArrayList<String>();
        expResult.add("cat");
        expResult.add("car");
        expResult.add("card");
        
        assertEquals(expResult, result);
    }
    
    @Test
    public void testWords2() {
        System.out.println("words");
        char[][] chars = {{'s','k','e', 'r'}, {'t', 'y', 'd', 'p'}, {'z', 'n', 'p', 'l'}};
        WordGrid instance = new WordGrid();
        
        ArrayList<String> result = instance.words(chars, new String[]{"sky", "key", "keen"});
        
        /*Setting expected result */
        ArrayList<String> expResult = new ArrayList<String>();
        expResult.add("sky");
        expResult.add("key");
        
        
        assertEquals(expResult, result);
    }
    
    @Test
    public void testWords3() {
        System.out.println("words");
        char[][] chars = {{'s','k','z', 'r'}, {'t', 'p', 'd', 'p'}, {'z', 'n', 'p', 'l'}};
        WordGrid instance = new WordGrid();
        
        ArrayList<String> result = instance.words(chars, new String[]{"sky", "key", "keen"});
       
        /*Setting expected result */
        ArrayList<String> expResult = new ArrayList<String>();
        
        assertEquals(expResult, result);
    }
    

    

    /**
     * Test of dfs method, of class WordGrid.
     */
    @Test
    public void testDfs() {
        System.out.println("dfs");
        /* Setting function parameters*/
        Dictionary.setDictionary(new String[]{"cat", "cart", "car", "card"});
        ArrayList<String> allWords = new ArrayList<String>();
        int x = 1;
        int y = 1;
        char[][] chars = {{'a','a','r'}, {'t', 'c', 'd'}};
        String result_2 = "c";
        boolean[][] visited = new boolean[2][3];
        WordGrid instance = new WordGrid();
        
        /* Calling function */
        instance.dfs(x, y, chars, result_2, visited, allWords );
        
        /*Setting expected result */
        ArrayList<String> expRes = new ArrayList<String>();
        expRes.add("cat");
        expRes.add("car");
        expRes.add("card");
        
        assertEquals(expRes, allWords);
        
    }
    
    @Test
    public void testDfs2() {
        System.out.println("dfs");
        /* Setting function parameters*/
        Dictionary.setDictionary(new String[]{"cat", "cart", "car", "card"});
        ArrayList<String> allWords = new ArrayList<String>();
        int x = 0;
        int y = 1;
        char[][] chars = {{'a','a','r'}, {'t', 'c', 'd'}};
        String result_2 = "a";
        boolean[][] visited = new boolean[2][3];
        WordGrid instance = new WordGrid();
        
        /* Calling function */
        instance.dfs(x, y, chars, result_2, visited, allWords );
        
        /*Setting expected result */
        ArrayList<String> expRes = new ArrayList<String>();
        
        assertEquals(expRes, allWords);
        
    }
    
    @Test
    public void testDfs3() {
        System.out.println("dfs");
        /* Setting function parameters*/
        Dictionary.setDictionary(new String[]{"key", "sky"});
        ArrayList<String> allWords = new ArrayList<String>();
        int x = 0;
        int y = 0;
        char[][] chars = {{'s','k','e', 'r'}, {'t', 'y', 'd', 'p'}, {'z', 'n', 'p', 'l'}};
        String result_2 = "s";
        boolean[][] visited = new boolean[3][4];
        WordGrid instance = new WordGrid();
        
        /* Calling function */
        instance.dfs(x, y, chars, result_2, visited, allWords );
        
        /*Setting expected result */
        ArrayList<String> expRes = new ArrayList<String>();
        expRes.add("sky");
                
        assertEquals(expRes, allWords);
        
    }
    
    @Test
    public void testDfs4() {
        System.out.println("dfs");
        /* Setting function parameters*/
        Dictionary.setDictionary(new String[]{"key", "sky"});
        ArrayList<String> allWords = new ArrayList<String>();
        int x = 0;
        int y = 1;
        char[][] chars = {{'s','k','e', 'r'}, {'t', 'y', 'd', 'p'}, {'z', 'n', 'p', 'l'}};
        String result_2 = "k";
        boolean[][] visited = new boolean[3][4];
        WordGrid instance = new WordGrid();
        
        /* Calling function */
        instance.dfs(x, y, chars, result_2, visited, allWords );
        
        /*Setting expected result */
        ArrayList<String> expRes = new ArrayList<String>();
        expRes.add("key");
                
        assertEquals(expRes, allWords);
        
    }

  
    
}
