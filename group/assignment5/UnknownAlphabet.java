import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.LinkedHashSet;

/**
 * Assumptions : 
 *  - the language of the dictionary can contain any character (of the native char data type)
 *  - upper/lower case letters are treated differently
 *  - standard lexicographic ordering
 */
public class UnknownAlphabet {

  /**
   * Computes an alphabet that is consistent with the given dictionary.
   *
   * Time complexity: O(m*n)
   *
   * @param dictionary list of words in lexicographic order
   * @return one possible alphabet consistent with the dictionary or null if the dictionary is inconsistent
   */
  public List extractAlphabet(List<String> dictionary) {
    if (dictionary == null) {
      throw new IllegalArgumentException();
    }

    if (dictionary.isEmpty()) {
      return new ArrayList();
    }

    Graph graph = listToGraph(dictionary);
    // topological sort returns null when the dictionary is inconsistent
    return graph.topologicalSort();
  }

  /**
   * Finds all the possible alphabets that are consistent with the given dictionary of words.
   *
   * @param dictionary list of words in lexicographic order
   * @return all possible alphabets consistent with the given dictionary
   * @throws IllegalArgumentException if the dictionary is null
   */
  public List<List<Character>> extractAllAlphabets(List<String> dictionary) {
    if (dictionary == null) {
      throw new IllegalArgumentException();
    }

    if (dictionary.isEmpty()) {
      return new ArrayList();
    }

    Graph graph = listToGraph(dictionary);
    return graph.topologicalSorting();
  }

  /**
   * Identifies inconsistent dictionaries.
   *
   * The algorithm works by creating a graph that has all the letters of the dictionary words as vertices and
   * the lexicographic order requirements as edges. An inconsistent dictionary is one that has cycles,
   * since the orders of letters involved in the cycle are dependant on each other.
   *
   * The vertex where the cycle is detected and its closest ancestor on the recursion stack
   * represent a reason of the inconsistency : the value of the vertex must be before the value of
   * the ancestor but at the same time the opposite scenario is true
   *
   * @param dictionary the dictionary to be inspected
   * @return true if the dictionary is consistent, false otherwise
   * @throws IllegalArgumentException if the dictionary is null
   */
  public boolean isConsistentDictionary(List<String> dictionary) {
    if (dictionary == null) {
      throw new IllegalArgumentException();
    }
    if (dictionary.isEmpty()) {
      return true;
    }
    Graph graph = listToGraph(dictionary);
    Graph.Result result = graph.detectCycle();

    if (result.hasCycle()) {
      if (!result.getPair().isEmpty()) {
        System.out.println("Letter "+result.getPair().get(0).getValue() + " must be before letter " + result.getPair().get(1).getValue() + " and vice versa");
      }
    }
    //the dictionary is consistent if there is no cycle in the graph
    return !result.hasCycle();
  }

  /**
   * Makes the given dictionary consistent.
   *
   * The algorithm works by detecting and removing one inconsistency(edge that creates cycle) at a
   * time until the graph becomes acyclic. The words in the returned dictionary depend on what edge
   * is removed first.
   *
   * @param dictionary the dictionary of interest
   * @return if the original dictionary is inconsistent, a maximal subset of the original dictionary
   * that is consistent, otherwise the original dictionary
   */
  public List<String> makeDictionaryConsistent(List<String> dictionary) {
    if (dictionary == null) {
      throw new IllegalArgumentException();
    }

    if (dictionary.isEmpty()) {
      return dictionary;
    }

    Graph alphabetGraph = listToGraph(dictionary);
    Graph.Result detectCycleResult = alphabetGraph.detectCycle();
    while (detectCycleResult.hasCycle()) {
      List<Vertex> cyclePair = detectCycleResult.getPair();
      dictionary = removeInconsistency(cyclePair, dictionary, alphabetGraph);
      detectCycleResult = alphabetGraph.detectCycle();
    }

    return dictionary;
  }

  /**
   * Removes the minimum number of words from an inconsistent dictionary to make it consistent.
   *
   * Time complexity: O(n*m), where n is the number of words in the dictionary and m is the average length of a word.
   *
   * @param cyclePair the vertices that form the edge that needs to be removed
   * @param dictionary the inconsistent dictionary of interest
   * @param graph the graph created from the given dictionary
   * @return a consistent dictionary that is a maximal subset of the inconsistent dictionary
   */
  private List<String> removeInconsistency(List<Vertex> cyclePair, List<String> dictionary, Graph graph) {
    // the vertices that form the edge that creates the cycle
    Vertex firstVertex = cyclePair.get(0);
    Vertex secondVertex = cyclePair.get(1);
    // dictionary without words imposing rule: firstVertex before secondVertex
    List<String> firstNewDictionary = filterDictionaryWords(firstVertex, secondVertex, dictionary);
    // dictionary without words imposing rule: secondVertex before firstVertex
    List<String> secondNewDictionary = filterDictionaryWords(secondVertex, firstVertex, dictionary);

    boolean moreWordsInFirstDictionary =  firstNewDictionary.size() > secondNewDictionary.size();
    List<String> newDictionary = moreWordsInFirstDictionary? firstNewDictionary : secondNewDictionary;

    if(moreWordsInFirstDictionary) {
      // the constraint where the firstVertex is before secondVertex should be removed
      graph.getVertex(firstVertex.getValue()).removeNeighbour(secondVertex);
    } else {
      // the constraint where the secondVertex is before firstVertex should be removed
      graph.getVertex(secondVertex.getValue()).removeNeighbour(firstVertex);
    }

    return newDictionary;
  }

