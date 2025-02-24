package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        int n= prerequisites.length;
        for(int i=0; i< numCourses; i++) {
            adj.add(new ArrayList<>());
        }


        int ind[] = new int[numCourses];
        for(int i=0; i< n; i++) {
            adj.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }

        for(int i=0; i< numCourses; i++) {
            System.out.println(i);
            for(int it: adj.get(i)) {
                ind[it]++;
                System.out.print(it + " ");
                System.out.println(ind[it]);
            }
        }

        Queue<Integer> q = new LinkedList<>();

        for(int i=0; i< numCourses; i++) {
            if (ind[i] == 0) {
                q.add(i);
            }
        }

        int top[] = new int[numCourses];
        int count =0;
        while(!q.isEmpty()) {
            int node = q.poll();
            top[count++] = node;

            for(int it: adj.get(node)) {
                ind[it]--;
                if(ind[it] == 0) {
                    q.offer(it);
                }
            }
        }

        if (count == numCourses) {
            return top;
        }

        return new int[0];
    }
}
*/

public class TopologicalSort {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

            int n= prerequisites.length;
            for(int i=0; i< numCourses; i++) {
                adj.add(new ArrayList<>());
            }


            int ind[] = new int[numCourses];
            for(int i=0; i< n; i++) {
                adj.get(prerequisites[i][1]).add(prerequisites[i][0]);
            }

            for(int i=0; i< numCourses; i++) {
                for(int it: adj.get(i)) {
                    ind[it]++;
                }
            }

            Queue<Integer> q = new LinkedList<>();

            for(int i=0; i< numCourses; i++) {
                if (ind[i] == 0) {
                    q.add(i);
                }
            }

            int top[] = new int[numCourses];
            int count =0;
            while(!q.isEmpty()) {
                int node = q.poll();
                top[count++] = node;

                for(int it: adj.get(node)) {
                    ind[it]--;
                    if(ind[it] == 0) {
                        q.offer(it);
                    }
                }
            }

            if (count == numCourses) {
                return true;
            }

            return false;
        }

    public static void main(String[] args) {
        TopologicalSort solution = new TopologicalSort();

        // Example 1: Possible to finish all courses
        int numCourses1 = 4;
        int[][] prerequisites1 = {{1, 0}, {2, 1}, {3, 2}};
        System.out.println("Can finish courses? " + solution.canFinish(numCourses1, prerequisites1)); // Output: true

        // Example 2: Not possible to finish due to cycle
        int numCourses2 = 3;
        int[][] prerequisites2 = {{0, 1}, {1, 2}, {2, 0}};
        System.out.println("Can finish courses? " + solution.canFinish(numCourses2, prerequisites2)); // Output: false

        // Example 3: No prerequisites (all courses can be taken freely)
        int numCourses3 = 5;
        int[][] prerequisites3 = {};
        System.out.println("Can finish courses? " + solution.canFinish(numCourses3, prerequisites3)); // Output: true
    }

}
