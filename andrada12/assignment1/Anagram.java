
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
public class Anagram {
    
    
    private static final int FIRST_CHARACTER = 96;
    private static final int ALPHABET_SIZE = 27;
    
    
    public  static boolean areAnagrams(String s1, String s2){
        if (s1.length()!= s2.length())
            return false;
        /* I convert all he letters to lower case in order to verify
        if 2 strings are anagrams - > I consider that even string Ab and ab
        anagrams*/
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();
        
        char[] a = s1.toCharArray();
        char[] b = s2.toCharArray();
        
        /* initalize with number of letters in alphabet + 1  */
        int[] v1 = new int[ALPHABET_SIZE + 1]; 
        int[] v2 = new int[ALPHABET_SIZE + 1]; 
   
        /*  will be considers just the lower case letters, that in ascii code
            are from 97 to 122 (a-z)*/
        for (int i = 0; i < a.length; i++)
            v1[a[i] - FIRST_CHARACTER]++;
        
        for (int i = 0; i <  b.length; i++)
            v2[b[i] - FIRST_CHARACTER]++;
        
                    
        return Arrays.equals(v1, v2);
    }
    
    public static void main(String[] args){
        
        if (Anagram.areAnagrams("apple","ppAle"))
            System.out.println("Anagram");
        else
            System.out.println("Non Anagram");

    }
}
