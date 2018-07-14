import java.util.*;

public class FindAlphabet {
    public static ArrayList<Character> findAlpahet (ArrayList<String> dictionary) {
        if (dictionary.isEmpty())
            return new ArrayList<Character>();
        if (dictionary == null)
            throw new IllegalArgumentException();

        Map<Character,Vertex> alphabetGraph = new HashMap<Character, Vertex>();
        String s1, s2;

        for (int i = 0; i < dictionary.size() - 1; i++) {
            s1 = dictionary.get(i);
            addCharacters(s1,alphabetGraph);
            s2 = dictionary.get(i+1);
            addCharacters(s2,alphabetGraph);

            int index = -1;
            for(int j = 0; j < Math.min(s1.length(),s2.length()); j++) {
                if (s1.charAt(j) != s2.charAt(j)) {
                    index = j;
                    break;
                }
            }

            if (index != -1) {
                char first = s1.charAt(index);
                char second = s2.charAt(index);
                alphabetGraph.get(first).addEdge(alphabetGraph.get(second));
                alphabetGraph.get(second).setIndegree(alphabetGraph.get(second).getIndegree()+1);
            }
        }

    }
    public static void addCharacters (String word,Map<Character,Vertex> alphabetGraph) {
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            if (!alphabetGraph.containsKey(letter)) {
                Vertex v = new Vertex(letter);
                alphabetGraph.put(letter,v);
            }
        }
    }
    public static Set<Character> topologicalSort (Map<Character,Vertex> alphabetGraph) {
        Set<Character> alphabet = new LinkedHashSet<>();
        Queue<Vertex> queue = new LinkedList<>();
        while (hasZeroInDegreedVertices(alphabetGraph)) {
            for (int i = 0; i < alphabetGraph.size(); i++) {
                if (alphabetGraph.get(i).getIndegree() == 0) {
                    ((LinkedList) queue).add(alphabetGraph.get(i));
                    alphabetGraph.get(i).setIndegree(-1);
                }
            }
            for (int i = 0; i < queue.size(); i++) {
                Vertex letter = queue.poll();
                alphabet.add(letter.getValue());
                for (int j = 0; j < letter.getEdges().size(); j++) {
                    Vertex neighbour = letter.getEdges().get(j);
                    neighbour.setIndegree(neighbour.getIndegree()-1);
                }
            }
        }
        return alphabet;
    }
    public static boolean hasZeroInDegreedVertices (Map<Character,Vertex> alphabetGraph) {
        for (int i = 0; i < alphabetGraph.size(); i++) {
            if (alphabetGraph.get(i).getIndegree() == 0)
                return true;
        }
        return false;
    }
}
