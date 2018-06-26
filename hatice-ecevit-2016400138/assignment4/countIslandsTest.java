import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class countIslandsTest {
    boolean[][] mapNormal;
    boolean[][] mapAllTrue;
    boolean[][] mapAllFalse;
    boolean[][] mapOneGridFalse;
    boolean[][] mapOneGridTrue;
    boolean[][] mapBig;
    boolean[][] mapNonSquare;

    void setUp() {
        mapNormal = new boolean[][]{
                {false, true, false, true},
                {true, true, false, false},
                {false, false, true, false},
                {false, false, true, false}};
        mapAllTrue = new boolean[][]{
                {true, true, true, true},
                {true, true, true, true},
                {true, true, true, true},
                {true, true, true, true}
        };
        mapAllFalse = new boolean[][]{
                {false, false, false, false},
                {false, false, false, false},
                {false, false, false, false},
                {false, false, false, false}
        };
        mapOneGridFalse = new boolean[][] {
                {false}
        };
        mapOneGridTrue = new boolean[][] {
                {true}
        };
        mapBig = new boolean[][]{
                {false, true, false, true, false, false, true, true, true},
                {true, true, true, false, true, false, false, false, true},
                {false, false, true, false, false, true, true, true, false},
                {false, true, false, true, false, false, true, true, true},
                {true, true, true, false, true, false, false, false, true},
                {false, false, true, false, false, true, true, true, false},
                {false, true, false, true, false, false, true, true, true},
                {true, true, true, false, true, false, false, false, true},
                {false, false, true, false, false, true, true, true, false},
                {false, true, false, true, false, false, true, true, true},
                {true, true, true, false, true, false, false, false, true},
                {false, false, true, true, false, true, true, true, false},
                {false, true, false, true, false, false, true, true, false},
                {true, true, true, false, true, false, false, false, true},
                {false, false, true, false, false, true, true, true, false},
                {true, true, false, true, false, false, true, true, true},
                {true, true, true, false, true, false, false, false, true},
                {false, false, true, false, false, true, true, true, false},
                {false, true, false, true, false, false, true, true, true},
                {true, true, true, true, true, true, true, true, true},
                {false, false, false, false, false, false, false, false, false},
        };
        mapNonSquare = new boolean[][]{
                {false, true, false, true},
                {true, true, true, false},
                {false, false, true, false}
        };
    }


        @Test
    void findNumOfIslandsNormal() {
            setUp();
            assertEquals(3, countIslands.findNumOfIslands(mapNormal, 4, 4));
    }

    @Test
    void findNumOfIslandsAllTrue() {
        setUp();
        assertEquals(1, countIslands.findNumOfIslands(mapAllTrue, 4, 4));
    }

    @Test
    void findNumOfIslandsAllFalse() {
        setUp();
        assertEquals(0, countIslands.findNumOfIslands(mapAllFalse, 4, 4));
    }

    @Test
    void findNumOfIslandsOneGridFalse() {
        setUp();
        assertEquals(0, countIslands.findNumOfIslands(mapOneGridFalse, 1, 1));
    }

    @Test
    void findNumOfIslandsOneGridTrue() {
        setUp();
        assertEquals(1, countIslands.findNumOfIslands(mapOneGridTrue, 1, 1));
    }

    @Test
    void findNumOfIslandsBig() {
        setUp();
        assertEquals(25, countIslands.findNumOfIslands(mapBig, 21, 9));
    }

    @Test
    void findNumOfIslandsNonSquare() {
        setUp();
        assertEquals(2, countIslands.findNumOfIslands(mapNonSquare, 3, 4));
    }
}