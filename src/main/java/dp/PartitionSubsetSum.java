package dp;

/*
Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements
in both subsets is equal or false otherwise.

 

Example 1:

Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:

Input: nums = [1,2,3,5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.
*/

class PartitionSubsetSum {

    private boolean dfs(int[] nums, int index, int target, Boolean[][] dp) {
        if (target == 0) return true;
        if (index >= nums.length || target < 0) return false;

        if (dp[index][target] != null) return dp[index][target];

        Boolean include = dfs(nums, index + 1, target - nums[index], dp);
        Boolean exclude = dfs(nums, index + 1, target, dp);

        dp[index][target] = include || exclude;
        return dp[index][target];
    }
    public boolean canPartition(int[] nums) {
         int total = 0;
        for (int num : nums) {
            total += num;
        }

        if (total % 2 != 0) return false;
        Boolean[][] dp = new Boolean[nums.length][total + 1];
        int target = total / 2;
        return dfs(nums, 0, target, dp);
    }
}
