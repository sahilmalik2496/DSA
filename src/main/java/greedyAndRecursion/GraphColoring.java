package greedyAndRecursion;

/*
M - Coloring Problem


62

3
Problem Statement: Given an undirected graph and a number m, determine if the graph can be colored with at most m colors such that no two adjacent vertices of the graph are colored with the same color.

Examples:

Example 1:
Input:
N = 4
M = 3
E = 5
Edges[] = {
  (0, 1),
  (1, 2),
  (2, 3),
  (3, 0),
  (0, 2)
}

Output: 1

Explanation: It is possible to colour the given graph using 3 colours.
 */
import java.util.*;

class GraphColoring {
    public static boolean graphColoring(List<Integer>[] G, int[] color, int i, int C) {
        return backtrackGraphColoring(i, G, color, G.length, C);
    }

    private static boolean isColorValid(int node, List<Integer>[] G, int[] color, int col) {
        for (int neighbor : G[node]) {
            if (color[neighbor] == col) return false;
        }
        return true;
    }

    private static boolean backtrackGraphColoring(int node, List<Integer>[] G, int[] color, int n, int m) {
        if (node == n) return true;

        for (int i = 1; i <= m; i++) {
            if (isColorValid(node, G, color, i)) {
                color[node] = i;
                if (backtrackGraphColoring(node + 1, G, color, n, m)) return true;
                color[node] = 0; // Backtrack
            }
        }
        return false;
    }
}

