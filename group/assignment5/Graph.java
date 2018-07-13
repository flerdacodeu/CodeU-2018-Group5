package task1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
/*
 * A class to store a graph where we have a map that has T type as keys and List as all predecessors for that key
 */
public class Graph<T> {

	private Map<T, List<T>> map = new HashMap<T, List<T>>();
	private int numberOfVertices = 0;
	
	/*Method for adding NEW vertices to the map
	 * 
	 * @param vertex a vertex that we want to add to the map
	 */
	public void addVertex(T vertex) {
		if(!map.containsKey(vertex)) {
			map.put(vertex, new LinkedList<T>());
			numberOfVertices++;
		}
	}
	/*Method for creating a "follows after" connections
	 * 
	 * @param first vertex that is followed
	 * @param second vertex that is dependent on the first one
	 * 
	 */
	public void addConnection(T first, T second) {
		//we take the first vertex, get a connection 
		//list and then add the second vertex to that list
		map.get(first).add(second);
	}
	/*
	 * Method for topological sorting
	 * @return List<T> list of topologically sorted vertices
	 */
	public List<T> getTopologicalSortedVertices(){
		List<T> ordered = new LinkedList<T>();
		
		Set<T> visited = new HashSet<T>();
		Set<T> used = new HashSet<T>();
		Stack<T> stack = new Stack<T>();
		
		for (T vertex : map.keySet()) {
		      if (!visited.contains(vertex)) {
	
		      stack.push(vertex);
		      while (!stack.isEmpty()) {
		    	
		    	//we look at the last in the stack vertex
		        T last = stack.peek();
		        //if we have already visited the last vertex
		        if (visited.contains(last)) {
		        	// we remove the vertex from the stack and check if it is in the sorted list already
		        	stack.pop();
		        	if (!used.contains(last)) {
			            used.add(last);
			            ordered.add(last);
			        }		          
		        }
		        //if it was not visited, we mark it as visited 
		        else {
		        	visited.add(last);
		        	
			        for (T followers : map.get(last)) {
			            stack.push(followers);
			        }
		        }
		      }
		    }
		}

		    return ordered;
		
	}
}
