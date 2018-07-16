package task1;

import java.util.Iterator;
import java.util.List;
/*
 * Solution for the 5th assignment
 * The idea is to create a graph for all possible characters and then do a topological sorting
 * 
 * I have not done the extra tasks (checking for the circle graphs - tarjans algorithm, validating dictionary and etc)
 */
public class Alphabet {
	/*
	 * function that returns an alphabet as a list
	 * @return List<Character> a list of ordered alphabet is returned
	 * @param dictionary a list of ordered words as in dictionary
	 */
	public List<Character> getAlphabet(List<Character[]> dictionary){
		Graph<Character> dictionaryGraph =  createAGraph(dictionary);
		
		return dictionaryGraph.getTopologicalSortedVertices();
	}
	
	
	private Graph<Character> createAGraph(List<Character[]> dictionary){
		
		Iterator<Character[]> iterator = dictionary.iterator();
		Graph<Character> alphabetGraph = new Graph<Character>();
		Character[] firstWord = null;
		
		//we add all possible vertices to the graph
		insertVertices(dictionary, alphabetGraph);
		
		if(iterator.hasNext()) {
			firstWord = iterator.next();
		}
		//then we scan all the possible words in the dictionary
		while(iterator.hasNext()) {
				Character[] secondWord = iterator.next();
				
				int x = 0; 
				int y = 0;
				
				while(x < firstWord.length && y < secondWord.length && firstWord[x]==secondWord[y]) {
					x++;
					y++;
				}
				//if index is not out of bounds we add the connection, which means if we have "abc" and "adf", there is a connection
				//between b and d
				if(x < firstWord.length && y < secondWord.length) {
					alphabetGraph.addConnection(firstWord[x], secondWord[x]);
				}
				
				firstWord = secondWord;
		}
		return alphabetGraph;
	}
	
	private void insertVertices(List<Character[]> dictionary, Graph<Character> graph) {
		Iterator<Character[]> iterator = dictionary.iterator();
		
		while(iterator.hasNext()) {
			for(Character c : iterator.next()) {
				graph.addVertex(c);
			}
		}
	}
}
