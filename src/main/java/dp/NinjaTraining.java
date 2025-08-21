package dp;

import java.util.Arrays;

/*

Ninja is planing this ‘N’ days-long training schedule. Each day, he can perform any one of these three
activities. (Running, Fighting Practice or Learning New Moves). Each activity has some merit points on
each day. As Ninja has to improve all his skills, he can’t do the same activity in two consecutive days.
Can you help Ninja find out the maximum merit points Ninja can earn?
You are given a 2D array of size N*3 ‘POINTS’ with the points corresponding to each day and activity.
Your task is to calculate the maximum number of merit points that Ninja can earn.
For Example
If the given ‘POINTS’ array is [[1,2,5], [3 ,1 ,1] ,[3,3,3] ],the answer will be 11 as 5 + 3 + 3.


Sample Input:
2
3
18 11 19
4 13 7
1 8 13
2
10 50 1
5 100 11
Sample Output 2:
45
110


Intuition
1. Recursive Relation:
• On any given day day and a last activity performed on the previous day:
• The ninja has two valid activities to choose from (not equal to last).
• The points for the current day are:
max(points[day][] + f(day-1, i))
where i represents the activity index, and f(day-1, i) gives the maximum points for the previous day.
2. Base Case:
• On the first day (day = 0), the ninja can pick any activity except the one performed on the "previous" day ( last ). The maximum points are:
max(points (0) fi), i ‡ last
3. Memoization Table:
• Use a 2D table dp [day] [last] to store results for the subproblem:
• day: Current day.
• last: The last activity performed.

 Complexity Analysis
1. Time Complexity:
    * Each state (day, last) is calculated once. O(n×4)=O(4n).
2. Space Complexity:
    * Memoization table:  O(n×4).
    * Recursion stack:  O(n).
    * Total:  O(n).

 */
public class NinjaTraining {
    // Recursive function to calculate the maximum points for a given day and last activity
    static int f(int day, int last, int[][] points, int[][] dp) {
        // Check memoization table
        if (dp[day][last] != -1) return dp[day][last];

        // Base case: First day
        if (day == 0) {
            int maxi = 0;
            for (int i = 0; i <= 2; i++) {
                if (i != last) {
                    maxi = Math.max(maxi, points[0][i]);
                }
            }
            return dp[day][last] = maxi;
        }

        int maxi = 0;
        // Iterate through all activities
        for (int i = 0; i <= 2; i++) {
            if (i != last) {
                int activity = points[day][i] + f(day - 1, i, points, dp);
                maxi = Math.max(maxi, activity);
            }
        }

        return dp[day][last] = maxi; // Store result in dp table
    }

    // Function to find the maximum points
    static int ninjaTraining(int n, int[][] points) {
        int dp[][] = new int[n][4];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        return f(n - 1, 3, points, dp); // Start from the last day, no activity constraint
    }

    public static void main(String args[]) {
        int[][] points = {
                {10, 40, 70},
                {20, 50, 80},
                {30, 60, 90}
        };

        int n = points.length;
        System.out.println(ninjaTraining(n, points)); // Output: 210
    }
}
