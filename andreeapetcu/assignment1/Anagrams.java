/**
 * Algorithm handles both case sensitive and case insensitive anagrams
 * Algorithm handles anagrams of sentences
 *
 * ASSUMPTIONS :
 *     ASCII characters encoding for words/sentences
 *     For sentence anagrams punctuation marks are not considered separate words
 */

public class Anagrams {

    private final String splitExpression = "\\s*[^a-zA-Z0-9]+\\s*";

    /**
     * Checks whether two words are anagrams of each other
     *
     * @param input1 first word
     * @param input2 second word
     * @param caseSensitive flag for case sensitive or case insensitive anagrams
     *
     */
    public boolean wordAnagrams(String input1, String input2, boolean caseSensitive){
        //contains count of each letter in the first word
        // array size equals the number of ASCII characters
        int[] lettersCount = new int[128];

        if (input1.length() != input2.length())
            return false;

        char currentLetter;

        //iterate over the first word and increase count of each letter in the word
        for (int i=0; i< input1.length(); i++){
            if (caseSensitive)
                currentLetter = input1.charAt(i);
            else
                currentLetter = Character.toLowerCase(input1.charAt(i));

            if (lettersCount[currentLetter] == 0)
                lettersCount[currentLetter] = 1;
            else
                lettersCount[currentLetter]++;
        }

        //iterate over the second word and decrease count of the letters
        for (int i = 0; i < input2.length(); i++){
            if (caseSensitive)
                currentLetter = input2.charAt(i);
            else
                currentLetter = Character.toLowerCase(input2.charAt(i));

            lettersCount[currentLetter]--;
            if (lettersCount[currentLetter] < 0)
                return false;
        }

        //if count of letters is not zero
        //then the words are not anagrams
        for (int i = 0; i < lettersCount.length; i++){
            if (lettersCount[i] > 0)
                return false;
        }

        return true;
    }


    /**
     * Checks if two words/sentences are anagrams of each other
     *
     * @param input1 first word/sentence
     * @param input2 second word/sentence
     * @param caseSensitive flag for case sensitive or case insensitive anagrams
     */
    public boolean sentenceAnagrams(String input1, String input2, boolean caseSensitive){
        String[] splitSentence1 = input1.split(splitExpression);
        String[] splitSentence2 = input2.split(splitExpression);

        if (splitSentence1.length != splitSentence2.length)
            return false;

        //words from sentence 1 that have a pair in sentence 2
        boolean[] checkedWordsSentence1 = new boolean[splitSentence1.length];

        //words from sentence 2 that have a pair in sentence 1
        boolean[] checkedWordsSentence2 = new boolean[splitSentence2.length];

        for (int i = 0; i< splitSentence1.length; i++){
            for (int j = 0; j < splitSentence2.length; j++){
                if (wordAnagrams(splitSentence2[j], splitSentence1[i], caseSensitive) && !checkedWordsSentence1[i] && !checkedWordsSentence2[j]) {
                    checkedWordsSentence1[i] = true;
                    checkedWordsSentence2[j] = true;
                }
            }
        }

        for (int i =0; i< splitSentence1.length; i++)
            if (!checkedWordsSentence1[i] || !checkedWordsSentence2[i])
                return false;

        return true;
    }
}
