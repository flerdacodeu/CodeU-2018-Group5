import java.util.HashMap;

public class DictNode {
    public HashMap<Character, DictNode> children = new HashMap<Character, DictNode>();
    private boolean lastCharacter = false;

    public HashMap<Character, DictNode> getChildren() {
        return children;
    }

    public void setChildren(HashMap<Character, DictNode> children) {
        this.children = children;
    }

    public boolean isLastCharacter() {
        return lastCharacter;
    }

    public void setLastCharacter(boolean lastCharacter) {
        this.lastCharacter = lastCharacter;
    }
}
