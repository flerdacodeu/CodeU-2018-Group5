import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A class representing a directed graph
 * each vertex is a character
 *
 */
public class Graph {

	public Map<Character, List<Character>> content;
	
	public Graph() {
		this.content = new LinkedHashMap<Character, List<Character>>(); 
	}
	
	/**
	 * add a vertex to the graph if not present yet
	 * @param c - the vertex
	 */
	public void addVertex(Character c) {
		if (!this.content.containsKey(c)) {
			this.content.put(c, new ArrayList<Character>());
		}
	}
	
	/**
	 * add an edge to the graph from c1 to c2
	 * @param c1 - the start vertex
	 * @param c2 - the target vertex
	 */
	public void updateEdge(Character c1, Character c2){
		System.out.println(c1+ " gets "+ c2);
		if (this.content.containsKey(c1)) {
			this.content.get(c1).add(c2);
		}
		else {
			throw new IllegalArgumentException("the character must have already been added to the vertices list");
		}
	}
	
	/**
	 * @return  the number of vertices in the graph
	 */
	public int size(){
		return this.content.size();
	}
	
	/**
	 * @return the content of the graph
	 */
	public Map<Character, List<Character>> getContent() {
		return this.content;
	}
	
	/**
	 * display the content of the graph on stdout
	 */
	public void displayContent() {
		System.out.println("================================================== NEW GRAPH ==================================================");
		
		for (char c : this.content.keySet()) {
			System.out.print(c+" --> ");
			for (char c2 : this.content.get(c)) {
				System.out.print(c2+" --> ");
			}
			System.out.println();
		}
	}
	
}
