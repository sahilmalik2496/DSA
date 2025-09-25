package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
