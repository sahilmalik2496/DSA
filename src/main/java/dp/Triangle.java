package dp;

import java.util.Arrays;

/*
Given a triangle array, return the minimum path sum from top to bottom.
For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.
 
Example 1:
Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
Output: 11
Explanation: The triangle looks like:
   2
  3 4
 6 5 7
4 1 8 3
The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).


￼

 */
public class Triangle {
    // Recursive function with memoization
    static int minimumPathSumUtil(int i, int j, int[][] triangle, int n, int[][] dp) {
        // Check if already computed
        if (dp[i][j] != -1)
            return dp[i][j];

        // Base case: If at the bottom row
        if (i == n - 1)
            return triangle[i][j];

        // Compute paths: down and diagonal
        int down = triangle[i][j] + minimumPathSumUtil(i + 1, j, triangle, n, dp);
        int diagonal = triangle[i][j] + minimumPathSumUtil(i + 1, j + 1, triangle, n, dp);

        // Store and return the minimum path sum
        return dp[i][j] = Math.min(down, diagonal);
    }

    // Driver function
    static int minimumPathSum(int[][] triangle, int n) {
        // Initialize dp array with -1
        int dp[][] = new int[n][n];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        // Start from the top of the triangle
        return minimumPathSumUtil(0, 0, triangle, n, dp);
    }

    public static void main(String args[]) {
        // Example triangle
        int triangle[][] = {
                {1},
                {2, 3},
                {3, 6, 7},
                {8, 9, 6, 10}
        };

        int n = triangle.length;

        // Compute and print the minimum path sum
        System.out.println("Minimum Path Sum: " + minimumPathSum(triangle, n));
    }
}
