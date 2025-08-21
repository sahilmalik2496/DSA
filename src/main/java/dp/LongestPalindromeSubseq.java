package dp;

/*
Given a string s, find the longest palindromic subsequence's length in s.
A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.
 
Example 1:
Input: s = "bbbab"
Output: 4
Explanation: One possible longest palindromic subsequence is "bbbb".
Example 2:
Input: s = "cbbd"
Output: 2
Explanation: One possible longest palindromic subsequence is "bb".


Intuition
1. Palindrome Concept:
    * A palindrome is a string that reads the same forward and backward. For example, "abcba" and "aabaa" are palindromes.
2. Reverse String Insight:
    * The problem of finding the longest palindromic subsequence can be reduced to finding the Longest Common Subsequence (LCS) between the string and its reverse. This is because a palindrome remains the same when reversed.

 */
public class LongestPalindromeSubseq {
    public int longestPalindromeSubseq(String s) {
        String reversed = new StringBuilder(s).reverse().toString();
        int n = s.length();
        int[][] dp = new int[n + 1][n + 1];

        // Build the DP table for LCS of s and its reverse
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == reversed.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[n][n]; // Length of the longest palindromic subsequence
    }

    /*
     private int longestPalindromeSubseqRecursive(String s, int left, int right) {
        // Base case: If the pointers cross, the subsequence is empty.
        if (left > right) {
            return 0;
        }
        if (left == right) {
            return 1;
        }

        // Check if the result for this subproblem is already computed.
        if (memo[left][right] != -1) {
            return memo[left][right];
        }

        int result;
        if (s.charAt(left) == s.charAt(right)) {
            result = 2 + longestPalindromeSubseqRecursive(s, left + 1, right - 1);
        } else {
            int excludeLeft = longestPalindromeSubseqRecursive(s, left + 1, right);
            int excludeRight = longestPalindromeSubseqRecursive(s, left, right - 1);
            result = Math.max(excludeLeft, excludeRight);
        }

        // Store the computed result in the memoization table before returning.
        memo[left][right] = result;
        return result;
    }
     */
}