  /**
   * Creates a new dictionary by removing the words that impose the lexicographic order of a given
   * letter before another given letter
   *
   * Complexity: O(m*n^2), where n is the number of words in the dictionary, m is the average length of a word
   * (n-1) * m + (n-2) * m + ... + 2 * m + m = m * ((n-1) + (n-2) + ... + 2 + 1) = m * (n^2)
   *
   * @param firstVertex vertex corresponding to first letter
   * @param secondVertex vertex corresponding to second letter
   * @param dictionary the dictionary of interest
   * @return the given dictionary without the words that impose the rule where first letter is before
   * second letter
   */
  private List<String> filterDictionaryWords(Vertex firstVertex, Vertex secondVertex, List<String> dictionary) {
    Set<String> newDictionary = new LinkedHashSet<>();

    for (int i = 0; i < dictionary.size(); i++) {
      boolean foundFirstBeforeSecond = false;
      String firstWord = dictionary.get(i);

      for (int j = i + 1; j < dictionary.size(); j++) {
        int firstWordIndex = 0;
        int secondWordIndex = 0;
        String secondWord = dictionary.get(j);

        while (firstWordIndex < firstWord.length() && secondWordIndex < secondWord.length() 
               && firstWord.charAt(firstWordIndex) == secondWord.charAt(secondWordIndex)) {
          firstWordIndex++;
          secondWordIndex++;
        }

        if (firstWordIndex < firstWord.length() && secondWordIndex < secondWord.length()) {
          if ((firstWord.charAt(firstWordIndex) == firstVertex.getValue() 
               && secondWord.charAt(secondWordIndex) == secondVertex.getValue())) {
            foundFirstBeforeSecond = true;
            break;
          }
        }
      }

      if (!foundFirstBeforeSecond) {
        newDictionary.add(firstWord);
      }
    }
    return new ArrayList<>(newDictionary);
  }
  
  /**
   * Creates a graph that has all the letters of the dictionary words as vertices and
   * the lexicographic order requirements as edges.
   *
   * Time complexity: 
   *    addAllCharactersAsVertices: O(n*m)
   *    extractLexicographicOrder: O(n*m)
   *    => O(n*m), where n is the number of words in the dictionary and m is the average length of a word.
   *
   * @param dictionary the dictionary that contains the words which impose the lexicographic order.
   * @return the graph that was created from the dictionary
   * @throws IllegalArgumentException if the dictionary of words is null
   */
  public Graph listToGraph(List<String> dictionary) {
    if (dictionary == null) {
      throw new IllegalArgumentException();
    }
    Graph graph = new Graph();
    addAllCharactersAsVertices(graph, dictionary);
    extractLexicographicOrder(graph, dictionary);
    return graph;
  }
  
  /**
   * Adds all distinct characters of the dictionary language to the graph.
   *
   * Time complexity: O(n*m), where n is the number of words in the dictionary and m is the average length of a word.
   *
   * @param graph the graph where the the distinct characters of the words in the dictionary are added as vertices
   * @param dictionary the list of words
   */
  private void addAllCharactersAsVertices(Graph graph, List<String> dictionary) {
    assert graph != null && dictionary != null;
    for (String word : dictionary) {
      for (char character : word.toCharArray()) {
        Vertex current = new Vertex(character);
        if (!graph.getVertices().contains(current)) {
          graph.addVertex(current);
        }
      }
    }
  }

  /**
   * Extracts the lexicographic order of the characters in the dictionary words.
   *
   * The order constraints are represented as directed edges in the given graph.
   *
   * Time complexity: O(n*m), where n is the number of words in the dictionary and m is the average length of a word.
   *
   * @param graph the graph where the edges are added
   * @param dictionary the list of words
   */
  private void extractLexicographicOrder(Graph graph, List<String> dictionary) {
    for (int i = 0; i < dictionary.size() - 1; i++) {
      String currentWord = dictionary.get(i);
      String nextWord = dictionary.get(i + 1);

      // if two consecutive words have different letters then an edge from the first letter of
      // the first word to the first letter of the second word is added
      if (currentWord.charAt(0) != nextWord.charAt(0)) {
        graph.addDirectedEdge(new Vertex(currentWord.charAt(0)), new Vertex(nextWord.charAt(0)));
      } else {
        // if two consecutive words have differing first letters, then the algorithm iterates through
        // the words until different letters are found on the same position and then creates an edge
        int size = currentWord.length() <= nextWord.length() ? currentWord.length() : nextWord.length();
        int index = 1;
        while (index < size && currentWord.charAt(index) == nextWord.charAt(index)) {
          index++;
        }
        if (index == size) {
          continue;
        } else {
          graph.addDirectedEdge(new Vertex(currentWord.charAt(index)), new Vertex(nextWord.charAt(index)));
        }
      }
    }
  }
}
