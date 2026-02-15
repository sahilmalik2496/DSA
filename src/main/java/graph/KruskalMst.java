package graph;

import java.util.*;

/*
Problem description (concise)

Task
- Implement Kruskal's algorithm to compute the weight of the Minimum Spanning Tree (MST) for an undirected weighted graph.
- The implementation lives in `src/main/java/graph/KruskalMst.java` and exposes the method `spanningTree(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj)`.

Input format
- `V`: number of vertices (vertices labeled `0` to `V-1`).
- `adj`: adjacency list where `adj.get(u)` is a list of neighbors; each neighbor is an `ArrayList<Integer>` with two elements: `[v, w]` meaning an edge between `u` and `v` with weight `w`. The graph is undirected, so edges appear in both adjacency lists.

Output
- Return the total weight (an `int`) of the MST. If the graph is disconnected, behavior depends on implementation (current code assumes a connected graph).

Example (based on the `main` in `src/main/java/graph/KruskalMst.java`)
```text
Vertices: 5
Edges: {0-1:2, 0-2:1, 1-2:1, 2-3:2, 3-4:1, 4-2:2}
Output (MST weight): 5
```

Complexity
- Time: O(E log E) for sorting edges plus near-constant amortized DSU operations.
- Space: O(E) to store edges.

Constraints (typical)
- 1 \<= V \<= 10^5, 0 \<= E \<= V*(V-1)/2 (adjust to actual project limits).
- Edge weights fit in `int`.
 */
class KruskalMst {

    public static int spanningTree(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj) {
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            for (ArrayList<Integer> neighbor : adj.get(i)) {
                edges.add(new Edge(i, neighbor.get(0), neighbor.get(1)));
            }
        }

        Collections.sort(edges);
        DisjointSet ds = new DisjointSet(V);
        int mstWeight = 0;

        for (Edge edge : edges) {
            if (ds.findUPar(edge.src) != ds.findUPar(edge.dest)) { // ✅ Now it will work
                mstWeight += edge.weight;
                ds.unionBySize(edge.src, edge.dest);
            }
        }

        return mstWeight;
    }

    public static void main(String[] args) {
        int V = 5;
        ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<>();
        int[][] edges = {
                {0, 1, 2}, {0, 2, 1}, {1, 2, 1}, {2, 3, 2}, {3, 4, 1}, {4, 2, 2}
        };

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adj.get(edge[0]).add(new ArrayList<>(Arrays.asList(edge[1], edge[2])));
            adj.get(edge[1]).add(new ArrayList<>(Arrays.asList(edge[0], edge[2])));
        }

        System.out.println("Minimum Spanning Tree Weight: " + spanningTree(V, adj));
    }
}
