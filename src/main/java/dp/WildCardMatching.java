package dp;
/*

https://leetcode.com/problems/wildcard-matching/description/

Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).



Example 1:

Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input: s = "aa", p = "*"
Output: true
Explanation: '*' matches any sequence.
Example 3:

Input: s = "cb", p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.



The problem involves matching two strings, S1 (the pattern) and S2 (the text), where:

? in S1 can match any single character.
* in S1 can match zero or more characters.
We use Dynamic Programming (DP) to solve this, as it allows breaking down the problem into smaller subproblems and avoiding redundant calculations. The key idea is to check for matches between substrings of S1 and S2 using recursion, with the following rules:

If characters are equal or S1 has a ?, they match, and we move to the next characters in both strings.
If S1 has a *, it can match:
Zero characters (ignore the * and move to the next character in S1).
One or more characters (consume a character in S2 and keep the * in S1 for further matching).
If characters do not match and there is no wildcard, the result is false for that state.
Algorithm
Base Cases:

Both S1 and S2 are empty (i < 0 and j < 0): Return 1 (true).
S1 is empty but S2 is not (i < 0 and j >= 0): Return 0 (false).
S2 is empty but S1 is not (j < 0 and i >= 0): Check if the remaining characters in S1 are all *. If yes, return 1; otherwise, return 0.
Recursive Matching:

If the characters match or S1[i] == '?', recurse to check the remaining substrings (i-1, j-1).
If S1[i] == '*', explore two options:
Skip the * in S1 and move to the next character (i-1, j).
Match the * with one or more characters in S2 (i, j-1).
Memoization:

Use a DP table dp[i][j] to store the result of matching the first i+1 characters of S1 with the first j+1 characters of S2.
Initialize DP Table:

Initialize the DP table with -1 to indicate uncomputed states.
Return Result:

Start from the full lengths of both strings (n-1, m-1) and compute using the recursive helper function.

 */
import java.util.*;

class WildCardMatching {
    // Helper function to check if all characters in S1[0...i] are '*'
    static boolean isAllStars(String S1, int i) {
        for (int j = 0; j <= i; j++) {
            if (S1.charAt(j) != '*')
                return false;
        }
        return true;
    }

    // Recursive function with memoization
    static int wildcardMatchingUtil(String S1, String S2, int i, int j, int[][] dp) {
        // Base Cases
        if (i < 0 && j < 0)
            return 1; // Both strings are empty
        if (i < 0 && j >= 0)
            return 0; // Pattern is empty, but S2 is not
        if (j < 0 && i >= 0)
            return isAllStars(S1, i) ? 1 : 0; // Check if remaining pattern is all '*'

        // If already computed, return the stored value
        if (dp[i][j] != -1)
            return dp[i][j];

        // Case 1: Characters match or '?' wildcard
        if (S1.charAt(i) == S2.charAt(j) || S1.charAt(i) == '?') {
            return dp[i][j] = wildcardMatchingUtil(S1, S2, i - 1, j - 1, dp);
        }

        // Case 2: '*' wildcard
        if (S1.charAt(i) == '*') {
            return dp[i][j] = (wildcardMatchingUtil(S1, S2, i - 1, j, dp) == 1 || // Match zero characters
                    wildcardMatchingUtil(S1, S2, i, j - 1, dp) == 1) ? 1 : 0; // Match one or more characters
        }

        // Case 3: Characters don't match
        return dp[i][j] = 0;
    }

    // Main function to start the matching process
    static int wildcardMatching(String S1, String S2) {
        int n = S1.length();
        int m = S2.length();

        // Initialize DP table
        int[][] dp = new int[n][m];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        // Call the recursive function
        return wildcardMatchingUtil(S1, S2, n - 1, m - 1, dp);
    }

    public static void main(String[] args) {
        String S1 = "ab*cd";
        String S2 = "abdefcd";

        if (wildcardMatching(S1, S2) == 1)
            System.out.println("String S1 and S2 do match");
        else
            System.out.println("String S1 and S2 do not match");
    }
}
