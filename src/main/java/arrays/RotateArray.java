package arrays;

/*

https://leetcode.com/problems/rotate-array/description/

Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.

Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]

It uses a three-step approach based on reversing parts of the array to achieve the desired result efficiently.

Reverse the Entire Array:
* First, reverse the whole array.
* This moves the elements that need to go to the front to the back in reversed order.
Reverse the First k Elements:
* Reverse the first k elements so they appear in the correct order after the rotation.
Reverse the Remaining Elements:
* Reverse the last n-k elements so they are in their correct order.
 */
public class RotateArray {
    void reverse(int[] nums, int left, int right) {
        while(left< right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums, 0, nums.length -1);
        reverse(nums, 0, k-1);
        reverse(nums, k, nums.length-1);
    }
}
