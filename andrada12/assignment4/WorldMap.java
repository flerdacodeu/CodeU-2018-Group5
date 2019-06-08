
import java.util.ArrayList;
import java.util.TreeSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author andrada
 */

 class MapPart{
        int islandNumber;
        char type;
        
        public MapPart(int islandNumber,  char type){
            this.islandNumber = islandNumber;
            this.type = type;
        }
        
        public String toString(){
            return islandNumber+" ";
        }
    }

 class Position{
        int x;
        int y;
        
        public Position(int x, int y){
            this.x = x;
            this.y = y;
        }
        
        public String toString(){
            return x + " " +y;
        }
    }

public class WorldMap {
    
   
    
    /* Calculates the positions of the neighbours of a given node.
        Returns just the neighbours that are land*/
    public  ArrayList<Position> landNeighbours(Position position, MapPart[][] map){
        ArrayList<Position> solution = new ArrayList<Position>();
        /* vertical moves */
        if (position.y - 1 > 0 ) 
            if ( map[position.x][position.y - 1 ].type == 'T' )
            solution.add(new Position(position.x, position.y - 1));
        if (position.y + 1 < map.length && map[position.x][position.y + 1 ].type == 'T' )
            solution.add(new Position(position.x, position.y + 1));
        
        /* orizontal moves */
        if (position.x - 1 > 0 && map[position.x - 1][position.y].type == 'T' )
            solution.add(new Position(position.x - 1, position.y));
        if (position.x + 1 < map[1].length && map[position.x + 1][position.y].type == 'T' )
            solution.add(new Position(position.x + 1, position.y));
        
        return solution;
    }
    
    public boolean anyIslandNeighbours(ArrayList<Position> neighbours, MapPart[][] map){
        for(Position n : neighbours)
            if (map[n.x][n.y].islandNumber != 0)
                return true;       
        return false;
    }
    
    public void updateIslands(ArrayList<Position> neighbours, MapPart[][] map, Position current){
        int min = Integer.MAX_VALUE;
        TreeSet<Integer> numbersOfNeighbourIslands = new TreeSet<Integer>();
        for (Position p : neighbours){
            if (map[p.x][p.y].islandNumber < min && map[p.x][p.y].islandNumber != 0)
                min = map[p.x][p.y].islandNumber;
                numbersOfNeighbourIslands.add(map[p.x][p.y].islandNumber);
        }
        
        /* Update current position with the number of the smalles island */
        map[current.x][current.y].islandNumber = min;
        
        /* If my current position unfies more island than these piecees of land
        should be seen as a whole island. This is indicated by numberIsland field*/
        if (numbersOfNeighbourIslands.size() >= 1){
            for(int i = 1; i < map.length; i++)
                for (int j = 1; j < map[1].length; j++){
                    if ( numbersOfNeighbourIslands.contains(map[i][j].islandNumber) )
                        map[i][j].islandNumber = min;
                }
        }
        
    }
    
    /* o sa numar numerele distincte ce rep o insula; ex am insula 1 si 3*/
    public int numberOfIslands(MapPart[][] map){
        int islandNumber = 0;
        ArrayList<MapPart> islands = new ArrayList<MapPart>();
        
        for(int i = 1; i < map.length; i++){
            for(int j = 1; j< map[1].length; j++){
                if (map[i][j].type == 'T'){
                    ArrayList<Position> neighbours = landNeighbours(new Position(i,j),map);
                    /* if it has no neighbours */ 
                    if (neighbours.size() == 0){
                        islandNumber++;
                        map[i][j].islandNumber = islandNumber;
                        continue;
                    }                   
                    /* its neighbours are not yet part of an island*/
                    if (!anyIslandNeighbours(neighbours, map)){
                        islandNumber++;
                        map[i][j].islandNumber = islandNumber;
                        continue;
                    }
                    updateIslands(neighbours,map, new Position(i,j));
                }
            }
        }
        
        TreeSet<Integer> islandNumbers  = new TreeSet<Integer>();
        for (int i = 1; i < map.length; i++){
            for(int j = 1; j < map[1].length; j++)
                islandNumbers.add(map[i][j].islandNumber);
        }
            
        return islandNumbers.size()-1;
    }
    
    
}
