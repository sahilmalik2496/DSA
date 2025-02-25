package graph;

import java.util.*;
import java.lang.*;
import java.io.*;

/*
Dijkstra’s Algorithm - Using Priority Queue : G-32


72

2
Given a weighted, undirected, and connected graph of V vertices and an adjacency list adj where adj[i] is a list of lists containing two integers where the first integer of each list j denotes there is an edge between i and j, second integers corresponds to the weight of that edge. You are given the source vertex S and You have to Find the shortest distance of all the vertex from the source vertex S. You have to return a list of integers denoting the shortest distance between each node and the Source vertex S.

Note: The Graph doesn’t contain any negative weight cycle.

Examples:

Example 1:


Input:

V = 2

adj [] = {{{1, 9}}, {{0, 9}}}

S = 0

Output:

0 9

Explanation:

The source vertex is 0. Hence, the shortest distance of node 0 from the source is 0 and the shortest distance of node 1 from source will be 9.

Example 2:


Input:

V = 3, E = 3

adj = {{{1, 1}, {2, 6}}, {{2, 3}, {0, 1}}, {{1, 3}, {0, 6}}}

S = 2

Output:

4 3 0

Explanation:

For nodes 2 to 0, we can follow the path 2-1-0. This has a distance of 1+3 = 4, whereas the path 2-0 has a distance of 6. So, the Shortest path from 2 to 0 is 4.

The shortest distance from 0 to 1 is 1.


O( V * ( pop vertex from min heap + no. of edges on each vertex * push in PQ ))

O( V * ( log(heapSize) + no. of edges * log(heapSize))

O( V * (log(heapSize) + V-1 * log(heapSize))    { one vertex can have V-1 edges }

O( V * (log(heapSize) * (V-1+1))

O( V * V * log(heapSize))

Now, at the worst case the heapSize will be equivalent to v² as if we consider pushing adjacent elements of a node, at the worst case each element will have V-1 nodes and they will be pushed onto the queue.

O( V * V * log(v²))

O ( v² * 2 log (V))

O ( E * 2 log(V))  { E= v², total number of edges}

O ( E * log(V))  Worst case Time Complexity of Dijkstra’s Algorithm.

 */

public class DijkastraPQ {

        public static void main(String[] args) throws IOException {
            int V = 3, E=3,S=2;
            ArrayList<Integer> node1 = new ArrayList<Integer>() {{add(1);add(1);}};
            ArrayList<Integer> node2 = new ArrayList<Integer>() {{add(2);add(6);}};
            ArrayList<Integer> node3 = new ArrayList<Integer>() {{add(2);add(3);}};
            ArrayList<Integer> node4 = new ArrayList<Integer>() {{add(0);add(1);}};
            ArrayList<Integer> node5 = new ArrayList<Integer>() {{add(1);add(3);}};
            ArrayList<Integer> node6 = new ArrayList<Integer>() {{add(0);add(6);}};

            ArrayList<ArrayList<Integer>> inter1 = new ArrayList<ArrayList<Integer>>(){
                {
                    add(node1);
                    add(node2);
                }
            };
            ArrayList<ArrayList<Integer>> inter2 = new ArrayList<ArrayList<Integer>>(){
                {
                    add(node3);
                    add(node4);
                }
            };
            ArrayList<ArrayList<Integer>> inter3 = new ArrayList<ArrayList<Integer>>(){
                {
                    add(node5);
                    add(node6);
                }
            };
            ArrayList<ArrayList<ArrayList<Integer>>> adj= new ArrayList<ArrayList<ArrayList<Integer>>>(){
                {
                    add(inter1); // for 1st node
                    add(inter2); // for 2nd node
                    add(inter3); // for 3rd node
                }
            };
            //add final values of adj here.
            DijkastraPQ obj = new DijkastraPQ();
            int[] res= obj.dijkstra(V,adj,S);

            for(int i=0;i<V;i++){
                System.out.print(res[i]+" ");
            }
            System.out.println();

        }

    class Pair{
        int node;
        int distance;
        public Pair(int distance,int node){
            this.node = node;
            this.distance = distance;
        }
    }
        //Function to find the shortest distance of all the vertices
        //from the source vertex S.
        int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S) {
            // Create a priority queue for storing the nodes as a pair {dist, node
            // where dist is the distance from source to the node.
            PriorityQueue<Pair> pq =
                    new PriorityQueue<Pair>(Comparator.comparingInt(x -> x.distance));

            int[] dist = new int[V];

            // Initialising distTo list with a large number to
            // indicate the nodes are unvisited initially.
            // This list contains distance from source to the nodes.
            Arrays.fill(dist, (int) (1e9));

            // Source initialised with dist=0.
            dist[S] = 0;
            pq.add(new Pair(0, S));

            // Now, pop the minimum distance node first from the min-heap
            // and traverse for all its adjacent nodes.
            while (!pq.isEmpty()) {
                int dis = pq.peek().distance;
                int node = pq.peek().node;
                pq.remove();

                // Check for all adjacent nodes of the popped out
                // element whether the prev dist is larger than current or not.
                for (int i = 0; i < adj.get(node).size(); i++) {
                    int edgeWeight = adj.get(node).get(i).get(1);
                    int adjNode = adj.get(node).get(i).get(0);

                    // If current distance is smaller,
                    // push it into the queue.
                    if (dis + edgeWeight < dist[adjNode]) {
                        dist[adjNode] = dis + edgeWeight;
                        pq.add(new Pair(dist[adjNode], adjNode));
                    }
                }
            }
            // Return the list containing shortest distances
            // from source to all the nodes.
            return dist;

        }
}
