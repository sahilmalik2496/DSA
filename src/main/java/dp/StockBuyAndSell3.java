package dp;

/*
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/description/

You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete at most two transactions.

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).



Example 1:

Input: prices = [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
Example 2:

Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
Example 3:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.

This problem is about finding the maximum profit from at most two stock transactions (buy and sell). The key observation is that we can split the array into two subarrays:

First transaction (buy-sell before or at index
𝑖
i)
Second transaction (buy-sell after index
𝑖
i)
If we compute the best possible profit for these two segments separately, we can efficiently find the optimal solution.

Algorithm Explanation
We maintain two auxiliary arrays:

left[i]: Maximum profit achievable from the left subarray (0 to i).
right[i]: Maximum profit achievable from the right subarray (i to n-1).
Step 1: Compute left[]
left[i] stores the best profit we can make by selling at or before i.
We keep track of the minimum price (lmin) seen so far, and update the maximum profit at each step.
Step 2: Compute right[]
right[i] stores the best profit we can make by buying at or after i.
We keep track of the maximum price (rmax) seen so far, and update the maximum profit at each step.
Step 3: Find the Maximum Profit
Iterate over all indices and compute left[i-1] + right[i], which represents the best combination of two transactions split at index i.
Keep track of the maximum value.
Time Complexity
left[] computation:
O(n)
right[] computation:
O(n)
Final combination check:
O(n)
Total:
O(n) time complexity

 */
public class StockBuyAndSell3 {
    public int maxProfit(int[] prices) {
        int n = prices.length;

        int[] left = new int[n+1];
        int[] right = new int[n+1];

        int lmin = prices[0];
        int rmax = prices[n-1];

        for(int i= 1; i< n; i++) {
            left[i] = Math.max(left[i-1], prices[i]- lmin);
            lmin = Math.min(lmin, prices[i]);
        }

        for(int i= n-2; i>=0; i--) {
            right[i] = Math.max(right[i+1], rmax - prices[i]);
            rmax = Math.max(rmax, prices[i]);
        }

        int res = right[0];
        for(int i= 1; i< n; i++) {
            System.out.println(left[i-1] + " " + right[i]);
            res = Math.max(res, left[i-1] + right[i]);
        }
        return res;
    }
}
