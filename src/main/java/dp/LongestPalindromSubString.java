package dp;

/*
Given a string s, return the longest
palindromic

substring
 in s.



Example 1:

Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.
Example 2:

Input: s = "cbbd"
Output: "bb"
 */
public class LongestPalindromSubString {
        public String longestPalindrome(String s) {
            if (s.length() == 0) return s;

            int n = s.length();
            boolean[][] dp = new boolean[n][n]; // DP table
            int start = 0, maxLength = 1;

            // Every single character is a palindrome
            for (int i = 0; i < n; i++) {
                dp[i][i] = true;
            }

            // Check for palindromes of length 2
            for (int i = 0; i < n - 1; i++) {
                if (s.charAt(i) == s.charAt(i + 1)) {
                    dp[i][i + 1] = true;
                    start = i;
                    maxLength = 2;
                }
            }

            // Check for palindromes of length 3 or more
            for (int k = 3; k <= n; k++) { // k = length of substring
                for (int i = 0; i < n - k + 1; i++) {
                    int j = i + k - 1; // Ending index of substring

                    // Check if s[i:j] is a palindrome
                    if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                        dp[i][j] = true;

                        if (k > maxLength) {
                            start = i;
                            maxLength = k;
                        }
                    }
                }
            }

            return s.substring(start, start + maxLength);
        }

        public static void main(String[] args) {
            LongestPalindromSubString solution = new LongestPalindromSubString();
            String s = "babad";
            System.out.println("Longest Palindromic Substring: " + solution.longestPalindrome(s));
        }

        /*
        class Solution {
    private Boolean[][] dp; // Memoization table
    private int start = 0, maxLength = 1; // Store longest palindrome details

    public String longestPalindrome(String s) {
        int n = s.length();
        if (n == 0) return "";

        dp = new Boolean[n][n];  // Initialize memoization table

        // Recursively check all substrings and update longest palindrome
        for (int i = 0; i < n; i++) {  // Try all start positions
            for (int j = i; j < n; j++) {  // Try all end positions
                if (isPalindrome(s, i, j)) {
                    if (j - i + 1 > maxLength) {
                        maxLength = j - i + 1;
                        start = i;
                    }
                }
            }
        }
        return s.substring(start, start + maxLength);
    }

    private boolean isPalindrome(String s, int i, int j) {
        if (i >= j) return true;  // Base case: Single character or empty string
        if (dp[i][j] != null) return dp[i][j]; // Return if already computed

        if (s.charAt(i) == s.charAt(j) && isPalindrome(s, i + 1, j - 1)) {
            return dp[i][j] = true;
        }
        return dp[i][j] = false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "babad";
        System.out.println("Longest Palindromic Substring: " + solution.longestPalindrome(s));
    }
}

         */
    }

