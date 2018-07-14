/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package package2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Vali
 */
public class Graph {
   HashMap<Character, TreeSet<Character> > graph;
    
    public HashMap<Character, TreeSet<Character>> setGraph (ArrayList<String> dictionary ){
        graph = new  HashMap<Character,TreeSet<Character>>();
         
         for (String word : dictionary) {
             char[] wordCharacters = word.toCharArray();
             
             for (char letter : wordCharacters) {
                 TreeSet neighbours;
                 if (!graph.containsKey(letter)){
                    neighbours = new TreeSet<Character>();
                 }  else {
                    neighbours = graph.get(letter);
                 }
                 
                 for (char c : wordCharacters){
                         if (c != letter)
                             neighbours.add(c);
                     }
                     graph.put(letter, neighbours);
             }
         }
          return graph;
    }
    
    public TreeSet<Character> getTopologicalSort() {
        TreeSet<Character> topSort = new TreeSet<Character>();
        Iterator it = graph.keySet().iterator();
        
        while (it.hasNext()) {
            char letter  = (char)it.next();
            
            if (!topSort.contains(letter)) {
                topSort.add(letter);
            }
            
            for (char c : graph.get(letter)) {
                if (!topSort.contains(c)) {
                    topSort.add(letter);
                }
            }
        }
        
        
        return topSort;
    }
}
