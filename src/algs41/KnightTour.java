package algs41;
import java.util.*;

public class KnightTour {
    private ChessGraph chess;

    public KnightTour(int size) {
        chess = new ChessGraph(size);
    }

    public List<Integer> knightTour(int start) {
        int limit = chess.V();
        List<Integer> path = new ArrayList<>();
        path.add(start);
        if (findTour(start, path, limit)) {
            return path;
        }
        return null;
    }

    private boolean findTour(int u, List<Integer> path, int limit) {
        if (path.size() == limit) {
            return true; // tour completed
        }

        

        List<Integer> candidates = new ArrayList<>();
        for (int v : chess.adj(u)) {
            if (!path.contains(v)) {
                candidates.add(v);
            }
        }

        

        // Apply Warnsdorff's heuristic by sorting the candidates by the number of onward moves
        Collections.sort(candidates, (v1, v2) -> chess.degree(v1) - chess.degree(v2));

        for (int next : candidates) {
            path.add(next);
            if (findTour(next, path, limit)) {
                return true;
            }
            path.removeLast(); // backtrack
        }

        return false;
    }

    public static void main(String[] args) {
        KnightTour knightTour = new KnightTour(8);
        List<Integer> tour = knightTour.knightTour(0); // starting from the top-left corner

        if (tour != null) {
            System.out.println("Tour found:");
            for (int v : tour) {
                System.out.print(v + " ");
            }
        } else {
            System.out.println("No tour found.");
        }
    }
}

// 0,1,2,3,4,5,6,7
// 8,9,10,11,12,13,14,15
// 16,17,18,19,20,21,22,23
// 24,25,26,27,28,29,30,31
// 32,33,34,35,36,37,38,39
// 40,41,42,43,44,45,46,47
// 48,49,50,51,52,53,54,55
// 56,57,58,59,60,61,62,63


// 0 17 32 49 59 53 63 46 61 55 38 23 6 12 2 8 25 40 
// 57 51 41 56 50 60 54 39 22 7 13 3 9 24 34 44 29 14 
// 31 37 47 62 52 58 48 33 16 1 11 5 15 30 45 35 18 28 
// 43 26 20 10 4 21 27 42 36 19