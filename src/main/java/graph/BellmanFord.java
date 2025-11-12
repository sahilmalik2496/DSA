package graph;

import java.util.ArrayList;
import java.util.Arrays;

/*
Bellman Ford Algorithm: G-41
Problem Statement: Given a weighted, directed and connected graph of V vertices and E edges, Find the shortest distance of
all the vertices from the source vertex S.

How to detect a negative cycle in the graph?
We know if we keep on rotating inside a negative cycle, the path weight will be decreased in every iteration. But according
 to our intuition, we should have minimized all the distances within N-1 iterations(that means, after N-1 iterations no
  relaxation of edges is possible).
In order to check the existence of a negative cycle, we will relax the edges one more time after the completion of N-1 iterations.
 And if in that Nth iteration, it is found that further relaxation of any edge is possible, we can conclude that the graph
 has a negative cycle. Thus, the Bellman-Ford algorithm detects negative cycles.

Two follow-up questions about the algorithm: Why do we need exact N-1 iterations? Let’s try to first understand this
using an example:

In the above graph, the algorithm will minimize the distance of the ith node in the ith iteration like dist[1] will be
updated in the 1st iteration, dist[2] will be updated in the 2nd iteration, and so on. So we will need a total of
4 iterations(i.e. N-1 iterations) to minimize all the distances as dist[0] is already set to 0. Note: Points to remember
since, in a graph of N nodes we will take at most N-1 edges to reach from the first to the last node, we need exact N-1 iterations.
It is impossible to draw a graph that takes more than N-1 edges to reach any node.
 */


public class BellmanFord {
    static int[] bellman_ford(int V,
                              ArrayList<ArrayList<Integer>> edges, int S) {
        int[] dist = new int[V];
        Arrays.fill(dist, (int) (1e8));
        dist[S] = 0;
        // V x E
        for (int i = 0; i < V - 1; i++) {
            for (ArrayList<Integer> it : edges) {
                int u = it.get(0);
                int v = it.get(1);
                int wt = it.get(2);
                if (dist[u] != 1e8 && dist[u] + wt < dist[v]) {
                    dist[v] = dist[u] + wt;
                }
            }
        }
        // Nth relaxation to check negative cycle
        for (ArrayList<Integer> it : edges) {
            int u = it.get(0);
            int v = it.get(1);
            int wt = it.get(2);
            if (dist[u] != 1e8 && dist[u] + wt < dist[v]) {
                int[] temp = new int[1];
                temp[0] = -1;
                return temp;
            }
        }
        return dist;
    }

}
