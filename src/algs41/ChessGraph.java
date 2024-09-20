package algs41;



public class ChessGraph extends Graph {
    private static final int[] dx = new int[]{-2, -1, 1, 2, 2, 1, -1, -2};
    private static final int[] dy = new int[]{-1, -2, -2, -1, 1, 2, 2, 1};

    public ChessGraph(int size) {
        super(size * size); // size*size for the total number of squares on the board
        buildGraph(size);
    }

    private void buildGraph(int size) {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                int v = toVertex(x, y, size);
                for (int k = 0; k < 8; k++) {
                    int xk = x + dx[k];
                    int yk = y + dy[k];
                    if (isValid(xk, yk, size)) {
                        int w = toVertex(xk, yk, size);
                        this.addEdge(v, w);
                    }
                }
            }
        }
    }

    private boolean isValid(int x, int y, int size) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    private int toVertex(int x, int y, int size) {
        return x * size + y;
    }
}

