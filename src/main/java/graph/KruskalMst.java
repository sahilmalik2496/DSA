package graph;

import java.util.*;

class Disjoint {
    private List<Integer> parent, rank, size;

    public Disjoint(int n) {
        parent = new ArrayList<>(Collections.nCopies(n + 1, 0));
        rank = new ArrayList<>(Collections.nCopies(n + 1, 0));
        size = new ArrayList<>(Collections.nCopies(n + 1, 1));

        for (int i = 0; i <= n; i++) {
            parent.set(i, i);
        }
    }

    // ✅ Make this method PUBLIC to be accessible from KruskalMst
    public int find(int node) {
        if (node != parent.get(node)) {
            parent.set(node, find(parent.get(node))); // Path Compression
        }
        return parent.get(node);
    }

    public void unionByRank(int u, int v) {
        int rootU = find(u), rootV = find(v);
        if (rootU == rootV) return;

        if (rank.get(rootU) < rank.get(rootV)) {
            parent.set(rootU, rootV);
        } else if (rank.get(rootV) < rank.get(rootU)) {
            parent.set(rootV, rootU);
        } else {
            parent.set(rootV, rootU);
            rank.set(rootU, rank.get(rootU) + 1);
        }
    }

    public void unionBySize(int u, int v) {
        int rootU = find(u), rootV = find(v);
        if (rootU == rootV) return;

        if (size.get(rootU) < size.get(rootV)) {
            parent.set(rootU, rootV);
            size.set(rootV, size.get(rootV) + size.get(rootU));
        } else {
            parent.set(rootV, rootU);
            size.set(rootU, size.get(rootU) + size.get(rootV));
        }
    }
}

class Edge implements Comparable<Edge> {
    int src, dest, weight;

    Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }
}

class KruskalMst {
    public static int spanningTree(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj) {
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            for (ArrayList<Integer> neighbor : adj.get(i)) {
                edges.add(new Edge(i, neighbor.get(0), neighbor.get(1)));
            }
        }

        Collections.sort(edges);
        Disjoint ds = new Disjoint(V);
        int mstWeight = 0;

        for (Edge edge : edges) {
            if (ds.find(edge.src) != ds.find(edge.dest)) { // ✅ Now it will work
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
