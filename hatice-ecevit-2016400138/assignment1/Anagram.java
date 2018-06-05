 import java.util.Arrays;
 import java.util.HashMap;
public class Anagram {

    public static void main(String[] args) {
        System.out.println(areAnagrams("apple","appll",true));
        System.out.println(areAnagrams("listen","Silent",false));
        System.out.println(areAnagramSentences("hi there hello","iH tHree lloeh",false));
        System.out.println(areAnagramSentences("a a", "a b", false));
        System.out.println(areAnagramSentences("a b b c", "a b c c", false));
    }

    /**
     * @param s1    First String
     * @param s2    Second String
     * @param caseSensitive true if the method should work in a case sensitive way, otherwise false
     * @return  true if s1 and s2 are anagrams, false otherwise
     */
    public static boolean areAnagrams(String s1, String s2, boolean caseSensitive){
        if (!caseSensitive) {
            s1 = s1.toLowerCase();
            s2 = s2.toLowerCase();
        }
        HashMap<Character,Integer> letterMap=new HashMap<Character, Integer>();
        if (s1.length()!= s2.length())
                return false;
        else {
            for (int i = 0;i<s1.length();i++) {
                char currentChar = s1.charAt(i);
                if (letterMap.containsKey(currentChar)) {
                    int value = letterMap.get(currentChar);
                    value++;
                    letterMap.put(currentChar,value);
                }
                else {
                    letterMap.put(currentChar,1);
                }
            }
            for (int i = 0;i<s2.length();i++) {
                char currentChar = s2.charAt(i);
                if (!letterMap.containsKey(currentChar))
                    return false;
                else {
                    int value = letterMap.get(currentChar);
                    value--;
                    letterMap.put(currentChar,value);
                }
            }
            for (Integer value: letterMap.values()) {
                if(value != 0)
                    return false;
            }
            return true;
        }
    }

    /**
     * @param s1    First Sentence
     * @param s2    Second Sentence
     * @param caseSensitive true if the method should work in a case sensitive way, otherwise false
     * @return  true if s1 and s2 are anagram sentences, false otherwise
     */
    public static boolean areAnagramSentences(String s1,String s2, boolean caseSensitive){
        String[] words1 = s1.split("\\s+");
        String[] words2 = s2.split("\\s+");
        Boolean[] isMatched1 = new Boolean[words1.length];
        Boolean[] isMatched2 = new Boolean[words2.length];
        if (words1.length != words2.length) {
            return false;
        }
        else {
            boolean[] anagram1 = new boolean[words1.length];
            boolean[] anagram2 = new boolean[words2.length];
            Arrays.fill(anagram1,Boolean.FALSE);
            Arrays.fill(anagram2,Boolean.FALSE);
            Arrays.fill(isMatched1,Boolean.FALSE);
            Arrays.fill(isMatched2,Boolean.FALSE);
            for (int i = 0;i<words1.length;i++) {
                for (int j = 0;j<words2.length;j++) {
                    if (areAnagrams(words1[i],words2[j],caseSensitive) && isMatched2[j] == false) {
                        anagram1[i] = true;
                        isMatched2[j] = true;
                    }
                    if (areAnagrams(words2[i],words1[j], caseSensitive) && isMatched1[j] == false) {
                        anagram2[i] = true;
                        isMatched1[j] = true;
                    }
                }
                if(anagram1[i] == false)
                    return false;
                if(anagram2[i] == false)
                    return false;
            }
            return !Arrays.asList(anagram1).contains(false)|!Arrays.asList(anagram2).contains(false);
        }
    }
}
