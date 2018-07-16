import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GraphTest {

  @Test
  public void testTopologicalSort_directedAcyclicGraph() {
    Graph graph = new Graph();
    graph.addDirectedEdge(new Vertex('a'), new Vertex('b'));
    graph.addDirectedEdge(new Vertex('a'), new Vertex('c'));
    graph.addDirectedEdge(new Vertex('b'), new Vertex('e'));
    graph.addDirectedEdge(new Vertex('c'), new Vertex('d'));
    graph.addDirectedEdge(new Vertex('d'), new Vertex('e'));
    List<Character> expectedOrder = Arrays.asList('a', 'b', 'c', 'd', 'e');
    assertEquals(expectedOrder, graph.topologicalSort());
  }

  @Test
  public void testTopologicalSorting_directedAcyclicGraph1() {
    Graph graph = new Graph();
    graph.addDirectedEdge(new Vertex('a'), new Vertex('b'));
    graph.addDirectedEdge(new Vertex('a'), new Vertex('c'));
    graph.addDirectedEdge(new Vertex('b'), new Vertex('e'));
    graph.addDirectedEdge(new Vertex('c'), new Vertex('d'));
    graph.addDirectedEdge(new Vertex('d'), new Vertex('e'));

    List<List<Character>> expectedTopologicalOrders = Arrays.asList(Arrays.asList('a', 'b', 'c', 'd', 'e'), Arrays.asList('a', 'c', 'b', 'd', 'e'), Arrays.asList('a', 'c', 'd', 'b', 'e'));
    List<List<Character>> topologicalOrders = graph.topologicalSorting();
    assertEquals(expectedTopologicalOrders, topologicalOrders);
  }

  @Test
  public void testTopologicalSorting_directedAcyclicGraph2() {
    Graph graph = new Graph();
    graph.addDirectedEdge(new Vertex('a'), new Vertex('b'));
    graph.addDirectedEdge(new Vertex('a'), new Vertex('c'));
    graph.addDirectedEdge(new Vertex('b'), new Vertex('c'));
    graph.addDirectedEdge(new Vertex('d'), new Vertex('e'));

    List<List<Character>> expectedTopologicalOrders = Arrays.asList(Arrays.asList('a', 'b', 'c', 'd', 'e'), Arrays.asList('a', 'b', 'd', 'c', 'e'), Arrays.asList('a', 'b', 'd', 'e', 'c'),
        Arrays.asList('a', 'd', 'b', 'c', 'e'), Arrays.asList('a', 'd', 'b', 'e', 'c'), Arrays.asList('a', 'd', 'e', 'b', 'c'), Arrays.asList('d', 'a', 'b', 'c', 'e'),
        Arrays.asList('d', 'a', 'b', 'e', 'c'), Arrays.asList('d', 'a', 'e', 'b', 'c'), Arrays.asList('d', 'e', 'a', 'b', 'c'));
    List<List<Character>> topologicalOrders = graph.topologicalSorting();
    assertEquals(expectedTopologicalOrders, topologicalOrders);

  }

  @Test
  public void testTopologicalSorting_directedAcyclicGraph3() {
    Graph graph = new Graph();
    graph.addDirectedEdge(new Vertex('a'), new Vertex('b'));
    graph.addDirectedEdge(new Vertex('a'), new Vertex('d'));
    graph.addDirectedEdge(new Vertex('b'), new Vertex('c'));
    graph.addDirectedEdge(new Vertex('d'), new Vertex('e'));
    graph.addDirectedEdge(new Vertex('c'), new Vertex('f'));
    graph.addDirectedEdge(new Vertex('e'), new Vertex('f'));

    List<List<Character>> expectedTopologicalOrders = Arrays.asList(Arrays.asList('a', 'b', 'd', 'c', 'e', 'f'),
        Arrays.asList('a', 'b', 'd', 'e', 'c', 'f'), Arrays.asList('a', 'b', 'c', 'd', 'e', 'f'), Arrays.asList('a', 'd', 'b', 'c', 'e', 'f'),
        Arrays.asList('a', 'd', 'b', 'e', 'c', 'f'), Arrays.asList('a', 'd', 'e', 'b', 'c', 'f'));
    List<List<Character>> topologicalOrders = graph.topologicalSorting();
    assertEquals(expectedTopologicalOrders, topologicalOrders);
  }

  @Test
  public void testDetectCycle_graphWithCycle() {
    Graph graph = new Graph();
    graph.addDirectedEdge(new Vertex('a'), new Vertex('b'));
    graph.addDirectedEdge(new Vertex('b'), new Vertex('c'));
    graph.addDirectedEdge(new Vertex('c'), new Vertex('d'));
    graph.addDirectedEdge(new Vertex('d'), new Vertex('b'));
    Graph.Result result = graph.detectCycle();
    assertEquals(true, result.hasCycle());
    assertEquals(Arrays.asList(new Vertex('b'), new Vertex('d')), result.getPair());
  }

  @Test
  public void detectCycle_graphWithoutCycle() {
    Graph graph = new Graph();
    graph.addDirectedEdge(new Vertex('a'), new Vertex('b'));
    graph.addDirectedEdge(new Vertex('b'), new Vertex('c'));
    graph.addDirectedEdge(new Vertex('c'), new Vertex('d'));
    Graph.Result result = graph.detectCycle();
    assertEquals(false, result.hasCycle());
    assertTrue(result.getPair().isEmpty());
  }
}
