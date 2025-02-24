import java.util.*;
import java.lang.*;
import java.io.*;

/*

Given an Undirected Graph having unit weight, find the shortest path from the source to all other nodes in this graph. In this problem statement, we have assumed the source vertex to be ‘0’. If a vertex is unreachable from the source node, then return -1 for that vertex.

Example 1:



Input:
n = 9, m = 10
edges = [[0,1],[0,3],[3,4],[4 ,5],[5, 6],[1,2],[2,6],[6,7],[7,8],[6,8]]
src=0 

Output: 0 1 2 1 2 3 3 4 4

The above output array shows the shortest path to all 
the nodes from the source vertex (0), Dist[0] = 0, 
Dist[1] = 1 , Dist[2] = 2 , …. Dist[8] = 4 
Where Dist[node] is the shortest path between src and 
the node. For a node, if the value of Dist[node]= -1, 
then we conclude that the node is unreachable from 
the src node.
*/


class Main{
    
    public static void main(String[] args) throws IOException{
        int n=9, m=10;
        int[][] edge = {{0,1},{0,3},{3,4},{4,5},{5,6},{1,2},{2,6},{6,7},{7,8},{6,8}};
          
        Solution obj = new Solution();
        int res[] = obj.shortestPath(edge,n,m,0);
        for(int i=0;i<n;i++){
            System.out.print(res[i]+" ");
        }
        System.out.println();
    }
}

class ShortestPathUndirectedGraph {
    
    public int[] shortestPath(int[][] edges,int n,int m ,int src) {
    //Create an adjacency list of size N for storing the undirected graph.
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(); 
        for(int i = 0;i<n;i++) {
            adj.add(new ArrayList<>()); 
        }
        for(int i = 0;i<m;i++) {
            adj.get(edges[i][0]).add(edges[i][1]); 
            adj.get(edges[i][1]).add(edges[i][0]); 
        }
    //A dist array of size N initialised with a large number to 
    //indicate that initially all the nodes are untraversed. 
        int dist[] = new int[n];
        for(int i = 0;i<n;i++) dist[i] = (int)1e9;
        dist[src] = 0; 

    // BFS Implementation
        Queue<Integer> q = new LinkedList<>();
        q.add(src); 
        while(!q.isEmpty()) {
            int node = q.peek(); 
            q.remove(); 
            for(int it : adj.get(node)) {
                if(dist[node] + 1 < dist[it]) {
                    dist[it] = 1 + dist[node]; 
                    q.add(it); 
                }
            }
        }
        // Updated shortest distances are stored in the resultant array ‘ans’.
        // Unreachable nodes are marked as -1. 
        for(int i = 0;i<n;i++) {
            if(dist[i] == 1e9) {
                dist[i] = -1; 
            }
        }
        return dist; 
    }
}
