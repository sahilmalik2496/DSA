package dp;

//Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.
//        Note: You can only move either down or right at any point in time.
//        Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
//Output: 7
//Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
//The problem can be solved using Dynamic Programming (DP). The idea is to calculate the minimum sum for each cell in the grid, building up from the top-left to the bottom-right.
//1. Recursive Relation:
//        • To reach a cell (i, j, you can come either from the left (i, j - 1) or from the top (i -
//        1, 5).
//        • The minimum sum to reach (i, j) is:
//dpiLl - grid|b] + min(dple - 1]bl, dp/ll - 1J)
//2. Base Cases:
//        • The top-left cell (dp(0)[°|) is the starting point:
//dp이이 = grid[이이
//• For the first row (i = 0), you can only come from the left:
//dp[0][i] = dp[0]Lj - 1] + grid[0][]
//        • For the first column (j= 0), you can only come from above:
//dp[i][0] = ple - 11[0] + grid|a1[0]
//        3. Dynamic Programming Table:
//        • Use a 2D DP table to store the minimum sum to reach each cell.
//• Alternatively, optimize space by modifying the input grid in place.

public class MinimumPathSum {
    int recur(int[][] grid, int[][] dp, int i, int j) {
        if (i ==0 && j == 0) {
            return grid[i][j];
        }
        if (i< 0 || j < 0 ){
            return Integer.MAX_VALUE;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        return dp[i][j] = grid[i][j] + Math.min(recur(grid, dp, i-1, j), recur(grid, dp, i, j-1));

    }

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;


        int[][] dp = new int[m][n];
        for(int i=0; i< m; i++) {
            for(int j=0; j< n; j++) {
                dp[i][j] = -1;
            }
        }
        return recur(grid, dp, m-1, n-1);
    }
}
