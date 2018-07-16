/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package package2;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 *
 * @author Vali
 */
public class Alphabet {
    
    public TreeSet<Character> getAlphabet(ArrayList<String> dictionary){
    
       Graph graph = new Graph();
       graph.setGraph(dictionary);
         
        return graph.getTopologicalSort();
    }
    
}
