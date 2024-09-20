package algs41;

import java.io.*;
import java.util.*;

public class WordLadder {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Usage: java WordLadder <startWord> <endWord>");
            return;
        }

        String startWord = args[0];
        String endWord = args[1];
        String filePath = "words.txt"; // Specify the file path here

        // Read the list of words from the specified file
        List<String> wordList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String word;
            while ((word = reader.readLine()) != null) {
                wordList.add(word.trim());
            }
        }

        long startTime = System.currentTimeMillis();
        WordGraph graph = new WordGraph(wordList);
        long graphTime = System.currentTimeMillis();
        
        List<String> ladder = graph.findShortestLadder(startWord, endWord);
        long endTime = System.currentTimeMillis();

        if (ladder != null) {
            System.out.println("Word Ladder: " + ladder);
        } else {
            System.out.println("No word ladder found.");
        }

        System.out.println("Number of nodes: " + graph.getNodeCount());
        System.out.println("Number of edges: " + graph.getEdgeCount());
        System.out.println("Time taken to construct the graph: " + (graphTime - startTime) + " ms");
        System.out.println("Total time: " + (endTime - startTime) + " ms");
    }
}

class WordGraph {
    private final Map<String, List<String>> adjacencyList = new HashMap<>();
    private int edgeCount = 0;
    
    public WordGraph(List<String> words) {
        buildGraph(words);
    }

    public void buildGraph(List<String> words) {
        Set<String> wordSet = new HashSet<>(words);

        for (String word : words) {
            List<String> neighbors = findNeighbors(word, wordSet);
            adjacencyList.put(word, neighbors);
            edgeCount += neighbors.size();
        }
    }

    private List<String> findNeighbors(String word, Set<String> wordSet) {
        List<String> neighbors = new ArrayList<>();
        char[] chars = word.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char originalChar = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == originalChar) continue;
                chars[i] = c;
                String newWord = new String(chars);
                if (wordSet.contains(newWord)) {
                    neighbors.add(newWord);
                }
            }
            chars[i] = originalChar;
        }

        return neighbors;
    }

    public List<String> findShortestLadder(String startWord, String endWord) {
        if (!adjacencyList.containsKey(startWord) || !adjacencyList.containsKey(endWord)) {
            return null;
        }

        Queue<List<String>> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(Arrays.asList(startWord));
        visited.add(startWord);

        while (!queue.isEmpty()) {
            List<String> path = queue.poll();
            String lastWord = path.get(path.size() - 1);

            if (lastWord.equals(endWord)) {
                return path;
            }

            for (String neighbor : adjacencyList.getOrDefault(lastWord, Collections.emptyList())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    List<String> newPath = new ArrayList<>(path);
                    newPath.add(neighbor);
                    queue.add(newPath);
                }
            }
        }

        return null;
    }

    public int getNodeCount() {
        return adjacencyList.size();
    }

    public int getEdgeCount() {
        return edgeCount;
    }
}


