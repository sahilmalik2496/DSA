package dp;

import java.util.Arrays;

/*

https://leetcode.com/problems/distinct-subsequences/description/


Given two strings s and t, return the number of distinct subsequences of s which equals t.
The test cases are generated so that the answer fits on a 32-bit signed integer.

Example 1:
Input: s = "rabbbit", t = "rabbit"
Output: 3
Explanation:
As shown below, there are 3 ways you can generate "rabbit" from s.
rabbbit
rabbbit
rabbbit
Example 2:
Input: s = "babgbag", t = "bag"
Output: 5
Explanation:
As shown below, there are 5 ways you can generate "bag" from s.
babgbag
babgbag
babgbag
babgbag
babgbag


Explanation
Key Observations
1. Matching Subsequences:
    * If the current characters of s1 and s2 match, we have two options:
        * Use the current character from s1 and move to the next character in s2.
        * Skip the current character in s1 and keep looking for matches.
    * Add these two possibilities.
2. Non-Matching Characters:
    * If the current characters do not match, skip the current character in s1 and continue looking.
3. Base Cases:
    * If s2 is exhausted (ind2<0), it means we have found a valid subsequence in s1, so return 1.
    * If  s1 is exhausted (ind1<0) but  s2 is not, there are no valid subsequences, so return 0
 */
public class DistinctSubsequence {
    static int prime = (int) (Math.pow(10, 9) + 7);

    // Recursive function with memoization
    static int countUtil(String s1, String s2, int ind1, int ind2, int[][] dp) {
        // Base cases
        if (ind2 < 0) return 1; // All of s2 is matched
        if (ind1 < 0) return 0; // s1 is exhausted, no match possible

        // Return already computed result
        if (dp[ind1][ind2] != -1) return dp[ind1][ind2];

        // Matching case
        if (s1.charAt(ind1) == s2.charAt(ind2)) {
            int leaveOne = countUtil(s1, s2, ind1 - 1, ind2 - 1, dp); // Match current character
            int stay = countUtil(s1, s2, ind1 - 1, ind2, dp);         // Skip current character
            dp[ind1][ind2] = (leaveOne + stay) % prime;
        } else {
            // Non-matching case
            dp[ind1][ind2] = countUtil(s1, s2, ind1 - 1, ind2, dp);
        }

        return dp[ind1][ind2];
    }

    // Driver function
    static int subsequenceCounting(String s1, String s2, int lt, int ls) {
        int dp[][] = new int[lt][ls];
        for (int rows[] : dp) Arrays.fill(rows, -1); // Initialize dp with -1
        return countUtil(s1, s2, lt - 1, ls - 1, dp);
    }

    public static void main(String args[]) {
        String s1 = "babgbag";
        String s2 = "bag";

        System.out.println("The Count of Distinct Subsequences is " +
                subsequenceCounting(s1, s2, s1.length(), s2.length()));
    }
}
