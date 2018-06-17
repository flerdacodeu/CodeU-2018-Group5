import java.util.ArrayList;

/**
 * Modified implementation of a trie node such that it contains the position (row and column index)
 * of a character in the grid
 *
 * ASSUMPTIONS : ASCII character encoding, lower case letters only
 */

public class TrieNode {
  // the number of letters in the alphabet
  private final static int LETTERS = 25;

  private char value;
  private TrieNode parent;
  // the row index of the node in the grid
  private int row;
  // the column index of the node in the grid
  private int column;
  /* nodes with the same value but different positions are treated as different nodes;
   * thus, the child with the given value consists of an array of nodes with identical values
   * but different positions
   */
  private ArrayList<TrieNode>[] children;
  boolean isWord;

  // used to create the root only
  public TrieNode(){
    children = new ArrayList[LETTERS];
  }

  public TrieNode (char value) {
    assert value >= 'a' && value <= 'z';
    this.value = value;
    children = new ArrayList[LETTERS];
  }

  public TrieNode (char value, int row, int column) {
    assert value >= 'a' && value <= 'z' && row >= 0 && column >= 0;
    this.value = value;
    this.row = row;
    this.column = column;
    children = new ArrayList[LETTERS];
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
    ArrayList<TrieNode> child = children[getIndexOfCharacter(letter)];
    if (child == null) {
      children[getIndexOfCharacter(letter)] = new ArrayList<>();
      children[getIndexOfCharacter(letter)].add(new TrieNode(letter));
    }

    if (word.length() > 1) {
      children[getIndexOfCharacter(letter)].get(0).insertWord(word.substring(1));
    } else {
      children[getIndexOfCharacter(letter)].get(0).isWord = true;
    }
  }

  /**
   * Checks if the given string is a word in the trie.
   *
   * @param word the word whose existence in trie is checked
   * @return true if the trie contains the word, false otherwise
   * @throws IllegalArgumentException if the element to be searched for is null
   */
  public boolean containsWord (String word) {
    if (word == null) {
      throw new IllegalArgumentException("null input");
    }

    if (word.length() > 0) {
      char letter = word.charAt(0);
      ArrayList<TrieNode> child = children[getIndexOfCharacter(letter)];
      if (child == null || !(child.get(0).value == letter)) {
        return false;
      } else {
        return child.get(0).containsWord(word.substring(1));
      }
    }

    return isWord;
  }

  /**
   * Checks if the given string is a prefix in the trie.
   *
   * @param prefix the prefix whose existence in trie is checked
   * @return true if the trie contains the prefix, false otherwise
   * @throws IllegalArgumentException if the element to be searched for is null
   */
  public boolean containsPrefix (String prefix) {
    if (prefix == null) {
      throw new IllegalArgumentException("null input");
    }

    if (prefix.length() > 0) {
      char letter = prefix.charAt(0);
      ArrayList<TrieNode> child = children[getIndexOfCharacter(letter)];
      if (child == null || !(child.get(0).value == letter)) {
        return false;
      } else {
        return child.get(0).containsPrefix(prefix.substring(1));
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
    ArrayList<TrieNode> childrenWithSameValue = children[getIndexOfCharacter(node.getValue())];
    if (childrenWithSameValue == null) {
      childrenWithSameValue = new ArrayList<>();
      childrenWithSameValue.add(node);
      children[getIndexOfCharacter(node.getValue())] = childrenWithSameValue;
    } else if (!node.exists(childrenWithSameValue)) {
      childrenWithSameValue.add(node);
    }
  }

  /**
   * Checks if a trie node with identical position (same row and column index) already exists
   * in the list of children with the same value
   *
   * @param childrenWithSameValue the array of child nodes that have the same value
   * @return true if a node with identical position exists, false otherwise
   */
  private boolean exists (ArrayList<TrieNode> childrenWithSameValue) {
    assert childrenWithSameValue != null;
    
    for (TrieNode n : childrenWithSameValue) {
      if (n.getRow() == getRow() && n.getColumn() == getColumn()) {
        return true;
      }
    }
    return false;
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

  /**
   * Returns the row index of the matrix the node is stored into
   */
  public int getRow() {
    return row;
  }

  /**
   * Returns the column index of the matrix the node is stored into
   */
  public int getColumn() {
    return column;
  }
}
