import java.util.HashMap;

public class Dict {
    public HashMap<Character,DictNode> letters = new HashMap<Character, DictNode>();

    public void insertWord (String s){
        if (!letters.containsKey(s.charAt(0))){
            letters.put(s.charAt(0),new DictNode());
        }
        DictNode current = letters.get(s.charAt(0));
        s = s.substring(1);
        insertWord(s,current);
    }

    /**
     *
     * @param s is the word that is inserted.
     * @param node is the current node in the dictionary tree.
     */
    private void insertWord (String s, DictNode node) {
        if (node.getChildren().containsKey(s.charAt(0))) {
            node = node.getChildren().get(s.charAt(0));
        }
        else {
            DictNode next = new DictNode();
            node.children.put(s.charAt(0),next);
            node = node.children.get(s.charAt(0));
        }

        if (s.length() == 1) {
            node.setLastCharacter(true);
            return;
        }
        else {
            insertWord(s.substring(1),node);
        }
    }

    public boolean isPrefix (String s){
        if (!letters.containsKey(s.charAt(0))) {
            return false;
        }
        else {
            return isPrefix(s.substring(1),letters.get(s.charAt(0)));
        }
    }

    /**
     * This method checks the given string is prefix or not by recursively traversing the tree.
     * @param s is the string that is searched.
     * @param node is the current node in the tree.
     * @return true is s is a prefix false otherwise.
     */
    private boolean isPrefix (String s, DictNode node) {
        if (!node.children.containsKey(s.charAt(0)))
            return false;
        else if (s.length() == 1 )
            return true;
        else
            return isPrefix(s.substring(1),node.children.get(s.charAt(0)));
    }

    public boolean isWord (String s){
        if (!letters.containsKey(s.charAt(0)))
            return false;
        else
            return isWord(s.substring(1),letters.get(s.charAt(0)));
    }

    /**
     * This method checks the given string is a word or not by traversing the tree recursively.
     * @param s is the given string.
     * @param node is the current position in the tree.
     * @return true if the given string is a word false otherwise.
     */
    private boolean isWord (String s, DictNode node) {
        if (!node.children.containsKey(s.charAt(0)))
            return false;
        else if (s.length() == 1)
            return node.children.get(s.charAt(0)).isLastCharacter();
        else
            return isWord(s.substring(1),node.children.get(s.charAt(0)));
    }
}
