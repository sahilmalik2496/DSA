package heaps;
import java.util.*;
/*

https://practice.geeksforgeeks.org/problems/merge-k-sorted-arrays/1

You have been given ‘K’ different arrays/lists, which are sorted individually (in ascending order). You need to merge
 all the given arrays/list such that the output array/list should be sorted in ascending order.
Input: K = 3, arr = { {1, 3, 5, 7}, {2, 4, 6, 8}, {0, 9, 10, 11}}
Output: 0 1 2 3 4 5 6 7 8 9 10 11


Input: k = 4, arr = { {1}, {2, 4}, {3, 7, 9, 11}, {13} }
Output: 1 2 3 4 7 9 11 13

Intuition
The problem requires merging K sorted arrays into a single sorted array efficiently. A naive approach would be:

Combine all K arrays into one list.
Sort the list.
Time Complexity:
O(NlogN), where
𝑁
N is the total number of elements across all arrays.
However, since each individual array is already sorted, we can efficiently merge them using a Min Heap (Priority Queue):

The heap helps us always extract the smallest available element from the arrays.
Instead of sorting everything at once, we incrementally pick the smallest element and add the next element from the same array.
Algorithm
Use a Min Heap (PriorityQueue)

Create a Triplet class that stores:
val: The value of the element.
x: The index of the array this element belongs to.
y: The index of this element within its array.
The heap stores one element from each array initially.
Initialize the Heap

Push the first element of each array into the heap.
The heap automatically keeps the smallest element on top.
Process Elements

Extract the smallest element from the heap (poll operation).
Add the extracted element to the result list.
Insert the next element from the same array into the heap (if it exists).
Repeat Until Heap is Empty

Continue extracting the minimum element and adding the next available element from the same array.
Time Complexity Analysis
Heap operations (insert and extract-min) take O(log K).
Since there are N total elements, each element is inserted and removed once.
Total Complexity:
O(NlogK), which is much more efficient than
O(NlogN).
Example Walkthrough
Input:
kArrays = [
    [1, 4, 7],
    [2, 5, 8],
    [3, 6, 9]
]
Execution Steps
Step	Heap Contents (MinHeap)	Result
Init	[1, 2, 3] (first elements from each array)	[]
1	[2, 3, 4] (add 4 from first array)	[1]
2	[3, 4, 5] (add 5 from second array)	[1, 2]
3	[4, 5, 6] (add 6 from third array)	[1, 2, 3]
4	[5, 6, 7] (add 7 from first array)	[1, 2, 3, 4]
5	[6, 7, 8] (add 8 from second array)	[1, 2, 3, 4, 5]
6	[7, 8, 9] (add 9 from third array)	[1, 2, 3, 4, 5, 6]
7	[8, 9]	[1, 2, 3, 4, 5, 6, 7]
8	[9]	[1, 2, 3, 4, 5, 6, 7, 8]
9	[] (heap empty)	[1, 2, 3, 4, 5, 6, 7, 8, 9]
Final Output
[1, 2, 3, 4, 5, 6, 7, 8, 9]

 */
public class MergeKSortedArrays {
    // Triplet class to store value and its position in the 2D list
    static class Triplet implements Comparable<Triplet> {
        int val, row, col;
        public Triplet(int val, int row, int col) {
            this.val = val;
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(Triplet other) {
            return Integer.compare(this.val, other.val);
        }
    }

    public static ArrayList<Integer> mergeKSortedArrays(ArrayList<ArrayList<Integer>> kArrays) {
        PriorityQueue<Triplet> minHeap = new PriorityQueue<>();
        ArrayList<Integer> result = new ArrayList<>();

        // Initialize heap with the first element of each array
        for (int i = 0; i < kArrays.size(); i++) {
            if (!kArrays.get(i).isEmpty()) {
                minHeap.add(new Triplet(kArrays.get(i).get(0), i, 0));
            }
        }

        // Process the heap
        while (!minHeap.isEmpty()) {
            Triplet current = minHeap.poll();
            result.add(current.val);

            // Move to the next element in the same row
            if (current.col + 1 < kArrays.get(current.row).size()) {
                minHeap.add(new Triplet(kArrays.get(current.row).get(current.col + 1), current.row, current.col + 1));
            }
        }
        return result;
    }

    // Main method for testing
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> kArrays = new ArrayList<>();
        kArrays.add(new ArrayList<>(Arrays.asList(1, 4, 7)));
        kArrays.add(new ArrayList<>(Arrays.asList(2, 5, 8)));
        kArrays.add(new ArrayList<>(Arrays.asList(3, 6, 9)));

        ArrayList<Integer> mergedList = mergeKSortedArrays(kArrays);
        System.out.println("Merged Sorted Array: " + mergedList);
    }
}
