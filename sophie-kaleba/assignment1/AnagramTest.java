import static org.junit.Assert.*;

import org.junit.Test;


public class AnagramTest {

    @Test
    public final void testAnagramWordsFigures() {
    	assertFalse(Anagram.areAnagrams("listen1", "silent", true));
    }
    
    @Test
    public final void testAnagramWordsEqualInsensitive() {
    	assertTrue(Anagram.areAnagrams("Listen", "silent", false));
    }
    
    @Test
    public final void testAnagramWordsEqualSensitive() {
    	assertTrue(Anagram.areAnagrams("liSten", "Silent", true));
    }
    
    @Test
    public final void testAnagramWordsOneEmpty() {
    	assertFalse(Anagram.areAnagrams("", "Silent", true));
    }
    
    @Test
    public final void testAnagramSentences() {
    	assertTrue(Anagram.areAnagrams("good morning sunshine", "sshinune doog morning", false));
    }
    
    @Test
    public final void testAnagramSentencesNotOK() {
    	assertFalse(Anagram.areAnagrams("good morning sunshine", "sshinune doog boring", false));
    }

}