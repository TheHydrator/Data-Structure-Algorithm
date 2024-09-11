import java.io.*;
import java.util.*;
import com.opencsv.*;
import graph.Graph;


import java.util.*;

public class Graph<N, E> {
    private final Map<N, List<Edge<N, E>>> adjacencyList = new HashMap<>();

    public void addNode(N node) {
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(N source, N destination, E label) {
        addNode(source);
        addNode(destination);
        adjacencyList.get(source).add(new Edge<>(source, destination, label));
    }

    public boolean containsNode(N node) {
        return adjacencyList.containsKey(node);
    }

    public List<Edge<N, E>> getEdgesFrom(N node) {
        return adjacencyList.getOrDefault(node, Collections.emptyList());
    }
}



public class Edge<N, E> {
    private final N source;
    private final N destination;
    private final E label;

    public Edge(N source, N destination, E label) {
        this.source = source;
        this.destination = destination;
        this.label = label;
    }

    public N getSource() {
        return source;
    }

    public N getDestination() {
        return destination;
    }

    public E getLabel() {
        return label;
    }
}

public class MarvelPaths {
    private Graph<String, String> graph;

    public MarvelPaths() {
        graph = new Graph<>();
    }

    // Method to build the graph from the Marvel dataset
    public void buildGraph(List<MarvelEntry> data) {
        Map<String, Set<String>> bookToCharacters = new HashMap<>();
        for (MarvelEntry entry : data) {
            bookToCharacters.putIfAbsent(entry.getBook(), new HashSet<>());
            bookToCharacters.get(entry.getBook()).add(entry.getCharacter());
        }
        for (Map.Entry<String, Set<String>> entry : bookToCharacters.entrySet()) {
            String book = entry.getKey();
            Set<String> characters = entry.getValue();
            for (String character1 : characters) {
                for (String character2 : characters) {
                    if (!character1.equals(character2)) {
                        graph.addEdge(character1, character2, book);
                    }
                }
            }
        }
    }
    
    // Method to find the shortest path using BFS
    public List<Edge<String, String>> findShortestPath(String start, String dest) {
        if (!graph.containsNode(start) || !graph.containsNode(dest)) {
            return null;
        }

        Queue<String> queue = new LinkedList<>();
        Map<String, List<Edge<String, String>>> paths = new HashMap<>();

        queue.add(start);
        paths.put(start, new ArrayList<>());

        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.equals(dest)) {
                return paths.get(current);
            }

            List<Edge<String, String>> edges = graph.getEdgesFrom(current);
            edges.sort(Comparator.comparing(Edge::getDestination).thenComparing(Edge::getLabel));

            for (Edge<String, String> edge : edges) {
                String neighbor = edge.getDestination();
                if (!paths.containsKey(neighbor)) {
                    List<Edge<String, String>> newPath = new ArrayList<>(paths.get(current));
                    newPath.add(edge);
                    paths.put(neighbor, newPath);
                    queue.add(neighbor);
                }
            }
        }
        return null;
    }

    // Method to parse the Marvel data file
    public List<MarvelEntry> parseData(String filename) throws IOException {
        List<MarvelEntry> entries = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                getClass().getResourceAsStream("/data/" + filename)))) {
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null) {
                String character = nextLine[0];
                String book = nextLine[1];
                entries.add(new MarvelEntry(character, book));
            }
        }
        return entries;
    }

    public static void main(String[] args) {
        MarvelPaths mp = new MarvelPaths();
        try {
            List<MarvelEntry> data = mp.parseData("marvel.tsv");
            mp.buildGraph(data);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the start character:");
            String start = scanner.nextLine();
            System.out.println("Enter the destination character:");
            String dest = scanner.nextLine();

            List<Edge<String, String>> path = mp.findShortestPath(start, dest);
            if (path != null) {
                System.out.println("Path found:");
                for (Edge<String, String> edge : path) {
                    System.out.println(edge.getSource() + " to " + edge.getDestination() + " via " + edge.getLabel());
                }
            } else {
                System.out.println("No path found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class MarvelEntry {
    private String character;
    private String book;

    public MarvelEntry(String character, String book) {
        this.character = character;
        this.book = book;
    }

    public String getCharacter() {
        return character;
    }

    public String getBook() {
        return book;
    }
}
