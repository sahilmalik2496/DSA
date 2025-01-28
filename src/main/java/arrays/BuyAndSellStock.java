package arrays;

/*
You are given an array prices where prices[i] is the price of a given stock on the ith day.
Find the maximum profit you can achieve. You may complete at most two transactions.
Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 
Example 1:
Input: prices = [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.

Intuition
1. One Transaction:
    * To compute the maximum profit with one transaction up to day i   i, we track:
        * The minimum price seen so far from the left.
        * The maximum profit up to that point.
    * Store this in the left array.
2. Another Transaction:
    * To compute the maximum profit with one transaction starting from day i   i, we track:
        * The maximum price seen so far from the right.
        * The maximum profit from that point onward.
    * Store this in the right array.
3. Combine Results:
    * The best two transactions are found by combining:
        * Maximum profit from the left subarray.
        * Maximum profit from the right subarray.

Compute

res=max(left[i−1]+right[i]) for all valid splits.
 */
public class BuyAndSellStock {
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
