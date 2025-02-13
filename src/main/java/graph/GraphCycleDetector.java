package graph;

import java.util.*;

class GraphCycleDetector {

    static class Node {
        int vertex, parent;
        Node(int vertex, int parent) {
            this.vertex = vertex;
            this.parent = parent;
        }
    }

    // Function to check for a cycle using BFS
    static boolean hasCycleBFS(ArrayList<ArrayList<Integer>> adj, int start, boolean[] visited) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(start, -1));
        visited[start] = true;

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            int node = current.vertex;
            int parent = current.parent;

            for (int neighbor : adj.get(node)) {
                if (!visited[neighbor]) {
                    queue.add(new Node(neighbor, node));
                    visited[neighbor] = true;
                } else if (neighbor != parent) {
                    return true; // Cycle detected
                }
            }
        }
        return false;
    }

    // Function to detect a cycle in an undirected graph
    public static boolean containsCycle(int vertices, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[vertices];

        for (int i = 0; i < vertices; i++) {
            if (!visited[i] && hasCycleBFS(adj, i, visited)) {
                return true;
            }
        }
        return false;
    }

    // Main method to test the cycle detection
    public static void main(String[] args) {
        int vertices = 5;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(vertices);

        for (int i = 0; i < vertices; i++) {
            adj.add(new ArrayList<>());
        }

        // Creating an undirected graph
        addEdge(adj, 0, 1);
        addEdge(adj, 1, 2);
        addEdge(adj, 2, 3);
        addEdge(adj, 3, 4);
        addEdge(adj, 4, 1); // Cycle exists here

        System.out.println("Cycle present: " + containsCycle(vertices, adj));
    }

    // Helper method to add an edge to the adjacency list
    private static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u); // Since it's an undirected graph
    }
}
