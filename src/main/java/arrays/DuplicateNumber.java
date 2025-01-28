package arrays;

/*
Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
There is only one repeated number in nums, return this repeated number.
You must solve the problem without modifying the array nums and using only constant extra space.

 */
public class DuplicateNumber {
    public int findDuplicate(int[] nums) {
        int n = nums.length;
        for(int i=0; i< n; i++) {
            if(nums[Math.abs(nums[i])] >= 0) {
                nums[Math.abs(nums[i])] *= -1;
            } else {
                return Math.abs(nums[i]);
            }
        }
        return -1;
    }
}
