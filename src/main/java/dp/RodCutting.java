package dp;

import java.util.Arrays;

/*
 Given an integer array cuts where cuts[i] denotes a position you should perform a cut at.
You should perform the cuts in order, you can change the order of the cuts as you wish.
The cost of one cut is the length of the stick to be cut, the total cost is the sum of costs of all cuts. When you cut a stick, it will be split into two smaller sticks (i.e. the sum of their lengths is the length of the stick before the cut). Please refer to the first example for a better explanation.
Return the minimum total cost of the cuts.

Input: n = 7, cuts = [1,3,4,5]
Output: 16
Explanation: Using cuts order = [1, 3, 4, 5] as in the input leads to the following scenario:

The first cut is done to a rod of length 7 so the cost is 7. The second cut is done to a rod of length 6 (i.e. the second part of the first cut), the third is done to a rod of length 4 and the last cut is to a rod of length 3. The total cost is 7 + 6 + 4 + 3 = 20.
Rearranging the cuts to be [3, 5, 1, 4] for example will lead to a scenario with total cost = 16 (as shown in the example photo 7 + 4 + 3 + 2 = 16).



Problem: Minimum Cost to Cut a Stick
The task is to find the minimum cost to cut a stick of length
n into smaller pieces at specific positions given in the cuts array. Each cut has a cost equal to the length of the segment being cut. The objective is to determine the optimal order of cuts to minimize the total cost.

Intuition
This is a classic Dynamic Programming (DP) problem where:
1. We recursively calculate the cost of cutting the stick at different positions and combine the results for optimal solutions.
2. The problem is analogous to the Matrix Chain Multiplication or Optimal Binary Search Tree problems, where the order of operations impacts the result.

Key Observations
1. Cut Cost Calculation:
• The cost of cutting at a position depends on the length of the current segment being cut:
Cost of cut = length of segment = list lj + 1] - list[i - 1]
2. Recursive Relation:
• For a segment from index i to j in the sorted cuts list:
• Try making a cut at every position id in the range [i, il.
• Combine the cost of the current cut with the optimal solutions for the two resulting subproblems (left and right segments):
dp(a) il = min (Cost of current cut + dp(i idx - 1] + dp idx + 1]])
3. Base Case:
• If i > j, no cuts are needed → cost = 0.
4. Sorting Cuts:
• To simplify the recursive logic, include the boundaries (0 and n) in the cuts array and sort it. This ensures every subproblem considers the exact segment being processed.
 */
public class RodCutting {
    int recur(int[] list, int[][] dp, int i, int j) {
        // Base case: no cuts needed
        if (i > j) {
            return 0;
        }

        // Check memoization table
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int min = Integer.MAX_VALUE;

        // Try every possible cut in the range [i, j]
        for (int idx = i; idx <= j; idx++) {
            int cost = (list[j + 1] - list[i - 1]) // Cost of current cut
                    + recur(list, dp, i, idx - 1) // Left subsegment
                    + recur(list, dp, idx + 1, j); // Right subsegment

            min = Math.min(min, cost); // Update minimum cost
        }

        // Store the result in the DP table
        return dp[i][j] = min;
    }

    public int minCost(int n, int[] cuts) {
        int m = cuts.length;
        int[][] dp = new int[m + 1][m + 1];

        // Initialize DP table with -1
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        // Create the sorted list of cuts with boundaries
        int[] list = new int[m + 2];
        for (int i = 0; i < cuts.length; i++) {
            list[i + 1] = cuts[i];
        }
        list[0] = 0;
        list[list.length - 1] = n;
        Arrays.sort(list);

        // Start recursion from the first and last cut
        return recur(list, dp, 1, m);
    }
}
