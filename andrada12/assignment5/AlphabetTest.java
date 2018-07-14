/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package package2;

import java.util.ArrayList;
import java.util.TreeSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Vali
 */
public class AlphabetTest {
    
    public AlphabetTest() {
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
     * Test of getAlphabet method, of class Alphabet.
     */
    @Test
    public void testGetAlphabet() {
        System.out.println("getAlphabet");
        ArrayList<String> dictionary = new ArrayList<String>();
        dictionary.add("RAT");
        dictionary.add("CAT");
        dictionary.add("MAP");
        dictionary.add("TRAP");
        Alphabet instance = new Alphabet();
        TreeSet<Character> expResult = new TreeSet<Character>();
        expResult.add('A');
        expResult.add('C');
        expResult.add('M');
        expResult.add('P');
        expResult.add('R');
        expResult.add('T');
        TreeSet<Character> result = instance.getAlphabet(dictionary);
        assertEquals(expResult, result);
       
    }
    
    @Test
    public void testGetAlphabet2() {
        System.out.println("getAlphabet");
        ArrayList<String> dictionary = new ArrayList<String>();

        Alphabet instance = new Alphabet();
        TreeSet<Character> expResult = new TreeSet<Character>();

        TreeSet<Character> result = instance.getAlphabet(dictionary);
        assertEquals(expResult, result);
       
    }
    
    @Test
    public void testGetAlphabet3() {
        System.out.println("getAlphabet");
        ArrayList<String> dictionary = new ArrayList<String>();
        dictionary.add("TRAP");
        Alphabet instance = new Alphabet();
        TreeSet<Character> expResult = new TreeSet<Character>();
        expResult.add('A');
        expResult.add('P');
        expResult.add('R');
        expResult.add('T');
        TreeSet<Character> result = instance.getAlphabet(dictionary);
        assertEquals(expResult, result);
       
    }
    
}
