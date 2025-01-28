package dp;
/*
Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
You have the following three operations permitted on a word:
* Insert a character
* Delete a character
* Replace a character
 
Example 1:
Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation:
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
Example 2:
Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation:
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')


Intuition
To transform word1 into word2, at each step, you can:
* Insert a character into word1 to match word2.
* Delete a character from word1.
* Replace a character in word1 with a character from word2.
At any point, the solution depends on solving smaller subproblems:
* Matching prefixes of the strings.
* Handling characters one by one and determining the optimal operation.

Recursive Approach
The function solve(int index1, int index2, ...) represents the minimum number of operations required to convert the first index1 + 1 characters of word1 into the first index2 + 1 characters of word2.
Base Cases
1. If index1 < 0: This means word1 is exhausted, so we need to insert all remaining characters of word2. The cost is index2 + 1.
2. If index2 < 0: This means word2 is exhausted, so we need to delete all remaining characters of word1. The cost is index1 + 1.
Recursive Cases
* If word1[index1] == word2[index2], the characters match, and no operation is needed. The result is the same as solving the problem for the previous characters: dp[index1][index2]=solve(index1−1,index2−1,...)
* Otherwise, we calculate the cost for the three possible operations:
    1. Insertion: Insert word2[index2] into word1. The cost is 1 + solve(index1, index2 - 1, ...).
    2. Deletion: Delete word1[index1]. The cost is 1 + solve(index1 - 1, index2, ...).
    3. Replacement: Replace word1[index1] with word2[index2]. The cost is 1 + solve(index1 - 1, index2 - 1, ...).
* Take the minimum of the three operations:  dp[index1][index2]=min(insertion,deletion,replacement)


 */
public class EditDistance {
    private int solve(int index1, int index2, String word1, String word2, int[][] dp) {
        if (index1 < 0) {
            return index2 + 1;
        }
        if (index2 < 0) {
            return index1 + 1;
        }
        if (dp[index1][index2] != -1) {
            return dp[index1][index2];
        }
        if (word1.charAt(index1) == word2.charAt(index2)) {
            return dp[index1][index2] = solve(index1 - 1, index2 - 1, word1, word2, dp);
        }
        int insertion = 1 + solve(index1, index2 - 1, word1, word2, dp);
        int deletion = 1 + solve(index1 - 1, index2, word1, word2, dp);
        int replacement = 1 + solve(index1 - 1, index2 - 1, word1, word2, dp);
        return dp[index1][index2] = Math.min(insertion, Math.min(deletion, replacement));
    }

    public int minDistance(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();
        int[][] dp = new int[n1][n2];
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                dp[i][j] = -1;
            }
        }
        return solve(n1 - 1, n2 - 1, word1, word2, dp);
    }

    public static void main(String[] args) {
        EditDistance editDistance = new EditDistance();

        String word1 = "intention";
        String word2 = "execution";

        int result = editDistance.minDistance(word1, word2);

        System.out.println("The minimum edit distance between \"" + word1 + "\" and \"" + word2 + "\" is: " + result);
    }
}
