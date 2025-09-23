package graph;
import java.util.*;
import java.io.*;

/*

Given a DAG, find the shortest path from the source to all other nodes in this DAG. In this problem statement,
we have assumed the source vertex to be ‘0’. You will be given the weighted edges of the graph.

Note: What is a DAG ( Directed Acyclic Graph)?

A Directed Graph (containing one-sided edges) having no cycles is said to be a Directed Acyclic Graph.

Input: n = 6, m= 7
edges =[[0,1,2],[0,4,1],[4,5,4],[4,2,2],[1,2,3],[2,3,6],[5,3,1]]


Output: 0 2 3 6 1 5


Explanation:  The above output list shows the shortest path 
to all the nodes from the source vertex (0), 


Dist[0] = 0 


Dist[1] = 2 


Dist[2] = 3 


Dist[3] = 6


Dist[4] = 1


Dist[5] = 5


Example 2:


Let's dry run and visualize the execution of this code step by step for:
Input:

n = 6, m = 7  
edges = [[0,1,2],[0,4,1],[4,5,4],[4,2,2],[1,2,3],[2,3,6],[5,3,1]]
Step 1: Create Graph (Adjacency List)

We represent the directed graph using an adjacency list:

0 → (1,2), (4,1)
1 → (2,3)
2 → (3,6)
4 → (5,4), (2,2)
5 → (3,1)
Step 2: Perform Topological Sorting

DFS Traversal & Stack Formation:
We perform DFS to get the topological ordering of nodes.

Start DFS from 0:
Visit 1 → Visit 2 → Visit 3 (Push 3 to stack)
Backtrack, push 2 to stack
Backtrack, push 1 to stack
Continue DFS for 4 → Visit 5 → Visit 3 (already visited)
Push 5 to stack, then push 4 to stack
Finally, push 0 to stack
Final Topological Order (Stack - Leftmost element processed first):

Stack: [0, 4, 5, 1, 2, 3]
Step 3: Initialize Distance Array

dist = [0, INF, INF, INF, INF, INF]
(Only the source node 0 has a distance of 0.)

Step 4: Process Nodes in Topological Order

We process nodes one by one from the stack:

Processing Node 0
Edge (0 → 1, weight 2) → dist[1] = 0 + 2 = 2
Edge (0 → 4, weight 1) → dist[4] = 0 + 1 = 1
dist = [0, 2, INF, INF, 1, INF]
Processing Node 4
Edge (4 → 5, weight 4) → dist[5] = 1 + 4 = 5
Edge (4 → 2, weight 2) → dist[2] = 1 + 2 = 3
dist = [0, 2, 3, INF, 1, 5]
Processing Node 5
Edge (5 → 3, weight 1) → dist[3] = 5 + 1 = 6
dist = [0, 2, 3, 6, 1, 5]
Processing Node 1
Edge (1 → 2, weight 3) (No update, as dist[2] = 3 is already smaller)
dist = [0, 2, 3, 6, 1, 5]
Processing Node 2
Edge (2 → 3, weight 6) (No update, as dist[3] = 6 is already smaller)
dist = [0, 2, 3, 6, 1, 5]
Processing Node 3 (No outgoing edges)
Final Distance Array

dist = [0, 2, 3, 6, 1, 5]
This means:

0 → 0: Distance = 0
0 → 1: Distance = 2
0 → 2: Distance = 3
0 → 3: Distance = 6
0 → 4: Distance = 1
0 → 5: Distance = 5
Final Output:
[0, 2, 3, 6, 1, 5]


*/
class DagTopolicalSort {
    public static void main(String[] args) throws IOException {
        int n = 6, m = 7;
        int[][] edges = {
                {0, 1, 2}, {0, 4, 1}, {4, 5, 4}, {4, 2, 2},
                {1, 2, 3}, {2, 3, 6}, {5, 3, 1}
        };

        DagTopolicalSort obj = new DagTopolicalSort();
        int[] res = obj.shortestPath(n, m, edges);

        System.out.println(Arrays.toString(res));
    }


    class Pair {
        int first, second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
        // Function to perform Topological Sorting using DFS
        private void topoSort(int node, List<List<Pair>> adj, boolean[] visited, Stack<Integer> stack) {
            visited[node] = true;

            for (Pair neighbor : adj.get(node)) {
                if (!visited[neighbor.first]) {
                    topoSort(neighbor.first, adj, visited, stack);
                }
            }

            stack.push(node);
        }

        public int[] shortestPath(int N, int M, int[][] edges) {
            // Create adjacency list representation of the graph
            List<List<Pair>> adj = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                adj.add(new ArrayList<>());
            }

            for (int[] edge : edges) {
                adj.get(edge[0]).add(new Pair(edge[1], edge[2]));
            }

            // Perform Topological Sort
            Stack<Integer> stack = new Stack<>();
            boolean[] visited = new boolean[N];

            for (int i = 0; i < N; i++) {
                if (!visited[i]) {
                    topoSort(i, adj, visited, stack);
                }
            }

            // Initialize distance array with a large value (Infinity equivalent)
            int[] dist = new int[N];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[0] = 0;

            // Process nodes in topological order
            while (!stack.isEmpty()) {
                int node = stack.pop();

                if (dist[node] != Integer.MAX_VALUE) {
                    for (Pair neighbor : adj.get(node)) {
                        if (dist[node] + neighbor.second < dist[neighbor.first]) {
                            dist[neighbor.first] = dist[node] + neighbor.second;
                        }
                    }
                }
            }

            // Convert unreachable distances to -1
            for (int i = 0; i < N; i++) {
                if (dist[i] == Integer.MAX_VALUE) {
                    dist[i] = -1;
                }
            }

            return dist;
        }

}
