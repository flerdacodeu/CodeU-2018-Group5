
import java.util.ArrayList;
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author andrada
 */
public class Dictionary {
    
    static HashMap<String, ArrayList<String>> dictionary = new HashMap<String, ArrayList<String>>();
    
    public static void setDictionary(String[] words){
        
        for(int i = 0; i < words.length; i++){
            for(int j = 0; j< words[i].length(); j++){
                String aux = words[i].substring(0, j);
                if (dictionary.containsKey(aux)){
                    dictionary.get(aux).add(words[i]);
                } else {
                    dictionary.put(aux, new ArrayList());
                    dictionary.get(aux).add(words[i]);
                }
            }
        }
    }
    
    public static boolean isWord(String s){
        if (s == null)
            return false;
        if (s.length() == 0 || s.length() == 1)
            return false;
        return dictionary.get(s.substring(0, s.length() - 1)).contains(s);
    }
    
    public static boolean isPrefix(String s){
        return dictionary.containsKey(s);
    }
    
    /*public static boolean isWord(String s){
        ArrayList<String> j = new ArrayList<String>();
        j.add("car");
        j.add("cart");
        j.add("cat");
        j.add("card");
        return j.contains(s);
    }
    
    public static boolean isPrefix(String s){
        ArrayList<String> j = new ArrayList<String>();
        j.add("c");
        j.add("ca");
        j.add("car");
        return j.contains(s);
    }*/
}
