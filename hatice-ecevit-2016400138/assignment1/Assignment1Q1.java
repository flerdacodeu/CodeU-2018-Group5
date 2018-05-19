package com.company;
 import java.util.HashMap;
public class Assignment1Q1 {

    public static void main(String[] args) {
        System.out.println(areAnagrams("apple","appll"));
        System.out.println(areAnagrams("listen","silent"));
    }
    public static boolean areAnagrams(String s1, String s2){
        HashMap<Character,Integer> letterMap=new HashMap<Character, Integer>();
        if (s1.length()!=s2.length())
                return false;
        else{
            for(int i=0;i<s1.length();i++){
                if(letterMap.containsKey(s1.charAt(i))){
                    int value=letterMap.get(s1.charAt(i));
                    letterMap.remove(s1.charAt(i));
                    value++;
                    letterMap.put(s1.charAt(i),value);
                }
                else{
                    letterMap.put(s1.charAt(i),1);
                }
            }
            for(int i=0;i<s2.length();i++){
                if(!letterMap.containsKey(s2.charAt(i)))
                    return false;
                else{
                    int value=letterMap.get(s2.charAt(i));
                    letterMap.remove(s2.charAt(i));
                    value--;
                    letterMap.put(s2.charAt(i),value);
                }
            }
            for(Integer value: letterMap.values()){
                if(value!=0)
                    return false;
            }
            return true;
        }
    }
}
