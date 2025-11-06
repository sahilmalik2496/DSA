package dp;
//

//https://leetcode.com/problems/coin-change-ii/description/
//You are given an integer array coins representing coins of different denominations and an integer amount representing
// a total amount of money.
//Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination
// of the coins, return 0.
//You may assume that you have an infinite number of each kind of coin.
//The answer is guaranteed to fit into a signed 32-bit integer.
//
//Example 1:
//Input: amount = 5, coins = [1,2,5]
//Output: 4
//Explanation: there are four ways to make up the amount:
//        5=5
//        5=2+2+1
//        5=2+1+1+1
//        5=1+1+1+1+1
//
//
//Choices for Each Coin:
//        * For each coin, we have two choices:
//        * Include the coin: Subtract the coin's value from the amount and continue with the same coin (since we can use it multiple times).
//        * Exclude the coin: Move to the next coin.
//Base Cases:
//        * If amount == 0, there is exactly 1 way (an empty combination).
//        * If n == 0 (no coins left) and amount > 0, there are 0 ways to form the amount.
//Recursive Relation:
//        * If the current coin's value is less than or equal to the remaining amount:  dp[n][amount]=Include Current Coin+Exclude Current Coin
//        * Otherwise: dp[n][amount]=Exclude Current Coin
//Memoization:
//        * Store the result of each subproblem dp[n][amount] to avoid redundant computations.


import java.util.Arrays;

class CoinChange2 {

    int recur(int[] coins, int[][] dp, int amount, int n) {
        if (amount == 0) {
            return 1;
        }
        if (n == 0) {
            return 0;
        }

        if (dp[n][amount] != -1) {
            return dp[n][amount];
        }

        if (coins[n-1] <= amount) {
            return dp[n][amount] = recur(coins, dp, amount - coins[n-1], n) + recur(coins, dp, amount, n-1);
        } else {
            return recur(coins, dp, amount, n-1);
        }
    }

    public int change(int amount, int[] coins) {
        int n = coins.length;

        int[][] dp = new int[n+1][amount+1];

        for(int[] row: dp) {
            Arrays.fill(row, -1);
        }

        return recur(coins, dp, amount, n);
    }
}