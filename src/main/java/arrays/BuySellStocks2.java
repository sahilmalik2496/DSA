package arrays;

/*
You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. However, you can buy it then immediately sell it on the same day.
Find and return the maximum profit you can achieve.

Input: prices = [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
Total profit is 4 + 3 = 7.

The key observation is that profit is only made when the stock price rises. For every price increase between two consecutive days, the difference between those prices is added to the total profit.

If prices[i]>prices[i-1]prices[i]>prices[i-1], it means a profit can be made by buying on day i−1 and selling on day i. The profit is prices[i] − prices[i-1]
prices[i]−prices[i-1].

Instead of explicitly keeping track of transactions, the algorithm simply adds up these "positive differences" to compute the total profit.
The algorithm assumes that you can complete multiple transactions without restrictions (e.g., no cooldown or transaction fee).

 */
public class BuySellStocks2 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int res = 0, curr = prices[0];
        for (int i = 1; i < n; i++) {
            if (prices[i] >= curr) {
                res += prices[i] - curr; // Add the profit
            }
            curr = prices[i]; // Update current price
        }
        return res; // Total profit
    }

}
