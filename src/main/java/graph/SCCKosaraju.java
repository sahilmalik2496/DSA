package graph

import java.util.*;

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
