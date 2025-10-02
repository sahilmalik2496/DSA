package graph;

import java.util.*;

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
