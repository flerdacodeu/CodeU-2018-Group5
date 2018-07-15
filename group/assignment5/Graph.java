/**
 * Assumption : the graph has vertices whose value is a character
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Graph {
  private List<Vertex> vertices;

  public Graph() {
    vertices = new ArrayList<>();
  }

  /**
   * Returns all vertices in the current graph.
   *
   * @return the list of the vertices in the current graph
   */
  public List<Vertex> getVertices() {
    return vertices;
  }

  /**
   * Adds a vertex to the graph if it does not already exist in the graph.
   *
   * @param vertex the vertex to be added.
   * @return true, if the edge was successfully added, false if it already existed
   * @throws IllegalArgumentException if the vertex to be added is null.
   */
  public boolean addVertex(Vertex vertex) {
    if (vertex == null) {
      throw new IllegalArgumentException();
    }
    if (!vertices.contains(vertex)) {
      vertices.add(vertex);
      return true;
    }
    return false;
  }

  /**
   * Returns the vertex that has the given character as value.
   *
   * @param value the value of interest
   * @return the vertex that has the given value, or null if the vertex is inexistent
   */
  public Vertex getVertex(char value) {
    for (Vertex v : vertices) {
      if (v.getValue() == value) {
        return v;
      }
    }
    return null;
  }

  /**
   * Topologically sorts the vertices using Kahn's non-recursive algorithm.
   *
   * @return one of the possible topological orders of the vertices.
   */
  public List<Character> topologicalSort() {
    List<Character> order = new ArrayList<>();
    LinkedList<Vertex> processNext = new LinkedList<>();
    for (Vertex vertex : getVertices()) {
      if (vertex.getInbound() == 0) {
        processNext.add(vertex);
      }
    }

    while (!processNext.isEmpty()) {
      Vertex currentVertex = processNext.poll();
      order.add(currentVertex.getValue());
      for (Vertex neighbour : currentVertex.getNeighbours()) {
        neighbour.decrementInbound();
        if (neighbour.getInbound() == 0) {
          processNext.add(neighbour);
        }
      }
    }
    
    // no vertex inbound should be null at this point if the graph is acyclic
    for (Vertex vertex : vertices) {
      if (vertex.getInbound() != 0) {
        return null;
      }
    }
    return order;
  }

  /**
   * Topologically sorts the vertices.
   *
   * @return all possible topological orders of the vertices.
   */
  public List<List<Character>> topologicalSorting() {
    List<List<Character>> allOrders = new ArrayList<>();
    LinkedList<Character> currentOrder = new LinkedList<>();
    List<Vertex> visited = new ArrayList<>();
    topologicalSortingHelper(allOrders, currentOrder, visited);
    return allOrders;
  }

  /**
   * Topologically sorts the vertices using recursion.
   *
   * @param allOrders all possible topological orders of the vertices.
   * @param currentOrder the topological order that the algorithm currently builds
   * @param visited visited vertices
   */
  private void topologicalSortingHelper(List<List<Character>> allOrders, LinkedList<Character> currentOrder, List<Vertex> visited) {
    boolean createdFullAlphabet = true;
    for (Vertex current : getVertices()) {
      if (!visited.contains(current) && current.getInbound() == 0) {
        List<Vertex> neighbours = current.getNeighbours();
        for (Vertex neighbour : neighbours) {
          neighbour.decrementInbound();
        }
        visited.add(current);
        currentOrder.add(current.getValue());

        topologicalSortingHelper(allOrders, currentOrder, visited);

        visited.remove(current);
        currentOrder.remove((Character) current.getValue());
        for (Vertex neighbour : current.getNeighbours()) {
          neighbour.incrementInbound();
        }
        createdFullAlphabet = false;
      }
    }

    if (createdFullAlphabet) {
      allOrders.add((List<Character>) currentOrder.clone());
    }
  }

  /**
   * Adds an edge from the first given vertex to the second given vertex.
   *
   * @param vertex1 first vertex
   * @param vertex2 second vertex
   * @throws IllegalArgumentException if the given vertices are null
   */
  public void addDirectedEdge(Vertex vertex1, Vertex vertex2) {
    if (vertex1 == null || vertex2 == null) {
      throw new IllegalArgumentException();
    }
    
    boolean addedVertex1 = addVertex(vertex1);
    boolean addedVertex2 = addVertex(vertex2);
    if (!addedVertex1) {
      vertex1 = getVertex(vertex1.getValue());
    }
    if (!addedVertex2) {
      vertex2 = getVertex(vertex2.getValue());
    }
    
    if (!vertex1.getNeighbours().contains(vertex2)) {
      getVertex(vertex2.getValue()).incrementInbound();
      getVertex(vertex1.getValue()).addNeighbour(getVertex(vertex2.getValue()));
    }
  }

  /**
   * Detects cycles in the current graph.
   *
   * The algorithm finds one cycle only even if the graph has more cycles.
   *
   * @return a Result wrapper class that indicates the existence of cycles and
   * the vertex (together with its closest ancestor) where the cycle was detected
   */
  public Result detectCycle() {
    Result result = new Result();
    Stack<Vertex> stack = new Stack<>();
    ArrayList<Vertex> visited = new ArrayList<>();
    for (Vertex vertex : vertices) {
      detectCycleUtil(startVertex, stack, visited, result);
    }
    return result;
  }

  /**
   * Helper method that finds the cycle in the graph using a modified version of depth first search.
   *
   * @param currentVertex the starting vertex of the traversal
   * @param ancestors the recursion stack of vertices
   * @param visited the visited vertices
   * @param result a Result wrapper class
   */
  private void detectCycleUtil(Vertex currentVertex, Stack<Vertex> ancestors, ArrayList<Vertex> visited, Result result) {
    assert currentVertex != null && ancestors != null && visited != null && result != null;
    if (!visited.contains(currentVertex)) {
      ancestors.push(currentVertex);
      visited.add(currentVertex);
      for (Vertex neighbour : currentVertex.getNeighbours()) {
        detectCycleUtil(neighbour, ancestors, visited, result);
      }
      ancestors.pop();
    } else {
      if (ancestors.contains(currentVertex)) {
        result.setHasCycle(true);
        result.setPair(currentVertex, ancestors.peek());
      }
    }
  }

  /**
   * Wrapper class that contains the result of the algorithm that detects cycles in the graph.
   * The class has a filed that indicates if a cycle was found and a pair of vertices : the vertex
   * where the cycle was detected and its closest ancestor.
   */
  class Result {
    private boolean hasCycle;
    private List<Vertex> pair;

    Result() {
      hasCycle = false;
      pair = new ArrayList<>();
    }

    public void setHasCycle(boolean hasCycle) {
      this.hasCycle = hasCycle;
    }

    public boolean hasCycle() {
      return hasCycle;
    }

    public void setPair(Vertex vertex1, Vertex vertex2) {
      pair.add(vertex1);
      pair.add(vertex2);
    }

    public List<Vertex> getPair() {
      return pair;
    }
  }
}
