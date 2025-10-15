package binarySearch;

public class MatrixMedianFinder {

    /*

    https://takeuforward.org/plus/dsa/problems/matrix-median


    Given a row-wise sorted matrix of size MXN, where M is no. of rows and N is no. of columns, find the median in the
    given matrix.

    Intuition
The problem is to find the median of a row-wise sorted matrix efficiently. A brute force approach would be to extract
all elements, sort them, and find the median, but that would take O(N × M log(N × M)) time, which is inefficient for
large matrices.

Instead, we use binary search on the value range (not indices) to efficiently determine the median:

Value Range for Binary Search
Since the matrix is sorted row-wise, the smallest element is in the first row's first column, and the largest element
is in the last row's last column.
However, instead of directly finding min and max, we start with a broad range:

Binary Search Process
We take the middle value (mid) and count how many numbers are ≤ mid in the matrix using binary search on each row.
If the count of numbers ≤ mid is ≤ half of total elements, it means the median is greater, so we increase low.
Otherwise, we decrease high to narrow the search.
Finding the Median
Since the median is the smallest number where at least half the elements are ≤ median, we return low after the binary
 search ends.
Time Complexity Analysis
Let's break it down:

Binary Search on Value Range
The value range is from 1 to 

 , so binary search takes

 ))=O(30)
Counting Elements ≤ mid
For each mid, we iterate over N rows and perform binary search on M elements in each row.
Each binary search in a row takes O(log M).
Total for one mid: O(N log M).
Final Complexity
Since the binary search runs for O(log 10^9) ≈ O(30) steps, the total time complexity is:

O(30×NlogM)≈O(NlogM)
Space Complexity
O(1) (No extra space used apart from variables)
Why is this Efficient?
Brute Force: Sorting all elements → O(NM log(NM))
Optimized Approach: O(N log M)
This is much faster, especially for large matrices (e.g., 
    */

    private static int countSmallerThanOrEqual(int[] array, int mid, int size) {
        int left = 0, right = size - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (array[middle] <= mid) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return left;
    }

    public static int findMedian(int[][] matrix, int rows, int cols) {
        int low = 1, high = 1_000_000_000;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int count = 0;
            for (int i = 0; i < rows; i++) {
                count += countSmallerThanOrEqual(matrix[i], mid, cols);
            }
            if (count <= (rows * cols) / 2) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }
    
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 3, 8},
            {2, 3, 4},
            {1, 2, 5}
        };
        System.out.println("The median of the row-wise sorted matrix is: " +
            findMedian(matrix, matrix.length, matrix[0].length));
    }
}
