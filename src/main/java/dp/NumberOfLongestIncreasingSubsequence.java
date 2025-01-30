package dp;

import java.util.Arrays;

/*
https://leetcode.com/problems/number-of-longest-increasing-subsequence/description/

Given an integer array nums, return the number of longest increasing subsequences.

Notice that the sequence has to be strictly increasing.



Example 1:

Input: nums = [1,3,5,4,7]
Output: 2
Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].
Example 2:

Input: nums = [2,2,2,2,2]
Output: 5
Explanation: The length of the longest increasing subsequence is 1, and there are 5 increasing subsequences of length 1, so output 5.


Intuition
The problem is to count the number of longest increasing subsequences (LIS) in an array.

For example, in:

arr = [1,5,4,3,2,6,7,2]
The LIS length is 4, and there are 3 different subsequences with length 4:

[1, 3, 6, 7]
[1, 4, 6, 7]
[1, 5, 6, 7]
Thus, the output is 3.

Approach
We use Dynamic Programming (DP) with two arrays:

dp[i] → Stores the LIS length ending at i.
ct[i] → Stores the number of ways to form an LIS ending at i.
Key Observations
Initially, each element alone is an LIS of length 1, so we initialize:
dp[i] = 1
ct[i] = 1
We iterate over all previous elements to check if they can extend an LIS ending at i:
If arr[prev] < arr[i] and dp[prev] + 1 > dp[i]:
Update dp[i] = dp[prev] + 1 (inherit LIS length).
Set ct[i] = ct[prev] (inherit LIS count).
If arr[prev] < arr[i] and dp[prev] + 1 == dp[i]:
Add ct[prev] to ct[i] (count additional LIS paths).
Track the maximum LIS length (maxi) and sum up all ct[i] where dp[i] == maxi.
Algorithm
Initialize DP arrays:
dp[i] = 1 (each element is an LIS of length 1).
ct[i] = 1 (each element has 1 way to form an LIS).
Iterate over the array:
Check all previous indices (prev) before i.
If arr[prev] < arr[i], update dp[i] and ct[i] based on LIS conditions.
Find the maximum LIS length (maxi).
Count all occurrences of this LIS in ct[i] and return the total count.

 */
public class NumberOfLongestIncreasingSubsequence {
        static int findNumberOfLIS(int[] arr){

            int n = arr.length;

            int[] dp= new int[n];
            int[] ct= new int[n];

            Arrays.fill(dp,1);
            Arrays.fill(ct,1);

            int maxi = 1;

            for(int i=0; i<=n-1; i++){
                for(int prev_index = 0; prev_index <=i-1; prev_index ++){

                    if(arr[prev_index]<arr[i] && dp[prev_index]+1>dp[i]){
                        dp[i] = dp[prev_index]+1;
                        //inherit
                        ct[i] = ct[prev_index];
                    }
                    else if(arr[prev_index]<arr[i] && dp[prev_index]+1==dp[i]){
                        //increase the count
                        ct[i] = ct[i] + ct[prev_index];
                    }
                }
                maxi = Math.max(maxi,dp[i]);
            }

            int nos =0;

            for(int i=0; i<=n-1; i++){
                if(dp[i]==maxi) nos+=ct[i];
            }

            return nos;
        }

        public static void main(String args[]) {

            int[] arr = {1,5,4,3,2,6,7,2};

            System.out.println("The count of LIS is "+findNumberOfLIS(arr));

        }
}
