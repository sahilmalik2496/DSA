package dp;
/*
https://leetcode.com/problems/shortest-common-supersequence/
Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences.
If there are multiple valid strings, return any of them.
A string s is a subsequence of string t if deleting some number of characters from t (possibly 0)
results in the string s.

Example 1:
Input: str1 = "abac", str2 = "cab"
Output: "cabac"
Explanation:
str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
The answer provided is the shortest such string that satisfies these properties.
Example 2:
Input: str1 = "aaaaaaaa", str2 = "aaaaaaaa"
Output: "aaaaaaaa"


Key Concepts
1. Supersequence:
    * A supersequence of two strings s1 and  s2 includes all characters of both strings while maintaining their relative order.
    * Example:
        *  s1 = "abc", s2 = "ac"
        * Possible supersequence: " "abc","abac".
        * Shortest supersequence: " "abc".
2. Relation with Longest Common Subsequence (LCS):
    * The shortest common supersequence is formed by merging  s1 and s2 s2 while avoiding redundancy, which can be
    minimized using the LCS.
    * For strings  s1 and s2 the length of the SCS is:  SCS Length=Length of s1+Length of s2−Length of LCS
3. Reconstruction of the SCS:
    * Use the DP table for the LCS to trace back and construct the shortest common supersequence.
 */
public class ShortestCommonSupersequence {
    public String shortestCommonSupersequence(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        // Step 1: Initialize the DP table
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) dp[i][0] = 0;
        for (int i = 0; i <= m; i++) dp[0][i] = 0;

        // Step 2: Fill the DP table using LCS logic
        for (int ind1 = 1; ind1 <= n; ind1++) {
            for (int ind2 = 1; ind2 <= m; ind2++) {
                if (s1.charAt(ind1 - 1) == s2.charAt(ind2 - 1)) {
                    dp[ind1][ind2] = 1 + dp[ind1 - 1][ind2 - 1];
                } else {
                    dp[ind1][ind2] = Math.max(dp[ind1 - 1][ind2], dp[ind1][ind2 - 1]);
                }
            }
        }

        // Step 3: Reconstruct the SCS from the DP table
        StringBuilder ans = new StringBuilder();
        int i = n, j = m;

        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                ans.append(s1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                ans.append(s1.charAt(i - 1));
                i--;
            } else {
                ans.append(s2.charAt(j - 1));
                j--;
            }
        }

        // Add remaining characters from either string
        while (i > 0) {
            ans.append(s1.charAt(i - 1));
            i--;
        }
        while (j > 0) {
            ans.append(s2.charAt(j - 1));
            j--;
        }

        // Step 4: Reverse the constructed string to get the final result
        return ans.reverse().toString();
    }
}
