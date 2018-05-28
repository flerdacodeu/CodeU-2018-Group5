 import java.util.Arrays;
 import java.util.HashMap;
public class Anagram {

    public static void main(String[] args) {
        System.out.println(areAnagrams("apple","appll",true));
        System.out.println(areAnagrams("listen","Silent",false));
        System.out.println(areAnagramSentences("hi there hello","iH tHree lloeh",false));
    }
    public static boolean areAnagrams(String s1, String s2, boolean caseSensitive){
        if(!caseSensitive){
            s1=s1.toLowerCase();
            s2=s2.toLowerCase();
        }
        HashMap<Character,Integer> letterMap=new HashMap<Character, Integer>();
        if (s1.length()!=s2.length())
                return false;
        else{
            for(int i=0;i<s1.length();i++){
                char currentChar=s1.charAt(i);
                if(letterMap.containsKey(s1.charAt(i))){
                    int value=letterMap.get(currentChar);
                    value++;
                    letterMap.put(currentChar,value);
                }
                else{
                    letterMap.put(currentChar,1);
                }
            }
            for(int i=0;i<s2.length();i++){
                char currentChar=s2.charAt(i);
                if(!letterMap.containsKey(currentChar))
                    return false;
                else{
                    int value=letterMap.get(currentChar);
                    value--;
                    letterMap.put(currentChar,value);
                }
            }
            for(Integer value: letterMap.values()){
                if(value!=0)
                    return false;
            }
            return true;
        }
    }
    public static boolean areAnagramSentences(String s1,String s2, boolean caseSensitive){
        String[] words1 = s1.split("\\s+");
        String[] words2 = s2.split("\\s+");
        if(words1.length!=words2.length){
            return false;
        }
        else{
            boolean[] anagram=new boolean[words1.length];
            Arrays.fill(anagram,Boolean.FALSE);
            for(int i=0;i<words1.length;i++){
                for(int j=0;j<words2.length;j++){
                    if(areAnagrams(words1[i],words2[j],caseSensitive))
                        anagram[i]=true;
                    if(j==words2.length-1 && anagram[i]==false){
                        return false;
                    }
                }
            }
            return !Arrays.asList(anagram).contains(false);

        }
    }
}
