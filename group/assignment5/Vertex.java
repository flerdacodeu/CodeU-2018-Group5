/**
 * Assumption : the value of the vertex is a character
 */

import java.util.ArrayList;
import java.util.List;

public class Vertex {
  private char value;
  private List<Vertex> neighbours;
  // inbound is the number of incoming edges
  private int inbound;

  public Vertex(char data) {
    value = data;
    neighbours = new ArrayList<>();
    inbound = 0;
  }

  public char getValue() {
    return value;
  }

  public List<Vertex> getNeighbours() {
    return neighbours;
  }

  /**
   * Adds an adjacent vertex to the current vertex.
   *
   * @param vertex the vertex to be added
   * @throws IllegalArgumentException if the vertex to be added is null
   */
  public void addNeighbour(Vertex vertex) {
    if (vertex == null) {
      throw new IllegalArgumentException();
    }
    if (!neighbours.contains(vertex)) {
      neighbours.add(vertex);
    }
  }

  public int getInbound() {
    return inbound;
  }

  public void incrementInbound() {
    inbound++;
  }

  public void decrementInbound() {
    inbound--;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || this.getClass() != obj.getClass()) {
      return false;
    }
    Vertex vertex = (Vertex) obj;
    return this.getValue() == vertex.getValue();
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}
