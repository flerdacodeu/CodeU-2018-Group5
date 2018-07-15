import java.util.*;

public class FindAlphabet {

    /**
     * This method inferes an alphabet with the given ordered list of words. It uses topological sort algorithm to achieve that.
     * @param dictionary is the given ordered list of words.
     * @return the alphabet inferred from that list of words.
     */
    public static ArrayList<Character> findAlphabet (ArrayList<String> dictionary) {
        if (dictionary.isEmpty())
            return new ArrayList<Character>();
        if (dictionary == null)
            throw new IllegalArgumentException();

        Map<Character,Vertex> alphabetGraph = new HashMap<Character, Vertex>();
        String s1 = dictionary.get(0);
        addCharacters(s1,alphabetGraph);
        String s2;

        for (int i = 1; i < dictionary.size(); i++) {
            s2 = s1;
            s1 = dictionary.get(i);
            addCharacters(s1,alphabetGraph);
            int index = -1;
            for(int j = 0; j < Math.min(s1.length(),s2.length()); j++) {
                if (s1.charAt(j) != s2.charAt(j)) {
                    index = j;
                    break;
                }
            }

            if (index != -1) {
                char first = s2.charAt(index);
                char second = s1.charAt(index);
                alphabetGraph.get(first).addEdge(alphabetGraph.get(second));
                alphabetGraph.get(second).setIndegree(alphabetGraph.get(second).getIndegree()+1);
            }
        }
        return new ArrayList<>(topologicalSort(alphabetGraph));
    }

    /**
     * This method adds characters with creating new vertices to a graph.
     * @param word is the given word whose characters are going to be added to given graph.
     * @param alphabetGraph is the given graph.
     */
    public static void addCharacters (String word,Map<Character,Vertex> alphabetGraph) {
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            if (!alphabetGraph.containsKey(letter)) {
                Vertex v = new Vertex(letter);
                alphabetGraph.put(letter,v);
            }
        }
    }

    /**
     * This method does applies the classical topological sort algorithm to the given alphabet graph.
     * @param alphabetGraph is the given graph.
     * @return alphabet of inferred from the fraph.
     */
    public static Set<Character> topologicalSort (Map<Character,Vertex> alphabetGraph) {
        Set<Character> alphabet = new LinkedHashSet<>();
        Queue<Vertex> queue = new LinkedList<>();
        while (hasZeroInDegreedVertices(alphabetGraph)) {
            /*for (int i = 0; i < alphabetGraph.size(); i++) {
                if (alphabetGraph.get(i).getIndegree() == 0) {
                    ((LinkedList) queue).add(alphabetGraph.get(i));
                    alphabetGraph.get(i).setIndegree(-1);
                }
            }*/
            Iterator<Map.Entry<Character,Vertex>> it = alphabetGraph.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Character,Vertex> pair = it.next();
                if (pair.getValue().getIndegree() == 0) {
                    ((LinkedList) queue).add(pair.getValue());
                    pair.getValue().setIndegree(-1);
                }
            }
            while (!queue.isEmpty()) {
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
        Iterator<Map.Entry<Character,Vertex>> it = alphabetGraph.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Character,Vertex> pair = it.next();
            if (pair.getValue().getIndegree() == 0)
                return true;
        }
        return false;
    }
}
