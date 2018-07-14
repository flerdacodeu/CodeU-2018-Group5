import java.util.ArrayList;
import java.util.List;

public class UnknownAlphabet {

  /**
   * Computes an alphabet that is consistent with the given dictionary.
   *
   * @param dictionary list of words in lexicographic order
   * @return one possible alphabet consistent with the dictionary
   */
  public List extractAlphabet(List<String> dictionary) {
    if (dictionary == null) {
      throw new IllegalArgumentException();
    }

    if (dictionary.isEmpty()) {
      return new ArrayList();
    }

    Graph graph = listToGraph(dictionary);
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
   * Creates a graph that has all the letters of the dictionary words as vertices and
   * the lexicographic order requirements as edges.
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
    for (String word : dictionary) {
      for (char character : word.toCharArray()) {
        Vertex current = new Vertex(character);
        if (!graph.getVertices().contains(current)) {
          graph.addVertex(current);
        }
      }
    }

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
    return graph;
  }
}
