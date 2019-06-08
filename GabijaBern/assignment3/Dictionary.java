package task1;

import java.util.HashMap;
/*
 * Dictionary class that stores words in a trie structure
 */
public class Dictionary {
	
	private HashMap<Character, Node> rootChildren = new HashMap<Character, Node>();

	/*
	 * Method for putting a new word to the dictionary
	 * @param word It is a word that needs to be added to the dictionary
	 */
	public void putFromRoot(String word) {
		if(!rootChildren.containsKey(word.charAt(0))) {
			rootChildren.put(word.charAt(0), new Node());
		}
		putWord(word.substring(1), rootChildren.get(word.charAt(0)));
	}	
	
	/*
	 * Method that adds a new word to a tree
	 * if the given root doesnt have the letter as a child, we create a new node for it
	 * if we have reached the end of the word -> we set the "end" variable to be true, we will use 
	 * it later in searching for words
	 * @param word the word that we want to put into trie
	 */
	private void putWord(String word, Node root) {
		
		Node nextRoot;
		
		if(!root.children.containsKey(word.charAt(0))) {
			nextRoot = new Node();
			root.children.put(word.charAt(0), nextRoot);
		}
		else{
			nextRoot = root.children.get(word.charAt(0));
		}
		
		if(word.length() == 1 ) {

			nextRoot.end=true;
			return;
		}
		else {
			putWord(word.substring(1), nextRoot);
		}
	}
	
	public boolean isPrefix(String word) {
		
		if(word.length()==0) {
			return true;
		}
		
		if(rootChildren.containsKey(word.charAt(0))){
			if(word.length()==1) {
				return true;
			}
			return recursivePrefixSearch(word.substring(1), rootChildren.get(word.charAt(0)));
		}
		else{
			return false;
		}	
	}
	
	private boolean recursivePrefixSearch(String word, Node root) {
		
		if(word.length()==1) {
			return true;
		}
		else if(root.children.containsKey(word.charAt(0))){
			return recursiveWordSearch(word.substring(1), root.children.get(word.charAt(0)));
		}
		else {
			return false;
		}		
	}
	/*
	 * Method for finding words, we check the dictionary rootChildren first and if we find the first letter
	 * we call a recursive method to go into the trie
	 * @param word that we are looking for 
	 * 
	 */
	public boolean isWord(String word) {
		if(word.length()==0) {
			return true;
		}
		
		if(rootChildren.containsKey(word.charAt(0))){
			if(word.length()==1) {
				if(rootChildren.get(word.charAt(0)).end) {
					return true;
				}
				return false;
			}
			return recursiveWordSearch(word.substring(1), rootChildren.get(word.charAt(0)));
		}
		else{
			return false;
		}	
	}
	/*
	 * Recursive method for going deeper into trie and searching if the word is in the dictionary
	 * @param word a substring without first letter of the original word, we omit first letter when
	 * the recursive method goes deeper into the trie
	 * @param node we check if the first letter that we have omitted contains children with the current first letter  
	 *
	 */
	private boolean recursiveWordSearch(String word, Node node) {
		if(word.length()==0) {
			if(node.end) {
				return true;
			}
			else {
				return false;
			}
		}
		if(node.children.containsKey(word.charAt(0))){
			return recursiveWordSearch(word.substring(1), node.children.get(word.charAt(0)));
		}
		else {
			return false;
		}
	}
}