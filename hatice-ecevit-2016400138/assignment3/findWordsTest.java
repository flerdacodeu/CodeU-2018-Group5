import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class findWordsTest {
    @org.junit.jupiter.api.Test
    void wordSearchNormal() {
        char[][] grid = {{'A','A','R'},{'T','C','D'}};
        assertEquals(new HashSet<String>(findWords.wordSearch(grid,2,3)),new HashSet<String>(){{add("CAR"); add("CAT"); add("CARD");}});
    }
    @org.junit.jupiter.api.Test
    void wordSearchEmptyGrid() {
        char[][] grid = new char[2][3];
        assertEquals(new HashSet<String>(findWords.wordSearch(grid,2,3)),new HashSet<String>());
    }
    @org.junit.jupiter.api.Test
    void wordSearchNoWords() {
        char[][] grid = {{'H','T','C'},{'C','V','T'}};
        assertEquals(new HashSet<String>(findWords.wordSearch(grid,2,3)),new HashSet<String>());
    }
    @org.junit.jupiter.api.Test
    void wordSearchOneLetter() {
        char[][] grid = {{'I'}};
        assertEquals(new HashSet<String>(findWords.wordSearch(grid,1,1)),new HashSet<String>(){{add("I");}});
    }
}