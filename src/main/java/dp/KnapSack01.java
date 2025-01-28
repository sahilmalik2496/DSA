package dp;
/*
Given N items where each item has some weight and profit associated with it and also given a bag with capacity W, [i.e., the bag can hold at most W weight in it]. The task is to put the items into the bag such that the sum of profits associated with them is the maximum possible. 
Note: The constraint here is we can either put an item completely into the bag or cannot put it at all [It is not possible to put a part of an item into the bag].
Input: N = 3, W = 4, profit[] = {1, 2, 3}, weight[] = {4, 5, 1} Output: 3 Explanation: There are two items which have weight less than or equal to 4. If we select the item with weight 4, the possible profit is 1. And if we select the item with weight 1, the possible profit is 3. So the maximum possible profit is 3. Note that we cannot put both the items with weight 4 and 1 together as the capacity of the bag is 4.
Input: N = 3, W = 3, profit[] = {1, 2, 3}, weight[] = {4, 5, 6} Output: 0
 */
public class KnapSack01 {

//    static int knapSack(int W, int wt[], int val[], int n) {
//        int i, w;
//        int K[][] = new int[n + 1][W + 1]; // DP table
//
//        // Build the table K[][] in a bottom-up manner
//        for (i = 0; i <= n; i++) {
//            for (w = 0; w <= W; w++) {
//                if (i == 0 || w == 0) {
//                    // Base case: no items or capacity
//                    K[i][w] = 0;
//                } else if (wt[i - 1] <= w) {
//                    // Include or exclude the current item
//                    K[i][w] = Math.max(val[i - 1] + K[i - 1][w - wt[i - 1]], K[i - 1][w]);
//                } else {
//                    // Exclude the current item
//                    K[i][w] = K[i - 1][w];
//                }
//            }
//        }
//
//        return K[n][W]; // Maximum value that can be obtained
//    }


    static int knapSackRec(int W, int wt[], int val[],
                           int n, int[][] dp)
    {

        // Base condition
        if (n == 0 || W == 0)
            return 0;

        if (dp[n][W] != -1)
            return dp[n][W];

        if (wt[n - 1] > W)

            // Store the value of function call
            // stack in table before return
            return dp[n][W]
                    = knapSackRec(W, wt, val, n - 1, dp);

        else

            // Return value of table after storing
            return dp[n][W]
                    = Math.max((val[n - 1]
                            + knapSackRec(W - wt[n - 1], wt, val,
                            n - 1, dp)),
                    knapSackRec(W, wt, val, n - 1, dp));
    }

    static int knapSack(int W, int wt[], int val[], int N)
    {

        // Declare the table dynamically
        int dp[][] = new int[N + 1][W + 1];

        // Loop to initially filled the
        // table with -1
        for (int i = 0; i < N + 1; i++)
            for (int j = 0; j < W + 1; j++)
                dp[i][j] = -1;

        return knapSackRec(W, wt, val, N, dp);
    }
}
