import org.junit.Test;

import static org.junit.Assert.*;

public class CountIslandsTest {
    boolean[][] mapNormal;
    boolean[][] mapAllTrue;
    boolean[][] mapAllFalse;
    boolean[][] mapOneGridFalse;
    boolean[][] mapOneGridTrue;
    boolean[][] mapBig;
    boolean[][] mapNonSquare;
    boolean[][] mapVShaped;
    boolean[][] mapCircular;
    boolean[][] mapConcentric;

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
        mapVShaped =  new boolean[][] {
                {true, true, false, false, false, true, true},
                {false, true, true, false, true, true, false},
                {false, false, true, true, true, false, false}
        };
        mapCircular = new boolean[][] {
                {true, true, true},
                {true, false, true},
                {true, true, true}
        };
        mapConcentric = new boolean[][] {
                {true, true, true, true, true},
                {true, false, false, false, true},
                {true, false, true, false, true},
                {true, false, false, false, true},
                {true, true, true, true, true}
        };
    }


    @Test
    public void findNumOfIslandsNormal() {
        setUp();
        assertEquals(3, CountIslands.findNumOfIslands(mapNormal));
    }

    @Test
    public void findNumOfIslandsAllTrue() {
        setUp();
        assertEquals(1, CountIslands.findNumOfIslands(mapAllTrue));
    }

    @Test
    public void findNumOfIslandsAllFalse() {
        setUp();
        assertEquals(0, CountIslands.findNumOfIslands(mapAllFalse));
    }

    @Test
    public void findNumOfIslandsOneGridFalse() {
        setUp();
        assertEquals(0, CountIslands.findNumOfIslands(mapOneGridFalse));
    }

    @Test
    public void findNumOfIslandsOneGridTrue() {
        setUp();
        assertEquals(1, CountIslands.findNumOfIslands(mapOneGridTrue));
    }

    @Test
    public void findNumOfIslandsBig() {
        setUp();
        assertEquals(25, CountIslands.findNumOfIslands(mapBig));
    }

    @Test
    public void findNumOfIslandsNonSquare() {
        setUp();
        assertEquals(2, CountIslands.findNumOfIslands(mapNonSquare));
    }

    @Test
    public void findNumOfIslandsVShaped() {
        setUp();
        assertEquals(1, CountIslands.findNumOfIslands(mapVShaped));
    }

    @Test
    public void findNumOfIslandsCircular() {
        setUp();
        assertEquals(1, CountIslands.findNumOfIslands(mapCircular));
    }

    @Test
    public void findNumOfIslandsConcentric() {
        setUp();
        assertEquals(2, CountIslands.findNumOfIslands(mapConcentric));
    }
}