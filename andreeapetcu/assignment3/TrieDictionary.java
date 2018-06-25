import java.util.List;

/**
 * The trie implementation of the Dictionary interface
 * O(n) complexity for checking the existence of a word/ inserting a new word,
 * where n is the length of the word
 */
public class TrieDictionary implements Dictionary {

  private TrieNode root = new TrieNode();

  public TrieDictionary(List<String> words) {
    assert words != null;
    for (String word : words) {
      root.insertWord(word);
    }
  }

  /**
   * Checks if the given word is a valid word
   *
   * @param input the word whose validity is checked
   * @return true if the word exists in the dictionary, false otherwise
   * @throws IllegalArgumentException if the input word is null
   */
  public boolean isWord(String input) {
    if (input == null) {
      throw new IllegalArgumentException("null input");
    }

    return root.containsWord(input);
  }

  /**
   * Checks if the given prefix is a valid prefix
   *
   * @param input the prefix whose validity is checked
   * @return true if the prefix exists in the dictionary, false otherwise
   * @throws IllegalArgumentException if the input prefix is null
   */
  public boolean isPrefix (String input) {
    if (input == null) {
      throw new IllegalArgumentException("null input");
    }

    return root.containsPrefix(input);
  }
}
