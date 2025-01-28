package dp;

/*
 Given an integer array nums, find a 
subarray

 that has the largest product, and return the product.

The test cases are generated so that the answer will fit in a 32-bit integer.
 
Example 1:
Input: nums = [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.

Intuition
The task is to find the maximum product subarray in a given array of integers. This is challenging because:
1. A negative number can become positive when multiplied by another negative number.
2. The maximum product can be influenced by both the maximum and minimum products of subarrays.
Thus, we track both the maximum and minimum products at each step and update them dynamically to account for the effect of negative numbers.

Key Observations
1. Two Products:
    * At each index, the maximum product can be achieved by either:
        * Extending the previous maximum product by multiplying the current number.
        * Extending the previous minimum product (if the current number is negative).
        * Starting fresh with the current number.
2. Similarly, the minimum product is tracked for handling negative numbers.
3. Dynamic Programming:
    * Use two variables, max and min, to store the maximum and minimum products up to the current index.
4. Result:
    * At each step, compare max with the global result to track the overall maximum product.

 */
public class MaxProductSubarray {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int max = nums[0]; // Maximum product ending at current index
        int min = nums[0]; // Minimum product ending at current index
        int result = nums[0]; // Overall maximum product

        for (int i = 1; i < nums.length; i++) {
            int currMax = max * nums[i];
            int currMin = min * nums[i];

            // Update max and min considering the current number
            max = Math.max(Math.max(currMax, currMin), nums[i]);
            min = Math.min(Math.min(currMax, currMin), nums[i]);

            // Update the result to hold the overall maximum product
            result = Math.max(result, max);
        }

        return result;
    }
}
