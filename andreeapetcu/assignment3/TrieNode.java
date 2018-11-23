/**
 * ASSUMPTIONS : ASCII character encoding, lower case letters only
 */

public class TrieNode {
  // the number of letters in the alphabet
  private final static int LETTERS = 25;

  private char value;
  private TrieNode parent;
  private TrieNode[] children;
  private boolean isWord;

  // used to create the root only
  public TrieNode(){
    children = new TrieNode[LETTERS];
  }

  public TrieNode (char value) {
    assert value >= 'a' && value <= 'z';
    this.value = value;
    children = new TrieNode[LETTERS];
  }

  /**
   * Adds a new word into the trie.
   *
   * @param word the word to be added
   * @throws IllegalArgumentException if the word to be inserted is null
   */
  public void insertWord (String word) {
    if (word == null) {
      throw new IllegalArgumentException("null input");
    }

    if (word.isEmpty()) {
      return;
    }

    char letter = word.charAt(0);
    TrieNode child = children[getIndexOfCharacter(letter)];
    if (child == null) {
      children[getIndexOfCharacter(letter)] = new TrieNode(letter);
    }

    if (word.length() > 1) {
      children[getIndexOfCharacter(letter)].insertWord(word.substring(1));
    } else {
      children[getIndexOfCharacter(letter)].isWord = true;
    }
  }

  /**
   * Checks if the given string is a word in the trie.
   *
   * @param word the word whose existence in trie is checked
   * @return true if the trie contains the word, false otherwise
   * @throws IllegalArgumentException id the element to be searched for is null
   */
  public boolean containsWord (String word) {
    if (word == null) {
      throw new IllegalArgumentException("null input");
    }

    if (word.length() > 0) {
      char letter = word.charAt(0);
      TrieNode child = children[getIndexOfCharacter(letter)];
      if (child == null || !(child.value == letter)) {
        return false;
      } else {
        return child.containsWord(word.substring(1));
      }
    }

    return isWord;
  }

  /**
   * Checks if the given string is a prefix in the trie.
   *
   * @param prefix the prefix whose existence in trie is checked
   * @return true if the trie contains the prefix, false otherwise
   * @throws IllegalArgumentException if the input prefix is null
   */
  public boolean containsPrefix (String prefix) {
    if (prefix == null) {
      throw new IllegalArgumentException("null input");
    }

    if (prefix.length() > 0) {
      char letter = prefix.charAt(0);
      TrieNode child = children[getIndexOfCharacter(letter)];
      if (child == null || !(child.value == letter)) {
          return false;
      } else {
        return child.containsPrefix(prefix.substring(1));
      }
    }

    return true;
  }

  /**
   * Adds a child node to the current trie node.
   *
   * @param node the child node to be added
   * @throws IllegalArgumentException if the node to be added is null
   */
  public void addChild (TrieNode node) {
    if (node == null) {
      throw new IllegalArgumentException("Null node");
    }
    node.parent = this;
    if (children[getIndexOfCharacter(node.getValue())] == null) {
      children[getIndexOfCharacter(node.getValue())] = node;
    }
  }

  /**
   * Maps ASCII codes into indexes of arrays.
   *
   * @param c the character of interest
   * @return the index of the given character in the array
   */
  private int getIndexOfCharacter(char c) {
    return c - 97;
  }

  /**
   * Marks this node as the last node in a word
   */
  public void setIsWord() {
    isWord = true;
  }

  /**
   * Returns the value stored in the trie node
   */
  public char getValue() {
    return value;
  }

  /**
   * Returns the parent of this node
   */
  public TrieNode getParent() {
    return parent;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || this.getClass() != obj.getClass()) {
      return false;
    }

    TrieNode node = (TrieNode) obj;
    return this.value == node.value ;
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + value;
    return result;
  }
}
