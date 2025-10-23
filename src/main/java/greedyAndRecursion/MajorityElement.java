package greedyAndRecursion;

/*
Given an array nums of size n, return the majority element.

The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element
always exists in the array.

Example 1:

Input: nums = [3,2,3]
Output: 3
Example 2:

Input: nums = [2,2,1,1,1,2,2]
Output: 2
 */
public class MajorityElement {
    public int majorityElement(int[] nums) {
        int n = nums.length;

        int max_el = nums[0], cnt =0;
        for (int num : nums) {
            if (num == max_el) {
                cnt++;
            } else {
                cnt--;
            }

            if (cnt == 0) {
                max_el = num;
                cnt = 1;
            }
        }
        return max_el;
    }
}
