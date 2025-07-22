package dp;

import java.util.Arrays;

// https://leetcode.com/problems/coin-change/description/
//You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
//Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
//You may assume that you have an infinite number of each kind of coin.
//
//
//Intuition
//The problem is to find the minimum number of coins needed to make up a given amount using a set of denominations (coins). If it's not possible to make the amount, return -1.
//This is a classic Dynamic Programming (DP) problem, where:
//        1. We aim to minimize the number of coins required to achieve the target amount.
//        2. We solve the problem using top-down recursion with memoization to avoid redundant calculations.
//
//Input: coins = [1,2,5], amount = 11
//Output: 3
//Explanation: 11 = 5 + 5 + 1
//
//
//Key Observations
//1. Recursive Relation:
//        * For each coin denomination, you have two choices:
//        * Take the coin: Reduce the target by the coin's value and increment the count.
//        * Don't take the coin: Move to the next coin.
//        * The result is the minimum of these two choices.
//2. Mathematically: dp[idx][t]=min(notTaken,taken)
//3. Base Cases:
//        * If you're at the smallest denomination (idx == 0):
//        * If the target is divisible by the coin, return the quotient (t / coins[0]).
//        * Otherwise, return Integer.MAX_VALUE (indicating it's not possible to form the amount with this coin).
//        4. Memoization:
//        * To avoid recalculating the result for the same (idx, t), store it in a DP table (dp[idx][t]).
//        5. Edge Cases:
//        * If the amount is 0, no coins are needed → return 0.
//        * If no combination of coins can form the target, return -1.


class CoinChange {

    int recur(int[] coins, int idx, int t, int[][] dp){
        if (idx == 0){
            if (t % coins[idx] == 0){
                return t / coins[idx];
            }
            return Integer.MAX_VALUE;
        }

        if (dp[idx][t] != -1) {
            return dp[idx][t];
        }

        int notTaken = recur(coins, idx-1, t, dp);
        int taken = Integer.MAX_VALUE;
        if (coins[idx] <= t){
            int count = recur(coins, idx, t - coins[idx], dp);
            if (count != Integer.MAX_VALUE) {
                taken = count+1;
            }
        }

        dp[idx][t] = Math.min(taken , notTaken);
        return dp[idx][t];
    }

    public int coinChange(int[] coins, int amount) {
        int dp[][] = new int[coins.length][amount+1];
        for(int[] row: dp){
            Arrays.fill(row, -1);
        }

        int result = recur(coins, coins.length-1, amount, dp);

        return result == Integer.MAX_VALUE ? -1 : result;
    }
}