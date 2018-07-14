import java.util.ArrayList;

public class Vertex {
    private char value;
    private int indegree;
    private ArrayList<Vertex> edges;

    public Vertex (char value) {
        this.value = value;
        this.indegree = 0;
        this.edges = new ArrayList<Vertex>();
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public int getIndegree() {
        return indegree;
    }

    public void setIndegree(int indegree) {
        this.indegree = indegree;
    }

    public ArrayList<Vertex> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<Vertex> edges) {
        this.edges = edges;
    }

    public void addEdge (Vertex neighbour) {
        edges.add(neighbour);
    }
}
