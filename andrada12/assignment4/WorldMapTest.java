/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author andrada
 */
public class WorldMapTest {
    
    public WorldMapTest() {
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
     * Test of landNeighbours method, of class WorldMap.
     */

    /**
     * Test of anyIslandNeighbours method, of class WorldMap.
     */
    @Test
    public void testAnyIslandNeighbours() {
        System.out.println("anyIslandNeighbours");
        ArrayList<Position> neighbours = new ArrayList<Position>();
        neighbours.add(new Position(2,1));
        neighbours.add(new Position(1,2));
        
        MapPart[][] map = new MapPart[5][5];
        map[1][1] = new MapPart(0,'F');
        map[1][2] = new MapPart(1,'T');
        map[1][3] = new MapPart(0,'F');
        map[1][4] = new MapPart(0,'T');
        
        map[2][1] = new MapPart(0,'T');
        map[2][2] = new MapPart(0,'T');
        map[2][3] = new MapPart(0,'F');
        map[2][4] = new MapPart(0,'F');
        
        map[3][1] = new MapPart(0,'F');
        map[3][2] = new MapPart(0,'F');
        map[3][3] = new MapPart(0,'T');
        map[3][4] = new MapPart(0,'F');
        
        map[4][1] = new MapPart(0,'F');
        map[4][2] = new MapPart(0,'F');
        map[4][3] = new MapPart(0,'T');
        map[4][4] = new MapPart(0,'F');
        
        
        WorldMap instance = new WorldMap();
        boolean expResult = true;
        boolean result = instance.anyIslandNeighbours(neighbours, map);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateIslands method, of class WorldMap.
     */


    /**
     * Test of numberOfIslands method, of class WorldMap.
     */
    @Test
    public void testNumberOfIslands() {
        System.out.println("numberOfIslands");
        
        MapPart[][] map = new MapPart[5][5];
        map[1][1] = new MapPart(0,'F');
        map[1][2] = new MapPart(0,'T');
        map[1][3] = new MapPart(0,'F');
        map[1][4] = new MapPart(0,'T');
        
        map[2][1] = new MapPart(0,'T');
        map[2][2] = new MapPart(0,'T');
        map[2][3] = new MapPart(0,'F');
        map[2][4] = new MapPart(0,'F');
        
        map[3][1] = new MapPart(0,'F');
        map[3][2] = new MapPart(0,'F');
        map[3][3] = new MapPart(0,'T');
        map[3][4] = new MapPart(0,'F');
        
        map[4][1] = new MapPart(0,'F');
        map[4][2] = new MapPart(0,'F');
        map[4][3] = new MapPart(0,'T');
        map[4][4] = new MapPart(0,'F');
        
        
        WorldMap instance = new WorldMap();
        int expResult = 3;
        int result = instance.numberOfIslands(map);
        assertEquals(expResult, result);

    }
    
     @Test
    public void testNumberOfIslands3() {
        System.out.println("numberOfIslands");
        
        MapPart[][] map = new MapPart[5][5];
        map[1][1] = new MapPart(0,'F');
        map[1][2] = new MapPart(0,'F');
        map[1][3] = new MapPart(0,'F');
        map[1][4] = new MapPart(0,'F');
        
        map[2][1] = new MapPart(0,'F');
        map[2][2] = new MapPart(0,'F');
        map[2][3] = new MapPart(0,'F');
        map[2][4] = new MapPart(0,'F');
        
        map[3][1] = new MapPart(0,'F');
        map[3][2] = new MapPart(0,'F');
        map[3][3] = new MapPart(0,'F');
        map[3][4] = new MapPart(0,'F');
        
        map[4][1] = new MapPart(0,'F');
        map[4][2] = new MapPart(0,'F');
        map[4][3] = new MapPart(0,'F');
        map[4][4] = new MapPart(0,'F');
        
        
        WorldMap instance = new WorldMap();
        int expResult = 0;
        int result = instance.numberOfIslands(map);
        assertEquals(expResult, result);

    }
    
     @Test
    public void testNumberOfIsland3() {
        System.out.println("numberOfIslands");
        
        MapPart[][] map = new MapPart[5][5];
        map[1][1] = new MapPart(0,'F');
        map[1][2] = new MapPart(0,'T');
        map[1][3] = new MapPart(0,'F');
        map[1][4] = new MapPart(0,'T');
        
        map[2][1] = new MapPart(0,'T');
        map[2][2] = new MapPart(0,'T');
        map[2][3] = new MapPart(0,'T');
        map[2][4] = new MapPart(0,'F');
        
        map[3][1] = new MapPart(0,'F');
        map[3][2] = new MapPart(0,'T');
        map[3][3] = new MapPart(0,'T');
        map[3][4] = new MapPart(0,'F');
        
        map[4][1] = new MapPart(0,'F');
        map[4][2] = new MapPart(0,'F');
        map[4][3] = new MapPart(0,'T');
        map[4][4] = new MapPart(0,'F');
        
        
        WorldMap instance = new WorldMap();
        int expResult = 1;
        int result = instance.numberOfIslands(map);
        assertEquals(expResult, result);

    }
    
    
}
