package graph;

import java.util.*;

/*

https://leetcode.com/problems/critical-connections-in-a-network/

There are n servers numbered from 0 to n - 1 connected by undirected server-to-server connections forming
a network where connections[i] = [ai, bi] represents a connection between servers ai and bi. Any server can
reach
 other servers directly or indirectly through the network.

A critical connection is a connection that, if removed, will make some servers unable to reach some other
server.

Return all critical connections in the network in any order.


This algorithm efficiently finds bridges in an undirected graph using DFS traversal and maintaining
discovery time
 and lowest reachable time for each node.

Key Idea
Perform DFS traversal while keeping track of:
disc[] → Discovery time of each node.
low[] → Lowest discovery time reachable.
A connection [u, v] is critical if low[v] > disc[u], meaning v has no back edge to reach ancestors.

Time & Space Complexity
Time Complexity: O(V + E)
DFS runs in O(V + E)
Each edge is processed once
Space Complexity: O(V + E)
Adjacency list takes O(V + E)
Arrays disc[] and low[] take O(V)

*/

public class CriticalConnections {
    private int time = 0; // Global timer for DFS discovery time
    private List<List<Integer>> result;
    private List<Integer>[] graph;
    private int[] disc, low;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        result = new ArrayList<>();
        graph = new ArrayList[n];
        disc = new int[n];
        low = new int[n];
        Arrays.fill(disc, -1); // Mark all nodes as unvisited

        // Build adjacency list for the undirected graph
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (List<Integer> conn : connections) {
            int u = conn.get(0), v = conn.get(1);
            graph[u].add(v);
            graph[v].add(u);
        }

        // Run DFS from node 0 (assuming the graph is fully connected)
        dfs(0, -1);

        return result;
    }

    private void dfs(int node, int parent) {
        disc[node] = low[node] = time++; // Assign discovery time and low value

        for (int neighbor : graph[node]) {
            if (neighbor == parent) continue; // Ignore the parent node in DFS traversal

            if (disc[neighbor] == -1) { // If the neighbor is unvisited
                dfs(neighbor, node);
                low[node] = Math.min(low[node], low[neighbor]); // Update the low-link value

                // If the lowest reachable node from neighbor is higher than discovery of node, it's a bridge
                if (low[neighbor] > disc[node]) {
                    result.add(Arrays.asList(node, neighbor));
                }
            } else {
                // Back edge detected, update low-link value
                low[node] = Math.min(low[node], disc[neighbor]);
            }
        }
    }

    // Driver code
    public static void main(String[] args) {
        CriticalConnections obj = new CriticalConnections();
        int n = 4;
        List<List<Integer>> connections = Arrays.asList(
                Arrays.asList(0, 1),
                Arrays.asList(1, 2),
                Arrays.asList(2, 0),
                Arrays.asList(1, 3)
        );

        List<List<Integer>> criticalEdges = obj.criticalConnections(n, connections);
        System.out.println("Critical Connections: " + criticalEdges);
    }
}
