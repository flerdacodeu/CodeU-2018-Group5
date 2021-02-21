
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author andrada
 */
public class WordGrid {
    int[] Ox = new int[]{-1,0,1};
    int[] Oy = new int[]{-1,0,1};
    
    static class Position implements Comparable<Position>{
        int x, y;
        
        public Position(int x, int y){
            this.x = x;
            this.y = y;
        }
        
        public String toString(){
            return x + " "+y;
        }

        @Override
        public int compareTo(Position o) {
            if (x == o.x && y == o.y)
                return 0;
            if (x == o.x)
                return y - o.y;
            return x - o.x;
        }
    }
    
    /* Initiates dfs from all the graph nodes */
    public ArrayList<String> words(char[][] chars, String[] dictionary){
       Dictionary.setDictionary(dictionary); 
       boolean[][] visited = new boolean[chars.length][chars[0].length];       
       String result = "";
       ArrayList<String> allWards = new ArrayList<String>(); 
       for (int i = 0; i< chars.length; i++)
           for (int j = 0; j < chars[0].length; j++){
               result = "" + chars[i][j];
               dfs(i,j,chars,result,visited, allWards);
               visited = new boolean[chars.length][chars[0].length];
           }
       return allWards;
    }
    
    /* Given a position (x,y) it generates all its neighbours from a matrix */
    public ArrayList<Position> neighbors(int x, int y, char[][] chars, String result, boolean[][] visited){
        ArrayList<Position> res = new ArrayList<Position>();
        
        for(int i = 0; i < Ox.length; i++){
            for(int j = 0; j < Oy.length; j++){
                int nextX = x + Ox[i];
                int nextY = y + Oy[j];
                if (nextX != x || nextY != y){
                    if (nextX >= 0 && nextX < chars.length){
                        if (nextY >=0 && nextY < chars[0].length ){
                            if (visited[nextX][nextY] == false){
                                String aux = "";
                               
                                aux = aux + chars[nextX][nextY];
                                String aux2 = result.concat(aux);
                                if ( Dictionary.isPrefix(result) )
                                    res.add(new Position(nextX, nextY));
                            }
                        }
                    }
                }
            }
        }
        
        return res;
    }
    
    /*  Goes through the graph from one initial node; continues on a path only
     if it is a prefix*/
    public void dfs(int x, int y, char[][] chars, String result, boolean visited[][], ArrayList<String> allWords){
        if (Dictionary.isWord(result))
            allWords.add(result);
        
        visited[x][y] = true;
        ArrayList<Position> neighbours = neighbors(x,  y, chars, result, visited);
      
        if (neighbours.size() == 0)
           return;
        
        for (Position neighbour : neighbours){        
            result = result + chars[neighbour.x][neighbour.y];
            dfs(neighbour.x, neighbour.y, chars, result, visited, allWords);
            result = result.substring(0, result.length() - 1);
        }
    }
}
