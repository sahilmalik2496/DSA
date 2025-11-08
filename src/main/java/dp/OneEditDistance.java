package dp;

/*


You are given two strings, s and t. Write a function to determine if s and t are one edit distance apart. If s and t differ
by exactly one operation (insertion, deletion, or replacement), return true; otherwise, return false.

Intuition
To be one edit distance apart:
1. If s and t are of the same length, they must differ by exactly one character (Replacement).
2. If the lengths differ by 1, one string must be obtained by adding or removing a single character from the other (Insertion/Deletion).
3. If the lengths differ by more than 1, they cannot be one edit distance apart.

Key Idea
We use recursion to compare the two strings, character by character:
1. If the characters are the same, move both pointers forward.
2. If the characters are different, try all possible operations:
    * Insert: Skip a character in the second string.
    * Delete: Skip a character in the first string.
    * Replace: Replace the current character in the first string with the one in the second string.
3. Use a memoization table to store results of subproblems (index1, index2, edits).
The recursion stops if:
* No edits are allowed (edits == 0) and the strings differ.
* One of the strings is exhausted.

 */

class OneEditDistance {
    public boolean isOneEditDistance(String s, String t) {
        int len1 = s.length();
        int len2 = t.length();
        int[][][] dp = new int[len1 + 1][len2 + 1][2]; // DP table for memoization
        for (int[][] d1 : dp) {
            for (int[] d2 : d1) {
                d2[0] = d2[1] = -1; // Initialize with -1 (uncomputed)
            }
        }
        return solve(0, 0, 1, s, t, dp);
    }

    private boolean solve(int i, int j, int edits, String s, String t, int[][][] dp) {
        if (i == s.length() && j == t.length()) {
            return edits == 0; // If both strings are exhausted, edits must be 0
        }
        if (i == s.length() || j == t.length()) {
            // If one string is exhausted, the other string must have exactly one remaining character for one edit
            return edits == 1 && Math.abs(s.length() - i - (t.length() - j)) == 1;
        }

        // If already computed
        if (dp[i][j][edits] != -1) {
            return dp[i][j][edits] == 1;
        }

        if (s.charAt(i) == t.charAt(j)) {
            // Characters match, move both pointers
            dp[i][j][edits] = solve(i + 1, j + 1, edits, s, t, dp) ? 1 : 0;
        } else {
            if (edits == 0) {
                dp[i][j][edits] = 0; // No edits left, return false
            } else {
                // Try all three possible operations
                boolean insert = solve(i, j + 1, edits - 1, s, t, dp); // Insert in `s`
                boolean delete = solve(i + 1, j, edits - 1, s, t, dp); // Delete from `s`
                boolean replace = solve(i + 1, j + 1, edits - 1, s, t, dp); // Replace
                dp[i][j][edits] = (insert || delete || replace) ? 1 : 0;
            }
        }
        return dp[i][j][edits] == 1;
    }

    public static void main(String[] args) {
        OneEditDistance oneEditDistance = new OneEditDistance();

        // Test cases
        String s1 = "abc";
        String t1 = "ab";
        String s2 = "abc";
        String t2 = "adc";
        String s3 = "abc";
        String t3 = "abcd";
        String s4 = "abc";
        String t4 = "xyz";

        // Check if strings are one edit distance apart
        System.out.println("Is \"" + s1 + "\" and \"" + t1 + "\" one edit distance apart? " + oneEditDistance.isOneEditDistance(s1, t1));
        System.out.println("Is \"" + s2 + "\" and \"" + t2 + "\" one edit distance apart? " + oneEditDistance.isOneEditDistance(s2, t2));
        System.out.println("Is \"" + s3 + "\" and \"" + t3 + "\" one edit distance apart? " + oneEditDistance.isOneEditDistance(s3, t3));
        System.out.println("Is \"" + s4 + "\" and \"" + t4 + "\" one edit distance apart? " + oneEditDistance.isOneEditDistance(s4, t4));
    }
}