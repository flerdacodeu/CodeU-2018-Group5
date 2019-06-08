package task1;
/*
 * Node class for prefix tree
 */
import java.util.HashMap;
public class Node {
	public boolean end = false;
	public HashMap<Character, Node> children;
	
	public Node() {
		children = new HashMap<Character, Node>();
	}
}
