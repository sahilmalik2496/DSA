package arrays;

import java.util.Arrays;

/*
You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.

Find the maximum profit you can achieve. You may complete at most k transactions: i.e. you may buy at most k times and
 sell at most k times.

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).



Example 1:

Input: k = 2, prices = [2,4,1]
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
Example 2:

Input: k = 2, prices = [3,2,6,5,0,3]
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0)
 and sell on day 6 (price = 3), profit = 3-0 = 3.
 */
public class BuySellStock4 {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n == 0 || k == 0) return 0;

        // dp[index][transactions_left][holding_stock]
        int[][][] dp = new int[n][k + 1][2];
        for (int[][] matrix : dp) {
            for (int[] row : matrix) {
                Arrays.fill(row, -1);
            }
        }

        return calculateMaxProfit(prices, 0, k, 0, dp);
    }

    private int calculateMaxProfit(int[] prices, int index, int k, int holding, int[][][] dp) {
        // Base cases: reached the end of prices or no transactions left
        if (index >= prices.length || k == 0) {
            return 0;
        }

        // Return cached result if already calculated
        if (dp[index][k][holding] != -1) {
            return dp[index][k][holding];
        }

        // Option 1: Skip the current day (do nothing)
        int profit = calculateMaxProfit(prices, index + 1, k, holding, dp);

        if (holding == 1) {
            // Option 2: Sell the stock (adds to profit, reduces remaining transactions)
            int sell = prices[index] + calculateMaxProfit(prices, index + 1, k - 1, 0, dp);
            profit = Math.max(profit, sell);
        } else {
            // Option 2: Buy the stock (subtracts from profit)
            int buy = -prices[index] + calculateMaxProfit(prices, index + 1, k, 1, dp);
            profit = Math.max(profit, buy);
        }

        return dp[index][k][holding] = profit;
    }
}
