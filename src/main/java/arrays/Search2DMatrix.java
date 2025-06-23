package arrays;

/*

https://leetcode.com/problems/search-a-2d-matrix/description/

You are given an m x n integer matrix matrix with the following two properties:
* Each row is sorted in non-decreasing order.
* The first integer of each row is greater than the last integer of the previous row.
Given an integer target, return true if target is in matrix or false otherwise.
You must write a solution in O(log(m * n)) time complexity.

Key Observations
1. Matrix Properties:
    * Each row is sorted, and elements in a column are also sorted (from top to bottom).
    * This gives a natural stair-step pattern when traversing the matrix.
2. Optimal Starting Point:
    * Start at the top-right corner of the matrix (matrix[0][m-1]):
        * If the value is greater than the target, move left (since all elements to the right are larger).
        * If the value is smaller than the target, move down (since all elements below are larger).
3. Binary Search-Like Behavior:
    * By reducing the search space either row-wise or column-wise, this approach achieves  O(n+m) complexity, where n  n is the number of rows and m  m is the number of columns.

 */
public class Search2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length, m = matrix[0].length;

        int i =0, j = m-1;

        while(i< n && j>= 0) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }

        return false;
    }
}
