package greedyAndRecursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

class GraphColoring {

    /*
    Problem: M-Coloring (Graph Coloring)

Description
- Given an undirected graph and an integer `M`, determine whether the graph can be colored using at most `M` colors such that no two adjacent vertices share the same color.
- If a valid coloring exists, return/print that the graph is colorable and provide one valid color assignment (colors numbered `1..M`). Otherwise return/print that it is not possible.

Input
- `N` — number of vertices (vertices are `0`-indexed).
- `M` — maximum number of colors.
- `E` — number of edges (implicit or given).
- `edges` — list of `E` pairs `(u, v)` representing undirected edges.

Output
- A boolean indicating whether a valid coloring using at most `M` colors exists.
- If true, a color assigned to every vertex (an array of length `N` with values in `1..M`).

Constraints (practical)
- `N >= 1`. Backtracking solution is exponential; `N` is typically small (e.g., `N <= 20–25`) for brute-force search.
- `0 <= u, v < N`.

Example
- Input: `N = 4`, `M = 3`, `edges = {{0,1},{1,2},{2,3},{3,0},{0,2}}`
- Possible output:
  - Colorable: `true` (or `1`)
  - One valid assignment: `Node 0 -> Color 1`, `Node 1 -> Color 2`, `Node 2 -> Color 3`, `Node 3 -> Color 2`

     */


    public static boolean graphColoring(List<Integer>[] G, int[] color, int i, int C) {
        return backtrackGraphColoring(i, G, color, G.length, C);
    }

    private static boolean isColorValid(int node, List<Integer>[] G, int[] color, int col) {
        // Check all neighbors of the current node
        for (int neighbor : G[node]) {
            // If the neighbor is already colored and has the same color, it's invalid.
            if (color[neighbor] == col) return false;
        }
        return true;
    }

    private static boolean backtrackGraphColoring(int node, List<Integer>[] G, int[] color, int n, int m) {
        // Base case: All nodes have been successfully colored (0 to n-1)
        if (node == n) {
            return true;
        }

        for (int i = 1; i <= m; i++) {
            if (isColorValid(node, G, color, i)) {
                color[node] = i;
                if (backtrackGraphColoring(node + 1, G, color, n, m)) {
                    return true; // Solution found!
                }

                color[node] = 0;
            }
        }

        return false;
    }

    public class Driver {
        public static void main(String[] args) {
            // --- Example 1 Setup ---
            // N = 4 (Number of vertices/nodes)
            final int N = 4;
            // M = 3 (Maximum number of colors)
            final int M = 3;
            // E = 5 (Number of edges)

            // Define the edges as an array of pairs (u, v)
            int[][] edges = {
                    {0, 1},
                    {1, 2},
                    {2, 3},
                    {3, 0},
                    {0, 2}
            };

            // 1. Initialize Adjacency List (G)
            // Note: The array of lists needs to be type-casted upon creation in Java.
            @SuppressWarnings("unchecked")
            List<Integer>[] G = new ArrayList[N];
            for (int i = 0; i < N; i++) {
                G[i] = new ArrayList<>();
            }

            // 2. Build the Graph (G) from edges (undirected)
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                // Add edge u-v and v-u for an undirected graph
                G[u].add(v);
                G[v].add(u);
            }

            int[] color = new int[N];
            boolean result = GraphColoring.graphColoring(G, color, 0, M);

            // 5. Print the result
            if (result) {
                System.out.println("\nOutput: 1 (True)");
                System.out.println("Explanation: A valid coloring was found using at most " + M + " colors.");
                System.out.println("Color Assignment (Node -> Color):");
                // Example of a valid coloring:
                for (int i = 0; i < N; i++) {
                    System.out.println("Node " + i + " -> Color " + color[i]);
                }
                System.out.println("\nOutput: 0 (False)");
                System.out.println("Explanation: It is not possible to color the given graph using " + M + " colors.");
            }
        }
    }
}
