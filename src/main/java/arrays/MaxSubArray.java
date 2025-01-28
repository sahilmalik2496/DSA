package arrays;

/*
Given an integer array nums, find the
subarray
 with the largest sum, and return its sum.

Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: The subarray [4,-1,2,1] has the largest sum 6.

Intuition
The problem is to find the maximum sum of a contiguous subarray within a given array nums. This is a classic problem solved efficiently using Kadane's Algorithm.
The core idea of Kadane’s algorithm is based on dynamic programming:
* At each position in the array, decide whether to include the current element in the existing subarray or start a new subarray.
* This decision ensures that at every step, we keep track of the maximum sum of subarrays ending at the current position.
Key Observations
1. Subarray Continuity:
    * For any subarray, either:
        1. The current element is added to the existing subarray (extending it), or
        2. A new subarray starts from the current element.
2. Local vs. Global Maximum:
    * Use a variable (sum) to track the current subarray sum.
    * Use another variable (maxx) to store the maximum sum encountered so far across all subarrays.
3. Efficiency:
    * Instead of evaluating all possible subarrays (which takes   O(n2) time),
    * this approach iterates through the array once (O  O(n)) and keeps updating the result in real time.

 */
public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        int sum = nums[0], maxx = nums[0];
        for(int i=1; i< nums.length; i++) {
            sum = Math.max(sum+ nums[i], nums[i]);
            maxx = Math.max(sum, maxx);
        }
        return maxx;
    }
}
