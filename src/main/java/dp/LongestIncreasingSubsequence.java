package dp;

import java.util.Arrays;

/*

https://leetcode.com/problems/longest-increasing-subsequence/

The problem is to find the length of the longest increasing subsequence (LIS) in an array.
A subsequence is a sequence derived by deleting some or no elements without changing the
order of the remaining elements.

For example, in:

css
Copy
Edit
arr = [10, 9, 2, 5, 3, 7, 101, 18]
The LIS is [2, 3, 7, 18] of length 4.

We use recursion with memoization (top-down dynamic programming) to efficiently solve this.

Recursive Approach
The function getAns(arr, n, ind, prev_index, dp) determines the LIS length by:

Deciding not to include arr[ind] in the subsequence.
Including arr[ind] only if it is greater than arr[prev_index].
Key Decisions at ind:
Case 1 (Not taking arr[ind]): Move to the next index.
Case 2 (Taking arr[ind] if valid): If arr[ind] is greater than arr[prev_index], include it and move forward.
Base Case:
If ind == n, we have reached the end, so return 0.
Memoization:
Since prev_index can be -1, we offset it by +1 in the dp array to handle negative indices.
Algorithm
Create a DP array dp[n][n+1] initialized to -1.
Recursive function getAns(arr, n, ind, prev_index, dp):
Base case: If ind == n, return 0.
If dp[ind][prev_index + 1] is already computed, return it.
Compute notTake (skip arr[ind]).
Compute take (include arr[ind] only if it forms an increasing sequence).
Store the maximum of these two choices in dp[ind][prev_index + 1].
Return the result of getAns(arr, n, 0, -1, dp).
Time & Space Complexity
Time Complexity:
O(n^2)
There are n states (ind from 0 to n-1).
prev_index ranges from -1 to n-1 (tracked in dp as 0 to n).
Hence, O(n^2) states, each taking O(1) computation.
Space Complexity:
O(n^2) (DP array) +
O(n) (Recursion Stack)
Can be reduced to O(n^2) using bottom-up DP or O(n log n) using binary search + DP.

 */
public class LongestIncreasingSubsequence {
    // Function to find the length of the longest increasing subsequence
    static int getAns(int arr[], int n, int ind, int prev_index, int[][] dp) {
        // Base condition
        if (ind == n) {
            return 0;
        }

        if (dp[ind][prev_index + 1] != -1) {
            return dp[ind][prev_index + 1];
        }

        int notTake = getAns(arr, n, ind + 1, prev_index, dp);

        int take = 0;

        if (prev_index == -1 || arr[ind] > arr[prev_index]) {
            take = 1 + getAns(arr, n, ind + 1, ind, dp);
        }

        dp[ind][prev_index + 1] = Math.max(notTake, take);

        return dp[ind][prev_index + 1];
    }

    // Function to find the length of the longest increasing subsequence
    static int longestIncreasingSubsequence(int arr[], int n) {
        int dp[][] = new int[n][n + 1];

        // Initialize dp array with -1 to mark states as not calculated yet
        for (int row[] : dp) {
            Arrays.fill(row, -1);
        }

        return getAns(arr, n, 0, -1, dp);
    }

    public static void main(String args[]) {
        int arr[] = {10, 9, 2, 5, 3, 7, 101, 18};

        int n = arr.length;

        System.out.println("The length of the longest increasing subsequence is " + longestIncreasingSubsequence(arr, n));
    }
}
