import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class FindAlphabetTest {

    @Test
    public void findAlphabetNormal() {
        ArrayList<String> words = new ArrayList<String>(Arrays.asList("ART", "RAT", "CAT", "CAR"));
        List<Character> alphabet1 = Arrays.asList('A', 'T', 'R', 'C');
        List<Character> alphabet2 = Arrays.asList('T', 'A', 'R', 'C');
        assertTrue(FindAlphabet.findAlphabet(words).equals(alphabet1) || FindAlphabet.findAlphabet(words).equals(alphabet2));
    }

    @Test
    public void findAlphabetEmpyList() {
        ArrayList<String> words = new ArrayList<String>();
        assertEquals(FindAlphabet.findAlphabet(words),new ArrayList<String>());
    }

    @Test
    public void findAlphabetOneWord() {
        ArrayList<String> words = new ArrayList<String>(Arrays.asList("ART"));
        assertEquals(FindAlphabet.findAlphabet(words).size(),3);
    }

    @Test
    public void findAlphabetDifferentLength() {
        ArrayList<String> words = new ArrayList<>(Arrays.asList("ART", "TAR", "TARD", "RAD", "DAT"));
        List<Character> alphabet = Arrays.asList('A', 'T', 'R', 'D');
        assertEquals(alphabet, FindAlphabet.findAlphabet(words));
    }
}