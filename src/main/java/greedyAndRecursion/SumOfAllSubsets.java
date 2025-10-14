package greedyAndRecursion;


/*
Sum of all Subsets

110

2
Problem Statement: Given an array print all the sum of the subset generated from it, in the increasing order.

Examples:

Example 1:

Input: N = 3, arr[] = {5,2,1}

Output: 0,1,2,3,5,6,7,8

Explanation: We have to find all the subset’s sum and print them.in this case the generated subsets are
 [ [], [1], [2], [2,1], [5], [5,1], [5,2]. [5,2,1],so the sums we get will be  0,1,2,3,5,6,7,8
*/

import java.util.*;

public class SumOfAllSubsets {
    static void subsetSumsHelper(int index, int currentSum, List<Integer> arr, PriorityQueue<Integer> minHeap) {
        if (index == arr.size()) {
            minHeap.add(currentSum); // Add sum directly into a min-heap
            return;
        }

        // Include the current element
        subsetSumsHelper(index + 1, currentSum + arr.get(index), arr, minHeap);

        // Exclude the current element
        subsetSumsHelper(index + 1, currentSum, arr, minHeap);
    }

    static List<Integer> subsetSums(List<Integer> arr) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        subsetSumsHelper(0, 0, arr, minHeap);

        List<Integer> sortedSubsetSums = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            sortedSubsetSums.add(minHeap.poll()); // Retrieve elements in sorted order
        }
        return sortedSubsetSums;
    }

    public static void main(String[] args) {
        List<Integer> arr = Arrays.asList(3, 1, 2);
        List<Integer> result = subsetSums(arr);
        System.out.println("Subset sums: " + result);
    }
}
