import java.util.List;

/**
 * The dictionary is implemented as a trie
 * O(n) complexity for checking the existence of a word/ inserting a new word,
 * where n is the length of the word
 */

public class Dictionary {

  private TrieNode root = new TrieNode();

  public Dictionary(List<String> words) {
    for (String word : words) {
      root.insertWord(word);
    }
  }

  /**
   * Checks if the given word is a valid word
   *
   * @param input the word whose validity is checked
   * @return true if the word exists in the dictionary, false otherwise
   */
  public boolean isWord(String input) {
    return root.containsWord(input);
  }

  /**
   * Checks if the given prefix is a valid prefix
   *
   * @param input the prefix whose validity is checked
   * @return true if the prefix exists in the dictionary, false otherwise
   */
  public boolean isPrefix (String input) {
    return root.containsPrefix(input);
  }
}
