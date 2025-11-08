package dp;

//Given the dimension of a sequence of matrices in an array arr[], where the dimension of the ith matrix is (arr[i-1] * arr[i]),
// the task is to find the most efficient way to multiply these matrices together such that the total number of element
// multiplications is minimum. When two matrices of size m*n and n*p when multiplied, they generate a matrix of size m*p
// and the number of multiplications performed is m*n*p.
//        Examples:
//Input: arr[] = {2, 1, 3, 4} Output: 20 Explanation: There are 3 matrices of dimensions 2×1, 1×3, and 3×4,  Let the input 3 matrices be M1, M2, and M3. There are two ways to multiply ((M1 x M2) x M3) and (M1 x (M2 x M3)),  Please note that the result of M1 x M2 is a 2 x 3 matrix and result of (M2 x M3) is a 1 x 4 matrix. ((M1 x M2) x M3)  requires (2 x 1 x 3)  + (0) +  (2 x 3 x 4) = 30  (M1 x (M2 x M3))  requires (0)  + (1 x 3 x 4) +  (2 x 1 x 4) = 20  The minimum of these two is 20.
//
//Intuition
//The Matrix Chain Multiplication (MCM) problem is to determine the minimum number of scalar multiplications needed to multiply a chain of matrices. The goal is to find the most efficient way to parenthesize the matrices for multiplication.
//1. Matrix Representation:
//        * If we have  N matrices, represented as dimensions in an array arr[] such that the dimensions of the  i-th matrix are  (arr[i−1]×arr[i]), then arr = [10, 20, 30] arr=[10,20,30] represents matrices  A(10×20) and  B(20×30).
//        2. Recursive Relation:
//        * Divide the chain into two parts at every possible k
//    *
//            * Left part: i to k
//    * Right part: k+1 to j
//    * Compute the cost for each split and add the cost of multiplying the two resulting matrices:  Cost=(arr[i−1]×arr[k]×arr[j])+Left Cost+Right Cost
//3. Base Case:
//        * If  i==j, there is only one matrix, and no multiplication is needed ( 0).
//        4. Dynamic Programming:
//        * Use a 2D DP table to store the results of subproblems (d dp[i][j]), avoiding redundant calculations.


import java.util.Arrays;

public class MatrixChain {
    static int recur(int arr[], int i, int j, int dp[][]) {
        // Base case: single matrix
        if (i == j) {
            return 0;
        }

        // Check memoization table
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int ans = Integer.MAX_VALUE;

        // Try all possible splits
        for (int k = i; k < j; k++) {
            int cost = arr[i - 1] * arr[k] * arr[j]
                    + recur(arr, i, k, dp)
                    + recur(arr, k + 1, j, dp);
            ans = Math.min(ans, cost); // Update minimum cost
        }

        dp[i][j] = ans; // Store result in DP table
        return ans;
    }

    static int matrixMultiplication(int N, int arr[]) {
        int dp[][] = new int[N][N];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return recur(arr, 1, N - 1, dp);
    }


}
