package graph;

import java.util.*;

/*
Strongly Connected Components (SCC) – Kosaraju’s Algorithm

A Strongly Connected Component (SCC) is a subgraph in which every vertex is reachable from every other vertex.

Logic Behind Kosaraju's Algorithm
Kosaraju’s algorithm finds SCCs using two Depth First Search (DFS) traversals:

Steps of the Algorithm
First DFS (Step 1: Compute Finish Order)
Perform DFS traversal on the original graph.
Maintain a stack to store nodes in order of their finishing times (i.e., when DFS completes for a node).
This helps in determining which nodes to process first in the second DFS.
Transpose the Graph (Step 2: Reverse All Edges)
Create a transposed graph where all the edges are reversed.
If there was a directed edge A → B in the original graph, it is changed to B → A in the transposed graph.
Second DFS (Step 3: Find SCCs)
Perform DFS on the transposed graph, processing nodes in the order stored in the stack (from Step 1).
Every new DFS call represents a new SCC, as nodes visited in one DFS belong to the same strongly connected component.
Time Complexity Analysis

First DFS Traversal: 
O(V+E)
Graph Transposition: 
O(V+E)
Second DFS Traversal: 

O(V+E)
Total Complexity: 

O(V+E) (linear time)

*/
public class SCCKosaraju {
    private int V;
    private List<List<Integer>> adj;

    public SCCKosaraju(int vertices) {
        this.V = vertices;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v) {
        adj.get(u).add(v);
    }

    private void dfs1(int v, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;
        for (int neighbor : adj.get(v)) {
            if (!visited[neighbor]) {
                dfs1(neighbor, visited, stack);
            }
        }
        stack.push(v);
    }

    private void dfs2(int v, boolean[] visited, List<List<Integer>> transpose, List<Integer> component) {
        visited[v] = true;
        component.add(v);
        for (int neighbor : transpose.get(v)) {
            if (!visited[neighbor]) {
                dfs2(neighbor, visited, transpose, component);
            }
        }
    }

    public void findSCCs() {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs1(i, visited, stack);
            }
        }

        List<List<Integer>> transpose = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            transpose.add(new ArrayList<>());
        }
        for (int v = 0; v < V; v++) {
            for (int neighbor : adj.get(v)) {
                transpose.get(neighbor).add(v);
            }
        }

        Arrays.fill(visited, false);
        System.out.println("Strongly Connected Components:");
        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (!visited[v]) {
                List<Integer> component = new ArrayList<>();
                dfs2(v, visited, transpose, component);
                System.out.println(component);
            }
        }
    }

    public static void main(String[] args) {
        SCCKosaraju graph = new SCCKosaraju(5);
        graph.addEdge(1, 0);
        graph.addEdge(0, 2);
        graph.addEdge(2, 1);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);

        graph.findSCCs();
    }
}
