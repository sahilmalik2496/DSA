package graph;

import java.util.*;

/*
Problem Statement: Given a weighted, undirected, and connected graph of V vertices and E edges. The task is to find the sum of weights of the edges of the Minimum Spanning Tree.
(Sometimes it may be asked to find the MST as well, where in the MST the edge-informations will be stored in the form {u, v}(u = starting node, v = ending node).)

Let's perform a dry run of the spanningTree function with:

V = 5 (vertices: {0, 1, 2, 3, 4})
Edges:
{0, 1, 2}
{0, 3, 6}
{1, 2, 3}
{1, 3, 8}
{1, 4, 5}
{4, 2, 7}
Adjacency list representation:
adj[0] → {(1,2), (3,6)}
adj[1] → {(0,2), (2,3), (3,8), (4,5)}
adj[2] → {(1,3), (4,7)}
adj[3] → {(0,6), (1,8)}
adj[4] → {(1,5), (2,7)}
Step-by-step Execution (Prim's Algorithm)
Initialize Priority Queue (pq) → Start from node 0, insert (0, 0).
Start Processing MST
Extract (0, 0), visit node 0. MST sum = 0.
Push its neighbors: (2,1) and (6,3).
pq = {(2,1), (6,3)}
Extract (2,1), visit node 1. MST sum = 2.
Push its neighbors (3,2), (8,3), (5,4).
pq = {(3,2), (6,3), (8,3), (5,4)}
Extract (3,2), visit node 2. MST sum = 2+3 = 5.
Push its neighbor (7,4).
pq = {(5,4), (6,3), (8,3), (7,4)}
Extract (5,4), visit node 4. MST sum = 5+5 = 10.
No unvisited neighbors.
pq = {(6,3), (8,3), (7,4)}
Extract (6,3), visit node 3. MST sum = 10+6 = 16.
No new neighbors to push.
pq = {(8,3), (7,4)} (but all nodes are visited, so they are ignored)
Final MST Sum: 16
Edges in MST:
{0, 1, 2}
{1, 2, 3}
{1, 4, 5}
{0, 3, 6}
Final Output
Sum of weights of edges in MST: 16

Time Complexity: O(E*logE) + O(E*logE)~ O(E*logE), where E = no. of given edges. The maximum size of the priority queue can be E so after at most E iterations the priority queue will be empty and the loop will end. Inside the loop, there is a pop operation that will take logE time. This will result in the first O(E*logE) time complexity. Now, inside that loop, for every node, we need to traverse all its adjacent nodes where the number of nodes can be at most E. If we find any node unvisited, we will perform a push operation and for that, we need a logE time complexity. So this will result in the second O(E*logE). 
Space Complexity: O(E) + O(V), where E = no. of edges and V = no. of vertices. O(E) occurs due to the size of the priority queue and O(V) due to the visited array. If we wish to get the mst, we need an extra O(V-1) space to store the edges of the most.
*/



class PrimMst {
    static class Pair {
        int node;
        int distance;

        public Pair(int distance, int node) {
            this.node = node;
            this.distance = distance;
        }
    }
    // Function to find sum of weights of edges of the Minimum Spanning Tree.
    static int spanningTree(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj) {
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.distance));
        int[] vis = new int[V];
        pq.add(new Pair(0, 0));
        int sum = 0;
        
        while (!pq.isEmpty()) {
            Pair current = pq.poll();
            int wt = current.distance;
            int node = current.node;
            
            if (vis[node] == 1) continue;
            
            vis[node] = 1;
            sum += wt;

            for (ArrayList<Integer> neighbor : adj.get(node)) {
                int adjNode = neighbor.get(0);
                int edW = neighbor.get(1);
                
                if (vis[adjNode] == 0) {
                    pq.add(new Pair(edW, adjNode));
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int V = 5;
        ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<>();
        
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        
        adj.get(0).add(new ArrayList<>(Arrays.asList(1, 2)));
        adj.get(1).add(new ArrayList<>(Arrays.asList(0, 2)));
        adj.get(1).add(new ArrayList<>(Arrays.asList(2, 3)));
        adj.get(2).add(new ArrayList<>(Arrays.asList(1, 3)));
        adj.get(2).add(new ArrayList<>(Arrays.asList(3, 1)));
        adj.get(3).add(new ArrayList<>(Arrays.asList(2, 1)));
        adj.get(3).add(new ArrayList<>(Arrays.asList(4, 6)));
        adj.get(4).add(new ArrayList<>(Arrays.asList(3, 6)));
        
        int mstWeight = spanningTree(V, adj);
        System.out.println("Sum of weights of edges in MST: " + mstWeight);
    }
}

